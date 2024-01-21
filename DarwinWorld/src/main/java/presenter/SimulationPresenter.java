package presenter;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import model.*;

public class SimulationPresenter implements MapChangeListener {
    private final int CELL_WIDTH = 40;
    private final int CELL_HEIGHT = 40;
    @FXML
    public GridPane gridMap;
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

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            drawMap(worldMap);
        });
    }

    private void addLabel(String text, int columnIndex, int rowIndex) {
        Label label = new Label(text);
        GridPane.setHalignment(label, HPos.CENTER);
        gridMap.add(label, columnIndex, rowIndex);
    }

    private void addElementLabel(String text1, String text2, int columnIndex, int rowIndex){
        Label label1 = new Label(text1);
        Label label2 = new Label(text2);
        GridPane.setHalignment(label1, HPos.CENTER);
        GridPane.setHalignment(label2, HPos.CENTER);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(label1, label2);

        gridMap.add(vbox, columnIndex, rowIndex);
    }


    public void drawMap(WorldMap map){
        clearGrid();
        Vector2d size = map.getCurrentBounds().upperRight().subtract(map.getCurrentBounds().lowerLeft());
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
                String labelContent = " ";
                String energy = " ";
                if(element != null) {
                    labelContent = element.toString();
                    if(element instanceof Animal)
                        energy = Integer.toString (((Animal) element).getEnergy());
                }
                addElementLabel(labelContent, energy, x+1, y+1);
            }
        }
    }

}
