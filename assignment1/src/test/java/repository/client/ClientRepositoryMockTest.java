package repository.client;

import model.Account;
import model.Client;
import model.builder.ClientBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.Cache;
import repository.EntityNotFoundException;

import java.util.Date;

import static org.junit.Assert.assertTrue;

public class ClientRepositoryMockTest {

    private static ClientRepository repository;

    @BeforeClass
    public static void setupClass() {
        repository = new ClientRepositoryCacheDecorator(new ClientRepositoryMock(), new Cache<>());
    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void findAll() throws Exception {
        assertTrue(repository.findAll().size() == 0);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdEx() throws Exception {
        repository.findById(1L);
    }

    @Test
    public void save() throws Exception {
        Client client = new ClientBuilder()
                .setId(1L)
                .setName("name")
                .setIdentityCardNumber("111111")
                .setCNP("1001010101010")
                .setAccount(new Account())
                .build();

        assertTrue(repository.save(client));
    }

    @Test
    public void updateName() throws Exception {
        Client client = new ClientBuilder()
                .setId(1L)
                .setName("name")
                .setIdentityCardNumber("111111")
                .setCNP("1001010101010")
                .setAccount(new Account())
                .build();
        repository.save(client);
        assertTrue(repository.updateName(1L, "gigi"));
    }
}
