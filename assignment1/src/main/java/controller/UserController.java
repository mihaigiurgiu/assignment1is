package controller;

import factory.ComponentFactory;
import model.Client;
import model.builder.ClientBuilder;
import repository.EntityNotFoundException;
import view.EditClientView;
import view.UserView;
import view.ViewClients;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UserController {
    private final UserView userView;
    private final ComponentFactory componentFactory;

    public UserController(UserView userView, ComponentFactory componentFactory) {
        this.userView = userView;
        this.componentFactory = componentFactory;
        userView.setViewClientsButtonListener(new ViewClientsButtonListener());
        userView.setEditClientButtonListener(new EditClientButtonListener());
        userView.setProcessUtilityButtonListener(new ProcessUtilityButtonListener());
        userView.setTransferMoneyButtonListener(new TransferMoneyButtonListener());
        userView.setCreateClientButtonListener(new CreateClientActionListener());
    }

    private class ViewClientsButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame view = null;
            try {
                view = new ViewClients("Client Information", componentFactory);
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }
            view.pack();
            view.setVisible(true);
        }
    }

    private class EditClientButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selected = userView.getList().getSelectedIndex();
            Client client = (Client) userView.getList().getModel().getElementAt(selected);
            new EditClientView(client);
        }
    }

    private class ProcessUtilityButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selected = userView.getList().getSelectedIndex();
            Client client = (Client) userView.getList().getModel().getElementAt(selected);
            try {
                componentFactory.getClientService().processUtilityBill(client.getId(), userView.getDrain(), Double.parseDouble(userView.getAmount()));
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

    }

    private class TransferMoneyButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int[] selected = userView.getList().getSelectedIndices();
            Client client1 = (Client) userView.getList().getModel().getElementAt(selected[0]);
            Client client2 = (Client) userView.getList().getModel().getElementAt(selected[1]);
            try {
                componentFactory.getAccountService().transferMoney(client1.getId(), client2.getId(), Double.parseDouble(userView.getAmount()));
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }
        }

    }

    private class CreateClientActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ClientBuilder cb = new ClientBuilder();

            Client newClient = cb.setName(userView.getName())
                    .setIdentityCardNumber(userView.getICN())
                    .setCNP(userView.getCNP())
                    .setAddress(userView.getAddress())
                    .build();

            componentFactory.getClientService().save(newClient);
        }
    }

}
