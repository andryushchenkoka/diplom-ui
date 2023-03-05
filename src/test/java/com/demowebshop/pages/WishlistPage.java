package com.demowebshop.pages;

import com.codeborne.selenide.ElementsCollection;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class WishlistPage {

    private final ElementsCollection productList = $$(".cart-item-row .product a");

    public Boolean isProductInWishlist(String href) {

        List<String> hrefs = productList.stream()
                .map(x -> x.getAttribute("href").replace("https://demowebshop.tricentis.com", ""))
                .collect(Collectors.toList());
        return hrefs.contains(href);
    }

    public WishlistPage selectProduct(String href) {

        executeJavaScript("const xpathExp = \"//a[@href='" + href + "']//ancestor::td//ancestor::tr//td[@class='remove-from-cart']//input[@name='removefromcart']\";" +
                "document.evaluate(xpathExp, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.checked = true;");
        return this;
    }

    public WishlistPage updateWishlist() {

        $("input[name='updatecart']").click();
        return this;
    }

    public int getCurrentCount(String href) {

        String count = $x("//a[@href='" + href + "']//ancestor::td//ancestor::tr//input[@class='qty-input']").getValue();
        return Integer.parseInt(count);
    }
}
