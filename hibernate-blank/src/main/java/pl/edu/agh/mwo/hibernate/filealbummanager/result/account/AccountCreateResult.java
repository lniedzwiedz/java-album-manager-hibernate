package pl.edu.agh.mwo.hibernate.filealbummanager.result.account;

public class AccountCreateResult {

    private final AccountCreateStatus status;
    private final String userName;

    public AccountCreateResult(AccountCreateStatus status, String userName) {
        this.status = status;
        this.userName = userName;
    }

    public AccountCreateStatus getStatus() {
        return status;
    }

    public String getUserName() {
        return userName;
    }
}