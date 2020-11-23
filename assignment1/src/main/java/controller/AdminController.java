package controller;

import factory.ComponentFactory;
import model.User;
import model.validation.Notification;
import view.AdminView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminController {
    private final AdminView adminView;
    private final ComponentFactory componentFactory;

    public AdminController(AdminView adminView, ComponentFactory componentFactory) {
        this.adminView = adminView;
        this.componentFactory = componentFactory;
        adminView.setCreateButtonListener(new CreateButtonListener());
        adminView.setGenerateReportButtonListener(new GenerateReportButtonListener());
        adminView.setRemoveButtonListener(new RemoveButtonListener());
        adminView.setUpdateUsernameButtonListener(new UpdateUsernameButtonListener());
        adminView.setUpdatePasswordButtonListener(new UpdatePasswordButtonListener());

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

        }
    }

    private class UpdateUsernameButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String newUsername = adminView.getUsername();
            int selected = adminView.getList().getSelectedIndex();
            componentFactory.getAuthenticationService().updateUsername((User) adminView.getList().getModel().getElementAt(selected), newUsername);
        }

    }

    private class UpdatePasswordButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String newPassword = adminView.getPassword();
            int selected = adminView.getList().getSelectedIndex();
            componentFactory.getAuthenticationService().updatePassword((User) adminView.getList().getModel().getElementAt(selected), newPassword);
        }

    }

    private class RemoveButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selected = adminView.getList().getSelectedIndex();
            componentFactory.getAuthenticationService().remove((User) adminView.getList().getModel().getElementAt(selected));
        }
    }

    private class GenerateReportButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

}
