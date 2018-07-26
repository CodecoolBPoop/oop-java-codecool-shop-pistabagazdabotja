package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        // SUPPLIERS
        Supplier zara = new Supplier("ZARA", "Fashionable clothes for farmers.");
        supplierDataStore.add(zara);
        Supplier bershka = new Supplier("Bershka", "Going to the lands in style");
        supplierDataStore.add(bershka);

        Supplier johndeere = new Supplier("John Deere", "A lot of horsepower.");
        supplierDataStore.add(johndeere);

        Supplier makita = new Supplier("MAKITA", "A hundred years of professionalism.");
        supplierDataStore.add(makita);
        Supplier bosch = new Supplier("Bosch", "Household machines for your comfort.");
        supplierDataStore.add(bosch);

        Supplier acme = new Supplier("ACME Ltd.", "The best of the best.");
        supplierDataStore.add(acme);

        Supplier umbrella = new Supplier("Umbrella Ltd.", "Everything what moves.");
        supplierDataStore.add(umbrella);

        // CATEGORIES
        ProductCategory clothes = new ProductCategory("Clothes", "Hardware", "");
        productCategoryDataStore.add(clothes);
        ProductCategory tools = new ProductCategory("Tools", "Hardware", "");
        productCategoryDataStore.add(tools);
        ProductCategory machines = new ProductCategory("Machines", "Hardware", "");
        productCategoryDataStore.add(machines);
        ProductCategory animals = new ProductCategory("Animals", "Hardware", "");
        productCategoryDataStore.add(animals);
        ProductCategory people = new ProductCategory("People", "Hardware", "");
        productCategoryDataStore.add(people);
        ProductCategory other = new ProductCategory("Other", "Hardware", "");
        productCategoryDataStore.add(other);


        // PRODUCTS
        productDataStore.add(new Product("Gumicsimma", 29.9f, "USD", "Tartós gumicsizma minőségi alapanyagokból kerti és partmenti használatra.", clothes, zara));
        productDataStore.add(new Product("Otthonka", 99.9f, "USD", "Kényelmes otthonka, nem csak otthon.", clothes, zara));
        productDataStore.add(new Product("Fejfödő", 19.9f, "USD", "Praktikus viselet, védelmet nyút az időjárás viszontagságaival szemben.", clothes, zara));
        productDataStore.add(new Product("Kertésznadrág", 59.9f, "USD", "Nadrág, nem csak kertészeknek.", clothes, bershka));
        productDataStore.add(new Product("Halásznadrág", 49.9f, "USD", "Nadrág, nem csak halászoknak.", clothes, bershka));
        productDataStore.add(new Product("Klumpa", 19.9f, "USD", "Munkavédelmi klumpa, egyszerű és szép.", clothes, bershka));
        productDataStore.add(new Product("Mellcsizma", 29.9f, "USD", "Mellcsizma, ideális viselet tengerparti nyaraláshoz.", clothes, bershka));

        productDataStore.add(new Product("Ásó", 89.9f, "USD", "Az ásó, ami megváltoztaja az Ön életét is!", tools, makita));
        productDataStore.add(new Product("Vasvella", 69.9f, "USD", "Nincsenek többé legyőzhetetlen szénaboglyák.", tools, makita));
        productDataStore.add(new Product("Kapa", 29.9f, "USD", "Kapa, bárhol, bármikor.", tools, makita));
        productDataStore.add(new Product("Herélőkés", 19.9f, "USD", "Extra éles.", tools, makita));
        productDataStore.add(new Product("Dugványozó", 89.9f, "USD", "Egyszerű használat, tartós kivitel.", tools, bosch));
        productDataStore.add(new Product("Cirokseprű", 39.9f, "USD", "Letisztult forma, hagyományos minőség.", tools, bosch));
        productDataStore.add(new Product("Talicska", 59.9f, "USD", "Ergonomikus kialakítás, egyszerű működés.", tools, bosch));

        productDataStore.add(new Product("Kombájn", 99999.9f, "USD", "A kombájn, amivel nem marad észrevétlen az utakon.", machines, johndeere));
        productDataStore.add(new Product("Permetező", 99.9f, "USD", "Permetező komoly húsvéti bulihoz.", machines, johndeere));
        productDataStore.add(new Product("Tárcsa", 1999.9f, "USD", "Betárcsázós internethez nélkülözhetetlen.", machines, johndeere));
        productDataStore.add(new Product("Eke", 99.9f, "USD", "Évezredes dizájn.", machines, johndeere));
        productDataStore.add(new Product("Lovaskocsi", 590.9f, "USD", "Egy vagy két lóerős, megkímélt állapotban.", machines, johndeere));
        productDataStore.add(new Product("Vetőgép", 8999.9f, "USD", "A kocka el van vetve.", machines, johndeere));
        productDataStore.add(new Product("Trágyázó", 2999.9f, "USD", "Minden típusú trágyához használható.", machines, johndeere));
        productDataStore.add(new Product("Rotikapa", 899.9f, "USD", "Mindent felforgat.", machines, johndeere));
        productDataStore.add(new Product("Pörzsölő", 99.9f, "USD", "Bármilyen eseményen feldobja a hangulatot.", machines, johndeere));
        productDataStore.add(new Product("Keltetőgép", 99.9f, "USD", "Megbízható, bármit kikeltet.", machines, johndeere));

        productDataStore.add(new Product("Búgatópor", 9.9f, "USD", "Minden típusú állathoz ajánlott.", other, acme));
        productDataStore.add(new Product("Búzadara", 29.9f, "USD", "Nem csak állati fogyasztásra.", other, acme));
        productDataStore.add(new Product("Gázpalack", 99.9f, "USD", "A konyha elmaradhatatlan kiegészítője.", other, acme));
        productDataStore.add(new Product("Műtrágya", 59.9f, "USD", "Bombakészítéshez is ideális.", other, acme));

        productDataStore.add(new Product("Süldő", 129.9f, "USD", "Aranyos, de meg fog nőni.", animals, umbrella));
        productDataStore.add(new Product("Kisbornyú", 99.9f, "USD", "Lakásban is tarható.", animals, umbrella));
        productDataStore.add(new Product("Üsző", 159.9f, "USD", "Kis fogyasztású.", animals, umbrella));
        productDataStore.add(new Product("Napos csibe", 19.9f, "USD", "Csomagban olcsóbb.", animals, umbrella));
        productDataStore.add(new Product("Ártány", 229.9f, "USD", "Megkímélt állapotban.", animals, umbrella));
        productDataStore.add(new Product("Kappan", 99.9f, "USD", "Sok gondozást igényel, gyakran szomorú.", animals, umbrella));

        productDataStore.add(new Product("Kiskondás", 529.9f, "USD", "Kiválóan képzett, bármilyen disznócsordához.", people, umbrella));
        productDataStore.add(new Product("Gulyás", 529.9f, "USD", "Előtanított.", people, umbrella));
        productDataStore.add(new Product("Juhászgyerek", 529.9f, "USD", "Kutyákkal jól kijön.", people, umbrella));
        productDataStore.add(new Product("Pásztor", 529.9f, "USD", "Betanítást igényel, picit lassú.", people, umbrella));
        productDataStore.add(new Product("Csikós", 529.9f, "USD", "Felnőtt lovakhoz is használható.", people, umbrella));

    }
}
