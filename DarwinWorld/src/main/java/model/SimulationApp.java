package model;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import presenter.SimulationPresenter;
import presenter.StartPresenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimulationApp extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("start.fxml"));
        BorderPane viewRoot = loader.load();

        StartPresenter presenter = loader.getController();
        presenter.setApplication(this);

        configureStage(primaryStage, viewRoot);
        primaryStage.show();

        primaryStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public void startNewSimulation(int grassNumber, int animalsNumber, int width, int height, int newAnimalEnergy,
                                   int grassEnergy, int newGrassNumber) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
        BorderPane viewRoot = loader.load();

        SimulationPresenter presenter = loader.getController();
        Map map = new Map(width, height);
        map.addGrasses(grassNumber);
        map.addAnimals(animalsNumber, newAnimalEnergy);

        Simulation simulation = new Simulation(map, newGrassNumber);
        List<Simulation> simulations = new ArrayList<>();
        simulations.add(simulation);
        SimulationEngine simulationEngine = new SimulationEngine(simulations);
        simulationEngine.runAsyncInThreadPool();
        presenter.setWorldMap(map);

        Stage stage = new Stage();
        configureStage(stage, viewRoot);
        stage.show();
    }
    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Darwin World");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }
}
