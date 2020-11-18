package repository.account;

import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import repository.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountRepositoryMySQL implements AccountRepository {

    private final Connection connection;

    public AccountRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                accounts.add(getAccountFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    @Override
    public Account findById(Long clientId) throws EntityNotFoundException {
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account where client_id=" + clientId;
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return getAccountFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(clientId, Account.class.getSimpleName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(clientId, Account.class.getSimpleName());
        }
    }

    @Override
    public boolean save(Long clientId, Account account) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO account values (null, ?, ?, ?, ?)");
            insertStatement.setString(1, account.getType());
            insertStatement.setDouble(2, account.getBalance());
            insertStatement.setDate(3, new java.sql.Date(account.getBirthday().getTime()));
            insertStatement.setLong(4, clientId);
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateType(Long clientId, String type) throws EntityNotFoundException {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("UPDATE account SET account.type = '" + type + "' WHERE account.client_id = "+ clientId);
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateBalance(Long clientId, double sum) throws EntityNotFoundException {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("UPDATE account SET account.balance = " + sum + " WHERE account.client_id = "+ clientId);
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateBirthday(Long clientId, Date birthday) throws EntityNotFoundException {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("UPDATE account SET account.birthday = " + birthday + " WHERE account.client_id = "+ clientId);
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeAccount(Long clientId) {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from account where client_id = " + clientId;
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Account getAccountFromResultSet(ResultSet rs) throws SQLException {
        return new AccountBuilder()
                .setId(rs.getLong("id"))
                .setType(rs.getString("type"))
                .setBalance(rs.getDouble("balance"))
                .setBirthday(new Date(rs.getDate("birthday").getTime()))
                .build();
    }

}
