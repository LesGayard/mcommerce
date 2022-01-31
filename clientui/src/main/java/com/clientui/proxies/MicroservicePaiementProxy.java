package com.clientui.proxies;

import com.clientui.beans.PaiementBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservice-paiements", url = "localhost:9003")
public interface MicroservicePaiementProxy {


    @PostMapping
    public ResponseEntity<PaiementBean> payerUneCommande(@RequestBody PaiementBean paiementBean);

}
