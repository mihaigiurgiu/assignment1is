import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import repository.EntityNotFoundException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Launcher {
    public static void main(String[] args) throws EntityNotFoundException {
        ComponentFactory componentFactory = ComponentFactory.instance(false);
        ClientBuilder clientBuilder = new ClientBuilder();
        Client client = clientBuilder.setName("tomitza").setIdentityCardNumber("12abc").setAddress("strada").setCNP("1000000000").build();
      //  componentFactory.getClientRepository().save(client);
       // componentFactory.getClientRepository().updateName(Long.valueOf(1), "basescu");
        AccountBuilder accountBuilder = new AccountBuilder();

        Account account = accountBuilder.setType("valuta").setBalance(23.12).setBirthday(new Date()).build();
        componentFactory.getAccountRepository().save(Long.valueOf(1), account);
        System.out.println(componentFactory.getClientRepository().findById(Long.valueOf(1)).toString());
        //componentFactory.getAccountRepository().removeAccount(Long.valueOf(1));
    }
}
