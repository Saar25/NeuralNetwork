package math;

public interface Vector2 {

    static Vector2 immutable(float x, float y) {
        return new Vector2Immutable(x, y);
    }

    static Vector2 mutable(float x, float y) {
        return new Vector2Mutable(x, y);
    }

    float getX();

    float getY();

    Vector2 add(float x, float y);

    Vector2 sub(float x, float y);

    Vector2 mul(float x, float y);

    Vector2 div(float x, float y);

    Vector2 set(float x, float y);

    Vector2 copy();

    default Vector2 add(Vector2 v) {
        return add(v.getX(), v.getY());
    }

    default Vector2 sub(Vector2 v) {
        return sub(v.getX(), v.getY());
    }

    default Vector2 mul(Vector2 v) {
        return mul(v.getX(), v.getY());
    }

    default Vector2 div(Vector2 v) {
        return div(v.getX(), v.getY());
    }

    default Vector2 set(Vector2 v) {
        return set(v.getX(), v.getY());
    }

    default Vector2 add(float v) {
        return add(v, v);
    }

    default Vector2 sub(float v) {
        return sub(v, v);
    }

    default Vector2 mul(float v) {
        return mul(v, v);
    }

    default Vector2 div(float v) {
        return div(v, v);
    }

    default Vector2 set(float v) {
        return set(v, v);
    }

    default Vector2 setX(float x) {
        return set(x, getY());
    }

    default Vector2 setY(float y) {
        return set(getX(), y);
    }

    default float dot(Vector2 v) {
        return getX() * v.getX() + getY() + v.getY();
    }

}
