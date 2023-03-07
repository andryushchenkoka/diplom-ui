package com.demowebshop.tests;

import com.codeborne.selenide.Configuration;
import com.demowebshop.pages.MainPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static io.qameta.allure.Allure.step;

@Tag("UI")
@Epic("Web")
@Feature("Поиск товаров")
@Owner("andryushchenkoka")
public class SearchTests extends BaseTest {

    MainPage mainPage = new MainPage();

    @ParameterizedTest
    @ValueSource(strings = {
            "computer",
            "iphone"
    })
    @Story("Поиск существующего товара")
    @DisplayName("Поиск существующего товара")
    @Severity(SeverityLevel.CRITICAL)
    public void checkSearchResultsTest(String request) {

        step("Поиск существующего товара", () -> {

            List<String> dropResults = mainPage.openPage().getSearchDropResults(request);

            step("Проверить, что выпадающий список не пуст", () -> {
                Assertions.assertTrue(dropResults.size() > 0);
            });

            step("Все результаты поиска содержат поисковое слово", () -> {
                Assertions.assertTrue(mainPage.areAllItemsContains(request, dropResults));
            });

            List<String> fullResults = mainPage.goSearch().getSearchResults();
            System.out.println("BASE URL: " + Configuration.baseUrl);
            step("Результаты поиска в выпадающем списке и на странице результатов идентичны", () -> {
                Assertions.assertEquals(dropResults, fullResults);
            });
        });
    }
}
