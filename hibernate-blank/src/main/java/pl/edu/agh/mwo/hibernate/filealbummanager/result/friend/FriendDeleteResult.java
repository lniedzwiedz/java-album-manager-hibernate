package pl.edu.agh.mwo.hibernate.filealbummanager.result.friend;

public class FriendDeleteResult {

    private final FriendDeleteStatus status;
    private final String userName;

    public FriendDeleteResult(FriendDeleteStatus status, String userName) {
        this.status = status;
        this.userName = userName;
    }

    public FriendDeleteStatus getStatus() {
        return status;
    }

    public String getUserName() {
        return userName;
    }
}