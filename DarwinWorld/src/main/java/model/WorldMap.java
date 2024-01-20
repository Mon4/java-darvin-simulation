package model;

import java.util.Collection;

public interface WorldMap {
    java.util.Map<Vector2d, Animal> getAnimals();
    boolean isOccupied(Vector2d position);
    WorldElement objectAt(Vector2d position);
    void move(Animal animal);
    public void addObserver(MapChangeListener observer);
    public abstract Boundary getCurrentBounds();
    public void addGrasses(int grassNumber);

}
