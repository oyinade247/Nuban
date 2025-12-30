package data.repositories;

import data.models.Bank;

import java.util.List;

public interface BankRepository {
    long count();
    Bank save(Bank bank);
    List <Bank>  findAll();
    Bank findBankByName(String name);
    void deleteBank(Bank bank);
}
