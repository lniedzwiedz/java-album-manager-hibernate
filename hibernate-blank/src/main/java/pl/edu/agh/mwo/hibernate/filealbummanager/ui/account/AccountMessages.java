package pl.edu.agh.mwo.hibernate.filealbummanager.ui.account;

import pl.edu.agh.mwo.hibernate.filealbummanager.ui.ConfirmationOption;

public final class AccountMessages {

    private AccountMessages() {
    }

    public static final String SELECT_LOGIN_OR_CREATE =
            "Choose an option: "
                    + LoginOption.LOGIN.getValue()
                    + " - log in, "
                    + LoginOption.CREATE_ACCOUNT.getValue()
                    + " - create a new account";

    public static final String SELECT_CREATE_RETRY =
            "What would you like to do? "
                    + LoginOption.CREATE_ACCOUNT.getValue()
                    + " - create a new account, "
                    + LoginOption.TRY_AGAIN.getValue()
                    + " - try logging in again";

    public static final String LOGIN_USERNAME =
            "Log in. Please enter your username: ";

    public static final String CREATE_ACCOUNT_USERNAME =
            "Create a new account. Please choose a username: ";

    public static final String WELCOME =
            "Welcome %s";

    public static final String USER_NOT_FOUND =
            "User not found.";

    public static final String USER_NOT_FOUND_RETRY =
            "User not found. Would you like to try again or create a new account?";

    public static final String USER_DOES_NOT_EXIST =
            "User %s does not exist.";

    public static final String WELCOME_ACCOUNT_CREATED =
            "Welcome, %s! Your new account is ready.";

    public static final String ACCOUNT_EXISTS_AUTO_LOGIN =
            "Welcome back! Your account already exists, so you have been logged in automatically.";

    public static final String USERS_HEADER =
            "### Users";

    public static final String CONFIRM_DELETE_ACCOUNT =
            "Are you sure you want to permanently delete your account? "
                    + ConfirmationOption.YES.getValue()
                    + "-yes, "
                    + ConfirmationOption.NO.getValue()
                    + "-no";

    public static final String GOODBYE =
            "Goodbye %s!";

    public static final String ACCOUNT_NOT_DELETED =
            "Your account has not been deleted.";

    public static final String CONFIRM_LOGOUT =
            "Are you sure you want to log out? "
                    + ConfirmationOption.YES.getValue()
                    + "-yes, "
                    + ConfirmationOption.NO.getValue()
                    + "-no";
}