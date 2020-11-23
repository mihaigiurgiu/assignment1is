package controller;

import factory.ComponentFactory;
import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import repository.EntityNotFoundException;
import view.EditClientView;

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
        editClientView.setCreateClientButtonListener(new CreateClientButtonListener());
        editClientView.setRemoveAccount(new RemoveAccountButtonListener());
    }

    private class UpdateNameButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newName = editClientView.getName();
            try {
                componentFactory.getClientService().updateName(editClientView.getClient().getId(), newName);
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }
        }
    }

    private class UpdateCNPButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newCNP = editClientView.getCNP();
            try {
                componentFactory.getClientService().updateCNP(editClientView.getClient().getId(), newCNP);
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }
        }
    }

    private class UpdateICNButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newICN = editClientView.getICN();
            try {
                componentFactory.getClientService().updateIdentityCardNumber(editClientView.getClient().getId(), newICN);
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }
        }
    }

    private class UpdateAddressButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newAddress = editClientView.getAddress();
            try {
                componentFactory.getClientService().updateAddress(editClientView.getClient().getId(), newAddress);
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }
        }
    }

    private class UpdateAccountTypeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newType = editClientView.getAccountType();
            try {
                componentFactory.getAccountService().updateType(editClientView.getClient().getId(), newType);
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }
        }
    }

    private class UpdateAccountBalanceButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newBalance = editClientView.getAccountBalance();
            try {
                componentFactory.getAccountService().updateBalance(editClientView.getClient().getId(), Double.valueOf(newBalance));
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }
        }
    }

    private class UpdateAccountBirthdayButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newBirthday = editClientView.getAccountBirthday();
            try {
                componentFactory.getAccountService().updateBirthday(editClientView.getClient().getId(), new SimpleDateFormat("dd/MM/yyyy").parse(newBirthday));
            } catch (EntityNotFoundException | ParseException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
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
                               .setBalance(Double.valueOf(editClientView.getAccountBalance()))
                               .setBirthday(new SimpleDateFormat("dd/MM/yyyy").parse(editClientView.getAccountBirthday()))
                               .build();
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }

            try {
                componentFactory.getAccountService().save(editClientView.getClient().getId(), newAccount);
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }
        }

    }

    private class CreateClientButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ClientBuilder cb = new ClientBuilder();
            Client newClient = null;

            newClient = cb.setName(editClientView.getName())
                          .setIdentityCardNumber(editClientView.getICN())
                          .setCNP(editClientView.getCNP())
                          .setAddress(editClientView.getAddress())
                          .build();

            componentFactory.getClientService().save(newClient);
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
        }

    }



}
