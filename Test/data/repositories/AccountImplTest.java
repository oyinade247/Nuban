package data.repositories;

import data.models.Account;
import data.models.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AccountImplTest {

    private AccountImpl accountRepository;


    @BeforeEach
    void setUp() {
        accountRepository = new AccountImpl();
    }

    @Test
    void testthatAccountSaveNewAccount() {
        Account account1 = new Account();
        account1 = new Account();
        account1.setName("Oyin");
        account1.setBvn(12345678901L);
        account1.setNubanAccountNumber("0110000014579");
        account1.setBalance(5000.0);
        account1.setCards(new ArrayList<>());
        account1.setPin("1234");
        account1.setTransactionList(new ArrayList<>());
        accountRepository.save(account1);
        Account found = accountRepository.findByNubanAccount(account1.getNubanAccountNumber());
        assertNotNull(found);
        assertEquals("Oyin", found.getName());
    }

    @Test
    void testCannotSaveDuplicateAccount() {
        Account account1 = new Account();
        account1 = new Account();
        account1.setName("Oyin");
        account1.setBvn(12345678901L);
        account1.setNubanAccountNumber("0110000014579");
        account1.setBalance(5000.0);
        account1.setCards(new ArrayList<>());
        account1.setPin("1234");
        account1.setTransactionList(new ArrayList<>());
        accountRepository.save(account1);
        accountRepository.save(account1);
        Account found = accountRepository.findByNubanAccount(account1.getNubanAccountNumber());
        assertNotNull(found);
        assertEquals("Oyin", found.getName());
    }

    @Test
    void testThatAccountCanBeFoundBByNubanAccount() {

        Account account1 = new Account();
        account1 = new Account();
        account1.setName("Oyin");
        account1.setBvn(12345678901L);
        account1.setNubanAccountNumber("0110000014579");
        account1.setBalance(5000.0);
        account1.setCards(new ArrayList<>());
        account1.setPin("1234");
        account1.setTransactionList(new ArrayList<>());
        accountRepository.save(account1);

        Account account2 = new Account();
        account2 = new Account();
        account2.setName("Tunde");
        account2.setBvn(12345678902L);
        account2.setNubanAccountNumber("0110000014580");
        account2.setBalance(8000.0);
        account2.setCards(new ArrayList<>());
        account2.setPin("5678");
        account2.setTransactionList(new ArrayList<>());
        accountRepository.save(account2);

        Account found = accountRepository.findByNubanAccount(account2.getNubanAccountNumber());
        assertNotNull(found);
        assertEquals("Tunde", found.getName());
    }

        @Test
        void testThatAccountCanBeFindByBvn() {
            Account account1 = new Account();
            account1 = new Account();
            account1.setName("Oyin");
            account1.setBvn(12345678901L);
            account1.setNubanAccountNumber("0110000014579");
            account1.setBalance(5000.0);
            account1.setCards(new ArrayList<>());
            account1.setPin("1234");
            account1.setTransactionList(new ArrayList<>());
            accountRepository.save(account1);

            Account account2 = new Account();
            account2 = new Account();
            account2.setName("Tunde");
            account2.setBvn(12345678902L);
            account2.setNubanAccountNumber("0110000014580");
            account2.setBalance(8000.0);
            account2.setCards(new ArrayList<>());
            account2.setPin("5678");
            account2.setTransactionList(new ArrayList<>());
            accountRepository.save(account2);

            Account found = accountRepository.findByBvn(account1.getBvn());
            assertNotNull(found);
            assertEquals("Oyin", found.getName());
        }

        @Test
        void testThatAccountCanBeDeleted() {
            Account account1 = new Account();
            account1 = new Account();
            account1.setName("Oyin");
            account1.setBvn(12345678901L);
            account1.setNubanAccountNumber("0110000014579");
            account1.setBalance(5000.0);
            account1.setCards(new ArrayList<>());
            account1.setPin("1234");
            account1.setTransactionList(new ArrayList<>());
            accountRepository.save(account1);

            Account account2 = new Account();
            account2 = new Account();
            account2.setName("Tunde");
            account2.setBvn(12345678902L);
            account2.setNubanAccountNumber("0110000014580");
            account2.setBalance(8000.0);
            account2.setCards(new ArrayList<>());
            account2.setPin("5678");
            account2.setTransactionList(new ArrayList<>());
            accountRepository.save(account2);

            accountRepository.deleteAccount(account1);

            assertNull(accountRepository.findByNubanAccount(account1.getNubanAccountNumber()));
            assertNotNull(accountRepository.findByNubanAccount(account2.getNubanAccountNumber()));
        }

        @Test
        void testThatAccountWillUpdateAccountBalance() {
            Account account1 = new Account();
            account1 = new Account();
            account1.setName("Oyin");
            account1.setBvn(12345678901L);
            account1.setNubanAccountNumber("0110000014579");
            account1.setBalance(5000.0);
            account1.setCards(new ArrayList<>());
            account1.setPin("1234");
            account1.setTransactionList(new ArrayList<>());
            accountRepository.save(account1);

            accountRepository.save(account1);

            account1.setBalance(7000.0);
            accountRepository.save(account1);

            Account updated = accountRepository.findByNubanAccount(account1.getNubanAccountNumber());
            assertEquals(7000.0, updated.getBalance());
        }
    }

