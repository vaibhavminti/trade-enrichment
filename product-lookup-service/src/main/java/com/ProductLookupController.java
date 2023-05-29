package com;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductLookupController {

    //TODO : Load upfront  or lazily
    private final Map<String,String> loadedProducts;
    public ProductLookupController(){
        //Hardcoded for now
        loadedProducts = new HashMap<>();
        for(int i = 0; i < 100; ++i){
            loadedProducts.put(i +"", "product" + i);
        }

    }
    @GetMapping("/{id}")
    public String getProductName(@PathVariable String id){
        //return product name or null
        if(loadedProducts.containsKey(id))
            return loadedProducts.get(id);

        return null;
    }
}
