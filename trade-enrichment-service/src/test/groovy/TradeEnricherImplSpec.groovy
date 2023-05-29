import com.ProductLookupService
import com.Trade
import com.TradeProductEnricherImpl
import spock.lang.Specification

public class TradeEnricherImplSpec extends Specification{
    def "test"(){
        given:
            ProductLookupService productLookupService = Mock(){
                getProductNameById("p1") >> "product1"
                getProductNameById("p2") >> null
            }
            TradeProductEnricherImpl impl = new TradeProductEnricherImpl(productLookupService);
            Trade trade1 = Mock(){
                getProductId() >> "p1"
            }
            Trade trade2 = Mock(){
                getProductId() >> "p2"
            }
        when:
            Trade t1 = impl.enrich(trade1);
        then:
            t1.productName == "product1"
        when:
            Trade t2 = impl.enrich(trade2);
        then:
            t2.productName == "Missing Product Name"

    }
}
