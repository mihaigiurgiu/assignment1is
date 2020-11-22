package view;

import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class AdminView extends JFrame {

    private JList users;

    private JButton editUser;
    private JButton removeUser;
    private JButton createUser;
    private JButton updateUsername;
    private JButton updatePassword;

    private JTextField tfUsername;
    private JTextField tfPassword;

    public AdminView(DefaultListModel<User> userList) throws HeadlessException {
        setSize(300, 300);
        setLocationRelativeTo(null);
        initializeFields(userList);
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        add(users);
        add(editUser);
        add(removeUser);
        add(tfUsername);
        add(tfPassword);
        add(createUser);
        add(updateUsername);
        add(updatePassword);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields(DefaultListModel<User> userList) {
        tfUsername = new JTextField();
        tfPassword = new JTextField();
        editUser = new JButton("Edit selection");
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

    public void setEditButtonListener(ActionListener editButtonListener) {
        editUser.addActionListener(editButtonListener);
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

    public JList getList() {
        return users;
    }



}

