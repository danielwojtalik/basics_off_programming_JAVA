package com.app.converter;

import com.app.model.Product;
import java.util.List;

public class ProductJsonConverter extends JsonConverter<List<Product>> {

    public ProductJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
