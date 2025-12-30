package data.repositories;

import data.models.Bank;

import java.util.ArrayList;
import java.util.List;

public class BankImpl implements BankRepository {

    List<Bank> banks;


    public BankImpl() {
        banks = new ArrayList<>();
    }

    @Override
    public long count() {
        return banks.size();
    }

    @Override
    public Bank save(Bank bank) {
        if (isNew(bank)) saveBank(bank);
        else updateBank(bank);
        return bank;
    }

    private boolean isNew(Bank bank) {
        for (Bank bank1 : banks) {
            if (bank1.getBankCode() == bank.getBankCode()) return false;
        }
        return true;
    }

    private void saveBank(Bank bank) {
        banks.add(bank);
    }

    private Bank updateBank(Bank bank) {
        deleteBank(bank);
        banks.add(bank);
        return bank;
    }

    @Override
    public List<Bank> findAll() {
        return banks;
    }

    @Override
    public Bank findBankByName(String name) {
        for (Bank bank : banks) {
            if (bank.getName().equalsIgnoreCase(name)) return bank;
        }
        return null;
    }

    @Override
    public void deleteBank(Bank bank) {
        banks.removeIf(b -> b.getBankCode() == bank.getBankCode());
    }
}
