package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Animal implements WorldElement{
    private MapDirection currentForwarding;
    private Vector2d position;
    private int energy;
    private List<Integer> dna;

    public Animal() {
        this.currentForwarding = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
        this.energy = 100;
        this.dna = List.of(0);
    }
    public Animal(Vector2d position) {
        this.currentForwarding = MapDirection.NORTH;
        this.position = position;
        this.energy = 100;
        this.dna = new ArrayList<>(List.of(1, 0, 2));
    }
    public Animal(Vector2d position, int energy, List<Integer> dna) {
        this.currentForwarding = MapDirection.NORTH;
        this.position = position;
        this.energy = energy;
        this.dna = dna;
    }


    public void move(int direction, MoveValidator validator) {  // add going around the world
        for(int i = 0; i < direction; i ++){
            this.currentForwarding = currentForwarding.next();
            Vector2d positionTempForward = position.add(currentForwarding.toUnitVector());
            if (validator.canMoveTo(positionTempForward)){
                position = positionTempForward;}
        }
    }


    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public boolean isAt(Vector2d position) {
        return Objects.equals(this.position, position);
    }

    @Override
    public String toString() {
        return currentForwarding.toString().substring(0, 1);
    }

}
