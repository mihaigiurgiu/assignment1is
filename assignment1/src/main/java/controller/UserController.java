package controller;

import factory.ComponentFactory;
import view.UserView;

public class UserController {
    private final UserView userView;
    private final ComponentFactory componentFactory;

    public UserController(UserView userView, ComponentFactory componentFactory) {
        this.userView = userView;
        this.componentFactory = componentFactory;
    }

}
