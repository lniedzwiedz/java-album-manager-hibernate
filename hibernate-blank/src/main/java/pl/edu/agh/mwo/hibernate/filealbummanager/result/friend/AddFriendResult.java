package pl.edu.agh.mwo.hibernate.filealbummanager.result.friend;

import pl.edu.agh.mwo.hibernate.filealbummanager.status.friend.AddFriendStatus;

public class AddFriendResult {

    private final AddFriendStatus status;
    private final String userName;

    public AddFriendResult(AddFriendStatus status, String userName) {
        this.status = status;
        this.userName = userName;
    }

    public AddFriendStatus getStatus() {
        return status;
    }

    public String getUserName() {
        return userName;
    }
}