package controller;

import factory.ComponentFactory;
import model.Account;
import model.User;
import model.validation.Notification;
import model.validation.UserValidator;
import view.AdminView;
import view.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminController {
    private final AdminView adminView;
    private final LoginView loginView;
    private final ComponentFactory componentFactory;

    public AdminController(AdminView adminView, LoginView loginView, ComponentFactory componentFactory) {
        this.adminView = adminView;
        this.loginView = loginView;
        this.componentFactory = componentFactory;
        adminView.setCreateButtonListener(new CreateButtonListener());
        adminView.setGenerateReportButtonListener(new GenerateReportButtonListener());
        adminView.setRemoveButtonListener(new RemoveButtonListener());
        adminView.setUpdateUsernameButtonListener(new UpdateUsernameButtonListener());
        adminView.setUpdatePasswordButtonListener(new UpdatePasswordButtonListener());
        adminView.setLogoutButtonListener(new LogoutButtonListener());

    }

    private class CreateButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = adminView.getUsername();
            String password = adminView.getPassword();

            Notification<Boolean> registerNotification = componentFactory.getAuthenticationService().register(username, password);
            if (registerNotification.hasErrors()) {
                JOptionPane.showMessageDialog(adminView.getContentPane(), registerNotification.getFormattedErrors());
            } else {
                if (!registerNotification.getResult()) {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), "Registration not successful, please try again later.");
                } else {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), "Registration successful!");
                }
            }

            SwingUtilities.updateComponentTreeUI(adminView);

        }
    }

    private class UpdateUsernameButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String newUsername = adminView.getUsername();
            int selected = adminView.getList().getSelectedIndex();
            Notification<Boolean> updateUsernameNotification = new Notification<>();
            updateUsernameNotification = componentFactory.getAuthenticationService().updateUsername((User) adminView.getList().getModel().getElementAt(selected), newUsername);
            if(updateUsernameNotification.hasErrors()){
                JOptionPane.showMessageDialog(adminView.getContentPane(), updateUsernameNotification.getFormattedErrors());
            }
            else {
                if(!updateUsernameNotification.getResult()) {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), "failed updating username");
                }
                else {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), "successfully updated username");
                }
            }

            SwingUtilities.updateComponentTreeUI(adminView);
        }

    }

    private class UpdatePasswordButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String newPassword = adminView.getPassword();
            int selected = adminView.getList().getSelectedIndex();
            Notification<Boolean> updatePasswordNotification = new Notification<>();
            updatePasswordNotification = componentFactory.getAuthenticationService().updatePassword((User) adminView.getList().getModel().getElementAt(selected), newPassword);
            if(updatePasswordNotification.hasErrors()){
                JOptionPane.showMessageDialog(adminView.getContentPane(), updatePasswordNotification.getFormattedErrors());
            }
            else {
                if(!updatePasswordNotification.getResult()) {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), "failed updating password");
                }
                else {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), "successfully updated password");
                }
            }

            SwingUtilities.updateComponentTreeUI(adminView);
        }

    }

    private class RemoveButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selected = adminView.getList().getSelectedIndex();
            componentFactory.getAuthenticationService().remove((User) adminView.getList().getModel().getElementAt(selected));
            JOptionPane.showMessageDialog(adminView.getContentPane(), "User successfully removed");
        }
    }

    private class GenerateReportButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class LogoutButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new LoginController(new LoginView(), componentFactory);
            adminView.dispose();
        }

    }

}
