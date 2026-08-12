package pl.edu.agh.mwo.hibernate.filealbummanager.result.account;

import pl.edu.agh.mwo.hibernate.filealbummanager.status.account.LogoutStatus;

public class LogoutResult {

    private final LogoutStatus status;
    private final String userName;

    public LogoutResult(LogoutStatus status, String userName) {
        this.status = status;
        this.userName = userName;
    }

    public LogoutStatus getStatus() {
        return status;
    }

    public String getUserName() {
        return userName;
    }
}