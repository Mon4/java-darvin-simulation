package model;

import java.util.LinkedList;

public interface WorldMap {
    java.util.Map<Vector2d, LinkedList<Animal>> getAnimals();
    boolean isOccupied(Vector2d position);
    WorldElement objectAt(Vector2d position);
    public void move(Animal animal);
    public void addObserver(MapChangeListener observer);
    public abstract Boundary getCurrentBounds();
    public void addGrasses(int grassNumber);
    public void removeAnimal(Animal animal);
    public java.util.Map<Vector2d, Grass> getGrasses();

}
