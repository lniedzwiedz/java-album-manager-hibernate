package pl.edu.agh.mwo.hibernate.filealbummanager.result.friend;

import pl.edu.agh.mwo.hibernate.filealbummanager.status.friend.DeleteFriendStatus;

public class DeleteFriendResult {

    private final DeleteFriendStatus status;
    private final String userName;

    public DeleteFriendResult(DeleteFriendStatus status, String userName) {
        this.status = status;
        this.userName = userName;
    }

    public DeleteFriendStatus getStatus() {
        return status;
    }

    public String getUserName() {
        return userName;
    }
}