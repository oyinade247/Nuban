package services.interfaces;

import data.models.Account;
import data.models.Bank;
import data.repositories.AccountImpl;
import data.repositories.BankRepository;

public interface BankServices {
    Account createAccount(Bank bank, Account account, long bvn);
    Account createBvn(Account account);
    void deposit(Account account, double amount , String pin);
    void transfer(Account sender, Account receiver, double amount, String pin);
    void withdraw(Account account, double amount , String pin);

}
