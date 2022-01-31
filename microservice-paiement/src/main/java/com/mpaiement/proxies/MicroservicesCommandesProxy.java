package com.mpaiement.proxies;

import com.mpaiement.beans.CommandeBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(name= "microservice-commande", url = "localhost:9002")
public interface MicroservicesCommandesProxy {

    @GetMapping(value="/commandes/{id}")
    Optional<CommandeBean>recupererUneCommande(@PathVariable("id") int id);

    @PutMapping("/commandes")
    void updateCommande(@RequestBody CommandeBean commande);
}
