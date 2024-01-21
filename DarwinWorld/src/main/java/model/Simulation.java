package model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Simulation implements Runnable{
    private final WorldMap map;
    private final int newGrass;


    public Simulation(WorldMap map, int newGrass) {
        this.map = map;
        this.newGrass = newGrass;
    }

    public void run(){
        //1. remove dead

        while(true){
        //2. animals moving
        Map<Vector2d, LinkedList<Animal>> animals = map.getAnimals();
        Map<Vector2d, LinkedList<Animal>> copiedAnimals = new HashMap<>(animals);
        for(Vector2d v : copiedAnimals.keySet()) {
            LinkedList<Animal> animalsList = copiedAnimals.get(v);
            for(Animal animal : animalsList){
                try {
                    map.move(animal);
                    animal.changeEnergy(-10);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        //3. eat grass

        //4. procrastinate

        //5. adding new grasses
        try {
            this.map.addGrasses(newGrass);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        }
    }
}