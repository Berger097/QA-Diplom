package ru.netology.tests.creditrequest;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;
import ru.netology.tests.TestBase;

import static ru.netology.data.Data.*;

public class CreditPayNumberOfMonthFieldTest extends TestBase {

    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void setUpForPayWithCredit() {
        mainPage.payWithCredit();
    }

    @Test
    public void shouldFailurePaymentIfEmptyNumberOfMonth() {
        val cardData = getInvalidNumberOfMonthIfEmpty();
        paymentPage.fillCardData(cardData);
        paymentPage.emptyFieldNotification();
    }

    @Test
    public void shouldFailurePaymentIfNumberOfMonthIfOneSym() {
        val cardData = getInvalidNumberOfMonthIfOneSym();
        paymentPage.fillCardData(cardData);
        paymentPage.improperFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfNumberOfMonthIfMore12() {
        val cardData = getInvalidNumberOfMonthIfMore12();
        paymentPage.fillCardData(cardData);
        paymentPage.invalidExpiredDateNotification();
    }

    @Test
    public void shouldFailurePaymentIfNumberOfMonthZero() {
        val cardData = getInvalidNumberOfMonthIfZero();
        paymentPage.fillCardData(cardData);
        paymentPage.invalidExpiredDateNotification();
    }
}
