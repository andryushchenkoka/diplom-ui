package com.demowebshop.tests;

import com.demowebshop.pages.LoginPage;
import com.demowebshop.pages.MainPage;
import com.demowebshop.pages.ProfilePage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Tag("UI")
@Epic("Web")
@Feature("Авторизация")
@Owner("andryushchenkoka")
public class AuthTests extends BaseTest {

    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();
    ProfilePage profilePage = new ProfilePage();

    @Test
    @Story("Вход в систему")
    @DisplayName("Логин с помощью cookies")
    public void loginByCookiesTest() {

        step("Авторизация с использованием cookies", () -> {

            step("Открыть страницу профиля", () -> {
                profilePage.openPage();
            });

            step("Загрузить cookie авторизации", () -> {
                profilePage
                        .setCookie(authCookies.nopcommerceAuth())
                        .refreshPage();
            });

            step("Проверить логин авторизованного пользователя", () -> {
                Assertions.assertEquals("", profilePage.getProfileName());
            });
        });
    }

    @Test
    @Story("Вход в систему")
    @DisplayName("Логин с помощью пароля")
    public void loginByPasswordTest() {

        step("Открыть страницу авторизации", () -> {
            loginPage.openPage();
        });

        step("Авторизоваться по паролю", () -> {
            loginPage.loginByPassword("", "");
        });

        step("Проверить наличие пользовательского меню в хедере", () -> {
            mainPage.checkLoggedHeader();
            Assertions.assertEquals("", mainPage.getUserName());
        });
    }

    @Test
    @Story("Вход в систему")
    @DisplayName("Проверить редирект на страницу авторизации из хедера")
    public void checkRedirectMainToLoginTest() {

        step("Открыть главную страницу", () -> {
            mainPage.openPage();
        });

        step("Нажать в хедере кнопку логина", () -> {
            mainPage.openLoginForm();
        });

        step("На странице присутствует форма авторизации", () -> {
            loginPage.checkLoginForm();
        });
    }

    @Test
    @Story("Выход из профиля")
    @DisplayName("Выйти из профиля через хедер")
    public void logoutTest() {

        step("Зайти в профиль авторизованного пользователя", () -> {
            profilePage
                    .openPage()
                    .setCookie(authCookies.nopcommerceAuth())
                    .refreshPage();
        });

        step("Нажать в хедере кнопку выхода", () -> {
            profilePage.exitProfile();
        });

        step("Проверить меню в хедере", () -> {
            mainPage.checkUnloggedHeader();
        });
    }
}
