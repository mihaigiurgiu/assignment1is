import controller.LoginController;
import factory.ComponentFactory;
import repository.EntityNotFoundException;
import view.LoginView;

import java.io.IOException;

public class Launcher {
    public static void main(String[] args) throws EntityNotFoundException, IOException {
        ComponentFactory componentFactory = ComponentFactory.instance(false);
        new LoginController(new LoginView(), componentFactory);
    }
}
