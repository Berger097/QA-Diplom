package ru.netology.tests.creditrequest;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;
import ru.netology.tests.TestBase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.Data.getApprovedCard;
import static ru.netology.data.Data.getDeclinedCard;
import static ru.netology.data.SQL.getPaymentId;
import static ru.netology.data.SQL.getPaymentStatusForCreditRequest;

public class CreditPayHappyPathTest extends TestBase{
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void setUpForPayWithCredit() {
        mainPage.payWithCredit();
    }

    @Test
    public void shouldSuccessCreditRequestIfValidApprovedCards() {
        val cardData = getApprovedCard();
        paymentPage.fillCardData(cardData);
        paymentPage.successNotification();
        val paymentId = getPaymentId();
        val expectedStatus = "APPROVED";
        val actualStatus = getPaymentStatusForCreditRequest(paymentId);
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailurePayIfValidDeclinedCards() {
        val cardData = getDeclinedCard();
        paymentPage.fillCardData(cardData);
        paymentPage.failureNotification();
        val paymentId = getPaymentId();
        val expectedStatus = "DECLINED";
        val actualStatus = getPaymentStatusForCreditRequest(paymentId);
        assertEquals(expectedStatus, actualStatus);
    }
}
