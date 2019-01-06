import com.mall.customer.modle.MemberGrade;
import org.junit.Test;

/**
 * 会员等级
 * Created by cuilidong on 2019/1/6.
 */

public class MemberGradeBusTest {

    @Test
    public void testMemberShip(){

        MemberGrade grade = new MemberGrade();
        grade.setGradeId(1);
        grade.setGradeName("初级会员");
        grade.setGradeDetail("新手入门级别");
        grade.setLevel(0);
        grade.setUpExper(0);
        grade.setIsDefault(1);

        grade = new MemberGrade();
        grade.setGradeId(2);
        grade.setGradeName("普通会员");
        grade.setGradeDetail("普通级别");
        grade.setLevel(1);
        grade.setUpExper(100);
        grade.setIsDefault(0);


    }

}
