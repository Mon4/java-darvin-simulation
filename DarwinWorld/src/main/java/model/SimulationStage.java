package model;

import java.util.Map;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimulationStage {

    WorldMap map;
    java.util.Map<Vector2d, LinkedList<Animal>> animals;
    Map<Vector2d, LinkedList<Animal>> copiedAnimals;
    public SimulationStage(WorldMap map) {
        this.map = map;
        this.animals = map.getAnimals();
        this.copiedAnimals = new HashMap<>(animals);
    }

    public void removeDead() {
        this.animals = map.getAnimals();
        this.copiedAnimals = new HashMap<>(animals);

        for (Vector2d v : copiedAnimals.keySet()) {
            LinkedList<Animal> animalsList = copiedAnimals.get(v);
            for (Animal animal : animalsList) {
                if (animal.ifDead()) {
                    System.out.println("i'm dead");
                    map.removeAnimal(animal);
                }
                if (animalsList.isEmpty())
                    break;
            }
        }
    }

    public void move() {
        this.animals = map.getAnimals();
        this.copiedAnimals = new HashMap<>(animals);

        for (Vector2d v : copiedAnimals.keySet()) {
            LinkedList<Animal> animalsList = copiedAnimals.get(v);
            for (Animal animal : animalsList) {
                try {
                    map.move(animal);
                    animal.changeEnergy(-10);
                    Thread.sleep(1000);
                    if (animalsList.isEmpty())
                        break;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void eat(int grassEnergy) {
        this.animals = map.getAnimals();
        this.copiedAnimals = new HashMap<>(animals);
        Map<Vector2d, Grass> grasses = map.getGrasses();

        for (Vector2d v : copiedAnimals.keySet()) {
            LinkedList<Animal> animalsList = copiedAnimals.get(v);
            for (Animal animal : animalsList) {
                if (animalsList.size() == 1) {  // only one animal
                    if (grasses.get(animal.getPosition()) != null) {
                        animal.changeEnergy(grassEnergy);
                        grasses.remove(animal.getPosition());
                    }
                }
                // multiple animals but with another energy level
                else {
                    Collections.sort(animalsList, Comparator.comparingInt(Animal::getEnergy));
                    Animal animalEnergy1 = animalsList.get(animalsList.size() - 1);
                    Animal animalEnergy2 = animalsList.get(animalsList.size() - 2);
                    if (animalEnergy1 != animalEnergy2) {
                        animalEnergy1.changeEnergy(grassEnergy);
                        grasses.remove(animal.getPosition());
                    }
                }
            }
        }
    }

    private LinkedList<Animal> getAnimalsWithHighestEnergy(LinkedList<Animal> animalsReady){
        return animalsReady.stream()
                .sorted(Comparator.comparingInt(Animal::getEnergy).reversed())
                .limit(2)
                .collect(Collectors.toCollection(LinkedList::new));}

    public void procrastinate(int minReproductionEnergy, int useReproductionEnergy){
        this.animals = map.getAnimals();
        this.copiedAnimals = new HashMap<>(animals);
        for (Vector2d v : copiedAnimals.keySet()) {
            LinkedList<Animal> animalsList = copiedAnimals.get(v);
                LinkedList<Animal> animalsReady = animalsList.stream()
                        .filter(x -> x.getEnergy() > minReproductionEnergy)
                        .collect(Collectors.toCollection(LinkedList::new));
            while (animalsReady.size() >= 2){
                    LinkedList<Animal> nextTwoAnimals = getAnimalsWithHighestEnergy(animalsReady);
                    animalsReady.removeAll(nextTwoAnimals);

                    Vector2d pos = nextTwoAnimals.get(0).getPosition();
                    Animal parent1 = nextTwoAnimals.get(0);
                    Animal parent2 = nextTwoAnimals.get(1);

                    List<Integer> dna = generateDNA(parent1, parent2);
                    Animal animal = new Animal(pos, useReproductionEnergy*2, parent1.getGenomeLen(), dna);
                    map.addAnimal(pos, animal);

                    parent1.changeEnergy(-useReproductionEnergy);
                    parent2.changeEnergy(-useReproductionEnergy);
            }
        }
    }

    private List<Integer> generateDNA(Animal parent1, Animal parent2) {
        int energy1 = parent1.getEnergy();
        int energy2 = parent2.getEnergy();
        int genLen = parent1.getGenomeLen();
        int parent1genomeLen = Math.round((float) energy1 / (energy1+energy2) * genLen);
        int parent2genomeLen = genLen - parent1genomeLen;

        Random random = new Random();
        int side = random.nextInt(2);
        int numberOfMutations = random.nextInt(genLen);

        List<Integer> dna1;
        List<Integer> dna2;
        if (energy1 > energy2)
        {
            if (side == 0) {
                dna1 = parent1.getDna().subList(0, parent1genomeLen); // left
                dna2 = parent2.getDna().subList(parent1genomeLen, genLen);
            }
            else{
                dna1 = parent1.getDna().subList(parent2genomeLen, genLen); //right
                dna2 = parent2.getDna().subList(0, parent2genomeLen);
            }
        }
        else {
            if (side == 0) {
                dna1 = parent1.getDna().subList(parent2genomeLen, genLen); //right
                dna2 = parent2.getDna().subList(0, parent2genomeLen);
            }
            else{
                dna1 = parent1.getDna().subList(0, parent1genomeLen); // left
                dna2 = parent2.getDna().subList(parent1genomeLen, genLen);
            }
        }

        List<Integer> dna = Stream.concat(dna1.stream(), dna2.stream())
                .collect(Collectors.toList());

        //mutations
        for(int i = 0; i < numberOfMutations; i++){  // powinno uwzględniać, że już zrobiło podmiankę na danej pozycji
            int pos = random.nextInt(genLen);
            int newGen = random.nextInt(8);
            dna.set(pos, newGen);
        }

        return dna;



    }

    public void addNewGrasses(int newGrass) {
        try {
            this.map.addGrasses(newGrass);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("1 ");

    }


}
