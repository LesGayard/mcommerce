package com.clientui.controller;

import com.clientui.beans.ProductBean;
import com.clientui.proxies.MicroserviceProduitProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ClientUIController {

    @Autowired
    private MicroserviceProduitProxy produitProxy;

    @RequestMapping("/")
    public String acceuil(Model model){

        List<ProductBean>produits = produitProxy.listeDesProduits();
        model.addAttribute("produits",produits);
        return "Accueil";
    }
}
