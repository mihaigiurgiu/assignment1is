package repository.client;

import database.DBConnectionFactory;
import model.Account;
import model.Client;
import model.builder.ClientBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.Cache;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ClientRepositoryMySQLTest {

    private static ClientRepository clientRepository;

    @BeforeClass
    public static void setupClass() {
        clientRepository = new ClientRepositoryCacheDecorator(new ClientRepositoryMySQL(new DBConnectionFactory().getConnectionWrapper(true).getConnection()), new Cache<>());
    }

    @Before
    public void cleanUp() {

    }

    @Test
    public void findAll() throws Exception {
        List<Client> clients = clientRepository.findAll();
        assertEquals(clients.size(), 0);
    }

    @Test
    public void findAllWhenDbNotEmpty() throws Exception {
        Client client = new ClientBuilder()
                .setId(1L)
                .setName("name")
                .setIdentityCardNumber("111111")
                .setCNP("1001010101010")
                .setAddress("palat")
                .setAccount(new Account())
                .build();

        clientRepository.save(client);
        clientRepository.save(client);

        List<Client> clients = clientRepository.findAll();
        assertNotNull(clients);
    }

    @Test
    public void findById() throws Exception {
        Client client = new ClientBuilder()
                .setId(1L)
                .setName("name")
                .setIdentityCardNumber("111111")
                .setCNP("1001010101010")
                .setAddress("palat")
                .setAccount(new Account())
                .build();

        clientRepository.save(client);
        assertNotNull(clientRepository.findById(1L));
    }

    @Test
    public void save() throws Exception {
        Client client = new ClientBuilder()
                .setId(1L)
                .setName("name")
                .setIdentityCardNumber("111111")
                .setCNP("1001010101010")
                .setAddress("palat")
                .setAccount(new Account())
                .build();
        assertTrue(clientRepository.save(client));
    }

    @Test
    public void updateName() throws Exception {
        Client client = new ClientBuilder()
                .setId(1L)
                .setName("name")
                .setIdentityCardNumber("111111")
                .setCNP("1001010101010")
                .setAddress("palat")
                .setAccount(new Account())
                .build();
        clientRepository.save(client);
        assertTrue(clientRepository.updateName(1L, "gigi"));
    }
}
