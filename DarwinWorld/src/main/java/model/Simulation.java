package model;


import java.util.ArrayList;

public class Simulation implements Runnable{
    private final ArrayList<Animal> animals = new ArrayList<>();
    private final ArrayList<Integer> directions;
    private int currentAnimalIndex = 0;
    private WorldMap map;


    public ArrayList<Animal> getAnimals() {
        return animals;
    }
    public Simulation(ArrayList<Vector2d> positions, ArrayList<Integer> moves, WorldMap map) {
        this.directions = moves;
        this.map = map;

        for (Vector2d pos : positions) {
            Animal animal = new Animal(pos);
            animals.add(animal);
        }
    }

    public void run(){
        for(Animal animal : this.animals){
            try {
                this.map.place(animal);
            } catch (PositionAlreadyOccupiedException e) {
                throw new RuntimeException(e);
            }
        }

        if (this.directions != null  && this.map != null){
            for (int direction : directions){
                Animal currentAnimal = animals.get(currentAnimalIndex);
                this.map.move(currentAnimal, direction);
                this.currentAnimalIndex = (currentAnimalIndex + 1) % animals.size();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}