package com;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
    An implementation of the product name look up Service.
    This wraps a proxy to the product-lookup Rest Service
    and retrieves the name of the product for the productid passed on the GET request call
 */
public class ProductLookupServiceImpl implements ProductLookupService{
    private final String productLookUpServiceUrl;
    public ProductLookupServiceImpl(String productLookUpServiceUrl){
        this.productLookUpServiceUrl = productLookUpServiceUrl;

    }

    @Override
    public String getProductNameById(String productId) throws Exception {
        URL url = new URL(productLookUpServiceUrl + productId);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "text/plain");
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        while ((output = br.readLine()) != null) {
            return output;
        }
        conn.disconnect();
        return null;
    }
}
