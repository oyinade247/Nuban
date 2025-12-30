package data.repositories;

import data.models.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountImpl implements AccountRepository{
    private List<Account> accounts;

    public AccountImpl(){
        accounts = new ArrayList<>();
    }

    @Override
    public Account save(Account account) {
        if(isNew(account))saveAccount(account);
        else updateAccount(account);
        return account;
    }

    private boolean isNew(Account account) {
        for (Account account1 : accounts) {
            if (account1.getNubanAccountNumber().equals(account.getNubanAccountNumber())) return false;
        }
        return true;
    }

    private void saveAccount(Account account) {
        accounts.add(account);
    }

    private Account updateAccount(Account account) {
        deleteAccount(account);
        accounts.add(account);
        return account;
    }

    @Override
    public Account findByNubanAccount(String nubanAccountNumber){
        for (Account acct : accounts) {
            if (acct.getNubanAccountNumber().equals(nubanAccountNumber)) return acct;
        }
        return null;

    }

    @Override
    public Account findByBvn(long bvn){
        for (Account acct : accounts) {
            if (acct.getBvn() == bvn) return acct;
        }
        return null;

    }

    @Override
    public void deleteAccount(Account account) {
        accounts.removeIf(b -> b.getNubanAccountNumber() .equals( account.getNubanAccountNumber()));

    }



}
