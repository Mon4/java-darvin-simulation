package model;


public class Simulation implements Runnable{
    private final WorldMap map;
    private final int newGrass;
    private final int grassEnergy;
    private final int minReproductionEnergy;
    private final int useReproductionEnergy;


    public Simulation(WorldMap map, int newGrass, int grassEnergy, int minReproductionEnergy, int useReproductionEnergy) {
        this.map = map;
        this.newGrass = newGrass;
        this.grassEnergy = grassEnergy;
        this.minReproductionEnergy = minReproductionEnergy;
        this.useReproductionEnergy = useReproductionEnergy;
    }

    public void run(){
        while(true){
            SimulationStage sim = new SimulationStage(map);
            sim.removeDead();
            sim.move();
            sim.procrastinate(minReproductionEnergy, useReproductionEnergy);
            sim.eat(grassEnergy);
            sim.addNewGrasses(newGrass);

            System.out.println("1 ");
            if(map.getAnimals().isEmpty())
                break;
        }
    }
}