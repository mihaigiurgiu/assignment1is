package controller;

import model.Client;
import model.User;
import model.validation.Notification;
import repository.user.AuthenticationException;
import service.user.AuthenticationService;

import view.AdminView;
import view.LoginView;
import factory.ComponentFactory;
import view.UserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private final LoginView loginView;
    private final ComponentFactory componentFactory;

    public LoginController(LoginView loginView, ComponentFactory componentFactory) {
        this.loginView = loginView;
        this.componentFactory = componentFactory;
        loginView.setLoginButtonListener(new LoginButtonListener());
        loginView.setRegisterButtonListener(new RegisterButtonListener());
    }

    private class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<User> loginNotification = null;
            try {
                loginNotification = componentFactory.getAuthenticationService().login(username, password);
            } catch (AuthenticationException e1) {
                e1.printStackTrace();
            }

            if (loginNotification != null) {
                if (loginNotification.hasErrors()) {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), loginNotification.getFormattedErrors());
                } else {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Login successful!");
                    if(loginView.getUsername().equals(loginView.getPassword()) && loginView.getUsername().equals("admin")) {
                        DefaultListModel<User> users = new DefaultListModel<User>();
                        for(User user : componentFactory.getAuthenticationService().findAll()) {
                            users.addElement(user);
                        }
                        new AdminController(new AdminView(users), loginView, componentFactory);
                        loginView.dispose();
                    }
                    else {
                        DefaultListModel<Client> clients = new DefaultListModel<Client>();
                        for(Client client : componentFactory.getClientService().findAll()) {
                            clients.addElement(client);
                        }

                        new UserController(new UserView(clients, loginView.getUsername()), componentFactory);
                        loginView.dispose();
                    }
                }
            }
        }
    }

    private class RegisterButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<Boolean> registerNotification = componentFactory.getAuthenticationService().register(username, password);
            if (registerNotification.hasErrors()) {
                JOptionPane.showMessageDialog(loginView.getContentPane(), registerNotification.getFormattedErrors());
            } else {
                if (!registerNotification.getResult()) {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration not successful, please try again later.");
                } else {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration successful!");
                }
            }

        }
    }
}
