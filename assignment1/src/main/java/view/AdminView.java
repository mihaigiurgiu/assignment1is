package view;

import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class AdminView extends JFrame {

    private JList users;

    private JButton generateReport;
    private JButton removeUser;
    private JButton createUser;
    private JButton updateUsername;
    private JButton updatePassword;
    private JButton logout;

    private JTextField tfUsername;
    private JTextField tfPassword;

    public AdminView(DefaultListModel<User> userList) throws HeadlessException {
        setSize(300, 300);
        setLocationRelativeTo(null);
        initializeFields(userList);
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        add(users);
        add(generateReport);
        add(removeUser);
        add(tfUsername);
        add(tfPassword);
        add(createUser);
        add(updateUsername);
        add(updatePassword);
        add(logout);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields(DefaultListModel<User> userList) {
        tfUsername = new JTextField();
        tfPassword = new JTextField();
        logout = new JButton("Logout");
        generateReport = new JButton("Generate report");
        removeUser = new JButton("Remove selection");
        createUser = new JButton("Create user");
        updateUsername = new JButton("Update username");
        updatePassword = new JButton("Update password");
        users = new JList(userList);
    }

    public String getUsername() {
        return tfUsername.getText();
    }

    public String getPassword() {
        return tfPassword.getText();
    }

    public void setGenerateReportButtonListener(ActionListener generateReportButtonListener) {
        generateReport.addActionListener(generateReportButtonListener);
    }

    public void setUpdateUsernameButtonListener(ActionListener updateUsernameButtonListener) {
        updateUsername.addActionListener(updateUsernameButtonListener);
    }

    public void setUpdatePasswordButtonListener(ActionListener updatePasswordButtonListener) {
        updatePassword.addActionListener(updatePasswordButtonListener);
    }

    public void setRemoveButtonListener(ActionListener removeButtonListener) {
        removeUser.addActionListener(removeButtonListener);
    }

    public void setCreateButtonListener(ActionListener createButtonListener) {
        createUser.addActionListener(createButtonListener);
    }

    public void setLogoutButtonListener(ActionListener logoutButtonListener) {
        logout.addActionListener(logoutButtonListener);
    }

    public JList getList() {
        return users;
    }

}

