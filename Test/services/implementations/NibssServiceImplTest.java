package services.implementations;

import data.models.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NibssServiceImplTest {

    @Test
     void testThatNibssServiceCanDeposit(){
        Account account = new Account();
        account.setBalance(5000.00);
        double deposit = 3000.00;
        NibssServiceImpl.deposit(account, deposit);
        assertEquals(8000.00, account.getBalance());
    }

    @Test
    void testThatNibssCanWithdraw(){
        Account account = new Account();
        account.setBalance(5000.00);
        double withdraw = 3000.00;
        NibssServiceImpl.withdraw(account , withdraw);
        assertEquals(2000.00, account.getBalance());
    }

    @Test
    void testThatNibbsServiceCanTransfer(){
        Account account1 = new Account();
        Account account2 = new Account();
        account1.setBalance(5000.00);
        account2.setBalance(2000.00);
        NibssServiceImpl.transfer(account1, account2, 1500.00);
        assertEquals(3500.00, account2.getBalance());
        assertEquals(3500.00, account1.getBalance());


    }
}