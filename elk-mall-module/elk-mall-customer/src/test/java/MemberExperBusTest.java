import com.mall.customer.modle.ExperDict;
import com.mall.customer.modle.MemberExper;
import com.mall.customer.modle.MemberExperDetailed;
import org.junit.Test;

import java.util.Date;

/**
 *
 * Created by cuilidong on 2019/1/6.
 *
 * https://www.jianshu.com/p/eefd9cb8dbb0 起点书友们如何快速获得经验值升级
 *
 */
public class MemberExperBusTest {

    @Test
    public void testExperDict(){
        ExperDict dict = new ExperDict();
        dict.setDictId(1);
        dict.setDictName("exper_type_buy_product");
        dict.setDictValue(100);
        dict.setDesc("购买产品增加经验值");
        dict.setStatus(1);
        dict.setSortNo(0);

        dict = new ExperDict();
        dict.setDictId(2);
        dict.setDictName("exper_type_login");
        dict.setDictValue(5);
        dict.setDesc("登录增加经验值");
        dict.setStatus(1);
        dict.setSortNo(1);


        dict = new ExperDict();
        dict.setDictId(3);
        dict.setDictName("exper_type_set_password_strength");
        dict.setDictValue(100);
        dict.setDesc("设置密码强度");
        dict.setStatus(1);
        dict.setSortNo(2);

        dict = new ExperDict();
        dict.setDictId(4);
        dict.setDictName("exper_type_bind_mail");
        dict.setDictValue(100);
        dict.setDesc("绑定邮箱");
        dict.setStatus(1);
        dict.setSortNo(3);
    }

    @Test
    public void saveMemberExperDetailed(){

        //1.获取经验值类型
        ExperDict dict = new ExperDict();
        dict = new ExperDict();
        dict.setDictId(4);
        dict.setDictName("exper_type_bind_mail");
        dict.setDictValue(100);
        dict.setDesc("绑定邮箱");
        dict.setStatus(1);
        dict.setSortNo(3);

        //2.保存经验值明细
        MemberExperDetailed  detailed = new MemberExperDetailed();
        detailed.setDetailedId(1);
        detailed.setCustomerId(1);
        detailed.setExperDict(dict.getDictName());
        detailed.setExperV(500);
        detailed.setDesc("绑定邮箱增加积分");
        detailed.setCreateTime(new Date());

        //3.更新客户总经验值
        int total = 0;
        total = detailed.getExperV();
        MemberExper exper = new MemberExper();
        exper.setExperId(1);
        exper.setLastExperDetailedId(1);
        exper.setCustomerId(1);
        exper.setExperTotal(total);

    }
}
