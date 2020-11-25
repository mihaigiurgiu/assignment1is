package repository.account;

import database.DBConnectionFactory;
import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.Cache;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryCacheDecorator;
import repository.client.ClientRepositoryMySQL;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AccountRepositoryMySQLTest {

    private static ClientRepository clientRepository;
    private static AccountRepository accountRepository;

    @BeforeClass
    public static void setupClass() {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(true).getConnection();
        clientRepository = new ClientRepositoryCacheDecorator(new ClientRepositoryMySQL(connection), new Cache<>());
        accountRepository = new AccountRepositoryCacheDecorator(new AccountRepositoryMySQL(connection), new Cache<>());
    }

    @Before
    public void cleanUp() {

    }

    @Test
    public void findAll() throws Exception {
//        List<Account> accounts = accountRepository.findAll();
//        assertEquals(accounts.size(), 0);
    }

    @Test
    public void findAllWhenDbNotEmpty() throws Exception {
//        Client client = new ClientBuilder()
//                .setId(1L)
//                .setName("name")
//                .setIdentityCardNumber("111111")
//                .setCNP("1001010101010")
//                .setAddress("palat")
//                .setAccount(new Account())
//                .build();
//
//        clientRepository.save(client);
//        clientRepository.save(client);
//
//        Account account = new AccountBuilder()
//                .setId(1L)
//                .setType("type")
//                .setBalance(999)
//                //.setBirthday(new Date("10/07/2020"))
//                .build();
//
//        accountRepository.save(1L, account);
//        accountRepository.save(2L, account);
//
//        List<Account> accounts = accountRepository.findAll();
//        assertNotNull(accounts);
    }

}
