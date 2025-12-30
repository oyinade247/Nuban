package data.repositories;

import data.models.Account;
import data.models.Bank;
import data.models.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class BankImplTest {
    private  BankRepository bankRepository;

    @BeforeEach
    void setUp(){
        bankRepository = new BankImpl();
    }

    @Test
    void testThatBankCanIsEmpty(){
        assertEquals(0, bankRepository.count());
    }

    @Test
    void testThatIfABankIsAddedBankCountIncreases(){
        assertEquals(0, bankRepository.count());
        Bank bank = new Bank();
        bankRepository.save(bank);
        assertEquals(1, bankRepository.count());
    }

    @Test
    void testThatYouCannotAddBankOfSameType(){
        assertEquals(0, bankRepository.count());
        Account account = new Account();
        account.setName("Oyin");
        account.setBvn(12345678901L);
        account.setNubanAccountNumber("0110000014579");
        account.setBalance(5000.0);
        account.setCards(new ArrayList<>());
        account.setPin("1234");
        account.setTransactionList(new ArrayList<>());
        Bank bank = new Bank(101, "Access Bank", new ArrayList<>());
        bankRepository.save(bank);
        Bank bank1 = new Bank(102, "first Bank", new ArrayList<>());
        bankRepository.save(bank1);
        assertEquals(2, bankRepository.count());


    }



    @Test
    void testThatMultipleBanksWithDifferentCodesCanBeAdded() {
        Bank bank1 = new Bank(101, "Access Bank", new ArrayList<>());
        Bank bank2 = new Bank(102, "First Bank", new ArrayList<>());

        bankRepository.save(bank1);
        bankRepository.save(bank2);

        assertEquals(2, bankRepository.count());
    }

    @Test
    void testThantBankCanBeFindBankByName() {
        Bank bank = new Bank(101, "Access Bank", new ArrayList<>());
        bankRepository.save(bank);
        Bank found = bankRepository.findBankByName("Access Bank");
        assertNotNull(found);
        assertEquals(101, found.getBankCode());
    }

    @Test
    void testBankCanBeDeleted() {
        Bank bank = new Bank(101, "Access Bank", new ArrayList<>());
        bankRepository.save(bank);

        bankRepository.deleteBank(bank);
        assertEquals(0, bankRepository.count());
    }

    }