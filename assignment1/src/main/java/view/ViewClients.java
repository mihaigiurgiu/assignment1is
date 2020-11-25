package view;

import factory.ComponentFactory;
import model.Account;
import model.Client;
import repository.EntityNotFoundException;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class ViewClients extends JFrame {

    private JPanel pane = new JPanel(new FlowLayout());
    private JTable allClients;
    private ComponentFactory componentFactory;

    public ViewClients(String asd, ComponentFactory componentFactory) throws EntityNotFoundException {
        super(asd);
        this.componentFactory = componentFactory;
        String[] columnNames = {"Id", "Name", "Id card no", "CNP", "Address", "Account type", "Balance", "Account birthday"};
        ArrayList<Client> clients = new ArrayList<Client>(componentFactory.getClientService().findAll());
        String[][] data = new String[clients.size()][8];

        for(int i = 0; i < clients.size(); i++) {
            data[i][0] = String.valueOf(clients.get(i).getId());
            data[i][1] = clients.get(i).getName();
            data[i][2] = clients.get(i).getIdentityCardNumber();
            data[i][3] = clients.get(i).getCNP();
            data[i][4] = clients.get(i).getAddress();
            Account account = componentFactory.getAccountService().findById(Long.valueOf(clients.get(i).getId()));
            data[i][5] = account.getType();
            data[i][6] = String.valueOf(account.getBalance());
            data[i][7] = String.valueOf(account.getBirthday());
         //   }

             // data[i][5] = data[i][6] = data[i][7] = "";
        }
        //}

        allClients = new JTable(data, columnNames);
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)allClients.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        JScrollPane scrollPane = new JScrollPane(allClients);
        allClients.setFillsViewportHeight(true);
        pane.add(scrollPane);
        this.add(pane);
        this.setLocationRelativeTo(null);
    }


}
