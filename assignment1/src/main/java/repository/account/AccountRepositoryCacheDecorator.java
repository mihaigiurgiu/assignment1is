package repository.account;

import model.Account;
import repository.Cache;
import repository.EntityNotFoundException;

import java.util.Date;
import java.util.List;

public class AccountRepositoryCacheDecorator extends AccountRepositoryDecorator {

    private Cache<Account> cache;

    public AccountRepositoryCacheDecorator(AccountRepository accountRepository, Cache<Account> cache) {
        super(accountRepository);
        this.cache = cache;
    }

    @Override
    public List<Account> findAll() {
        if (cache.hasResult()) {
            return cache.load();
        }
        List<Account> accounts = decoratedRepository.findAll();
        cache.save(accounts);
        return accounts;
    }

    @Override
    public Account findById(Long clientId) throws EntityNotFoundException {
        return decoratedRepository.findById(clientId);
    }

    @Override
    public boolean save(Long clientId, Account account) {
        return decoratedRepository.save(clientId, account);
    }

    @Override
    public boolean updateType(Long clientId, String type) throws EntityNotFoundException {
        return decoratedRepository.updateType(clientId, type);
    }

    @Override
    public boolean updateBalance(Long clientId, double sum) throws EntityNotFoundException {
        return decoratedRepository.updateBalance(clientId, sum);
    }

    @Override
    public boolean updateBirthday(Long clientId, Date birthday) throws EntityNotFoundException {
        return decoratedRepository.updateBirthday(clientId, birthday);
    }

    @Override
    public void removeAccount(Long clientId) throws EntityNotFoundException {
        decoratedRepository.removeAccount(clientId);
    }

    @Override
    public boolean transferMoney(Long clientId1, Long clientId2, double amount) throws EntityNotFoundException {
        return decoratedRepository.transferMoney(clientId1, clientId2, amount);
    }
}
