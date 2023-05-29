package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TradeEnrichmentServiceApp {
    @Bean
    public TradeCsvParser getTradeCsvParser(){
        return new TradeCsvParserImpl();
    }
    //TODO : Drive prodLookUpServiceUrl using Spring configuration - remove hardcoding
    private static String prodLookUpServiceUrl = "http://localhost:8088/";
    @Bean
    public Enricher getEnricher(){
        //TODO : config driven
        //Currently I am just adding the 2 enrichers here
        Enricher dateValidator = new DateValidator();
        ProductLookupService productLookupService = new ProductLookupServiceImpl(prodLookUpServiceUrl);
        Enricher productEnricher = new TradeProductEnricherImpl(productLookupService);
        List<Enricher> enrichers = new ArrayList<>();
        enrichers.add(dateValidator);
        enrichers.add(productEnricher);
        return new CompositeEnricherImpl(enrichers);
    }
    public static void main(String[] args) {
        SpringApplication.run(TradeEnrichmentServiceApp.class, args);
    }

}
