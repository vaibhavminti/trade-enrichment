import com.DateValidator
import com.Trade
import com.TradeImpl
import spock.lang.Specification

public class DateValidatorSpec extends Specification{

    def "Output trade not null when date is in correct format"(){
        given:
            DateValidator dateValidator = new DateValidator();
            Trade trade = Mock()
            trade.getDate() >> "20230529"
        when:
            Trade outTrade = dateValidator.enrich(trade)
        then:
            outTrade != null
    }

    def "Output trade null when date is in incorrect format"(){
        given:
        DateValidator dateValidator = new DateValidator();
        Trade trade = Mock()
        trade.getDate() >> "05122023"
        when:
            Trade outTrade = dateValidator.enrich(trade)
        then:
            outTrade == null
    }
}
