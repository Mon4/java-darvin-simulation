package model;

import java.util.*;

public class Map implements WorldMap {
    private java.util.Map<Vector2d, LinkedList<Animal>> animals = new HashMap<>();
    private java.util.Map<Vector2d, Grass> grasses = new HashMap<>();
    private java.util.Map<Vector2d, PoisonFruit> poisonFruits = new HashMap<>();
    final private List<MapChangeListener> observers = new ArrayList<>();
    final private int width;
    final private int height;
    int down;
    int left;
    int up;
    int right;

    @Override
    public java.util.Map<Vector2d, LinkedList<Animal>> getAnimals() {return animals;}

    public void addGrasses(int grassNumber){
        Random rand = new Random();
        int x, y;
        for (int i = 0; i < grassNumber; i++) {
            do {
                x = rand.nextInt(0, width+1);  // trzeba zmienić, bo trawki mają się robić na równiku
                y = rand.nextInt(0, height+1);
            } while (isOccupied(new Vector2d(x, y)));

            Grass grass = new Grass(new Vector2d(x, y));
            grasses.put(new Vector2d(x, y), grass);
            mapChanged("grasses added");
        }
    }

    public void addAnimal(Vector2d vector2d, Animal animal){
        Vector2d v = animal.getPosition();
        LinkedList<Animal> animalsList = new LinkedList<Animal>();
        if (animals.get(v) != null){
            if (! animals.get(v).isEmpty())
                animalsList = animals.get(v);
        }
        animalsList.add(animal);
        animals.put(vector2d, animalsList);
    }

    public void addAnimals(int animalsNumber, int newAnimalEnergy){
        Random rand = new Random();
        int x, y;
        for (int i = 0; i < animalsNumber; i++) {
            x = rand.nextInt(0, width+1);
            y = rand.nextInt(0, height+1);

            Animal animal = new Animal(new Vector2d(x, y), newAnimalEnergy);
            addAnimal(new Vector2d(x, y), animal);
        }
    }


    public Map(int width, int height) {
        this.height = height;
        this.width = width;
        int down = 0;
        int left = 0;
        this.up = getCurrentBounds().upperRight().getY();
        this.right = getCurrentBounds().upperRight().getX();
    }

    public void removeAnimal(Animal animal){
        Vector2d v = animal.getPosition();
        animals.get(v).remove(animal);
        if (animals.get(v).isEmpty())
            animals.remove(v);
        mapChanged("removed animal");
    }

    public void mapChanged(String message){
        for(MapChangeListener observer : observers){
            observer.mapChanged(this, message);
        }
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
            return animals.get(position).getFirst();
        }
        else if (poisonFruits.get(position) != null){
            return poisonFruits.get(position);
        }
        return null;
    }

//    public int getAnimalsNumber(){
//        int counter = 0;
//        for(Vector2d v : animals.keySet()){
//            counter += animals.get(v).size();
//        }
//        return counter;
//    }
    @Override
    public void move(Animal animal) {
        Vector2d oldPosition = animal.getPosition();
        removeAnimal(animal);
        animal.move(this);
        addAnimal(animal.getPosition(), animal);
        mapChanged("animal moved");
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
