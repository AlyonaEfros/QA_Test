package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import page.AuthorizationPage;

import static tests.TestData.*;


public class AuthorizationTests extends TestBase {
    private final AuthorizationPage authorizationPage = new AuthorizationPage();

    @DisplayName("Успешная авторизация по нажатию кнопки вход")
    @Test
    public void successfulAuthorizationClickTest() {

        authorizationPage
                .setEmail(successfulUserLoginEmail)
                .setPassword(successfulUserLoginPassword)
                .loginButtonClick()
                .verifyOpenPageWithForm();

    }

    @DisplayName("Успешная авторизация по нажатию Enter")
    @Test
    public void successfulAuthorizationEnterTest() {

        authorizationPage
                .setEmail(successfulUserLoginEmail)
                .setPassword(successfulUserLoginPassword)
                .loginEnterButton()
                .verifyOpenPageWithForm();

    }

    @DisplayName("Ошибка авторизации, чувствительность поля 'Email' к регистру букв")
    @Test
    public void emailUpperCaseTest() {

        authorizationPage
                .setEmail(upperCaseUserLoginEmail)
                .setPassword(successfulUserLoginPassword)
                .loginButtonClick()
                .invalidEmailOrPassword();

    }

    @ValueSource(strings = {"test.ru", ""})
    @ParameterizedTest(name = "Ошибка авторизации, неверный формат Email")
    public void errorEmailFormatTest(String testData) {

        authorizationPage
                .setEmail(testData)
                .setPassword(successfulUserLoginPassword)
                .loginButtonClick()
                .invalidFormatEmail();

    }

    @DisplayName("Ошибка авторизации, чувствительность поля 'Пароль' к регистру букв")
    @Test
    public void passwordUpperCaseTest() {

        authorizationPage
                .setEmail(successfulUserLoginEmail)
                .setPassword(upperCaseUserLoginPassword)
                .loginButtonClick()
                .invalidEmailOrPassword();

    }


    @ValueSource(strings = {"tes", ""})
    @ParameterizedTest(name = "Ошибка авторизации, неверный Email или пароль")
    public void errorEmailOrPasswordTest(String testData) {

        authorizationPage
                .setEmail(successfulUserLoginEmail)
                .setPassword(testData)
                .loginButtonClick()
                .invalidEmailOrPassword();

    }
}
