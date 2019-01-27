package com.app.converter;

import java.util.List;

public class ClientProductsJsonConverter extends JsonConverter <List<ClientProducts>> {

    public ClientProductsJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
