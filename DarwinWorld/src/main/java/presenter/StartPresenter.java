package presenter;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.SimulationApp;

import java.io.IOException;

public class StartPresenter {
    @FXML
    public TextField mapWidth;
    @FXML
    public TextField mapHeight;
    private SimulationApp application;
    public void setApplication(SimulationApp application){
        this.application = application;
    }

    @FXML
    private void onSimulationStartClicked() {
        try {
            int width = Integer.parseInt(mapWidth.getText());
            int height = Integer.parseInt(mapHeight.getText());
            application.startNewSimulation(width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
