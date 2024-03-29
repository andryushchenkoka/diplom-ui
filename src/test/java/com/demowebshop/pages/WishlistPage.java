package com.demowebshop.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.demowebshop.pages.components.HeaderComponent;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class WishlistPage {

    HeaderComponent headerComponent = new HeaderComponent();
    private final ElementsCollection productList = $$(".cart-item-row .product a");

    public Boolean isProductInWishlist(String href) {

        List<String> hrefs = productList.stream()
                .map(x -> x.getAttribute("href").replace(Configuration.baseUrl, ""))
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

    public SelenideElement getWishesQuantity() {

        return headerComponent.getWishesQuantity();
    }
}
