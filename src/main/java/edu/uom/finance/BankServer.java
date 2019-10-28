package edu.uom.finance;

public interface BankServer {
    public boolean checkMoneyAvailable(String source, int amount);
    public int transferMoney(String source, String destination, int amount);
    public boolean verifyTransaction(int transaction_id);
}
