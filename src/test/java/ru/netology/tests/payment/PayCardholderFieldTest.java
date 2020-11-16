package ru.netology.tests.payment;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;
import ru.netology.tests.TestBase;

import static ru.netology.data.Data.*;

public class PayCardholderFieldTest extends TestBase {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void setUpForPayWithCard() {
        mainPage.payWithCard();
    }

    @Test
    public void shouldFailurePaymentIfEmptyCardholderName() {
        val cardData = getInvalidCardholderNameIfEmpty();
        paymentPage.fillCardData(cardData);
        paymentPage.emptyFieldNotification();
    }

    @Test
    public void shouldFailurePaymentIfNameOneWord() {
        val cardData = getInvalidCardholderNameIfOneWord();
        paymentPage.fillCardData(cardData);
        paymentPage.improperFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfNameThreeWords() {
        val cardData = getInvalidCardholderNameIfThreeWords();
        paymentPage.fillCardData(cardData);
        paymentPage.improperFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfNameRusSym() {
        val cardData = getInvalidCardholderNameIfRusSym();
        paymentPage.fillCardData(cardData);
        paymentPage.improperFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfNameNumeric() {
        val cardData = getInvalidCardholderNameIfNumeric();
        paymentPage.fillCardData(cardData);
        paymentPage.improperFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfNameWildcard() {
        val cardData= getInvalidCardholderNameIfWildcard();
        paymentPage.fillCardData(cardData);
        paymentPage.improperFormatNotification();
    }
}
