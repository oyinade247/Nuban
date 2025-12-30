package data.repositories;

import data.models.Account;

public interface AccountRepository {
    Account save(Account account);
    Account findByNubanAccount(String nubanAccountNumber);
    Account findByBvn(long bvn);
    void deleteAccount(Account account);
}
