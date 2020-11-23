package view;

import model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;


public class UserView extends JFrame {

    private JList clients;

    private JButton viewClients;
    private JButton editClient;
    private JButton processUtility;
    private JButton transferMoney;

    private JTextField drain;
    private JTextField amount;

    public UserView(DefaultListModel<Client> clientList) throws HeadlessException {
        setSize(300, 300);
        setLocationRelativeTo(null);
        initializeFields(clientList);
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        add(clients);
        add(drain);
        add(amount);
        add(viewClients);
        add(editClient);
        add(processUtility);
        add(transferMoney);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields(DefaultListModel<Client> clientList) {
        clients = new JList(clientList);
        drain = new JTextField();
        amount = new JTextField();
        viewClients = new JButton("View clients");
        editClient = new JButton ("Edit client");
        processUtility = new JButton("Process bill");
        transferMoney = new JButton("Transfer money");
    }

    public String getDrain() {
        return drain.getText();
    }

    public String getAmount() {
        return amount.getText();
    }

    public JList getList() {
        return clients;
    }

    public void setViewClientsButtonListener(ActionListener viewClientsButtonListener) {
        viewClients.addActionListener(viewClientsButtonListener);
    }

    public void setEditClientButtonListener(ActionListener editClientButtonListener) {
        editClient.addActionListener(editClientButtonListener);
    }

    public void setProcessUtilityButtonListener(ActionListener processUtilityButtonListener) {
        processUtility.addActionListener(processUtilityButtonListener);
    }

    public void setTransferMoneyButtonListener(ActionListener transferMoneyButtonListener) {
        transferMoney.addActionListener(transferMoneyButtonListener);
    }




}
