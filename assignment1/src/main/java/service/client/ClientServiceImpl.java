package service.client;

import model.Client;
import repository.EntityNotFoundException;
import repository.client.ClientRepository;

import java.io.IOException;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Client> findAll() {
        return repository.findAll();
    }

    @Override
    public Client findById(Long id) throws EntityNotFoundException {
        return repository.findById(id);
    }

    @Override
    public boolean save(Client client) {
        return repository.save(client);
    }

    @Override
    public boolean updateName(Long id, String name) throws EntityNotFoundException {
        return repository.updateName(id, name);
    }

    @Override
    public boolean updateIdentityCardNumber(Long id, String icn) throws EntityNotFoundException {
        return repository.updateIdentityCardNumber(id, icn);
    }

    @Override
    public boolean updateCNP(Long id, String CNP) throws EntityNotFoundException {
        return repository.updateCNP(id, CNP);
    }

    @Override
    public boolean updateAddress(Long id, String address) throws EntityNotFoundException {
        return repository.updateAddress(id, address);
    }

    @Override
    public void processUtilityBill(Long id, String drain, double amount) throws EntityNotFoundException, IOException {
        repository.processUtilityBill(id, drain, amount);
    }
}
