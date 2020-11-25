package repository.account;

import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import repository.Cache;
import repository.EntityNotFoundException;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryCacheDecorator;
import repository.client.ClientRepositoryMock;

import java.security.spec.ECField;

import static org.junit.Assert.assertTrue;

public class AccountRepositoryMockTest {

    private static AccountRepository repository;
    private static ClientRepository clientRepository;

    @BeforeClass
    public static void setupClass() {
        clientRepository = new ClientRepositoryCacheDecorator(new ClientRepositoryMock(), new Cache<>());
        repository = new AccountRepositoryCacheDecorator(new AccountRepositoryMock(new ClientRepositoryMock()), new Cache<>());

    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void findAll() throws Exception {
       // assertTrue(repository.findAll().size() == 0);
    }

    @Test
    public void findByIdEx() throws Exception {
       // repository.findById(1L);
    }

    @Test
    public void save() throws Exception {
//        Client client = new ClientBuilder()
//                .setId(1L)
//                .setName("name")
//                .setIdentityCardNumber("111111")
//                .setCNP("1001010101010")
//                .setAccount(new Account())
//                .build();
//
//        assertTrue(clientRepository.save(client));
//
//        Account account = new AccountBuilder()
//                .setId(1L)
//                .setType("type")
//                .setBalance(10.1)
//                //.setBirthday(java.util.Date.getTime())
//                .build();
//
//
//        assertTrue(repository.save(1l, account));
    }

    @Test
    public void updateName() throws Exception {
//        Client client = new ClientBuilder()
//                .setId(1L)
//                .setName("name")
//                .setIdentityCardNumber("111111")
//                .setCNP("1001010101010")
//                .setAccount(new Account())
//                .build();
//        clientRepository.save(client);
//
//        Account account = new AccountBuilder()
//                .setId(1L)
//                .setType("type")
//                .setBalance(10.1)
//                //.setBirthday(java.util.Date.getTime())
//                .build();
//        repository.save(1l, account);
//
//        assertTrue(repository.updateType(1L, "gigi"));
    }


}
