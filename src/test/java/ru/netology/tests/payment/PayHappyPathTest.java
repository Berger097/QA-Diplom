package ru.netology.tests.payment;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;
import ru.netology.tests.TestBase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.Data.getApprovedCard;
import static ru.netology.data.Data.getDeclinedCard;
import static ru.netology.data.SQL.*;

public class PayHappyPathTest extends TestBase{

    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void setUpForPayWithCard() {
        mainPage.payWithCard();
    }

    @Test
    public void shouldSuccessPayIfValidApprovedCards() {
        val cardData = getApprovedCard();
        paymentPage.fillCardData(cardData);
        paymentPage.successNotification();
        val paymentId = getPaymentId();
        val expectedStatus = "APPROVED";
        val actualStatus = getPaymentStatus(paymentId);
        val expectedAmount = "4500000";
        val actualAmount = getAmountPayment(paymentId);
        assertEquals(expectedStatus, actualStatus);
        assertEquals(expectedAmount, actualAmount);
    }

    @Test
    public void shouldFailurePayIfValidDeclinedCards() {
        val cardData = getDeclinedCard();
        paymentPage.fillCardData(cardData);
        paymentPage.failureNotification();
        val paymentId = getPaymentId();
        val expectedStatus = "DECLINED";
        val actualStatus = getPaymentStatus(paymentId);
        assertEquals(expectedStatus, actualStatus);
    }
}
