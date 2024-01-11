package model;

import java.util.*;

public class Map implements WorldMap {
    private java.util.Map<Vector2d, Animal> animals = new HashMap<>();
    private java.util.Map<Vector2d, Grass> grasses = new HashMap<>();
    private java.util.Map<Vector2d, PoisonFruit> poisonFruits = new HashMap<>();
    final private List<MapChangeListener> observers = new ArrayList<>();
    final private int width;
    final private int height;
    final private int grassNumber;
    final private int animalsNumber;

    public Map(int grassNumber, int animalsNumber, int width, int height) {
        super();
        this.height = height;
        this.width = width;
        this.grassNumber = grassNumber;
        this.animalsNumber = animalsNumber;
        Random rand = new Random();
        int x, y;
        for (int i = 0; i < grassNumber; i++) {
            do {
                x = rand.nextInt(0, width);  // trzeba zmienić, bo trawki mają się robić na równiku
                y = rand.nextInt(0, height);
            } while (isOccupied(new Vector2d(x, y)));

            Grass grass = new Grass(new Vector2d(x, y));
            grasses.put(new Vector2d(x, y), grass);
        }

        for (int i = 0; i < animalsNumber; i++) {
            x = rand.nextInt(0, width);
            y = rand.nextInt(0, height);

            Animal animal = new Animal(new Vector2d(x, y));
            animals.put(new Vector2d(x, y), animal);
        }

    }

    public void mapChanged(String message){
        for(MapChangeListener observer : observers){
            observer.mapChanged(this, message);
        }
    }

    @Override
    public boolean place(Animal animal){
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
    public void addObserver(MapChangeListener observer) {
        observers.add(observer);
    }
    @Override
    public Boundary getCurrentBounds() {
        return new Boundary(new Vector2d(0, 0), new Vector2d(width, height));
    }


}
