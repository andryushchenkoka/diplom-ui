package com.demowebshop.pages;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class SearchPage {

    private final SelenideElement searchResults = $(".search-results");

    public List<String> getSearchResults() {

        return searchResults.$$(".item-box .product-title a").texts();
    }
}
