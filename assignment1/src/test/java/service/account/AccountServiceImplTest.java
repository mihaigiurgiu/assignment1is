package service.account;

import model.Account;
import model.Client;
import org.junit.Before;
import org.junit.Test;
import repository.EntityNotFoundException;
import repository.account.AccountRepositoryMock;
import repository.client.ClientRepositoryMock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountServiceImplTest {
    private AccountService accountService;

    @Before
    public void setup() {
        ClientRepositoryMock mock = new ClientRepositoryMock();
        accountService = new AccountServiceImpl(new AccountRepositoryMock(mock), new ClientRepositoryMock());
    }

    @Test
    public void findAll() throws Exception {
        assertEquals(0, accountService.findAll().size());
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdEx() throws Exception {
        accountService.findById(1L);
    }

}
