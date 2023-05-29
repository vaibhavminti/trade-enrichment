package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TradeEnrichmentController {
    private final TradeCsvParser tradeCsvParser;
    private final Enricher enricher;
    public TradeEnrichmentController(TradeCsvParser tradeCsvParser, Enricher enricher){
        this.tradeCsvParser = tradeCsvParser;
        this.enricher = enricher;
    }
    private static Logger LOGGER = LoggerFactory.getLogger(TradeEnrichmentController.class);
    @PostMapping(value = "/enrich")
    public String enrichTrades(@RequestBody String tradesCsv) throws Exception{
        //TODO : Using Flux streaming to return enriched trades instead of returning all at once
        // This will alleviate memory load
        List<Trade> trades = tradeCsvParser.getTrades(tradesCsv);
        StringBuilder outputBuilder = new StringBuilder();
        for(Trade trade : trades){
            Trade outTrade = enricher.enrich(trade);
            if(outTrade != null){
                outputBuilder.append(outTrade.toString());
            }
        }
        return outputBuilder.toString();
    }
}
