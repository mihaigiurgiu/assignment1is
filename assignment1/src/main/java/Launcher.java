import controller.LoginController;
import factory.ComponentFactory;
import repository.EntityNotFoundException;
import view.LoginView;

import java.io.IOException;

public class Launcher {
    public static void main(String[] args) throws EntityNotFoundException, IOException {
        ComponentFactory componentFactory = ComponentFactory.instance(false);
//        ClientBuilder clientBuilder = new ClientBuilder();
//        Client client = clientBuilder.setName("tomitza").setIdentityCardNumber("12abc").setAddress("strada").setCNP("1000000000").build();
//        componentFactory.getClientRepository().save(client);
//        componentFactory.getClientRepository().save(client);
//       // componentFactory.getClientRepository().updateName(Long.valueOf(1), "basescu");
//        AccountBuilder accountBuilder = new AccountBuilder();
//
//        Account account = accountBuilder.setType("valuta").setBalance(23.12).setBirthday(new Date()).build();
//        componentFactory.getAccountRepository().save(Long.valueOf(1), account);
//        account = accountBuilder.setType("valuta").setBalance(10.0).setBirthday(new Date()).build();
//        componentFactory.getAccountRepository().save(Long.valueOf(2), account);
      //  System.out.println(componentFactory.getClientRepository().findById(Long.valueOf(1)).toString());
        //componentFactory.getAccountRepository().removeAccount(Long.valueOf(1));
        //componentFactory.getAccountRepository().transferMoney(Long.valueOf(1), Long.valueOf(2), 10.0);
        //componentFactory.getClientRepository().processUtilityBill(Long.valueOf(1), "smokes", 10.0);
        new LoginController(new LoginView(), componentFactory);
    }
}
