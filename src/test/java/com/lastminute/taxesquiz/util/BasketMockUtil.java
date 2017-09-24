package com.lastminute.taxesquiz.util;

import com.lastminute.taxesquiz.sale.basket.item.model.BasketItem;
import com.lastminute.taxesquiz.sale.basket.model.Basket;
import com.lastminute.taxesquiz.sale.product.model.Product;
import com.lastminute.taxesquiz.sale.product.model.ProductCategory;
import com.lastminute.taxesquiz.sale.tax.model.Tax;

import java.math.BigDecimal;
import java.util.*;

public class BasketMockUtil {

    /*
        Input 1:
        1 book at 12.49
        1 music CD at 14.99
        1 chocolate bar at 0.85
    */
    public static Basket basket1(){
        return new Basket().basketItems(
                Arrays.asList(
                        basketItem(
                                bookProduct("book","", false),
                                new BigDecimal("12.49"),
                                1
                        ),
                        basketItem(
                                defaultTaxableProduct("music CD","",false),
                                new BigDecimal("14.99"),
                                1
                        ),
                        basketItem(
                                foodProduct("chocolate bar", "", false),
                                new BigDecimal("0.85"),
                                1
                        )
                )
        );
    }

    /*
        Output 1:
        1 book : 12.49
        1 music CD: 16.49
        1 chocolate bar: 0.85
        Sales Taxes: 1.50
        Total: 29.83
     */
    public static Basket basket1WithTaxes(){
        return new Basket().basketItems(
                Arrays.asList(
                        basketItem(
                                bookProduct("book", "", false),
                                new BigDecimal("12.49"),
                                1
                        ),
                        basketItem(
                                defaultTaxableProduct("music CD", "",false),
                                new BigDecimal("16.49"),
                                1
                        ),
                        basketItem(
                                foodProduct("chocolate bar", "",false),
                                new BigDecimal("0.85"),
                                1
                        )
                )
        );
    }

    /*
        Input 2:
        1 imported box of chocolates at 10.00
        1 imported bottle of perfume at 47.50
    */
    public static Basket basket2(){
        return new Basket().basketItems(
                Arrays.asList(
                        basketItem(
                                foodProduct("chocolates", "box of", true),
                                new BigDecimal("10.00"),
                                1
                        ),
                        basketItem(
                                defaultTaxableProduct("perfume","bottle of", true),
                                new BigDecimal("47.50"),
                                1
                        )
                )
        );
    }

    /*
        Output 2:
        1 imported box of chocolates: 10.50
        1 imported bottle of perfume: 54.65
        Sales Taxes: 7.65
        Total: 65.15
     */
    public static Basket basket2WithTaxes(){
        return new Basket().basketItems(
                Arrays.asList(
                        basketItem(
                                foodProduct("chocolates", "box of",true),
                                new BigDecimal("10.50"),
                                1
                        ),
                        basketItem(
                                defaultTaxableProduct("perfume", "bottle of", true),
                                new BigDecimal("54.65"),
                                1
                        )
                )
        );
    }

    /*
        Input 3:
        1 imported bottle of perfume at 27.99
        1 bottle of perfume at 18.99
        1 packet of headache pills at 9.75
        1 box of imported chocolates at 11.25
    */
    public static Basket basket3(){
        return new Basket().basketItems(
                Arrays.asList(
                        basketItem(
                                defaultTaxableProduct("perfume","bottle of", true),
                                new BigDecimal("27.99"),
                                1
                        ),
                        basketItem(
                                defaultTaxableProduct("perfume","bottle of",false),
                                new BigDecimal("18.99"),
                                1
                        ),
                        basketItem(
                                medicalProduct("headache pills", "packet of",false),
                                new BigDecimal("9.75"),
                                1
                        ),
                        basketItem(
                                foodProduct("chocolates", "box of",true),
                                new BigDecimal("11.25"),
                                1
                        )
                )
        );
    }

    /*
        Output 3:
        1 imported bottle of perfume: 32.19
        1 bottle of perfume: 20.89
        1 packet of headache pills: 9.75
        1 imported box of chocolates: 11.85
        Sales Taxes: 6.70
        Total: 74.68
     */
    public static Basket basket3WithTaxes(){
        return new Basket().basketItems(
                Arrays.asList(
                        basketItem(
                                defaultTaxableProduct("perfume", "bottle of", true),
                                new BigDecimal("32.19"),
                                1
                        ),
                        basketItem(
                                defaultTaxableProduct("perfume", "bottle of",false),
                                new BigDecimal("20.89"),
                                1
                        ),
                        basketItem(
                                medicalProduct("headache pills", "packet of", false),
                                new BigDecimal("9.75"),
                                1
                        ),
                        basketItem(
                                foodProduct("chocolates","box of",true),
                                new BigDecimal("11.85"),
                                1
                        )
                )
        );
    }

    public static BasketItem basketItem(Product product, BigDecimal price, Integer qty ){
        return new BasketItem()
                .price(price)
                .qty(qty)
                .product(product);
    }


    public static Product product( String description, String packaging, boolean imported ){
        Set<ProductCategory> productCategories = new HashSet<>();
        if(imported){
            productCategories.add(importedProductCategory());
        }
        return new Product().code(description)
                .description(description)
                .imported(imported)
                .packaging(packaging)
                .categories(productCategories);
    }

    public static Product defaultTaxableProduct(String productDescription, String packaging, boolean imported){
        Product product = product( productDescription, packaging, imported );
        product.getCategories().add( defaultProductCategory() );
        return product;
    }

    public static Product bookProduct(String productDescription, String packaging, boolean imported){
        Product product = product( productDescription, packaging, imported );
        product.getCategories().add(new ProductCategory().code("books").description("books"));
        return product;
    }

    public static Product foodProduct(String productDescription, String packaging, boolean imported){
        Product product = product( productDescription, packaging, imported );
        product.getCategories().add(new ProductCategory().code("food").description("food"));
        return product;
    }

    public static Product medicalProduct(String productDescription, String packaging, boolean imported){
        Product product = product( productDescription, packaging, imported );
        product.getCategories().add(new ProductCategory().code("medical").description("medical"));
        return product;
    }


    public static ProductCategory defaultProductCategory(){
        return new ProductCategory().code("default").description("default").taxes( basicSalesTaxes() );
    }

    private static List<Tax> basicSalesTaxes(){
        return Collections.singletonList(
                new Tax().percent(new BigDecimal("10.00"))
        );
    }

    public static ProductCategory importedProductCategory(){
        return productCategory( "imported", "imported",
                Collections.singletonList(
                        importedProductSaleTax()
                )
        );
    }

    public static ProductCategory productCategory(String code, String description, List<Tax> taxes){
        return new ProductCategory().code(code)
                .description(description)
                .taxes(taxes);
    }

    private static Tax importedProductSaleTax(){
        return new Tax().percent(new BigDecimal("5.00"));
    }

}
