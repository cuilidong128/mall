import com.mall.business.modle.CardType;
import org.junit.Test;

/**
 * Created by cuilidong on 2019/1/12.
 */
public class CardTypeBusTest {
    @Test
    public void testCardType(){

        CardType cardType = new CardType();
        cardType.setCardTypeId(1);
        cardType.setCardTypeName("金卡");
        cardType.setCardTypeDesc("金卡");

        cardType = new CardType();
        cardType.setCardTypeId(2);
        cardType.setCardTypeName("白金卡");
        cardType.setCardTypeDesc("白金卡");

        cardType = new CardType();
        cardType.setCardTypeId(3);
        cardType.setCardTypeName("黑卡");
        cardType.setCardTypeDesc("黑卡");

    }
}
