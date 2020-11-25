package service.client;

import model.Client;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.io.IOException;
import java.util.List;

public interface ClientService {

    List<Client> findAll();

    Client findById(Long id) throws EntityNotFoundException;

    Notification<Boolean> save(Client client);

    Notification<Boolean> updateName(Long id, String name) throws EntityNotFoundException;

    Notification<Boolean> updateIdentityCardNumber(Long id, String icn) throws EntityNotFoundException;

    Notification<Boolean> updateCNP(Long id, String CNP) throws EntityNotFoundException;

    Notification<Boolean> updateAddress(Long id, String address) throws EntityNotFoundException;

    void processUtilityBill(Long id, String drain, double amount) throws EntityNotFoundException, IOException;


}
