package pl.edu.agh.mwo.hibernate.filealbummanager.ui.option;

public enum LoginOption {

    LOGIN(1),
    CREATE_ACCOUNT(2);

    private final int value;

    LoginOption(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static LoginOption fromInt(int value) {
        for (LoginOption option : values()) {
            if (option.value == value) {
                return option;
            }
        }
        return null;
    }
}