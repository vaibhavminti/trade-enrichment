package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/*
    A custom enricher Impl that applies a list of enrichers to the trade.
 */
public class CompositeEnricherImpl implements Enricher{
    private static Logger LOGGER = LoggerFactory.getLogger(CompositeEnricherImpl.class);
    private final List<Enricher> enrichers;
    public CompositeEnricherImpl(List<Enricher> enrichers){
        if(enrichers == null || enrichers.isEmpty()){
            throw new IllegalArgumentException("enrichers arg can not be null or empty");
        }
        this.enrichers = enrichers;
    }
    @Override
    public Trade enrich(Trade inputTrade) {
        Trade trade = inputTrade;
        for(Enricher enricher : enrichers){
            try{
                trade = enricher.enrich(trade);
            }
            catch (Exception ex){
                LOGGER.warn("Error while calling enricher " + enricher + ": enrichment failed");
            }
        }
        return trade;
    }
}
