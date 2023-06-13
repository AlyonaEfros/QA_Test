package tests;


import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import page.AuthorizationPage;
import page.FormPage;

import static tests.TestData.*;
import static utils.RandomUtils.getRandomItemFromArray;


public class FormTests extends TestBase {
    private final AuthorizationPage authorizationPage = new AuthorizationPage();
    private final FormPage formPageTests = new FormPage();

    @DisplayName("Успешное заполнение формы, варианты 1.1 и 2.1")
    @Test
    public void option11SuccessfulFilingFormTest() {
        Faker faker = new Faker();
        String userEmail = faker.internet().emailAddress(),
                userName = faker.name().firstName(),
                userGender = getRandomItemFromArray(genders),
                result = userEmail + " " + userName + " " + userGender + " ";

        authorizationPage
                .setEmail(successfulUserLoginEmail)
                .setPassword(successfulUserLoginPassword)
                .loginButtonClick();

        formPageTests
                .setEmail(userEmail)
                .setName(userName)
                .setGender(userGender)
                .setOption11()
                .setOption21()
                .dataSend()
                .verifyAddData()
                .verifyOptions11(result);
    }

    @DisplayName("Успешное заполнение формы, варианты 1.2 и 2.2")
    @Test
    public void option12SuccessfulFilingFormTest() {
        Faker faker = new Faker();
        String userEmail = faker.internet().emailAddress(),
                userName = faker.name().firstName(),
                userGender = getRandomItemFromArray(genders),
                result = userEmail + " " + userName + " " + userGender + " ";

        authorizationPage
                .setEmail(successfulUserLoginEmail)
                .setPassword(successfulUserLoginPassword)
                .loginButtonClick();

        formPageTests
                .setEmail(userEmail)
                .setName(userName)
                .setGender(userGender)
                .setOption12()
                .setOption22()
                .dataSend()
                .verifyAddData()
                .verifyOptions12(result);
    }

    @DisplayName("Успешное заполнение формы, вариант 1.1 1.2 и 2.3")
    @Test
    public void option1112SuccessfulFilingFormTest() {
        Faker faker = new Faker();
        String userEmail = faker.internet().emailAddress(),
                userName = faker.name().firstName(),
                userGender = getRandomItemFromArray(genders),
                result = userEmail + " " + userName + " " + userGender + " ";

        authorizationPage
                .setEmail(successfulUserLoginEmail)
                .setPassword(successfulUserLoginPassword)
                .loginButtonClick();

        formPageTests
                .setEmail(userEmail)
                .setName(userName)
                .setGender(userGender)
                .setOption11()
                .setOption12()
                .setOption23()
                .dataSend()
                .verifyAddData()
                .verifyOptions1112(result);
    }

    @DisplayName("Успешное заполнение формы, без выбора вариантов")
    @Test
    public void noChoiceOptionNoSuccessfulFilingFormTest() {
        Faker faker = new Faker();
        String userEmail = faker.internet().emailAddress(),
                userName = faker.name().firstName(),
                userGender = getRandomItemFromArray(genders),
                result = userEmail + " " + userName + " " + userGender + " ";

        authorizationPage
                .setEmail(successfulUserLoginEmail)
                .setPassword(successfulUserLoginPassword)
                .loginButtonClick();

        formPageTests
                .setEmail(userEmail)
                .setName(userName)
                .setGender(userGender)
                .dataSend()
                .verifyAddData()
                .verifyNoChoiceOptions(result);
    }

    @ValueSource(strings = {"test@", "@protei.ru", " "})
    @ParameterizedTest(name = "Ошибка заполнения формы, неверный формат Email")
    public void errorFormatEmailTest(String data) {
        Faker faker = new Faker();
        String userName = faker.name().firstName();

        authorizationPage
                .setEmail(successfulUserLoginEmail)
                .setPassword(successfulUserLoginPassword)
                .loginButtonClick();

        formPageTests
                .setEmail(data)
                .setName(userName)
                .dataSend()
                .verifyEmailError();
    }

    @DisplayName("Ошибка заполнения формы, поле 'Имя' пустое")
    @Test
    public void nameFieldEmpty() {
        Faker faker = new Faker();
        String userEmail = faker.internet().emailAddress();

        authorizationPage
                .setEmail(successfulUserLoginEmail)
                .setPassword(successfulUserLoginPassword)
                .loginButtonClick();

        formPageTests
                .setEmail(userEmail)
                .dataSend()
                .verifyNameFieldEmpty();
    }

    @ValueSource(ints = {8, 14, 15, 16})
    @ParameterizedTest(name = "Отображение всей формы при {0} записаных результатов")
    public void formVisibilityTest(int counter) {
        Faker faker = new Faker();
        String userEmail = faker.internet().emailAddress(),
                userName = faker.name().firstName();

        authorizationPage
                .setEmail(successfulUserLoginEmail)
                .setPassword(successfulUserLoginPassword)
                .loginButtonClick();

        formPageTests
                .setEmail(userEmail)
                .setName(userName);
        for (int i = 0; i <= counter; i++) {
            formPageTests.dataSend()
                    .verifyAddData();
        }
        formPageTests.visibilityEmailField();

    }

}
