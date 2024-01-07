package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Map implements MoveValidator, WorldMap {
    protected java.util.Map<Vector2d, Animal> animals = new HashMap<>();
    protected java.util.Map<Vector2d, Grass> grasses = new HashMap<>();
    protected java.util.Map<Vector2d, PoisonFruit> poisonFruits = new HashMap<>();
    final private List<MapChangeListener> observers = new ArrayList<>();

    @Override
    public boolean canMoveTo(Vector2d position) {  // change to check if not out of boundaries
        for (Vector2d p : animals.keySet()) {
            if (p.equals(position)) {
                return false;
            }
        }
        return true;
    }

    public void mapChanged(String message){
        for(MapChangeListener observer : observers){
            observer.mapChanged(this, message);
        }
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public boolean place(Animal animal) throws PositionAlreadyOccupiedException{
        int x = animal.getPosition().getX();
        int y = animal.getPosition().getY();

        Vector2d position = new Vector2d(x, y);
        if (canMoveTo(position)){
            animals.put(position, animal);
            mapChanged("animal was placed on position: " + position.toString());
            return true;
        }
        else {
            throw new PositionAlreadyOccupiedException(position);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return false;
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return null;
    }

    @Override
    public Collection<WorldElement> getElements() {
        return null;
    }

    @Override
    public void addObserver(MapChangeListener observer) {

    }


}
