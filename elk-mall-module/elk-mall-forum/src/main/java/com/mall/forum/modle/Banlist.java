package com.mall.forum.modle;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.util.StringTokenizer;

/**
 * 黑名单
 */

@Data
@Entity
@ToString
@Table(name = "jforum_banlist")
public class Banlist {

    @Id
    @Column(name = "banlist_id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "banlist_ip")
    private String ip;

    @Column(name = "banlist_email")
    private String email;

    public boolean matches(Banlist b) {
        boolean status = false;

        if (this.matchesUserId(b) || this.matchesEmail(b)) {
            status = true;
        }
        else if (!StringUtils.isEmpty(b.getIp()) && !StringUtils.isEmpty(this.getIp())) {
            if (b.getIp().equalsIgnoreCase(this.getIp())) {
                status = true;
            }
            else {
                status = this.matchIp(b);
            }
        }

        return status;
    }

    private boolean matchesEmail(Banlist b) {
        return (!StringUtils.isEmpty(b.getEmail()) && b.getEmail().equals(this.getEmail()));
    }

    private boolean matchesUserId(Banlist b) {
        return b.getUserId() > 0 && this.getUserId() > 0 && b.getUserId() == this.getUserId();
    }

    private boolean matchIp(Banlist b) {
        boolean status = false;

        StringTokenizer userToken = new StringTokenizer(b.getIp(), ".");
        StringTokenizer thisToken = new StringTokenizer(this.getIp(), ".");

        if (userToken.countTokens() == thisToken.countTokens()) {
            String[] userValues = this.tokenizerAsArray(userToken);
            String[] thisValues = this.tokenizerAsArray(thisToken);

            status = this.compareIpValues(userValues, thisValues);
        }
        return status;
    }

    private boolean compareIpValues(String[] userValues, String[] thisValues) {
        boolean helperStatus = true;
        boolean onlyStars = true;

        for (int i = 0; i < thisValues.length; i++) {
            if (thisValues[i].charAt(0) != '*') {
                onlyStars = false;

                if (!thisValues[i].equals(userValues[i])) {
                    helperStatus = false;
                }
            }
        }

        return helperStatus && !onlyStars;
    }

    private String[] tokenizerAsArray(StringTokenizer token) {
        String[] values = new String[token.countTokens()];

        for (int i = 0; token.hasMoreTokens(); i++) {
            values[i] = token.nextToken();
        }

        return values;
    }
}
