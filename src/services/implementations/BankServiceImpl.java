package services.implementations;

import data.models.Account;
import data.models.Bank;
import data.repositories.AccountImpl;
import data.repositories.AccountRepository;
import data.repositories.BankRepository;
import exceptions.AccountHasBvnException;
import exceptions.InvalidAmountException;
import exceptions.InvalidPasswordException;
import services.interfaces.BankServices;
import services.interfaces.NubanService;

public class BankServiceImpl implements BankServices {
    private BankRepository bankRepository;
    private AccountRepository accountRepository;
    private NubanService nubanService;
    private static long bvnNumber = 1111112345;


    public BankServiceImpl(BankRepository bankRepository){
        this.bankRepository = bankRepository;
        accountRepository = new AccountImpl();
        nubanService = new NubanServiceImpl();

    }

    @Override
    public Account createAccount(Bank bank, Account account, long bvn) {
        if(account.getBvn() != 0L) throw new AccountHasBvnException("Account already has BVN");
        account.setBvn(bvn);
        int bankCode = bank.getBankCode();
        String nubanAccountNumber = nubanService.generateAccountNumber(bankCode);
        account.setNubanAccountNumber(nubanAccountNumber);
        accountRepository.save(account);

        return account;
    }




    @Override
    public Account createBvn(Account account) {
        validateAccount(account);
        account.setBvn(++bvnNumber);
        return account;
    }

    private void validateAccount(Account account){
            if(account.getBvn() != 0) throw new AccountHasBvnException("Account already has BVN");

    }



    @Override
    public void deposit(Account account, double amount, String pin) {
        validatePassword(account, pin);
        validateAmount(amount);
        NibssServiceImpl.deposit(account,amount);
    }

    private void validateAmount( double amount){
        if(amount <= 0)throw new InvalidAmountException("Invalid input");

    }

    private void validatePassword(Account account, String pin){
        if(!account.getPin().equals(pin))throw new InvalidPasswordException("Invalid password");
    }

    @Override
    public void transfer(Account sender, Account receiver, double amount, String pin) {
        validateAmount(amount);
        validatePassword(sender, pin);
        NibssServiceImpl.transfer(sender,receiver,amount);
    }

    @Override
    public void withdraw(Account account, double amount, String pin) {
        validateAmount(amount);
        validatePassword(account,pin);
        NibssServiceImpl.withdraw(account, amount);
    }


}
