package page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;


import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class FormPage {
    private final SelenideElement
            emailInput = $("#dataEmail"),
            nameInput = $("#dataName"),
            genderInput = $("#dataGender"),
            option11 = $("#dataCheck11"),
            option12 = $("#dataCheck12"),
            option21 = $("#dataSelect21"),
            option22 = $("#dataSelect22"),
            option23 = $("#dataSelect23"),
            buttonDataSend = $("#dataSend"),
            modalContent = $(".uk-modal-content"),
            buttonModalClose = $("button.uk-modal-close"),
            emailFormatError = $("#emailFormatError"),
            blankNameError = $("#blankNameError");

    @Step("Input email")
    public FormPage setEmail(String value) {
        emailInput.setValue(value);

        return this;
    }

    @Step("Input name")
    public FormPage setName(String value) {
        nameInput.setValue(value);

        return this;
    }

    @Step("Select gender")
    public FormPage setGender(String value) {
        genderInput.$(byText(value)).click();

        return this;
    }

    @Step("Select option 1.1")
    public FormPage setOption11() {
        option11.click();

        return this;
    }

    @Step("Select option 1.2")
    public FormPage setOption12() {
        option12.click();

        return this;
    }

    @Step("Select option 2.1")
    public FormPage setOption21() {
        option21.click();

        return this;
    }

    @Step("Select option 2.2")
    public FormPage setOption22() {
        option22.click();

        return this;
    }

    @Step("Select option 2.3")
    public FormPage setOption23() {
        option23.click();

        return this;
    }

    @Step("Click button data send")
    public FormPage dataSend() {
        buttonDataSend.click();

        return this;
    }

    @Step("Verify successful add data")
    public FormPage verifyAddData() {
        modalContent.shouldHave(text("Данные добавлены."));
        buttonModalClose.click();

        return this;
    }

    @Step("Verify invalid email format")
    public void verifyEmailError() {
        emailFormatError.shouldHave(text("Неверный формат E-Mail"));
    }

    @Step("Verify name field is empty")
    public void verifyNameFieldEmpty() {
        blankNameError.shouldHave(text("Поле имя не может быть пустым"));
    }

    @Step("Verify successful options 1.1 and 2.1")
    public void verifyOptions11(String result) {
        $$("#dataTable tbody tr").shouldHave(texts(result + "1.1" + " " + "2.1"));
    }

    @Step("Verify successful options 1.2 and 2.2")
    public void verifyOptions12(String result) {
        $$("#dataTable tbody tr").shouldHave(texts(result + "1.2" + " " + "2.2"));
    }

    @Step("Verify successful options 1.1 1.2 and 2.3")
    public void verifyOptions1112(String result) {
        $$("#dataTable tbody tr").shouldHave(texts(result + "1.1," + " " + "1.2" + " " + "2.3"));
    }

    @Step("Verify successful Verify successful no choice of options")
    public void verifyNoChoiceOptions(String result) {
        $$("#dataTable tbody tr").shouldHave(texts(result + "Нет" + " "));
    }

    @Step("Visibility of the email field")
    public void visibilityEmailField() {
        emailInput.shouldBe(visible);
    }

}
