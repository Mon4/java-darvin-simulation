package model;

import java.util.Collection;

public interface WorldMap {
    java.util.Map<Vector2d, Animal> getAnimals();

    boolean place(Animal animal) throws PositionAlreadyOccupiedException;
//    void move(Animal animal, MoveDirection direction);
    boolean isOccupied(Vector2d position);
    WorldElement objectAt(Vector2d position);
    void move(Animal animal);
    public void addObserver(MapChangeListener observer);
    public abstract Boundary getCurrentBounds();


}
