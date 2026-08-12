package pl.edu.agh.mwo.hibernate.filealbummanager.result.account;

public class LoginResult {

    private final LoginStatus status;
    private final String userName;

    public LoginResult(LoginStatus status, String userName) {
        this.status = status;
        this.userName = userName;
    }

    public LoginStatus getStatus() {
        return status;
    }

    public String getUserName() {
        return userName;
    }
}