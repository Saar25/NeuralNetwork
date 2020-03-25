package util;

public final class Maths {

    public Maths() {
        throw new AssertionError("Cannot create instance of " + getClass().getSimpleName());
    }

    public static boolean isInside(int value, int min, int max) {
        return value >= min && value <= max;
    }

}
