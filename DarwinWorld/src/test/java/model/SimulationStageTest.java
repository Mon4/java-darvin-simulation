package model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimulationStageTest {
    @Test
    public void procrastinateTwoAnimalsTest(){
        int minReproductionEnergy = 50;
        int useReproductionEnergy = 20;

        Map map = new Map(10, 10);

        Vector2d pos = new Vector2d(5, 5);
        List<Integer> dna1 = List.of(0, 1, 2, 3);
        List<Integer> dna2 = List.of(4, 5, 6, 7);
        Animal animal1 = new Animal(pos, 100, dna1, MapDirection.NORTH);
        Animal animal2 = new Animal(pos, 90, dna2, MapDirection.NORTH);
        map.addAnimal(pos, animal1);
        map.addAnimal(pos, animal2);

        SimulationStage sim = new SimulationStage(map);
        sim.procreating(minReproductionEnergy,useReproductionEnergy);

        assertEquals(3, map.getAnimals().get(pos).size());
    }
}
