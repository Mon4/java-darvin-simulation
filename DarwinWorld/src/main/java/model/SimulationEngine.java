package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {
    private List<Simulation> simulations;
    private List<Thread> threads;
    private ExecutorService executorService;
    public SimulationEngine(List<Simulation> simulations) {
        this.simulations = simulations;
        this.threads = new ArrayList<>();
        this.executorService = Executors.newFixedThreadPool(4);
    }

    public void awaitSimulationsEnd(Object threadsOrExecutorService){
        if (threadsOrExecutorService instanceof ExecutorService){
            ExecutorService executorService = (ExecutorService) threadsOrExecutorService;
            executorService.shutdown();
            try {
                if(!executorService.awaitTermination(10, TimeUnit.SECONDS)){
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else if (threadsOrExecutorService instanceof List<?>) {
            List<Thread> threads = (List<Thread>) threadsOrExecutorService;
            for (Thread thread : threads) {
                try {
                    thread.join(10_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void runAsyncInThreadPool(){
        for(Simulation simulation: simulations){
            executorService.submit(simulation);
        }
    }

    public void runSync() {
        simulations.forEach(Simulation::run);
    }

    public void runAsync() {
        for (Simulation simulation : simulations) {
            Thread thread = new Thread(simulation);
            threads.add(thread);
            thread.start();
        }
    }

}
