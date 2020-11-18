package database;

import java.util.*;

import static database.Constants.Rights.*;
import static database.Constants.Roles.*;

public class Constants {

    public static class Schemas {
        public static final String TEST = "test_bank";
        public static final String PRODUCTION = "bank";

        public static final String[] SCHEMAS = new String[]{TEST, PRODUCTION};
    }

    public static class Tables {
        public static final String CLIENT = "client";
        public static final String ACCOUNT = "account";
        public static final String USER = "user";
        public static final String ROLE = "role";
        public static final String RIGHT = "right";
        public static final String ROLE_RIGHT = "role_right";
        public static final String USER_ROLE = "user_role";

        public static final String[] ORDERED_TABLES_FOR_CREATION = new String[]{USER, ROLE, RIGHT, ROLE_RIGHT, USER_ROLE, CLIENT, ACCOUNT};
    }

    public static class Roles {
        public static final String ADMINISTRATOR = "administrator";
        public static final String EMPLOYEE = "employee";

        public static final String[] ROLES = new String[]{ADMINISTRATOR, EMPLOYEE};
    }

    public static class Rights {
        public static final String CREATE_USER = "create_user";
        public static final String DELETE_USER = "delete_user";
        public static final String UPDATE_USER = "update_user";
        public static final String VIEW_USER = "view_user";
        public static final String GENERATE_REPORT = "generate_report";

        public static final String ADD_TO_CLIENT = "add_to_client";
        public static final String UPDATE_CLIENT = "update_client";
        public static final String VIEW_CLIENT = "view_client";

        public static final String CREATE_CLIENT_ACCOUNT = "create_client_account";
        public static final String UPDATE_CLIENT_ACCOUNT = "update_client_account";
        public static final String VIEW_CLIENT_ACCOUNT = "view_client_account";
        public static final String DELETE_CLIENT_ACCOUNT = "delete_client_account";
        public static final String TRANSFER_MONEY = "transfer_money";
        public static final String PROCESS_BILL = "process_bill";

        public static final String[] RIGHTS = new String[]{CREATE_USER, DELETE_USER, UPDATE_USER, VIEW_USER, GENERATE_REPORT, ADD_TO_CLIENT, UPDATE_CLIENT, VIEW_CLIENT, CREATE_CLIENT_ACCOUNT, UPDATE_CLIENT_ACCOUNT, VIEW_CLIENT_ACCOUNT, DELETE_CLIENT_ACCOUNT, TRANSFER_MONEY, PROCESS_BILL};
    }

    public static Map<String, List<String>> getRolesRights() {
        Map<String, List<String>> ROLES_RIGHTS = new HashMap<>();
        for (String role : ROLES) {
            ROLES_RIGHTS.put(role, new ArrayList<>());
        }
        ROLES_RIGHTS.get(ADMINISTRATOR).addAll(Arrays.asList(CREATE_USER, DELETE_USER, UPDATE_USER, VIEW_USER, GENERATE_REPORT));

        ROLES_RIGHTS.get(EMPLOYEE).addAll(Arrays.asList(ADD_TO_CLIENT, UPDATE_CLIENT, VIEW_CLIENT, CREATE_CLIENT_ACCOUNT, UPDATE_CLIENT_ACCOUNT, VIEW_CLIENT_ACCOUNT, DELETE_CLIENT_ACCOUNT, TRANSFER_MONEY, PROCESS_BILL));

        return ROLES_RIGHTS;
    }

}
