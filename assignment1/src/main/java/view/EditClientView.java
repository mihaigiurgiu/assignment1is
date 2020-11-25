package view;

import model.Client;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

//violates first solid principle, should have been 2 classes
public class EditClientView extends JFrame {
    private Client client;
    private JButton updateName;
    private JButton updateICN;
    private JButton updateCNP;
    private JButton updateAddress;
    private JButton updateAccountType;
    private JButton updateAccountBalance;
    private JButton updateAccountBirthday;
 //   private JButton createClient;
    private JButton createAccount;
    private JButton removeAccount;

    private JTextField name;
    private JTextField ICN;
    private JTextField CNP;
    private JTextField address;
    private JTextField accountType;
    private JTextField accountBalance;
    private JTextField accountBirthday;

    public EditClientView(Client client) throws HeadlessException {
        setSize(300, 300);
        setLocationRelativeTo(null);
        initializeFields(client);
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        add(name);
        add(ICN);
        add(CNP);
        add(address);
        add(accountType);
        add(accountBalance);
        add(accountBirthday);
        add(updateName);
        add(updateICN);
        add(updateCNP);
        add(updateAddress);
        add(updateAccountType);
        add(updateAccountBalance);
        add(updateAccountBirthday);
        add(removeAccount);
       // add(createClient);
        add(createAccount);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields(Client client) {
        name = new JTextField();
        ICN = new JTextField();
        CNP = new JTextField();
        address = new JTextField();
        accountType = new JTextField();
        accountBalance = new JTextField();
        accountBirthday = new JTextField();
        this.client = client;

        updateName = new JButton("Update name");
        updateICN = new JButton("Update icn");
        updateCNP = new JButton("Update CNP");
        updateAddress = new JButton("Update address");
        updateAccountType = new JButton("Update account type");
        updateAccountBalance = new JButton("Update account balance");
        updateAccountBirthday = new JButton("Update account birthday");
       // createClient = new JButton("Create client");
        createAccount = new JButton("Create account");
        removeAccount = new JButton("Remove account");
    }

    public String getName() {
        return name.getText();
    }

    public String getICN() {
        return ICN.getText();
    }

    public String getCNP() {
        return CNP.getText();
    }

    public String getAddress() {
        return address.getText();
    }

    public String getAccountType() {
        return accountType.getText();
    }

    public String getAccountBalance() {
        return accountBalance.getText();
    }

    public String getAccountBirthday() {
        return accountBirthday.getText();
    }

    public void setUpdateNameButtonListener(ActionListener updateNameButtonListener) {
        updateName.addActionListener(updateNameButtonListener);
    }

    public Client getClient() {
        return this.client;
    }

    public void setUpdateICNButtonListener(ActionListener updateICNButtonListener) {
        updateICN.addActionListener(updateICNButtonListener);
    }

    public void setUpdateCNPButtonListener(ActionListener updateCNPButtonListener) {
        updateCNP.addActionListener(updateCNPButtonListener);
    }

    public void setUpdateAddressButtonListener(ActionListener updateAddressButtonListener) {
        updateAddress.addActionListener(updateAddressButtonListener);
    }

    public void setUpdateAccountTypeButtonListener(ActionListener updateAccountTypeButtonListener) {
        updateAccountType.addActionListener(updateAccountTypeButtonListener);
    }

    public void setUpdateAccountBalanceButtonListener(ActionListener updateAccountBalanceButtonListener) {
        updateAccountBalance.addActionListener(updateAccountBalanceButtonListener);
    }

    public void setUpdateAccountBirthdayButtonListener(ActionListener updateAccountBirthdayButtonListener) {
        updateAccountBirthday.addActionListener(updateAccountBirthdayButtonListener);
    }

//    public void setCreateClientButtonListener(ActionListener createClientButtonListener) {
//        createClient.addActionListener(createClientButtonListener);
//    }

    public void setCreateAccountButtonListener(ActionListener createAccountButtonListener) {
        createAccount.addActionListener(createAccountButtonListener);
    }

    public void setRemoveAccount(ActionListener removeAccountButtonListener) {
        removeAccount.addActionListener(removeAccountButtonListener);
    }











}
