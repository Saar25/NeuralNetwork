package math;

public class Vector2Immutable implements Vector2 {

    private final float x;
    private final float y;

    public Vector2Immutable() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2Immutable(float v) {
        this.x = v;
        this.y = v;
    }

    public Vector2Immutable(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2Immutable(Vector2 v) {
        this.x = v.getX();
        this.y = v.getY();
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public Vector2 add(float x, float y) {
        if (x == 0 && y == 0) return this;
        return new Vector2Immutable(this.x + x, this.y + y);
    }

    @Override
    public Vector2 sub(float x, float y) {
        if (x == 0 && y == 0) return this;
        return new Vector2Immutable(this.x - x, this.y - y);
    }

    @Override
    public Vector2 mul(float x, float y) {
        if (x == 1 && y == 1) return this;
        return new Vector2Immutable(this.x * x, this.y * y);
    }

    @Override
    public Vector2 div(float x, float y) {
        if (x == 1 && y == 1) return this;
        return new Vector2Immutable(this.x / x, this.y / y);
    }

    @Override
    public Vector2 set(float x, float y) {
        return new Vector2Immutable(x, y);
    }

    @Override
    public Vector2 copy() {
        return this;
    }

    @Override
    public String toString() {
        return String.format("(%.3f, %.3f)", x, y);
    }
}
