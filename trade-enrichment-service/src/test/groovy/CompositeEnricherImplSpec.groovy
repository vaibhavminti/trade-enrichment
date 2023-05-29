import com.CompositeEnricherImpl
import com.Enricher
import com.Trade
import spock.lang.Specification

public class CompositeEnricherImplSpec extends Specification{
    def "test that all enrichers are called"(){
        given:
            Trade t1 = Mock(){

            }
            Trade t2 = Mock(){
                getProductName() >> "EnrichedProductName"
            }
            Trade t3 = Mock(){
                getProductName() >> "EnrichedProductName"
                getCcy() >>"EnrichedCcy"
            }

            Enricher e1 = Mock(){
                enrich(t1) >> t2
            }
            Enricher e2 = Mock(){
                enrich(t2) >> t3
            }

            List<Enricher> enrichers = new ArrayList<>();
            enrichers.add(e1)
            enrichers.add(e2)
            CompositeEnricherImpl compositeEnricher = new CompositeEnricherImpl(enrichers);

        when:
            Trade outputTrade = compositeEnricher.enrich(t1)
        then:
            outputTrade.productName == "EnrichedProductName"
            outputTrade.ccy == "EnrichedCcy"
    }

    def "test when one enricher throws Exception"(){
        given:
        Trade t1 = Mock(){

        }
        Trade t2 = Mock(){
            getProductName() >> "EnrichedProductName"
        }
        Trade t3 = Mock(){
            getProductName() >> "EnrichedProductName"
            getCcy() >>"EnrichedCcy"
        }

        Enricher e1 = Mock(){
            enrich(t1) >> t2
        }
        Enricher e2 = Mock(){
            enrich(t2) >> { throw new Exception("msg") }
        }

        List<Enricher> enrichers = new ArrayList<>();
        enrichers.add(e1)
        enrichers.add(e2)
        CompositeEnricherImpl compositeEnricher = new CompositeEnricherImpl(enrichers);

        when:
        Trade outputTrade = compositeEnricher.enrich(t1)
        then:
        outputTrade.productName == "EnrichedProductName"
        outputTrade.ccy == null
    }
}
