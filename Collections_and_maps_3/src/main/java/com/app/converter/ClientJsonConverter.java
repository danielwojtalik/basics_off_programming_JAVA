package com.app.converter;

import com.app.model.Client;

import java.util.List;

public class ClientJsonConverter extends JsonConverter<List<Client>> {

    public ClientJsonConverter(String jsonFilename) {
            super(jsonFilename);
    }
}
