import com.mall.business.modle.Card;
import org.junit.Test;

import java.util.Date;

/**
 * Created by cuilidong on 2019/1/12.
 */
public class CardBusTest {

    @Test
    public void testCard(){

        Card card = new  Card();
        card.setCardId(1);
        card.setBatchNo("10009020110");
        card.setCardPasswd("000000");
        card.setApplyCardType(1);
        card.setCardTypeId(1);
        card.setCreateDate(new Date());
        card.setValidityTime(5);
        card.setStatus(1);
    }
}
