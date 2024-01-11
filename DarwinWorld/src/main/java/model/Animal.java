package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Animal implements WorldElement{
    private MapDirection currentForwarding;
    private Vector2d position;
    private int energy;
    private List<Integer> dna;
    private int dnaIndex;
    private int childrenNumber;
    private int lifeSpan;

    public List<Integer> getDna() {
        return dna;
    }

    public int getDnaIndex() {
        return dnaIndex;
    }

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
        this.dna = new ArrayList<>(List.of(0, 1));
    }
    public Animal(Vector2d position, int energy, List<Integer> dna) {
        this.currentForwarding = MapDirection.NORTH;
        this.position = position;
        this.energy = energy;
        this.dna = dna;
    }


    public void move(Map map) {
        int up = map.getCurrentBounds().upperRight().getY(); //to do prettier
        int down = map.getCurrentBounds().lowerLeft().getY();
        int left = map.getCurrentBounds().lowerLeft().getX();
        int right = map.getCurrentBounds().upperRight().getX();

        int direction = this.dna.get(this.dnaIndex);
        for(int i = 0; i < direction; i ++) this.currentForwarding = currentForwarding.next();  // change forwarding

        Vector2d positionTempForward = position.add(currentForwarding.toUnitVector());

        if(positionTempForward.getY() > up || positionTempForward.getY() < down){
            for(int i = 0; i < 4; i ++) this.currentForwarding = currentForwarding.next();  // change forwarding opposite
            position = position.add(currentForwarding.toUnitVector());}
        else if(positionTempForward.getX() < left)
            position = new Vector2d(right, positionTempForward.getY());
        else if(positionTempForward.getX() > right)
            position = new Vector2d(left, positionTempForward.getY());
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
