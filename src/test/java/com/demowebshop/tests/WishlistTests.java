package com.demowebshop.tests;

import com.demowebshop.pages.ProductPage;
import com.demowebshop.pages.WishlistPage;
import com.demowebshop.pages.components.Header;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.demowebshop.helpers.Cookies.getCookieName;
import static com.demowebshop.helpers.Cookies.getCookieValue;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

@Tag("UI")
@Epic("Web")
@Feature("Список желаний")
@Owner("andryushchenkoka")
public class WishlistTests extends BaseTest {

    ProductPage productPage = new ProductPage();
    WishlistPage wishlistPage = new WishlistPage();
    Header header = new Header();

    @ParameterizedTest(name = "Добавление товара в список желаний")
    @ValueSource(strings = {
            "/50s-rockabilly-polka-dot-top-jr-plus-size",
            "/health"
    })
    @Story("Добавление товара")
    public void addToWishlistTest(String productUrl) {

        step("Открыть страницу продукта с авторизованным профилем", () -> {

            productPage
                    .openPage(productUrl)
                    .setCookie(authCookies.nopcommerceAuth())
                    .refreshPage();
        });

        step("Проверка добавления товара в список желаний", () -> {

            int wishCountOld, wishCountActual;

            wishCountOld = header.getWishQuantity();

            step("Добавить товар в список желаний", () -> {
                productPage.addToWishlist();
            });

            step("Перейти на страницу списка желаний", () -> {
                header.openWishlist();
            });

            wishCountActual = header.getWishQuantity();

            step("Проверить, что продукт добавлен в список желаний", () -> {
                Assertions.assertTrue(wishlistPage.isProductInWishlist(productUrl));
            });

            step("Проверить, что размер списка желаний увеличился", () -> {
                Assertions.assertEquals(1, wishCountActual - wishCountOld);
            });
        });
    }

    @ParameterizedTest(name = "Удаление товара из списка желаний")
    @CsvFileSource(resources = "/data/remove_products.csv")
    @Story("Удаление товара")
    public void removeFromWishlist(String productUrl, String productId) {

        step("Добавить продукт в список желаний (API запрос)", () -> {

            given()
                    .contentType("application/x-www-form-urlencoded")
                    .cookie(getCookieName(authCookies.nopcommerceAuth()), getCookieValue(authCookies.nopcommerceAuth()))
                    .param("addtocart_" + productId + ".EnteredQuantity", 2)
                    //.body("addtocart_" + productId + ".EnteredQuantity:2")
                    .when()
                    .post(endpointConfig.getBaseUrl()
                            + endpointConfig.getAddWishlistPath()
                            + productId
                            + endpointConfig.getAddWishlistOperation())
                    .then().log().all()
                    .statusCode(200);
        });

        step("Открыть список желаний с авторизованным профилем", () -> {

            productPage
                    .openPage(productUrl)
                    .setCookie(authCookies.nopcommerceAuth())
                    .refreshPage();
        });

        step("Проверка удаления товара из списка желаний", () -> {

            int wishCountOld, wishCountActual, currentCount;

            step("Перейти на страницу списка желаний", () -> {
                header.openWishlist();
            });

            wishCountOld = header.getWishQuantity();

            step("Отметить чекбокс у выбранного товара", () -> {
                wishlistPage.selectProduct(productUrl);
            });

            currentCount = wishlistPage.getCurrentCount(productUrl);

            step("Обновить список желаний", () -> {
                wishlistPage.updateWishlist();
            });

            wishCountActual = header.getWishQuantity();

            step("Проверить, что продукт удален из списка желаний", () -> {
                Assertions.assertFalse(wishlistPage.isProductInWishlist(productUrl));
            });

            step("Проверить, что размер списка желаний уменьшился", () -> {
                Assertions.assertEquals(wishCountActual, wishCountOld - currentCount);
            });
        });
    }
}
