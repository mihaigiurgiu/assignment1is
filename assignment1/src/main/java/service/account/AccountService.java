package service.account;

import model.Account;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.util.Date;
import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Account findById(Long clientId) throws EntityNotFoundException;

    Notification<Boolean> save(Long clientId, Account account) throws EntityNotFoundException;

    Notification<Boolean> updateType(Long clientId, String type) throws EntityNotFoundException;

    Notification<Boolean> updateBalance(Long clientId, double sum) throws EntityNotFoundException;

    Notification<Boolean> updateBirthday(Long clientId, Date birthday) throws EntityNotFoundException;

    void removeAccount(Long clientId) throws EntityNotFoundException;

    boolean transferMoney(Long clientId1, Long clientId2, double amount) throws EntityNotFoundException;

}
