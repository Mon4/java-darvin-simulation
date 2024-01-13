package model;


import java.util.ArrayList;

public class Simulation implements Runnable{
    private final ArrayList<Animal> animals;
    private int currentAnimalIndex = 0;
    private final WorldMap map;


    public Simulation(WorldMap map) {
        this.map = map;
        this.animals = new ArrayList<>(map.getAnimals().values());
    }

    public void run(){
        for(Animal animal : this.animals){
            try {
                this.map.place(animal);
            } catch (PositionAlreadyOccupiedException e) {
                throw new RuntimeException(e);
            }
        }

        while(true){
            Animal currentAnimal = animals.get(currentAnimalIndex);
            this.map.move(currentAnimal);
            this.currentAnimalIndex = (currentAnimalIndex + 1) % animals.size();
            currentAnimal.changeEnergy(-10);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}