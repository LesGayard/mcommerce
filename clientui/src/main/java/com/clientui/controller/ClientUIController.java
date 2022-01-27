package com.clientui.controller;

import com.clientui.beans.ProductBean;
import com.clientui.proxies.MicroserviceProduitProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ClientUIController {

    @Autowired
    private MicroserviceProduitProxy produitProxy;

    /* HOME PAGE */
    @RequestMapping("/")
    public String acceuil(Model model){

        List<ProductBean>produits = produitProxy.listeDesProduits();
        model.addAttribute("produits",produits);
        return "Accueil";
    }


    /* PRODUCT PAGE */
    @RequestMapping("/details-produit/{id}")
    public String ficheProduit(@PathVariable int id, Model model){

        ProductBean produit = produitProxy.recupererUnProduit(id);

        model.addAttribute("produit", produit);

        return "FicheProduit";
    }
}
