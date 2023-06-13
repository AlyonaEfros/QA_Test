package page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class AuthorizationPage {

    private final SelenideElement
            loginEmail = $("#loginEmail"),
            loginPassword = $("#loginPassword"),
            inputsPage = $("#inputsPage"),
            loginButton = $("#authButton"),
            emailFormatError = $("#emailFormatError"),
            emailOrPasswordError = $("#invalidEmailPassword");

    @Step("Input login email")
    public AuthorizationPage setEmail(String value) {
        loginEmail.setValue(value);

        return this;
    }

    @Step("Input login password")
    public AuthorizationPage setPassword(String value) {
        loginPassword.setValue(value);

        return this;
    }

    @Step("Click login button")
    public AuthorizationPage loginButtonClick() {
        loginButton.click();

        return this;
    }

    @Step("Click login button")
    public AuthorizationPage loginEnterButton() {
        loginButton.pressEnter();

        return this;
    }

    @Step("Verify successful login")
    public void verifyOpenPageWithForm() {
        inputsPage.shouldBe(visible);

    }

    @Step("Verify login error 'Неверный формат E-Mail'")
    public void invalidFormatEmail() {
        emailFormatError.shouldHave(text("Неверный формат E-Mail"));

    }

    @Step("Verify login error 'Неверный E-Mail или пароль'")
    public void invalidEmailOrPassword() {
        emailOrPasswordError.shouldHave(text("Неверный E-Mail или пароль"));

    }


}
