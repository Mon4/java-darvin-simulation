package model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Simulation implements Runnable{
    private int currentAnimalIndex = 0;
    private final WorldMap map;
    private final int newGrass;


    public Simulation(WorldMap map, int newGrass) {
        this.map = map;
        this.newGrass = newGrass;
    }

    public void run(){

        Map<Vector2d, Animal> animals = map.getAnimals();

        while(true){
            //2. animals moving
//            for(int i=0; i<animals.size(); i++) {
//                Animal currentAnimal = animals.get(currentAnimalIndex);
//                this.map.move(currentAnimal);
//                this.currentAnimalIndex = (currentAnimalIndex + 1) % animals.size();
//                currentAnimal.changeEnergy(-10);
//
//            }
            //5. adding new grasses
            this.map.addGrasses(10);
            try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }



        }
    }
}