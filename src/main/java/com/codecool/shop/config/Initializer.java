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

        Supplier acme = new Supplier("ACME Corporation", "The best of the best.");
        supplierDataStore.add(acme);

        Supplier umbrella = new Supplier("Umbrella Corporation", "Everything what moves.");
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
        productDataStore.add(new Product("Gumicsimma", 29.9f, "USD", "", clothes, zara));
        productDataStore.add(new Product("Otthonka", 99.9f, "USD", "", clothes, zara));
        productDataStore.add(new Product("Fejfödő", 19.9f, "USD", "", clothes, zara));
        productDataStore.add(new Product("Kertésznadrág", 59.9f, "USD", "", clothes, bershka));
        productDataStore.add(new Product("Halásznadrág", 49.9f, "USD", "", clothes, bershka));
        productDataStore.add(new Product("Klumba", 19.9f, "USD", "", clothes, bershka));
        productDataStore.add(new Product("Mellcsizma", 29.9f, "USD", "", clothes, bershka));

        productDataStore.add(new Product("Ásó", 89.9f, "USD", "", tools, makita));
        productDataStore.add(new Product("Vasvella", 69.9f, "USD", "", tools, makita));
        productDataStore.add(new Product("Kapa", 29.9f, "USD", "", tools, makita));
        productDataStore.add(new Product("Herélőkés", 19.9f, "USD", "", tools, makita));
        productDataStore.add(new Product("Dugványozó", 89.9f, "USD", "", tools, bosch));
        productDataStore.add(new Product("Cirokseprű", 39.9f, "USD", "", tools, bosch));
        productDataStore.add(new Product("Talicska", 59.9f, "USD", "", tools, bosch));

        productDataStore.add(new Product("Kombájn", 99999.9f, "USD", "", machines, johndeere));
        productDataStore.add(new Product("Permetező", 99.9f, "USD", "", machines, johndeere));
        productDataStore.add(new Product("Tárcsa", 1999.9f, "USD", "", machines, johndeere));
        productDataStore.add(new Product("Eke", 99.9f, "USD", "", machines, johndeere));
        productDataStore.add(new Product("Lovaskocsi", 590.9f, "USD", "", machines, johndeere));
        productDataStore.add(new Product("Vetőgép", 8999.9f, "USD", "", machines, johndeere));
        productDataStore.add(new Product("Trágyázó", 2999.9f, "USD", "", machines, johndeere));
        productDataStore.add(new Product("Rotikapa", 899.9f, "USD", "", machines, johndeere));
        productDataStore.add(new Product("Pörzsölő", 99.9f, "USD", "", machines, johndeere));
        productDataStore.add(new Product("Keltetőgép", 99.9f, "USD", "", machines, johndeere));

        productDataStore.add(new Product("Búgatópor", 9.9f, "USD", "", other, acme));
        productDataStore.add(new Product("Búzadara", 29.9f, "USD", "", other, acme));
        productDataStore.add(new Product("Gázpalack", 99.9f, "USD", "", other, acme));
        productDataStore.add(new Product("Műtrágya", 59.9f, "USD", "", other, acme));

        productDataStore.add(new Product("Süldő", 129.9f, "USD", "", animals, umbrella));
        productDataStore.add(new Product("Kisbornyú", 99.9f, "USD", "", animals, umbrella));
        productDataStore.add(new Product("Üsző", 159.9f, "USD", "", animals, umbrella));
        productDataStore.add(new Product("Napos csibe", 19.9f, "USD", "", animals, umbrella));
        productDataStore.add(new Product("Ártány", 229.9f, "USD", "", animals, umbrella));
        productDataStore.add(new Product("Kappan", 99.9f, "USD", "", animals, umbrella));

        productDataStore.add(new Product("Kiskondás", 529.9f, "USD", "", people, umbrella));
        productDataStore.add(new Product("Gulyás", 529.9f, "USD", "", people, umbrella));
        productDataStore.add(new Product("Juhászgyerek", 529.9f, "USD", "", people, umbrella));
        productDataStore.add(new Product("Pásztor", 529.9f, "USD", "", people, umbrella));
        productDataStore.add(new Product("Csikós", 529.9f, "USD", "", people, umbrella));

    }
}
