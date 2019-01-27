package com.app.converter;

import java.util.Map;

public class CategoryJsonConverter extends JsonConverter<Map<String, String>> {

    public CategoryJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
