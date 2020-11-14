package ru.netology.tests.payment;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;
import ru.netology.tests.TestBase;

import static ru.netology.data.Data.*;

public class PayCardNumberFieldTest extends TestBase{

    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void setUpForPayWithCard() {
        mainPage.payWithCard();
    }

    @Test
    public void shouldFailurePaymentIfEmptyCardNumber() {
        val cardData = getInvalidCardNumberIfEmpty();
        paymentPage.fillCardData(cardData);
        paymentPage.emptyFieldNotification();
    }

    @Test
    public void shouldFailurePaymentIfCardNumberIfLess16Sym() {
        val cardData = getInvalidCardNumberIfLess16Sym();
        paymentPage.fillCardData(cardData);
        paymentPage.improperFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfCardNumberIfOutOfBase() {
        val cardData = getInvalidCardNumberIfOutOfBase();
        paymentPage.fillCardData(cardData);
        paymentPage.failureNotification();
    }
}
