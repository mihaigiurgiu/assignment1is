package controller;

import factory.ComponentFactory;
import model.Account;
import model.builder.AccountBuilder;
import model.validation.Notification;
import repository.EntityNotFoundException;
import view.EditClientView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EditClientController {
    private final EditClientView editClientView;
    private final ComponentFactory componentFactory;

    public EditClientController(EditClientView editClientView, ComponentFactory componentFactory) {
        this.editClientView = editClientView;
        this.componentFactory = componentFactory;
        editClientView.setUpdateNameButtonListener(new UpdateNameButtonListener());
        editClientView.setUpdateCNPButtonListener(new UpdateCNPButtonListener());
        editClientView.setUpdateICNButtonListener(new UpdateICNButtonListener());
        editClientView.setUpdateAddressButtonListener(new UpdateAddressButtonListener());
        editClientView.setUpdateAccountTypeButtonListener(new UpdateAccountTypeButtonListener());
        editClientView.setUpdateAccountBalanceButtonListener(new UpdateAccountBalanceButtonListener());
        editClientView.setUpdateAccountBirthdayButtonListener(new UpdateAccountBirthdayButtonListener());
        editClientView.setCreateAccountButtonListener(new CreateAccountButtonListener());
        editClientView.setRemoveAccount(new RemoveAccountButtonListener());
    }

    private class UpdateNameButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newName = editClientView.getName();
            Notification<Boolean> updateNameNotification = new Notification<>();
            try {
                updateNameNotification = componentFactory.getClientService().updateName(editClientView.getClient().getId(), newName);
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }
            if(updateNameNotification.hasErrors()) {
                JOptionPane.showMessageDialog(editClientView.getContentPane(), updateNameNotification.getFormattedErrors());
            }
            else {
                if (!updateNameNotification.getResult()) {
                    JOptionPane.showMessageDialog(editClientView.getContentPane(), "failed updating name");
                } else {
                    JOptionPane.showMessageDialog(editClientView.getContentPane(), "successfully updated name");
                }
            }
        }
    }

    private class UpdateCNPButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newCNP = editClientView.getCNP();
            Notification<Boolean> updateCNPNotification = new Notification<>();
            try {
                updateCNPNotification = componentFactory.getClientService().updateCNP(editClientView.getClient().getId(), newCNP);
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }
            if(updateCNPNotification.hasErrors()) {
                JOptionPane.showMessageDialog(editClientView.getContentPane(), updateCNPNotification.getFormattedErrors());
            }
            else {
                if(!updateCNPNotification.getResult()) {
                    JOptionPane.showMessageDialog(editClientView.getContentPane(), "failed updating CNP");
                }
                else {
                    JOptionPane.showMessageDialog(editClientView.getContentPane(), "successfully updated CNP");
                }
            }
        }
    }

    private class UpdateICNButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newICN = editClientView.getICN();
            Notification<Boolean> updateICNNotification = new Notification<>();
            try {
                updateICNNotification = componentFactory.getClientService().updateIdentityCardNumber(editClientView.getClient().getId(), newICN);
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }
            if(updateICNNotification.hasErrors()) {
                JOptionPane.showMessageDialog(editClientView.getContentPane(), updateICNNotification.getFormattedErrors());
            }
            else {
                if(!updateICNNotification.getResult()) {
                    JOptionPane.showMessageDialog(editClientView.getContentPane(), "failed updating identity card number");
                }
                else {
                    JOptionPane.showMessageDialog(editClientView.getContentPane(), "successfully updated identity card number");
                }
            }
        }
    }

    private class UpdateAddressButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newAddress = editClientView.getAddress();
            Notification<Boolean> updateAddressNotification = new Notification<>();
            try {
                updateAddressNotification = componentFactory.getClientService().updateAddress(editClientView.getClient().getId(), newAddress);
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }
            if(updateAddressNotification.hasErrors()) {
                JOptionPane.showMessageDialog(editClientView.getContentPane(), updateAddressNotification.getFormattedErrors());
            }
            else {
                if(!updateAddressNotification.getResult()) {
                    JOptionPane.showMessageDialog(editClientView.getContentPane(), "failed updating address");
                }
                else {
                    JOptionPane.showMessageDialog(editClientView.getContentPane(), "successfully updated address");
                }
            }
        }
    }

    private class UpdateAccountTypeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newType = editClientView.getAccountType();
            Notification<Boolean> updateTypeNotification = new Notification<>();
            try {
                updateTypeNotification = componentFactory.getAccountService().updateType(editClientView.getClient().getId(), newType);
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }
            if(updateTypeNotification.hasErrors()) {
                JOptionPane.showMessageDialog(editClientView.getContentPane(), updateTypeNotification.getFormattedErrors());
            }
            else {
                if(!updateTypeNotification.getResult()) {
                    JOptionPane.showMessageDialog(editClientView.getContentPane(), "failed updating account type");
                }
                else {
                    JOptionPane.showMessageDialog(editClientView.getContentPane(), "successfully updated account type");
                }
            }
        }
    }

    private class UpdateAccountBalanceButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newBalance = editClientView.getAccountBalance();
            Notification<Boolean> updateBalanceNotification = new Notification<>();
            try {
                updateBalanceNotification = componentFactory.getAccountService().updateBalance(editClientView.getClient().getId(), Double.valueOf(newBalance));
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }
            if(updateBalanceNotification.hasErrors()) {
                JOptionPane.showMessageDialog(editClientView.getContentPane(), updateBalanceNotification.getFormattedErrors());
            }
            else {
                if(!updateBalanceNotification.getResult()) {
                    JOptionPane.showMessageDialog(editClientView.getContentPane(), "failed updating balance");
                }
                else {
                    JOptionPane.showMessageDialog(editClientView.getContentPane(), "successfully updated balance");
                }
            }
        }
    }

    private class UpdateAccountBirthdayButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newBirthday = editClientView.getAccountBirthday();
            Notification<Boolean> updateBirthdayNotification = new Notification<>();
            try {
                updateBirthdayNotification = componentFactory.getAccountService().updateBirthday(editClientView.getClient().getId(), ((java.util.Date) (new SimpleDateFormat("dd/MM/yyyy").parse(newBirthday))));
            } catch (EntityNotFoundException | ParseException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }
            if(updateBirthdayNotification.hasErrors()) {
                JOptionPane.showMessageDialog(editClientView.getContentPane(), updateBirthdayNotification.getFormattedErrors());
            }
            else {
                if(!updateBirthdayNotification.getResult()) {
                    JOptionPane.showMessageDialog(editClientView.getContentPane(), "failed updating account birthday");
                }
                else {
                    JOptionPane.showMessageDialog(editClientView.getContentPane(), "successfully updated account birthday");
                }
            }
        }

    }

    private class CreateAccountButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            AccountBuilder ab = new AccountBuilder();
            Account newAccount = null;

            try {
                newAccount = ab.setType(editClientView.getAccountType())
                               .setBalance(Double.parseDouble(editClientView.getAccountBalance()))
                               .setBirthday(new SimpleDateFormat("dd/MM/yyyy").parse(editClientView.getAccountBirthday()))
                               .build();
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }

            Notification<Boolean> createAccountNotification = new Notification<>();

            try {
                createAccountNotification = componentFactory.getAccountService().save(editClientView.getClient().getId(), newAccount);
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }

            if(createAccountNotification.hasErrors()) {
                JOptionPane.showMessageDialog(editClientView.getContentPane(), createAccountNotification.getFormattedErrors());
            }
            else {
                if(!createAccountNotification.getResult()) {
                    JOptionPane.showMessageDialog(editClientView.getContentPane(), "error creating account");
                }
                else {
                    JOptionPane.showMessageDialog(editClientView.getContentPane(), "account creation successful");
                }
            }

        }

    }

    private class RemoveAccountButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                componentFactory.getAccountService().removeAccount(editClientView.getClient().getId());
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }
            JOptionPane.showMessageDialog(editClientView.getContentPane(), "account successfully removed");
        }

    }



}
