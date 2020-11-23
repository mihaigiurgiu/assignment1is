package service.account;

import model.Account;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;

import java.util.Date;
import java.util.List;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Account> findAll() {
        return repository.findAll();
    }

    @Override
    public Account findById(Long clientId) throws EntityNotFoundException {
        return repository.findById(clientId);
    }

    @Override
    public boolean save(Long clientId, Account account) throws EntityNotFoundException {
        return repository.save(clientId, account);
    }

    @Override
    public boolean updateType(Long clientId, String type) throws EntityNotFoundException {
        return repository.updateType(clientId, type);
    }

    @Override
    public boolean updateBalance(Long clientId, double sum) throws EntityNotFoundException {
        return repository.updateBalance(clientId, sum);
    }

    @Override
    public boolean updateBirthday(Long clientId, Date birthday) throws EntityNotFoundException {
        return repository.updateBirthday(clientId, birthday);
    }

    @Override
    public void removeAccount(Long clientId) throws EntityNotFoundException {
        repository.removeAccount(clientId);
    }

    @Override
    public boolean transferMoney(Long clientId1, Long clientId2, double amount) throws EntityNotFoundException {
        return repository.transferMoney(clientId1, clientId2, amount);
    }
}
