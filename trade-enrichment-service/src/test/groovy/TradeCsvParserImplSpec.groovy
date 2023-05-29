import com.Trade
import com.TradeCsvParserImpl
import spock.lang.Specification

public class TradeCsvParserImplSpec extends Specification{
    def "A valid csv is correctly parsed"(){
        given:
            String csv = "20230529,2,EUR,4.2\n20230530,3,USD,5.2";
        and:
            TradeCsvParserImpl tradeCsvParser = new TradeCsvParserImpl();
        when:
            List<Trade> trades = tradeCsvParser.getTrades(csv);
        then:
            trades.size() == 2
        and:
            trades[0].ccy == "EUR"
            trades[0].date == "20230529"
            trades[0].productId == "2"
        //todo more asserts
    }

    def "One invalid and one valid row csv is correctly parsed"(){
        given:
        String csv = "20230529,2,EUR,4.2\n20230530,3";
        and:
        TradeCsvParserImpl tradeCsvParser = new TradeCsvParserImpl();
        when:
        List<Trade> trades = tradeCsvParser.getTrades(csv);
        then:
        trades.size() == 1
        and:
        trades[0].ccy == "EUR"
        trades[0].date == "20230529"
        trades[0].productId == "2"
        //todo more asserts
    }
}
