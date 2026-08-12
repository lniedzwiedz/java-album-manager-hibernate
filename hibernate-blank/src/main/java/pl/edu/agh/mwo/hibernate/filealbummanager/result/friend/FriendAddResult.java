package pl.edu.agh.mwo.hibernate.filealbummanager.result.friend;

public class FriendAddResult {

    private final FriendAddStatus status;
    private final String userName;

    public FriendAddResult(FriendAddStatus status, String userName) {
        this.status = status;
        this.userName = userName;
    }

    public FriendAddStatus getStatus() {
        return status;
    }

    public String getUserName() {
        return userName;
    }
}