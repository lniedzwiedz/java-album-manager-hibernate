package pl.edu.agh.mwo.hibernate.filealbummanager.result.account;

public class AuthenticationResult {

    private final AuthenticationStatus status;
    private final String userName;

    public AuthenticationResult(AuthenticationStatus status, String userName) {
        this.status = status;
        this.userName = userName;
    }

    public AuthenticationStatus getStatus() {
        return status;
    }

    public String getUserName() {
        return userName;
    }
}