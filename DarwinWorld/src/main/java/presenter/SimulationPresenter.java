package presenter;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    private final int CELL_WIDTH = 40;
    private final int CELL_HEIGHT = 40;
    @FXML
    public GridPane gridMap;
    @FXML
    public Button startButton;
    @FXML
    private WorldMap map;


    public void setWorldMap(WorldMap map){
        this.map = map;
        map.addObserver(this);
        Platform.runLater(() -> {
            drawMap(map);
        });
    }

    private void clearGrid() {
        gridMap.getChildren().retainAll(gridMap.getChildren().get(0)); // hack to retain visible grid lines
        gridMap.getColumnConstraints().clear();
        gridMap.getRowConstraints().clear();
    }

    private void drawSquare(){
        gridMap.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        gridMap.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            drawMap(worldMap);
        });
    }

    private void addLabel(String text, int colIndex, int rowIndex) {
        Label label = new Label(text);
        GridPane.setHalignment(label, HPos.CENTER);
        gridMap.add(label, colIndex, rowIndex);
    }

    public void drawMap(WorldMap map){
        clearGrid();
        Vector2d size = map.getCurrentBounds().upperRight().subtract(map.getCurrentBounds().lowerLeft());
        int startX = map.getCurrentBounds().lowerLeft().getX();
        int startY = map.getCurrentBounds().lowerLeft().getY();
        int width = size.getX();
        int height = size.getY();

        Boundary boundary = map.getCurrentBounds();
        addLabel("x/y", 0, 0);
        gridMap.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        gridMap.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));

        for (int i = 1; i <= width + 1; i++) {
            gridMap.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
            addLabel(Integer.toString(i + boundary.lowerLeft().getX() - 1), i, 0);

        }

        for (int i = 1; i <= height + 1; i++) {
            gridMap.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
            addLabel(Integer.toString(boundary.upperRight().getY() - i + 1), 0, i);
        }

        for (int x = 0; x <= width; x++) {
            for (int y = 0; y <= height; y++) {
                Vector2d position = new Vector2d(x + boundary.lowerLeft().getX(), boundary.upperRight().getY() - y);
                WorldElement element = map.objectAt(position);
                String EMPTY_CELL = " ";
                String labelContent = (element == null) ? EMPTY_CELL : element.toString();
                addLabel(labelContent, x + 1, y + 1);
            }
        }
    }

    @FXML
    public void onSimulationStartClicked() {
        ArrayList<Vector2d> positions = new ArrayList<>(List.of(new Vector2d(2,2), new Vector2d(3,4)));
        Simulation simulation = new Simulation(positions, this.map);
        List<Simulation> simulations = new ArrayList<>();
        simulations.add(simulation);
        SimulationEngine simulationEngine = new SimulationEngine(simulations);
        simulationEngine.runAsyncInThreadPool();
    }

}
