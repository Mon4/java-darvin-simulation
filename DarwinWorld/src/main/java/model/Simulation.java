package model;


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
        while(true){
            //1. remove dead
            Map<Vector2d, LinkedList<Animal>> animals = map.getAnimals();
            Map<Vector2d, LinkedList<Animal>> copiedAnimals = new HashMap<>(animals);

            for(Vector2d v : copiedAnimals.keySet()) {
                LinkedList<Animal> animalsList = copiedAnimals.get(v);
                for(Animal animal : animalsList){
                        if (animal.ifDead()){
                            System.out.println("i'm dead");
                            map.removeAnimal(animal);
                        }
                    if (animalsList.isEmpty())
                        break;
                }
            }


            //2. animals moving
            animals = map.getAnimals();
            copiedAnimals = new HashMap<>(animals);
            for(Vector2d v : copiedAnimals.keySet()) {
                LinkedList<Animal> animalsList = copiedAnimals.get(v);
                for(Animal animal : animalsList){
                    try {
                        map.move(animal);
                        animal.changeEnergy(-10);
                        Thread.sleep(10);
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
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("1 ");
        }
    }
}