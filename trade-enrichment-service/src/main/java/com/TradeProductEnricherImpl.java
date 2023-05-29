package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*
    Product name enricher - wraps a proxy to the product lookup micro-service
 */
public class TradeProductEnricherImpl implements Enricher{
    private ProductLookupService productLookupService;
    private static String MISSING_PRODUCT_NAME_STR = "Missing Product Name";
    private static Logger LOGGER = LoggerFactory.getLogger(TradeProductEnricherImpl.class);
    public TradeProductEnricherImpl(ProductLookupService productLookupService){
        this.productLookupService = productLookupService;
    }
    @Override
    public Trade enrich(Trade inputTrade) {
        String productName;
        try {
            productName = productLookupService.getProductNameById(inputTrade.getProductId());
            LOGGER.info("ProductId = " + inputTrade.getProductId() + ", ProductName = " + productName);
        }
        catch (Exception ex){
            LOGGER.warn("Error while looking up product name for product id " + inputTrade.getProductId() + ", returning default");
            productName = MISSING_PRODUCT_NAME_STR;
        }
        if(productName == null)
            productName = MISSING_PRODUCT_NAME_STR;

        TradeImpl newTrade = TradeImpl.builder().productId(inputTrade.getProductId()).productName(productName).
                ccy(inputTrade.getCcy()).date(inputTrade.getDate()).price(inputTrade.getPrice()).build();

        return newTrade;
    }
}
