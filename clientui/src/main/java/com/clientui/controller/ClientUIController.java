package com.clientui.controller;

import com.clientui.beans.CommandeBean;
import com.clientui.beans.ProductBean;
import com.clientui.proxies.MicroserviceCommandeProxy;
import com.clientui.proxies.MicroserviceProduitProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
public class ClientUIController {

    @Autowired
    private MicroserviceProduitProxy produitProxy;

    @Autowired
    private MicroserviceCommandeProxy commandeProxy;

    /* HOME PAGE */
    @RequestMapping("/")
    public String acceuil(Model model){

        List<ProductBean>produits = this.produitProxy.listeDesProduits();
        model.addAttribute("produits",produits);
        return "Accueil";
    }


    /* PRODUCT PAGE */
    @RequestMapping("/details-produit/{id}")
    public String ficheProduit(@PathVariable int id, Model model){

        ProductBean produit = this.produitProxy.recupererUnProduit(id);

        model.addAttribute("produit", produit);

        return "FicheProduit";
    }

    /* COMMANDES */
    @RequestMapping(value ="/commander-produit/{idProduit}/{montant}")
    public String ajouterCommande(@PathVariable int idProduit, @PathVariable Double montant,  Model model){

        System.out.println("inside ajouter commande !!");
        /* PASSER LA COMMANDE */
        CommandeBean commandeBean = new CommandeBean();
        commandeBean.setProductId(idProduit);
        commandeBean.setDateCommande(new Date());
        commandeBean.setQuantite(1);

        System.out.println(commandeBean);

        /* RECUPERER LA COMMANDE CREEE */
        CommandeBean commandeAjoutee = this.commandeProxy.ajouterCommande(commandeBean);
        model.addAttribute("commande",commandeAjoutee);
        model.addAttribute("montant", montant);

        return "Paiement";
    }
}
