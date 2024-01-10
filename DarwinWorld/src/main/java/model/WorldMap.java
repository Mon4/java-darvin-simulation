package model;

import java.util.Collection;

public interface WorldMap {
    int getId();
    boolean place(Animal animal) throws PositionAlreadyOccupiedException;
//    void move(Animal animal, MoveDirection direction);
    boolean isOccupied(Vector2d position);
    WorldElement objectAt(Vector2d position);
    void move(Animal animal, int direction);

    public Collection<WorldElement> getElements();

//    public abstract Boundary getCurrentBounds();
    public void addObserver(MapChangeListener observer);
    public abstract Boundary getCurrentBounds();


}
