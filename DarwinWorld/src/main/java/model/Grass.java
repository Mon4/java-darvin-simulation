package model;

import java.util.Objects;

public class Grass implements WorldElement {

    private Vector2d position;

    public Grass(Vector2d position) {
        this.position = position;
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public boolean isAt(Vector2d position) {

        return Objects.equals(this.position, position);
    }

    @Override
    public String toString() {
        return "*";
    }
}
