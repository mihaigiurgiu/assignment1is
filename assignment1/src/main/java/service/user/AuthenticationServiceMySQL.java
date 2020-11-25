package service.user;

import model.Role;
import model.User;
import model.builder.UserBuilder;
import model.validation.Notification;
import model.validation.UserValidator;
import repository.security.RightsRolesRepository;
import repository.user.AuthenticationException;
import repository.user.UserRepository;

import java.security.MessageDigest;
import java.util.Collections;
import java.util.List;

import static database.Constants.Roles.EMPLOYEE;
import static database.Constants.Roles.ADMINISTRATOR;

public class AuthenticationServiceMySQL implements AuthenticationService {

    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;

    public AuthenticationServiceMySQL(UserRepository userRepository, RightsRolesRepository rightsRolesRepository) {
        this.userRepository = userRepository;
        this.rightsRolesRepository = rightsRolesRepository;
    }

    @Override
    public Notification<Boolean> register(String username, String password) {

        if (username.equals(password) && username.equals("admin")) {
            Role userRole = rightsRolesRepository.findRoleByTitle(ADMINISTRATOR);
            User user = new UserBuilder()
                    .setUsername(username)
                    .setPassword(password)
                    .setRoles(Collections.singletonList(userRole))
                    .build();
            Notification<Boolean> userRegisterNotification = new Notification<>();
            userRegisterNotification.setResult(userRepository.save(user));
            return userRegisterNotification;
        }
        else {
            Role userRole = rightsRolesRepository.findRoleByTitle(EMPLOYEE);
            User user = new UserBuilder()
                    .setUsername(username)
                    .setPassword(password)
                    .setRoles(Collections.singletonList(userRole))
                    .build();

            UserValidator userValidator = new UserValidator(user);
            boolean userValid = userValidator.validate();
            Notification<Boolean> userRegisterNotification = new Notification<>();

            if (!userValid) {
                userValidator.getErrors().forEach(userRegisterNotification::addError);
                userRegisterNotification.setResult(Boolean.FALSE);
            } else {
                user.setPassword(encodePassword(password));
                userRegisterNotification.setResult(userRepository.save(user));
            }
            return userRegisterNotification;
        }
    }

    @Override
    public Notification<User> login (String username, String password) throws AuthenticationException {
        return userRepository.findByUsernameAndPassword(username, encodePassword(password));
    }

    @Override
    public boolean logout (User user){
            return false;
        }

    @Override
    public void remove(User user) {
        userRepository.remove(user);
    }

    @Override
    public Notification<Boolean> updateUsername(User user, String newUsername) {
        UserValidator uv = new UserValidator(new User());
        boolean usernameValid = uv.validateUsername(newUsername);
        Notification<Boolean> updateUsernameNotification = new Notification<>();

        if(!usernameValid) {
            uv.getErrors().forEach(updateUsernameNotification::addError);
            updateUsernameNotification.setResult(Boolean.FALSE);
        }
        else {
            updateUsernameNotification.setResult(userRepository.updateUsername(user, newUsername));
        }

        return updateUsernameNotification;
    }

    @Override
    public Notification<Boolean> updatePassword(User user, String newPassword) {
        UserValidator uv = new UserValidator(new User());
        boolean passwordValid = uv.validatePassword(newPassword);
        Notification<Boolean> updatePasswordNotification = new Notification<>();

        if(!passwordValid) {
            uv.getErrors().forEach(updatePasswordNotification::addError);
            updatePasswordNotification.setResult(Boolean.FALSE);
        }
        else {
            updatePasswordNotification.setResult(userRepository.updatePassword(user, encodePassword(newPassword)));
        }

        return updatePasswordNotification;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    private String encodePassword (String password){
            if(password.equals("admin")) {
                return password;
            }
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(password.getBytes("UTF-8"));
                StringBuilder hexString = new StringBuilder();

                for (int i = 0; i < hash.length; i++) {
                    String hex = Integer.toHexString(0xff & hash[i]);
                    if (hex.length() == 1) hexString.append('0');
                    hexString.append(hex);
                }

                return hexString.toString();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

    }


