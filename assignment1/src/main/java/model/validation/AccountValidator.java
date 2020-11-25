package model.validation;

import model.Account;
import model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountValidator {

    public List<String> getErrors() {
        return errors;
    }

    private final List<String> errors;

    private final Account account;

    public AccountValidator(Account account) {
        this.account = account;
        errors = new ArrayList<>();
    }

    public boolean validate() {
        validateType(account.getType());
        validateBalance(account.getBalance());
        validateBirthday(account.getBirthday());
        return errors.isEmpty();
    }

    public boolean validateType(String type) {
        if(containsDigit(type) || type.length() == 0) {
            errors.add("what kind of type contains numbers?/empty type");
            return false;
        }
        return true;
    }

    //suppose it's impossible(just in this case)
    public boolean validateBalance(Double balance) {
        if(balance < 0){
            errors.add("negative balance");
            return false;
        }
        return true;
    }
    //suppose it's all right
    public boolean validateBirthday(Date date) {
        return true;
    }

    public boolean containsDigit(String s) {
        if (s != null && !s.isEmpty()) {
            for (char c : s.toCharArray()) {
                if (Character.isDigit(c)) {
                    return true;
                }
            }
        }
        return false;
    }
}
