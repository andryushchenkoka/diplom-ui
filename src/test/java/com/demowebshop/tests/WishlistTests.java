package com.demowebshop.tests;

import com.codeborne.selenide.Condition;
import com.demowebshop.helpers.CookieHelper;
import com.demowebshop.pages.ProductPage;
import com.demowebshop.pages.WishlistPage;
import com.demowebshop.pages.components.HeaderComponent;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

@Tag("UI")
@Epic("Web")
@Feature("Список желаний")
@Owner("andryushchenkoka")
public class WishlistTests extends BaseTest {

    ProductPage productPage = new ProductPage();
    WishlistPage wishlistPage = new WishlistPage();
    HeaderComponent headerComponent = new HeaderComponent();

    @ParameterizedTest
    @ValueSource(strings = {
            "/50s-rockabilly-polka-dot-top-jr-plus-size",
            "/health"
    })
    @Story("Добавление товара")
    @DisplayName("Добавление товара в список желаний")
    @Severity(SeverityLevel.NORMAL)
    public void addToWishlistTest(String productUrl) {

        step("Открыть страницу продукта с авторизованным профилем", () -> {

            productPage.openPage(productUrl);
        });

        step("Проверка добавления товара в список желаний", () -> {

            int wishCountOld = headerComponent.getWishQuantity();

            step("Добавить товар в список желаний", () -> {
                productPage.addToWishlist();
            });

            step("Проверить, что размер списка желаний увеличился", () -> {
                wishlistPage.getWishesQuantity().shouldHave(Condition.text((wishCountOld + 1) + ""));
            });

            step("Перейти на страницу списка желаний", () -> {
                headerComponent.openWishlist();
            });

            step("Проверить, что продукт добавлен в список желаний", () -> {
                Assertions.assertTrue(wishlistPage.isProductInWishlist(productUrl));
            });
        });
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data/remove_products.csv")
    @Story("Удаление товара")
    @DisplayName("Удаление товара из списка желаний")
    @Severity(SeverityLevel.NORMAL)
    public void removeFromWishlist(String productUrl, String productId) {

        step("Добавить продукт в список желаний (API запрос)", () -> {

            given()
                    .contentType("application/x-www-form-urlencoded")
                    .cookie(authCookie.getName(), authCookie.getValue())
                    .param("addtocart_" + productId + ".EnteredQuantity", 2)
                    .when()
                    .post(ENDPOINT_CONFIG.getBaseUrl()
                            + ENDPOINT_CONFIG.getAddWishlistPath()
                            + productId
                            + ENDPOINT_CONFIG.getAddWishlistOperation())
                    .then().log().all()
                    .statusCode(200);
        });

        step("Открыть список желаний с авторизованным профилем", () -> {

            productPage.openPage(productUrl);
            CookieHelper.setCookieAndRefresh(authCookie);
        });

        step("Проверка удаления товара из списка желаний", () -> {

            int wishCountOld, currentCount;

            step("Перейти на страницу списка желаний", () -> {
                headerComponent.openWishlist();
            });

            wishCountOld = headerComponent.getWishQuantity();

            step("Отметить чекбокс у выбранного товара", () -> {
                wishlistPage.selectProduct(productUrl);
            });

            currentCount = wishlistPage.getCurrentCount(productUrl);

            step("Обновить список желаний", () -> {
                wishlistPage.updateWishlist();
            });

            step("Проверить, что продукт удален из списка желаний", () -> {
                Assertions.assertFalse(wishlistPage.isProductInWishlist(productUrl));
            });

            step("Проверить, что размер списка желаний уменьшился", () -> {
                wishlistPage.getWishesQuantity().shouldHave(Condition.text((wishCountOld - currentCount) + ""));
            });
        });
    }
}
