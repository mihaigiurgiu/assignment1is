package repository.account;

import model.Account;
import model.Client;
import repository.EntityNotFoundException;
import repository.client.ClientRepositoryMock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AccountRepositoryMock implements AccountRepository {
    private List<Account> accounts;
    private ClientRepositoryMock clientRepo;

    public AccountRepositoryMock(ClientRepositoryMock clientRepo) {
        accounts = new ArrayList<>();
        this.clientRepo = clientRepo;
    }

    @Override
    public List<Account> findAll() {
        return accounts;
    }

    @Override
    public Account findById(Long clientId) throws EntityNotFoundException {
        List<Account> filteredAccounts = accounts.parallelStream()
                .filter(it -> {
                    try {
                        return it.getId().equals(clientRepo.findById(clientId).getAccount().getId());
                    } catch (EntityNotFoundException e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .collect(Collectors.toList());
        if (filteredAccounts.size() > 0) {
            return filteredAccounts.get(0);
        }
        throw new EntityNotFoundException(clientId, Account.class.getSimpleName());
    }

    @Override
    public boolean save(Long clientId, Account account) {
        return accounts.add(account);
    }

    @Override
    public boolean updateType(Long clientId, String type) throws EntityNotFoundException {
        findById(clientId).setType(type);
        return findById(clientId) != null;
    }

    @Override
    public boolean updateBalance(Long clientId, double sum) throws EntityNotFoundException {
        findById(clientId).setBalance(sum);
        return findById(clientId) != null;
    }

    @Override
    public boolean updateBirthday(Long clientId, Date birthday) throws EntityNotFoundException {
        findById(clientId).setBirthday(birthday);
        return findById(clientId) != null;
    }

    @Override
    public void removeAccount(Long clientId) throws EntityNotFoundException {
        accounts.remove(findById(clientId));
    }

    @Override
    public boolean transferMoney(Long clientId1, Long clientId2, double amount) throws EntityNotFoundException {
        return true;
    }
}
