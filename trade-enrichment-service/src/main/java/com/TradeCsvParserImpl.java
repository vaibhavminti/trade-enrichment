package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TradeCsvParserImpl implements TradeCsvParser {
    private static Logger LOGGER = LoggerFactory.getLogger(TradeCsvParserImpl.class);
    public List<Trade> getTrades(String csv){
        List<Trade> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new StringReader(csv))) {
            String line;
            //date,productid, ccy, price
            double price = 0;
            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");
                if(values.length != 4){
                    LOGGER.warn("Can not parse trade line " + line + ": Invalid number of columns");
                    continue;
                }
                try {
                    price = Double.parseDouble(values[3]);
                }
                catch (NumberFormatException nfe){
                    LOGGER.warn("Can not parse trade line " + line + ": Invalid price column value");
                    continue;
                }
                String date = values[0];
                TradeImpl trade = TradeImpl.builder().date(date).productId(values[1]).ccy(values[2]).price(price).build();
                records.add(trade);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //LOGGER.info("" + lines.length);
        return records;
    }
}
