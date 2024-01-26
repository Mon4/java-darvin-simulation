package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {


    @Test
    public void moveAnimalRight(){
        ArrayList<Integer> dna = new ArrayList<>(List.of(2));
        Animal animal = new Animal(new Vector2d(0, 0), 50, dna, MapDirection.NORTH);
        Map map = new Map(5, 5);
        map.addAnimal(animal.getPosition(), animal);

        animal.move(map);
        Vector2d animalPos = animal.getPosition();

        assertEquals(new Vector2d(1, 0), animalPos);
    }

    @Test
    public void moveAnimalUp(){
        ArrayList<Integer> dna = new ArrayList<>(List.of(0));
        Animal animal = new Animal(new Vector2d(0, 0), 50, dna, MapDirection.NORTH);
        Map map = new Map(5, 5);
        map.addAnimal(animal.getPosition(), animal);

        animal.move(map);
        Vector2d animalPos = animal.getPosition();

        assertEquals(new Vector2d(0, 1), animalPos);
    }

    @Test
    public void moveAnimalBottom(){
        ArrayList<Integer> dna = new ArrayList<>(List.of(4));
        Animal animal = new Animal(new Vector2d(0, 0), 50, dna, MapDirection.NORTH);
        Map map = new Map(5, 5);
        map.addAnimal(animal.getPosition(), animal);

        animal.move(map);
        Vector2d animalPos = animal.getPosition();

        assertEquals(new Vector2d(0, 0), animalPos);
        assertEquals(MapDirection.NORTH, animal.getCurrentForwarding());
    }

    @Test
    public void moveAnimalLeft(){
        ArrayList<Integer> dna = new ArrayList<>(List.of(6));
        Animal animal = new Animal(new Vector2d(0, 0), 50, dna, MapDirection.NORTH);
        Map map = new Map(5, 5);
        map.addAnimal(animal.getPosition(), animal);

        animal.move(map);
        Vector2d animalPos = animal.getPosition();

        assertEquals(new Vector2d(5, 0), animalPos);
        assertEquals(MapDirection.WEST, animal.getCurrentForwarding());
    }

    @Test
    public void moveAnimalLeftDiagonally(){
        ArrayList<Integer> dna = new ArrayList<>(List.of(7));
        Animal animal = new Animal(new Vector2d(0, 1), 50, dna, MapDirection.NORTH);
        Map map = new Map(5, 5);
        map.addAnimal(animal.getPosition(), animal);

        animal.move(map);
        Vector2d animalPos = animal.getPosition();

        assertEquals(new Vector2d(5, 2), animalPos);
        assertEquals(MapDirection.NORTHWEST, animal.getCurrentForwarding());
    }

    @Test
    public void moveAnimalBottomDiagonally(){
        ArrayList<Integer> dna = new ArrayList<>(List.of(3));
        Animal animal = new Animal(new Vector2d(2, 0), 50, dna, MapDirection.NORTH);
        Map map = new Map(5, 5);
        map.addAnimal(animal.getPosition(), animal);

        animal.move(map);
        Vector2d animalPos = animal.getPosition();

        assertEquals(new Vector2d(2, 0), animalPos);
        assertEquals(MapDirection.NORTHWEST, animal.getCurrentForwarding());
    }

    @Test
    public void moveAnimalLeftBottomCorner(){
        ArrayList<Integer> dna = new ArrayList<>(List.of(5));
        Animal animal = new Animal(new Vector2d(0, 0), 50, dna, MapDirection.NORTH);
        Map map = new Map(5, 5);
        map.addAnimal(animal.getPosition(), animal);

        animal.move(map);
        Vector2d animalPos = animal.getPosition();

        assertEquals(new Vector2d(5, 0), animalPos);
        assertEquals(MapDirection.NORTHEAST, animal.getCurrentForwarding());
    }

    @Test
    public void moveAnimalLeftUpCorner(){
        ArrayList<Integer> dna = new ArrayList<>(List.of(7));
        Animal animal = new Animal(new Vector2d(0, 5), 50, dna, MapDirection.NORTH);
        Map map = new Map(5, 5);
        map.addAnimal(animal.getPosition(), animal);

        animal.move(map);
        Vector2d animalPos = animal.getPosition();

        assertEquals(new Vector2d(5, 5), animalPos);
        assertEquals(MapDirection.SOUTHEAST, animal.getCurrentForwarding());
    }
    @Test
    public void moveAnimalRightBottomCorner(){
        ArrayList<Integer> dna = new ArrayList<>(List.of(1));
        Animal animal = new Animal(new Vector2d(5, 5), 50, dna, MapDirection.NORTH);
        Map map = new Map(5, 5);
        map.addAnimal(animal.getPosition(), animal);

        animal.move(map);
        Vector2d animalPos = animal.getPosition();

        assertEquals(new Vector2d(0, 5), animalPos);
        assertEquals(MapDirection.SOUTHWEST, animal.getCurrentForwarding());
    }

    @Test
    public void moveAnimalRightUpCorner(){
        ArrayList<Integer> dna = new ArrayList<>(List.of(3));
        Animal animal = new Animal(new Vector2d(5, 0), 50, dna, MapDirection.NORTH);
        Map map = new Map(5, 5);
        map.addAnimal(animal.getPosition(), animal);

        animal.move(map);
        Vector2d animalPos = animal.getPosition();

        assertEquals(new Vector2d(0, 0), animalPos);
        assertEquals(MapDirection.NORTHWEST, animal.getCurrentForwarding());
    }

    @Test
    public void twoAnimalsInOneFieldCrossing(){
        ArrayList<Integer> dna = new ArrayList<>(List.of(0));
        Animal animal1 = new Animal(new Vector2d(2, 2), 50, dna, MapDirection.NORTH);
        Animal animal2 = new Animal(new Vector2d(3, 3), 50, dna, MapDirection.WEST);

        Map map = new Map(5, 5);
        map.addAnimal(animal1.getPosition(), animal1);
        map.addAnimal(animal2.getPosition(), animal2);

        map.move(animal1);
        map.move(animal2);
        map.move(animal1);
        map.move(animal2);

        Vector2d animal1Pos = animal1.getPosition();
        Vector2d animal2Pos = animal2.getPosition();

        assertEquals(new Vector2d(2, 4), animal1Pos);
        assertEquals(new Vector2d(1, 3), animal2Pos);

    }

    @Test
    public void animalAndGrass(){
        ArrayList<Integer> dna = new ArrayList<>(List.of(0));
        Animal animal = new Animal(new Vector2d(2, 2), 50, dna, MapDirection.NORTH);
        Grass grass = new Grass(new Vector2d(2, 3));

        Map map = new Map(5, 5);
        map.addAnimal(animal.getPosition(), animal);
        map.setGrasses(new Vector2d(2, 3), grass);
        map.move(animal);

        Vector2d animalPos = animal.getPosition();

        assertEquals(new Vector2d(2, 3), animalPos);

    }

}
