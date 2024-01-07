package model;

public class PositionAlreadyOccupiedException extends Exception {
    public PositionAlreadyOccupiedException(Vector2d position) {
        super("Position: " + position.toString() + " Already Occupied Exception");
    }

}
