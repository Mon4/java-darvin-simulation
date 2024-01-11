package model;


import java.util.ArrayList;

public class Simulation implements Runnable{
    private final ArrayList<Animal> animals = new ArrayList<>();
    private int currentAnimalIndex = 0;
    private WorldMap map;


    public Simulation(ArrayList<Vector2d> positions, WorldMap map) {
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


//        for (int direction : directions){
        while(true){
            Animal currentAnimal = animals.get(currentAnimalIndex);
            this.map.move(currentAnimal);
            this.currentAnimalIndex = (currentAnimalIndex + 1) % animals.size();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}