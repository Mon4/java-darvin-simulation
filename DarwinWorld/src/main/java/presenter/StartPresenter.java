package presenter;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.SimulationApp;

import java.io.IOException;

public class StartPresenter {
    @FXML
    public TextField mapWidthText;
    @FXML
    public TextField mapHeightText;
    @FXML
    public TextField grassNumberText;
    @FXML
    public ComboBox mapTypeCombo;
    @FXML
    public TextField StartAnimalsText;

    private SimulationApp application;
    public void setApplication(SimulationApp application){
        this.application = application;
    }

    @FXML
    private void onSimulationStartClicked() {
        try {
            int width = Integer.parseInt(mapWidthText.getText());
            int height = Integer.parseInt(mapHeightText.getText());
            int grassNumber = Integer.parseInt(grassNumberText.getText());
            int animalsNumber = Integer.parseInt(StartAnimalsText.getText());
            application.startNewSimulation(grassNumber, animalsNumber, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
