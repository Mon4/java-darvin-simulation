package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Animal implements WorldElement{
    private MapDirection currentForwarding;
    private Vector2d position;
    private int energy;
    private List<Integer> dna;
    private int dnaIndex;
    private int childrenNumber;
    private int lifeSpan;
    public void changeEnergy(int level) {this.energy += level;}



    //animal to map ??
    public Animal(Vector2d position) {
        this.currentForwarding = MapDirection.values()[new Random().nextInt(MapDirection.values().length)];
        this.position = position;
        this.dna = new ArrayList<>(List.of(0, 1));
        this.dnaIndex = new Random().nextInt(dna.size());
    }

    // regular animal
    public Animal(Vector2d position, int newAnimalEnergy) {
        this.currentForwarding = MapDirection.values()[new Random().nextInt(MapDirection.values().length)];
        this.position = position;
        this.energy = newAnimalEnergy;
        this.dna = new ArrayList<>(List.of(0, 1));
        this.dnaIndex = new Random().nextInt(dna.size());
    }

    // new born animal
    public Animal(Vector2d position, int energy, List<Integer> dna) {
        this.currentForwarding = MapDirection.values()[new Random().nextInt(MapDirection.values().length)];
        this.position = position;
        this.energy = 50;
        this.dna = dna;
        this.dnaIndex = new Random().nextInt(dna.size());
    }

    public void move(Map map) {
        int direction = this.dna.get(this.dnaIndex);
        for(int i = 0; i < direction; i ++) this.currentForwarding = currentForwarding.next();  // change forwarding

        Vector2d positionTempForward = position.add(currentForwarding.toUnitVector());

        if(positionTempForward.getY() > map.up || positionTempForward.getY() < map.down){
            for(int i = 0; i < 4; i ++) this.currentForwarding = currentForwarding.next();  // changes forwarding to opposite
            position = position.add(currentForwarding.toUnitVector());}
        else if(positionTempForward.getX() < map.left)
            position = new Vector2d(map.right, positionTempForward.getY());
        else if(positionTempForward.getX() > map.right)
            position = new Vector2d(map.left, positionTempForward.getY());
        else
            position = positionTempForward;

        if (this.dnaIndex == dna.size()-1)
            this.dnaIndex = 0;
        else
            this.dnaIndex++;
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
