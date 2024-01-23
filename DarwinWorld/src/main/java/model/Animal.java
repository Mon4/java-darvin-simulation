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
    private int genomeLen;

    @Override
    public Vector2d getPosition() {
        return this.position;
    }
    public MapDirection getCurrentForwarding() {
        return this.currentForwarding;
    }
    public int getEnergy() {
        return this.energy;
    }
    public int getGenomeLen() {return genomeLen;}
    public List<Integer> getDna() {return dna;}

    //animal to map ??
    public Animal(Vector2d position, int newAnimalEnergy, int genomeLen, List<Integer> dna) {
        this.currentForwarding = MapDirection.values()[new Random().nextInt(MapDirection.values().length)];
        this.position = position;
        this.energy = newAnimalEnergy;
        this.genomeLen = genomeLen;
        this.dna = dna;
        this.dnaIndex = new Random().nextInt(dna.size());
    }

    // regular animal
    public Animal(Vector2d position, int newAnimalEnergy, int genomeLen) {
        this.currentForwarding = MapDirection.values()[new Random().nextInt(MapDirection.values().length)];
        this.position = position;
        this.energy = newAnimalEnergy;
        this.genomeLen = genomeLen;
        this.dna = generateGenome(this.genomeLen);
        this.dnaIndex = new Random().nextInt(dna.size());
    }

    // test animal
    public Animal(Vector2d position, int energy, List<Integer> dna, MapDirection direction) {
        this.currentForwarding = direction;
        this.position = position;
        this.energy = energy;
        this.dna = dna;
        this.dnaIndex = new Random().nextInt(dna.size());
    }

    public void changeEnergy(int level) {this.energy += level;}


    public boolean ifDead(){
        return this.energy == 0;
    }

    private List<Integer> generateGenome(int genomeLen){
        List<Integer> dna = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i<genomeLen; i++){
            dna.add(random.nextInt(0, 8));
        }
        return dna;
    }


    private void turnBack(){
        for(int i = 0; i < 4; i ++)
            this.currentForwarding = currentForwarding.next();
    }

    public void move(Map map) {
        int direction = this.dna.get(this.dnaIndex);

        // change forwarding
        for(int i = 0; i < direction; i ++)
            this.currentForwarding = currentForwarding.next();
        Vector2d posTempForward = position.add(currentForwarding.toUnitVector());

        //move
        // polar
        int x = posTempForward.getX();
        int y = posTempForward.getY();

        if((y > map.up || y < map.down) && (x < map.left)){
            turnBack();
            position = new Vector2d(map.right, this.getPosition().getY());
        }
        else if((y > map.up || y < map.down) && (x > map.right)){
            turnBack();
            position = new Vector2d(map.left, this.getPosition().getY());
        }
        else if((y > map.up || y < map.down))
            turnBack();
        else if(x < map.left)
            position = new Vector2d(map.right, y);
        else if(x > map.right)
            position = new Vector2d(map.left, y);
        else
            position = posTempForward;



        // update dnaIndex
        if (this.dnaIndex == dna.size()-1)
            this.dnaIndex = 0;
        else
            this.dnaIndex++;
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
