package com.clientui.proxies;

import com.clientui.beans.ProductBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "microservice-produits", url = "localhost:9001")
public interface MicroserviceProduitProxy {

    @GetMapping(value = "/Produits")
    List<ProductBean> listeDesProduits();

    @GetMapping( value = "/Produits/{id}")
    ProductBean recupererUnProduit(@PathVariable("id") int id);

}
