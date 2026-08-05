package pl.edu.agh.mwo.hibernate.filealbummanager.ui.account;

import pl.edu.agh.mwo.hibernate.filealbummanager.ui.ConfirmationOption;

public final class AccountMessages {

    private AccountMessages() {
    }

    public static final String SELECT_LOGIN_OR_CREATE =
            "select option: "
                    + LoginOption.LOGIN.getValue()
                    + " - log in, "
                    + LoginOption.CREATE_ACCOUNT.getValue()
                    + " - create account";

    public static final String SELECT_CREATE_RETRY =
            "select option: "
                    + LoginOption.CREATE_ACCOUNT.getValue()
                    + " - create account, "
                    + LoginOption.TRY_AGAIN.getValue()
                    + " - try again";

    public static final String LOGIN_USERNAME =
            "log in. user name: ";

    public static final String CREATE_ACCOUNT_USERNAME =
            "Create account. user name: ";

    public static final String WELCOME =
            "Welcome %s";

    public static final String USER_NOT_FOUND =
            "User not found.";

    public static final String USER_NOT_FOUND_RETRY =
            "User not found. Do you want to try login again or create account?";

    public static final String USER_DOES_NOT_EXIST =
            "User %s does not exist.";

    public static final String WELCOME_ACCOUNT_CREATED =
            "Welcome %s. User account created.";

    public static final String ACCOUNT_EXISTS_AUTO_LOGIN =
            "Account exists. You have been automatically logged in.";

    public static final String USERS_HEADER =
            "### Users";

    public static final String CONFIRM_DELETE_ACCOUNT =
            "Are you sure you want to remove yourself from database? "
                    + ConfirmationOption.YES.getValue()
                    + "-yes, "
                    + ConfirmationOption.NO.getValue()
                    + "-no";

    public static final String GOODBYE =
            "Goodbye %s!";

    public static final String ACCOUNT_NOT_DELETED =
            "Account has not been deleted. Wise choice!";

    public static final String CONFIRM_LOGOUT =
            "Are you sure you want to logout? "
                    + ConfirmationOption.YES.getValue()
                    + "-yes, "
                    + ConfirmationOption.NO.getValue()
                    + "-no";
}