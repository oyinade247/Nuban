package services.implementations;

import data.models.Account;


public class NibssServiceImpl  {



    public static void deposit(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);

    }


    public static void withdraw(Account account, double amount) {
        account.setBalance(account.getBalance() - amount);
    }


    public static void transfer(Account senderAccount, Account receiverAcccount, double amount) {

        withdraw(senderAccount, amount);
        deposit(receiverAcccount, amount);

    }
}
