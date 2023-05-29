package com;

import lombok.*;

/*
    An immutable Trade class
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradeImpl implements Trade {
    private String productId;
    private String productName;
    private String date;

    private String ccy;

    private double price;
    /*public Trade(String productId, String date, String ccy, double price){
        this.productId = productId;
        this.date = date;
        this.ccy = ccy;
        this.price = price;
    }*/

    @Override
    public String toString() {
        return date + "," + productName + "," + ccy + "," + price;
    }
}
