package model;

import java.util.*;

import static java.lang.Math.sqrt;

public class Map implements WorldMap {
    protected java.util.Map<Vector2d, Animal> animals = new HashMap<>();
    protected java.util.Map<Vector2d, Grass> grasses = new HashMap<>();
    protected java.util.Map<Vector2d, PoisonFruit> poisonFruits = new HashMap<>();
    final private List<MapChangeListener> observers = new ArrayList<>();
    private int grassNumber;

    public Map(int grassNumber) {
        super();
        this.grassNumber = grassNumber;
        Random rand = new Random();
        int x, y;
        for (int i = 0; i < grassNumber; i++) {
            do {
                x = rand.nextInt(0, (int) (sqrt(grassNumber) * 10));
                y = rand.nextInt(0, (int) (sqrt(grassNumber) * 10));
            } while (isOccupied(new Vector2d(x, y)));
            Grass grass = new Grass(new Vector2d(x, y));
            grasses.put(new Vector2d(x, y), grass);
        }
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
            animals.put(position, animal);
            mapChanged("animal was placed on position: " + position.toString());
            return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return false;
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if (grasses.get(position) != null){
            return grasses.get(position);
        }
        else if (animals.get(position) != null){
            return animals.get(position);
        }
        else if (poisonFruits.get(position) != null){
            return poisonFruits.get(position);
        }
        return null;
    }

    @Override
    public void move(Animal animal) {
        Vector2d oldPosition = animal.getPosition();
        animals.remove(oldPosition);
        animal.move(this);
        animals.put(animal.getPosition(), animal);
        mapChanged("Animal was moved from: " + oldPosition.toString() + " to position: "
                + animal.getPosition().toString());
    }

    @Override
    public Collection<WorldElement> getElements() {
        return null;
    }

    @Override
    public void addObserver(MapChangeListener observer) {
        observers.add(observer);
    }
    @Override
    public Boundary getCurrentBounds() {
        int width = 0;
        int height = 0;
        for (Vector2d position : grasses.keySet()) {
            if (position.getX() > width)
                width = position.getX();
            if (position.getY() > height)
                height = position.getY();
        }
        for (Vector2d position : animals.keySet()) {
            if (position.getX() > width)
                width = position.getX();
            if (position.getY() > height)
                height = position.getY();
        }
        return new Boundary(new Vector2d(0, 0), new Vector2d(width, height));
    }


}
