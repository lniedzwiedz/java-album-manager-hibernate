package pl.edu.agh.mwo.hibernate.filealbummanager.ui;

public enum BinaryOption {

    YES(1),
    NO(2);

    private final int value;

    BinaryOption(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static BinaryOption fromInt(int value) {
        for (BinaryOption option : BinaryOption.values()) {
            if (option.value == value)
                return option;
        }
        return null;
    }
}