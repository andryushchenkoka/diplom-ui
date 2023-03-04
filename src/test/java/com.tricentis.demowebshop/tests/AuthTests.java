package com.tricentis.demowebshop.tests;

import com.tricentis.demowebshop.pages.LoginPage;
import com.tricentis.demowebshop.pages.MainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

public class AuthTests extends BaseTest {

    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();

    @Test
    @DisplayName("Авторизация пользователя с помощью пароля")
    public void loginByPassword() {

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
    @DisplayName("Проверить редирект на страницу авторизации из хедера")
    public void checkRedirectMainToLogin() {

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
}
