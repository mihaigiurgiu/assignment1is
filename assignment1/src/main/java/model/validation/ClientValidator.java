package model.validation;

import model.Account;
import model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {

    public List<String> getErrors() {
        return errors;
    }

    private final List<String> errors;

    private final Client client;

    public ClientValidator(Client client) {
        this.client = client;
        errors = new ArrayList<>();
    }

    public boolean validate() {
        validateName(client.getName());
        validateICN(client.getIdentityCardNumber());
        validateCNP(client.getCNP());
        validateAddress(client.getAddress());
        return errors.isEmpty();
    }

    public boolean validateName(String name) {
        if(containsDigit(name) || name.length() == 0){
            errors.add("Invalid name");
            return false;
        }
        return true;
    }

    public boolean validateICN(String icn) {
        if(icn.length() != 6) {
            errors.add("invalid identity card number");
            return false;
        }
        return true;
    }

    public boolean validateCNP(String cnp) {
        if(cnp.length() != 13) {
            errors.add("cnp invalid");
            return false;
        }
        return true;
    }

    public boolean validateAddress(String address) {
        if(address.length() == 0) {
            errors.add("invalid address");
            return false;
        }
        return true;
    }

    private boolean containsDigit(String s) {
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
