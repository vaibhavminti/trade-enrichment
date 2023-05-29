package com;

import java.util.List;

public interface TradeCsvParser {
    List<Trade> getTrades(String csvContent);
}
