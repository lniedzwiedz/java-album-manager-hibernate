package pl.edu.agh.mwo.hibernate.filealbummanager.result.account;

public class AccountDeleteResult {

    private final AccountDeleteStatus status;
    private final String userName;

    public AccountDeleteResult(AccountDeleteStatus status, String userName) {
        this.status = status;
        this.userName = userName;
    }

    public AccountDeleteStatus getStatus() {
        return status;
    }

    public String getUserName() {
        return userName;
    }
}