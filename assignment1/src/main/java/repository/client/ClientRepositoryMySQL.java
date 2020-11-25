package repository.client;

import model.Account;
import model.Client;
import model.builder.ClientBuilder;
import model.validation.Notification;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySQL;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepositoryMySQL implements ClientRepository {

    private final Connection connection;

    public ClientRepositoryMySQL(Connection connection) {
        this.connection = connection;
        this.accountRepository = new AccountRepositoryMySQL(connection);
    }
    private AccountRepository accountRepository;

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from client";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                clients.add(getClientFromResultSet(rs));
            }
        } catch (SQLException | EntityNotFoundException e) {
            e.printStackTrace();
        }

        return clients;
    }

    @Override
    public boolean updateAccount(Long id, Long accountId) throws EntityNotFoundException {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("UPDATE client SET client.account = " + accountId + " WHERE client.id = "+ id);
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Client findById(Long id) throws EntityNotFoundException {
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from client where id=" + id;
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return getClientFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(id, Client.class.getSimpleName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(id, Client.class.getSimpleName());
        }
    }

    @Override
    public boolean save(Client client) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO client values (null, ?, ?, ?, ?, null)");
            insertStatement.setString(1, client.getName());
            insertStatement.setString(2, client.getIdentityCardNumber());
            insertStatement.setString(3, client.getCNP());
            insertStatement.setString(4, client.getAddress());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateName(Long id, String name) throws EntityNotFoundException {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("UPDATE client SET client.name = '"+ name+ "' WHERE client.id = "+ id);
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateIdentityCardNumber(Long id, String icn) throws EntityNotFoundException {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("UPDATE client SET client.identityCardNumber = '" + icn + "' WHERE client.id = "+ id);
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateCNP(Long id, String CNP) throws EntityNotFoundException {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("UPDATE client SET client.CNP = '" + CNP + "' WHERE client.id = "+ id);
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateAddress(Long id, String address) throws EntityNotFoundException {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("UPDATE client SET client.address = '" + address + "' WHERE client.id = "+ id);
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    //se renunta la  setAccount si se pune cand fac contu din service altfel nu stiu de vazut la pefeu
    private Client getClientFromResultSet(ResultSet rs) throws SQLException, EntityNotFoundException {
       return new ClientBuilder()
                .setId(rs.getLong("id"))
                .setName(rs.getString("name"))
                .setIdentityCardNumber(rs.getString("identityCardNumber"))
                .setCNP(rs.getString("CNP"))
                .setAddress(rs.getString("address"))
                //.setAccount(rs.getInt("account"))
                .build();
    }

    public void processUtilityBill(Long id, String drain, double amount) throws EntityNotFoundException, IOException {
        Account account = accountRepository.findById(id);
        FileWriter writer = new FileWriter(drain + "_bill_" + id + ".txt");
        if(account.getBalance() >= amount) {
            accountRepository.updateBalance(id, account.getBalance() - amount);
            writer.write(amount + " spent on " + drain + " by client " + id + ".");
        }
        else {
            writer.write("Insufficient funds!");
        }
        writer.close();
    }

}
