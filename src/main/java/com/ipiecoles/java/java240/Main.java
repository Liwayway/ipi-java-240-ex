package com.ipiecoles.java.java240;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {


        //Stocker les beans dans l'ApplicationContext
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);

        //Premiere solution
        BitcoinService bitcoinServiceWithoutCache = ctx.getBean("bitcoinServiceWithoutCache",BitcoinService.class);
        //Solution Alternative si il n'y a qu'un seul bean crée, pas utilisable si plusieurs :
        /*
        * BitcoinService bitCoinServiceWithoutCache = ctx.getBean("BitCoinServiceWithoutCache", BitcoinService.class);
        * */
        //On supprime car on l'utilisait uniquement dans produit manager et qu'il y a eu injection direct dans pbean produit manager
        //BitcoinService bitcoinServiceWithCache = ctx.getBean(BitcoinService.class);


        //Ajout de cette ligne pour utilisation du bean, puis suppression quand création du bean pour produit manager avec injection
        //WebPageManager webPageManager = ctx.getBean(WebPageManager.class);
        //idem pour produit Manager
        // Le bean est crée, plus nécessaire :
        // ProduitManager pm = new ProduitManager();
        ProduitManager pm = ctx.getBean(ProduitManager.class);



       /* //on crée deux bitcoin service pour récupérer ou non le cache si besoin
        BitcoinService bitcoinServiceWithCache = new BitcoinService();
        bitcoinServiceWithCache.setForceRefresh(false);
        BitcoinService bitcoinServiceWithoutCache = new BitcoinService();
        bitcoinServiceWithoutCache.setForceRefresh(true);*/


        // Idem on n'a plus besoin d'instancier ici :
        // WebPageManager webPageManager = new WebPageManager();

       /* pm.setWebPageManager(webPageManager);
        pm.setBitcoinService(bitcoinServiceWithCache);
        bitcoinServiceWithoutCache.setWebPageManager(webPageManager);
        bitcoinServiceWithoutCache.setWebPageManager(webPageManager);*/

        System.out.println("Bienvenue !");
        while(true){
            System.out.println("Vous souhaitez : ");
            System.out.println("1 - Connaître le cours du bitcoin");
            System.out.println("2 - Ajouter un produit au catalogue");
            System.out.println("3 - Voir tous les produits du catalogue");
            System.out.println("4 - Voir les détails d'un produit");
            System.out.println("5 - Initialiser le catalogue");
            System.out.println("0 - Quitter");

            Scanner scanner = new Scanner(System.in);
            int saisie = scanner.nextInt();
            switch (saisie){
                case 1:

                    System.out.println("1 BTC = " + bitcoinServiceWithoutCache.getBitcoinRate() + " €");
                    break;
                case 2:
                    pm.ajouterProduit();
                    break;
                case 3:
                    pm.afficherTousLesProduits();
                    break;
                case 4:
                    System.out.println("Quel numéro de produit ?");
                    pm.afficherDetailProduit(scanner.nextInt());
                    break;
                case 5:
                    pm.initialiserCatalogue();
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    return;
            }
        }
    }
}
