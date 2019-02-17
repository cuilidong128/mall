/**
 * 客户业务测试
 * Created by cuilidong on 2019/1/6.
 */

import com.mall.customer.modle.Customer;
import org.junit.Test;

public class CustomerBusinessTest {

    @Test
    public void testCustomer(){

        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setCustomerName("崔立东");
        customer.setMoble("15810324120");
        //customer.setMemberShipId(1);
        customer.setCustomerPosition("北京");
        customer.setRegisterIpAddress("127.0.0.1");
        customer.setRegisterPort("80");

    }
}
