package model.builder;

import model.Account;

import java.util.Date;

public class AccountBuilder {
    private Account account;

    public AccountBuilder() {
        account = new Account();
    }

    public AccountBuilder setType(String type) {
        account.setType(type);
        return this;
    }

    public AccountBuilder setId(Long id) {
        account.setId(id);
        return this;
    }

    public AccountBuilder setBalance(double balance) {
        account.setBalance(balance);
        return this;
    }

    public AccountBuilder setBirthday(Date birthday) {
        account.setBirthday(birthday);
        return this;
    }

    public Account build() {
        return account;
    }
}
