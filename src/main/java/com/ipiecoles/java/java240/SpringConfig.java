package com.ipiecoles.java.java240;


import com.sun.webkit.WebPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SpringConfig {
//beans
    @Bean(name="bitcoinServiceWithoutCache")
    @Scope("singleton")// Facultatif car scope singleton par défaut

    public BitcoinService bitcoinServiceWithoutCache()
    {
        BitcoinService bitcoinService= new BitcoinService();
        bitcoinService.setForceRefresh(true);
        //Une fois la création du bean WebPageManager  : Rajout du setter de WebPageManager, injection du bean
        bitcoinService.setWebPageManager(webPageManager());
        return bitcoinService;
    }



    //Création du bean pour webPageManager

    @Bean//Pas besoin de mettre de nom car il n'y en a qu'un seul
    public WebPageManager webPageManager()
    {
        WebPageManager webPageManager = new WebPageManager();
        return webPageManager();
    }

    //Création des bean restants
    @Bean
    public BitcoinService bitcoinServiceWithCache()
    {
        BitcoinService bitcoinService= new BitcoinService();
        bitcoinService.setForceRefresh(false);
        //Une fois la création du bean WebPageManager  : Rajout du setter de WebPageManager, injection du bean
        bitcoinService.setWebPageManager(webPageManager());
        return bitcoinService;
    }

    //Pourquoi initialisation  ici et pas au moment de l'instanciation ?
    @Bean(initMethod="initialiserCatalogue")
    public ProduitManager produitManager()
    {
        ProduitManager produitManager = new ProduitManager();
        //Appel de la méthode qui remplit le catalogue

        //Injection
        produitManager.setWebPageManager(webPageManager());
        produitManager.setBitcoinService(bitcoinServiceWithCache());
        return produitManager;
    }
}
