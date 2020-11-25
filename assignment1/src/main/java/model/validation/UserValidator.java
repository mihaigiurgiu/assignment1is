package model.validation;

import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private static final String EMAIL_VALIDATION_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private static final int MIN_PASSWORD_LENGTH = 8;

    public List<String> getErrors() {
        return errors;
    }

    private final List<String> errors;

    private final User user;

    public UserValidator(User user) {
        this.user = user;
        errors = new ArrayList<>();
    }

    public boolean validate() {
        validateUsername(user.getUsername());
        validatePassword(user.getPassword());
        return errors.isEmpty();
    }

    public boolean validateUsername(String username) {
        if (!Pattern.compile(EMAIL_VALIDATION_REGEX).matcher(username).matches()) {
            errors.add("Invalid email!");
            return false;
        }
        return true;
    }

    public boolean validatePassword(String password) {
        boolean check1, check2, check3;
        check1 = check2 = check3 = true;

        if (password.length() < MIN_PASSWORD_LENGTH) {
            errors.add("Password too short!");
            check1 = false;
        }
        if (!containsSpecialCharacter(password)) {
            errors.add("Password must contain at least one special character!");
            check2 = false;
        }
        if (!containsDigit(password)) {
            errors.add("Password must contain at least one number!");
            check3 = false;
        }
        return check1 && check2 && check3;
    }

    private boolean containsSpecialCharacter(String s) {
        if (s == null || s.trim().isEmpty()) {
            return false;
        }
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(s);
        return m.find();
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
