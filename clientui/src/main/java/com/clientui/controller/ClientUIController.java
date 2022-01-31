package com.clientui.controller;

import com.clientui.beans.CommandeBean;
import com.clientui.beans.PaiementBean;
import com.clientui.beans.ProductBean;
import com.clientui.proxies.MicroserviceCommandeProxy;
import com.clientui.proxies.MicroservicePaiementProxy;
import com.clientui.proxies.MicroserviceProduitProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class ClientUIController {

    @Autowired
    private MicroserviceProduitProxy produitProxy;

    @Autowired
    private MicroserviceCommandeProxy commandeProxy;

    @Autowired
    private MicroservicePaiementProxy paiementProxy;

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

    /* PAIEMENT */
    @RequestMapping(value ="/payer-commande/{idCommande}/{montant}")
    public String payerUneCommande(@PathVariable Integer idCommande, @PathVariable Integer montant, Model model){

        PaiementBean paiementBean = new PaiementBean();
        paiementBean.setIdCommande(idCommande);
        paiementBean.setMontant(montant);
        paiementBean.setNumeroCarte(numeroCarte());

        ResponseEntity<PaiementBean>paiementFinal = this.paiementProxy.payerUneCommande(paiementBean);

        Boolean paiementOk = false;

        if(paiementFinal.getStatusCode() == HttpStatus.CREATED){
            paiementOk = true;
            model.addAttribute("paiementOK", true);
        }else{
            model.addAttribute("paiementOK", false);
        }
        return "Confirmation";
    }

    private Long numeroCarte() {

        return ThreadLocalRandom.current().nextLong(1000000000000000L,9000000000000000L );
    }
}
