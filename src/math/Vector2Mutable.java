package math;

public class Vector2Mutable implements Vector2 {

    private float x;
    private float y;

    public Vector2Mutable() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2Mutable(float v) {
        this.x = v;
        this.y = v;
    }

    public Vector2Mutable(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2Mutable(Vector2 v) {
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
        this.x += x;
        this.y += y;
        return this;
    }

    @Override
    public Vector2 sub(float x, float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    @Override
    public Vector2 mul(float x, float y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    @Override
    public Vector2 div(float x, float y) {
        this.x /= x;
        this.y /= y;
        return this;
    }

    @Override
    public Vector2 set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    @Override
    public String toString() {
        return String.format("(%.3f, %.3f)", x, y);
    }

}
