package pl.edu.agh.mwo.hibernate.filealbummanager.ui;

public enum ConfirmationOption {

    YES(1),
    NO(2);

    private final int value;

    ConfirmationOption(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ConfirmationOption fromInt(int value) {
        for (ConfirmationOption option : values()) {
            if (option.value == value) {
                return option;
            }
        }
        return null;
    }
}