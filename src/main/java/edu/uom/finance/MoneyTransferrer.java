package edu.uom.finance;

public class MoneyTransferrer {

    BankServer bankServer;

    public MoneyTransferrer (BankServer bankServer){
        this.bankServer = bankServer;
    }

    public int transfer(String source, String destination, int amount) throws InvalidOperationException{
        if(bankServer.checkMoneyAvailable(source, amount)){
            int transction_id = bankServer.transferMoney(source, destination, amount);
            if(transction_id != -1) {
                if (bankServer.verifyTransaction(transction_id)) {
                    return transction_id;
                } else {
                    return -1;
                }
            }
        }
        throw new InvalidOperationException();
    }
}
