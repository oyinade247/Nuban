package services.implementations;

import data.models.Account;
import data.models.Bank;
import data.repositories.BankImpl;
import data.repositories.BankRepository;
import exceptions.AccountHasBvnException;
import exceptions.InvalidAmountException;
import exceptions.InvalidPasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.interfaces.BankServices;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BankServiceImplTest {

    private BankServices bankService;
    private BankRepository bankRepository;

    @BeforeEach
    void setUp(){
        bankRepository = new BankImpl();
        bankService = new BankServiceImpl(bankRepository);
    }

    @Test
    void testThatBankServiceCanDeposit(){
        Account account = new Account();
        account.setBalance(3000.00);
        account.setPin("password");
        double amount = 2000.00;
        bankService.deposit(account, amount,account.getPin());
        assertEquals(5000.00, account.getBalance());
    }

    @Test
    void testThatNegativeAmountOfMoneyCannotBeDeposit(){
        Account account = new Account();
        account.setBalance(3000.00);
        account.setPin("password");
        double amount = 0.00;
        assertThrows(InvalidAmountException.class, ()-> bankService.deposit(account, amount, account.getPin()));

    }

    @Test
    void testThatInvalidPasswordWillThrowException(){
        Account account = new Account();
        account.setBalance(3000.00);
        account.setPin("password");
        double amount = 0.00;
        assertThrows(InvalidPasswordException.class, () -> bankService.deposit(account, amount,"pass" ));

    }

    @Test
    void testThatBankServiceCanWithdraw(){
        Account account = new Account();
        account.setBalance(3000.00);
        account.setPin("password");
        double amount = 1000.00;
        bankService.withdraw(account, amount, account.getPin());
        assertEquals(2000.00, account.getBalance());

    }

    @Test
    void testThatNegativeAmountOfMoneyCannotBeWithDrawn(){
        Account account = new Account();
        account.setBalance(3000.00);
        account.setPin("password");
        double amount = 0.00;
        assertThrows(InvalidAmountException.class, () -> bankService.withdraw(account, amount,"password" ));
    }

    @Test
    void testThatIncorrectPasswordWillThrowException(){
        Account account = new Account();
        account.setBalance(3000.00);
        account.setPin("password");
        double amount = 3.00;
        assertThrows(InvalidPasswordException.class, () -> bankService.withdraw(account, amount,"pass" ));

    }

    @Test
    void testThatBankServiceCanTransferToAccounts(){
        Account account1 = new Account();
        Account account2 = new Account();
        account1.setBalance(3000.00);
        account1.setPin("password");
        account2.setBalance(1000.00);
        account2.setPin("pass");
        double amount = 1500.00;
        bankService.transfer(account1,account2, amount,account1.getPin());
        assertEquals(2500.00, account2.getBalance());
        assertEquals(1500.00, account1.getBalance());
    }

    @Test
    void testThatInvalidAmountWillThrowExceptions(){
        Account account1 = new Account();
        Account account2 = new Account();
        account1.setBalance(3000.00);
        account1.setPin("password");
        account2.setBalance(1000.00);
        account2.setPin("pass");
        double amount = 0.00;
        assertThrows(InvalidAmountException.class, ()-> bankService.transfer(account1,account2, amount,account1.getPin()));


    }

    @Test
    void testThatInvalidPasswordToTransferWillThrowException(){
        Account account1 = new Account();
        Account account2 = new Account();
        account1.setBalance(3000.00);
        account1.setPin("password");
        account2.setBalance(1000.00);
        account2.setPin("pass");
        double amount = 3.00;
        assertThrows(InvalidPasswordException.class, ()-> bankService.transfer(account1,account2, amount,"ooooo"));
    }

    @Test
    void testThatBankServiceCanCreateBvn(){
        Account account1 = new Account();
        bankService.createBvn(account1);
        assertEquals(1111112346, account1.getBvn());
    }

    @Test
    void testThatBankServiceCannotGenerateBVNTwiceForAnAccount(){
        Account account1 = new Account();
        bankService.createBvn(account1);
        assertEquals(1111112347, account1.getBvn());
        assertThrows(AccountHasBvnException.class, ()-> bankService.createBvn(account1));

    }

    @Test
    void testThatBankServiceCancreateAccount(){
        Bank bank = new Bank();
        bank.setBankCode(11);

        Account account = new Account();
        long bvn = 12345678910L;
        Account createdAccount = bankService.createAccount(bank, account, bvn);

        assertEquals(bvn, createdAccount.getBvn());
        assertNotNull(createdAccount.getNubanAccountNumber());

    }


}