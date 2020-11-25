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
    private JButton createClient;
    private JButton logout;

    private JTextField drain;
    private JTextField amount;
    private JTextField name;
    private JTextField icn;
    private JTextField cnp;
    private JTextField address;

    public UserView(DefaultListModel<Client> clientList) throws HeadlessException {
        setSize(300, 500);
        setLocationRelativeTo(null);
        initializeFields(clientList);
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        add(clients);
        add(drain);
        add(amount);
        add(name);
        add(icn);
        add(cnp);
        add(address);
        add(createClient);
        add(viewClients);
        add(editClient);
        add(processUtility);
        add(transferMoney);
        add(logout);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields(DefaultListModel<Client> clientList) {
        clients = new JList(clientList);
        drain = new JTextField("drain");
        amount = new JTextField("amount");
        name = new JTextField("name");
        icn = new JTextField("icn");
        cnp = new JTextField("cnp");
        address = new JTextField("address");

        viewClients = new JButton("View clients");
        editClient = new JButton ("Edit client");
        processUtility = new JButton("Process bill");
        transferMoney = new JButton("Transfer money");
        createClient = new JButton("Create client");
        logout = new JButton("logout");
    }

    public String getDrain() {
        return drain.getText();
    }

    public String getAmount() {
        return amount.getText();
    }

    public String getName() {
        return name.getText();
    }

    public String getICN() {
        return icn.getText();
    }

    public String getCNP() {
        return cnp.getText();
    }

    public String getAddress() {
        return address.getText();
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

    public void setCreateClientButtonListener(ActionListener createClientButtonListener) {
        createClient.addActionListener(createClientButtonListener);
    }

    public void setLogoutButtonListener(ActionListener logoutButtonListener) {
        logout.addActionListener(logoutButtonListener);
    }

    public void setList(JList list) {
        this.clients = list;
    }


}
