package repository.client;

import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import repository.EntityNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientRepositoryMock implements ClientRepository{

    private List<Client> clients;

    public ClientRepositoryMock() {
        clients = new ArrayList<>();
    }

    @Override
    public List<Client> findAll() {
        return clients;
    }

    @Override
    public Client findById(Long id) throws EntityNotFoundException {
        List<Client> filteredClients = clients.parallelStream()
                .filter(it -> it.getId().equals(id))
                .collect(Collectors.toList());
        if (filteredClients.size() > 0) {
            return filteredClients.get(0);
        }
        throw new EntityNotFoundException(id, Client.class.getSimpleName());
    }

    @Override
    public boolean save(Client client) {
        return clients.add(client);
    }

    @Override
    public boolean updateName(Long id, String name) throws EntityNotFoundException {
        findById(id).setName(name);
        return findById(id) != null;
    }

    @Override
    public boolean updateIdentityCardNumber(Long id, String icn) throws EntityNotFoundException {
        findById(id).setIdentityCardNumber(icn);
        return findById(id) != null;
    }

    @Override
    public boolean updateCNP(Long id, String CNP) throws EntityNotFoundException {
        findById(id).setCNP(CNP);
        return findById(id) != null;
    }

    @Override
    public boolean updateAddress(Long id, String address) throws EntityNotFoundException {
        findById(id).setAddress(address);
        return findById(id) != null;
    }

    @Override
    public boolean updateAccount(Long id, Long accountId) throws EntityNotFoundException {
        AccountBuilder ab = new AccountBuilder();
        Account account = ab.setId(accountId).build();
        findById(id).setAccount(account);
        return findById(id) != null;
    }

    @Override
    public void processUtilityBill(Long id, String drain, double amount) throws EntityNotFoundException, IOException {

    }


}
