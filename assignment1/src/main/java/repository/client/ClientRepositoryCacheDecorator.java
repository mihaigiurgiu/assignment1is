package repository.client;

import model.Client;
import repository.Cache;
import repository.EntityNotFoundException;

import java.io.IOException;
import java.util.List;

public class ClientRepositoryCacheDecorator extends ClientRepositoryDecorator {
    private Cache<Client> cache;

    public ClientRepositoryCacheDecorator(ClientRepository clientRepository, Cache<Client> cache) {
        super(clientRepository);
        this.cache = cache;
    }

    @Override
    public List<Client> findAll() {
        if (cache.hasResult()) {
            return cache.load();
        }
        List<Client> clients = decoratedRepository.findAll();
        cache.save(clients);
        return clients;
    }

    @Override
    public Client findById(Long id) throws EntityNotFoundException {
        return decoratedRepository.findById(id);
    }

    @Override
    public boolean save(Client client) {
        cache.invalidateCache();
        return decoratedRepository.save(client);
    }

    @Override
    public boolean updateName(Long id, String name) throws EntityNotFoundException {
        cache.invalidateCache();
        return decoratedRepository.updateName(id, name);
    }

    @Override
    public boolean updateIdentityCardNumber(Long id, String icn) throws EntityNotFoundException {
        cache.invalidateCache();
        return decoratedRepository.updateIdentityCardNumber(id, icn);
    }

    @Override
    public boolean updateCNP(Long id, String CNP) throws EntityNotFoundException {
        cache.invalidateCache();
        return decoratedRepository.updateCNP(id, CNP);
    }

    @Override
    public boolean updateAddress(Long id, String address) throws EntityNotFoundException {
        cache.invalidateCache();
        return decoratedRepository.updateAddress(id, address);
    }

    @Override
    public boolean updateAccount(Long id, Long accountId) throws EntityNotFoundException {
        cache.invalidateCache();
        return decoratedRepository.updateAccount(id, accountId);
    }

    @Override
    public void processUtilityBill(Long id, String drain, double amount) throws EntityNotFoundException, IOException {
        decoratedRepository.processUtilityBill(id, drain, amount);
    }
}
