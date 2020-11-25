package service.account;

import model.Account;
import model.Client;
import model.validation.AccountValidator;
import model.validation.ClientValidator;
import model.validation.Notification;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;
import repository.client.ClientRepository;

import java.util.Date;
import java.util.List;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    private final ClientRepository clientRepository;

    public AccountServiceImpl(AccountRepository repository, ClientRepository clientRepository) {
        this.repository = repository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Account> findAll() {
        return repository.findAll();
    }

    @Override
    public Account findById(Long clientId) throws EntityNotFoundException {
        return repository.findById(clientId);
    }

    //repository.save(clientId, account) && clientRepository.updateAccount(clientId, repository.findById(clientId).getId())

    @Override
    public Notification<Boolean> save(Long clientId, Account account) throws EntityNotFoundException {
        AccountValidator av = new AccountValidator(account);
        boolean accountValid = av.validate();
        Notification<Boolean> createAccountNotification = new Notification<>();

        if(!accountValid) {
            av.getErrors().forEach(createAccountNotification::addError);
            createAccountNotification.setResult(Boolean.FALSE);
        }
        else {
            createAccountNotification.setResult(repository.save(clientId, account) && clientRepository.updateAccount(clientId, repository.findById(clientId).getId()));
        }

        return createAccountNotification;
    }

    @Override
    public Notification<Boolean> updateType(Long clientId, String type) throws EntityNotFoundException {
        AccountValidator av = new AccountValidator(new Account());
        boolean typeValid = av.validateType(type);
        Notification<Boolean> updateTypeNotification = new Notification<>();

        if(!typeValid) {
            av.getErrors().forEach(updateTypeNotification::addError);
            updateTypeNotification.setResult(Boolean.FALSE);
        }
        else {
            updateTypeNotification.setResult(repository.updateType(clientId, type));
        }
        return updateTypeNotification;
    }

    @Override
    public Notification<Boolean> updateBalance(Long clientId, double sum) throws EntityNotFoundException {
        AccountValidator av = new AccountValidator(new Account());
        boolean balanceValid = av.validateBalance(sum);
        Notification<Boolean> updateBalanceNotification = new Notification<>();

        if(!balanceValid) {
            av.getErrors().forEach(updateBalanceNotification::addError);
            updateBalanceNotification.setResult(Boolean.FALSE);
        }
        else {
            updateBalanceNotification.setResult(repository.updateBalance(clientId, sum));
        }
        return updateBalanceNotification;
    }

    @Override
    public Notification<Boolean> updateBirthday(Long clientId, Date birthday) throws EntityNotFoundException {
        AccountValidator av = new AccountValidator(new Account());
        boolean birthdayValid = av.validateBirthday(birthday);
        Notification<Boolean> updateBirthdayNotification = new Notification<>();

        if(!birthdayValid) {
            av.getErrors().forEach(updateBirthdayNotification::addError);
            updateBirthdayNotification.setResult(Boolean.FALSE);
        }
        else {
            updateBirthdayNotification.setResult(repository.updateBirthday(clientId, birthday));
        }
        return updateBirthdayNotification;
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
