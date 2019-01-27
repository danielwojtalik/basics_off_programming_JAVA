package com.app.converter;

import com.app.model.Client;
import com.app.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor

public class ClientProducts {

private Client client;
private List<Product> productList;
}
