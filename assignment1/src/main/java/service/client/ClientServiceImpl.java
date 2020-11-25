package service.client;

import model.Client;
import model.validation.ClientValidator;
import model.validation.Notification;
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
    public Notification<Boolean> save(Client client) {
        ClientValidator cv = new ClientValidator(client);
        boolean clientValid = cv.validate();
        Notification<Boolean> createClientNotification = new Notification<>();

        if(!clientValid) {
            cv.getErrors().forEach(createClientNotification::addError);
            createClientNotification.setResult(Boolean.FALSE);
        }
        else {
            createClientNotification.setResult(repository.save(client));
        }

        return createClientNotification;
    }

    @Override
    public Notification<Boolean> updateName(Long id, String name) throws EntityNotFoundException {
        ClientValidator cv = new ClientValidator(new Client());
        boolean nameValid = cv.validateName(name);
        Notification<Boolean> updateNameNotification = new Notification<>();

        if(!nameValid) {
            cv.getErrors().forEach(updateNameNotification::addError);
            updateNameNotification.setResult(Boolean.FALSE);
        }
        else {
            updateNameNotification.setResult(repository.updateName(id, name));
        }
        return updateNameNotification;
    }

    @Override
    public Notification<Boolean> updateIdentityCardNumber(Long id, String icn) throws EntityNotFoundException {
        ClientValidator cv = new ClientValidator(new Client());
        boolean icnValid = cv.validateICN(icn);
        Notification<Boolean> updateICNNotification = new Notification<>();

        if(!icnValid) {
            cv.getErrors().forEach(updateICNNotification::addError);
            updateICNNotification.setResult(Boolean.FALSE);
        }
        else {
            updateICNNotification.setResult(repository.updateIdentityCardNumber(id, icn));
        }
        return updateICNNotification;
    }

    @Override
    public Notification<Boolean> updateCNP(Long id, String CNP) throws EntityNotFoundException {
        ClientValidator cv = new ClientValidator(new Client());
        boolean cnpValid = cv.validateCNP(CNP);
        Notification<Boolean> updateCNPNotification = new Notification<>();

        if(!cnpValid) {
            cv.getErrors().forEach(updateCNPNotification::addError);
            updateCNPNotification.setResult(Boolean.FALSE);
        }
        else {
            updateCNPNotification.setResult(repository.updateCNP(id, CNP));
        }
        return updateCNPNotification;
    }

    @Override
    public Notification<Boolean> updateAddress(Long id, String address) throws EntityNotFoundException {
        ClientValidator cv = new ClientValidator(new Client());
        boolean addressValid = cv.validateAddress(address);
        Notification<Boolean> updateAddressNotification = new Notification<>();

        if(!addressValid) {
            cv.getErrors().forEach(updateAddressNotification::addError);
            updateAddressNotification.setResult(Boolean.FALSE);
        }
        else {
            updateAddressNotification.setResult(repository.updateAddress(id, address));
        }
        return updateAddressNotification;
    }

    @Override
    public void processUtilityBill(Long id, String drain, double amount) throws EntityNotFoundException, IOException {
        repository.processUtilityBill(id, drain, amount);
    }
}
