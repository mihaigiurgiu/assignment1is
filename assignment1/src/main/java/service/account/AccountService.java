package service.account;

import model.Account;
import repository.EntityNotFoundException;

import java.util.Date;
import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Account findById(Long clientId) throws EntityNotFoundException;

    boolean save(Long clientId, Account account);

    boolean updateType(Long clientId, String type) throws EntityNotFoundException;

    boolean updateBalance(Long clientId, double sum) throws EntityNotFoundException;

    boolean updateBirthday(Long clientId, Date birthday) throws EntityNotFoundException;

    void removeAccount(Long clientId) throws EntityNotFoundException;

}
