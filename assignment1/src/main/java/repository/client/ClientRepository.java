package repository.client;

import model.Account;
import model.Client;
import repository.EntityNotFoundException;

import java.util.List;

public interface ClientRepository {

    List<Client> findAll();

    Client findById(Long id) throws EntityNotFoundException;

    boolean save(Client client);

    boolean updateName(Long id, String name) throws EntityNotFoundException;

    boolean updateIdentityCardNumber(Long id, String icn) throws EntityNotFoundException;

    boolean updateCNP(Long id, String CNP) throws EntityNotFoundException;

    boolean updateAddress(Long id, String address) throws EntityNotFoundException;




   


}
