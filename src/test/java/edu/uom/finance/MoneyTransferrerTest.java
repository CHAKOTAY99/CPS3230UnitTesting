package edu.uom.finance;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MoneyTransferrerTest {
    MoneyTransferrer moneyTransferrer;
    BankServer bankServer;

    // Shared entries
    public final String SOURCE = null;
    public final String DESTINATION = null;
    public final int AMOUNT = 100;
    public final int TRANSACTION_ID = 1;

    @Before
    public void setup(){
    bankServer = Mockito.mock(BankServer.class);
    moneyTransferrer = new MoneyTransferrer(bankServer);
    }

    @After
    public void teardown(){
    moneyTransferrer = null;
    bankServer = null;
    }

    @Test
    public void checkMoneyAvailableReturnFalse(){
        // Setup
        Mockito.when(bankServer.checkMoneyAvailable(SOURCE, AMOUNT)).thenReturn(false);

        // Exercise
        try {
            moneyTransferrer.transfer(SOURCE, DESTINATION, AMOUNT);
        } catch (InvalidOperationException ioe){
            // Verify
            assertFalse(Boolean.getBoolean(ioe.getMessage()));
        }
    }

    @Test
    public void unsuccessfulMoneyTransfer(){
        // Setup
        Mockito.when(bankServer.checkMoneyAvailable(SOURCE, AMOUNT)).thenReturn(true);
        Mockito.when(bankServer.transferMoney(SOURCE, DESTINATION, AMOUNT)).thenReturn(-1);

        // Exercise
        try {
            moneyTransferrer.transfer(SOURCE, DESTINATION, AMOUNT);
        } catch (InvalidOperationException ioe){
            // Verify
            assertFalse(Boolean.getBoolean(ioe.getMessage()));
        }
    }

    @Test
    public void verifyTransactionInvalid(){
        // Setup
        Mockito.when(bankServer.checkMoneyAvailable(SOURCE, AMOUNT)).thenReturn(true);
        Mockito.when(bankServer.transferMoney(SOURCE, DESTINATION, AMOUNT)).thenReturn(TRANSACTION_ID);
        Mockito.when(bankServer.verifyTransaction(TRANSACTION_ID)).thenReturn(false);

        // Exercise
        int result = moneyTransferrer.transfer(SOURCE, DESTINATION, AMOUNT);

        // Verify
        assertEquals(-1, result);
    }

    @Test
    public void happyPath(){
        // Setup
        Mockito.when(bankServer.checkMoneyAvailable(SOURCE, AMOUNT)).thenReturn(true);
        Mockito.when(bankServer.transferMoney(SOURCE, DESTINATION, AMOUNT)).thenReturn(TRANSACTION_ID);
        Mockito.when(bankServer.verifyTransaction(TRANSACTION_ID)).thenReturn(true);

        // Excercise
        int result = moneyTransferrer.transfer(SOURCE, DESTINATION, AMOUNT);

        // Verify
        assertEquals(TRANSACTION_ID, result);
    }
}
