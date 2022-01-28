package com.clientui.proxies;

import com.clientui.beans.CommandeBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservice-commande", url = "localhost:9002")
public interface MicroserviceCommandeProxy {


    @PostMapping(value = "/commandes")
    public CommandeBean ajouterCommande(@RequestBody CommandeBean commandeBean);

    @GetMapping(value = "/commandes/{id}")
    public CommandeBean recupererUneCommande(@PathVariable("id") int id);
}
