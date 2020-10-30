package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.Data;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {

    private final SelenideElement cardNumberField = $("input[placeholder='0000 0000 0000 0000']");
    private final SelenideElement numberOfMonthField = $("input[placeholder='08']");
    private final SelenideElement yearField = $("input[placeholder='22']");
    private final ElementsCollection fieldSet = $$(".input__top");
    private final SelenideElement cardholderField = fieldSet.get(4);
    private final SelenideElement cvvField = $("input[placeholder='999']");

    private final SelenideElement improperFormat =  $$(".input__sub").find(exactText("Неверный формат"));
    private final SelenideElement emptyField =  $$(".input__sub").find(exactText("Поле обязательно для заполнения"));
    private final SelenideElement invalidExpiredDate =  $$(".input__sub").find(exactText("Неверно указан срок действия карты"));
    private final SelenideElement expiredDatePass =  $$(".input__sub").find(exactText("Истёк срок действия карты"));
    private final SelenideElement successNote =  $$(".input__sub").find(exactText("Операция одобрена Банком."));
    private final SelenideElement failureNote =  $$(".input__sub").find(exactText("Ошибка! Банк отказал в проведении операции."));

    private final SelenideElement continueButton =  $$("button").find(exactText("Продолжить"));

    public void fillCardData(Data.CardNumber cardNumber, Data.NumberOfMonth numberOfMonth, Data.Year year, Data.Cardholder cardholder, Data.Cvv cvv) {
        cardNumberField.setValue(cardNumber.getCardNumber());
        numberOfMonthField.setValue(numberOfMonth.getNumberOfMonth());
        yearField.setValue(year.getYear());
        cardholderField.setValue(cardholder.getCardholder());
        cvvField.setValue(cvv.getCvv());
    }

    public void improperFormatNotification() {
        improperFormat.shouldBe(Condition.visible);
    }

    public void emptyFieldNotification() {
        emptyField.shouldBe(Condition.visible);
    }

    public void invalidExpiredDateNotification() {
        invalidExpiredDate.shouldBe(Condition.visible);
    }

    public void expiredDatePassNotification() {
        expiredDatePass.shouldBe(Condition.visible);
    }

    public void successNotification() {
        successNote.waitUntil(Condition.visible, 15000);
    }

    public void failureNotification() {
        failureNote.waitUntil(Condition.visible, 15000);
    }

}
