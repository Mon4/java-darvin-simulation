package model;

import java.io.Serializable;
import java.util.Objects;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Vector2d implements Serializable {
    private final int x;
    private final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {return x;}

    public int getY() {
        return y;
    }
    public boolean precedes(Vector2d other){
        return (this.x <= other.x) && (this.y <= other.y);
    }

    public boolean follows(Vector2d other){ return (this.x >= other.x) && (this.y >= other.y); }

    public Vector2d add(Vector2d other){ return new Vector2d(this.x + other.x, this.y + other.y); }

    public Vector2d subtract(Vector2d other){ return new Vector2d(this.x - other.x, this.y - other.y); }

    public Vector2d upperRight(Vector2d other){
        return new Vector2d(max(this.x, other.x), max(this.y, other.y));
    }

    public Vector2d lowerLeft(Vector2d other){
        return new Vector2d(min(this.x, other.x), min(this.y, other.y));
    }

    public Vector2d opposite(){
        return new Vector2d(-this.x, -this.y);
    }

    @Override
    public String toString(){
        String coords = "(" + String.valueOf(getX()) + ", " + String.valueOf(getY()) + ")";
        return coords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2d vector2d = (Vector2d) o;
        return x == vector2d.x && y == vector2d.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
