package model;

import java.util.ArrayList;

public class Simulation {
    private final ArrayList<Animal> animals = new ArrayList<>();
    private final ArrayList<Integer> directions = new ArrayList<>();
    private int currentAnimalIndex = 0;
//    private WorldMap map;


//    public ArrayList<Animal> getAnimals() {
//        return animals;
//    }
//    public Simulation(ArrayList<Vector2d> positions, ArrayList<MoveDirection> moves, WorldMap map) {
//        this.directions = moves;
//        this.map = map;
//
//        for (Vector2d pos : positions) {
//            Animal animal = new Animal(pos);
//            animals.add(animal);
//            try {
//                this.map.place(animal);
//            } catch (PositionAlreadyOccupiedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//    public void run(){
//        for (MoveDirection direction : directions){
//            Animal currentAnimal = animals.get(currentAnimalIndex);
//            map.move(currentAnimal, direction);
//            currentAnimalIndex = (currentAnimalIndex + 1) % animals.size();
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
}
