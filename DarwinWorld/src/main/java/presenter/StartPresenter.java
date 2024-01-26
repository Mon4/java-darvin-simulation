package presenter;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
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
    public TextField startAnimalsText;
    @FXML
    public TextField grassEnergyText;
    @FXML
    public TextField newGrassText;
    @FXML
    public ComboBox plantsCombo;
    @FXML
    public TextField minReproductionEnergyText;
    @FXML
    public TextField useReproductionEnergyText;
    @FXML
    public TextField minMutationEnergyText;
    @FXML
    public TextField maxMutationEnergyText;
    @FXML
    public ComboBox mutationOptionCombo;
    @FXML
    public TextField genomeLenText;
    @FXML
    public TextField newAnimalEnergyText;

    private SimulationApp application;
    public void setApplication(SimulationApp application){
        this.application = application;
    }

    @FXML
    private void onSimulationStartClicked() {
        try {
            int width = Integer.parseInt(mapWidthText.getText().trim());
            int height = Integer.parseInt(mapHeightText.getText().trim());
            int grassNumber = Integer.parseInt(grassNumberText.getText().trim());
            int animalsNumber = Integer.parseInt(startAnimalsText.getText().trim());
            int grassEnergy = Integer.parseInt(grassEnergyText.getText().trim());
            int newGrassNumber = Integer.parseInt(newGrassText.getText().trim());
            int newAnimalEnergy = Integer.parseInt(newAnimalEnergyText.getText().trim());
            int genomeLen = Integer.parseInt(genomeLenText.getText().trim());
            int minReproductionEnergy = Integer.parseInt(minReproductionEnergyText.getText().trim());
            int useReproductionEnergy = Integer.parseInt(useReproductionEnergyText.getText().trim());

            application.startNewSimulation(grassNumber, animalsNumber, width, height, newAnimalEnergy, grassEnergy,
                    newGrassNumber, genomeLen, minReproductionEnergy, useReproductionEnergy);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkAndAddData() {

    }
    @FXML
    public void onFocusLost() {
        int width = Integer.parseInt(mapWidthText.getText().trim());
        int height = Integer.parseInt(mapHeightText.getText().trim());
        Tooltip tooltip;

        if (!(width > 0 && width <= 100)){
            tooltip = new Tooltip("Set the width from 0 to 100.");
            tooltip.setShowDelay(javafx.util.Duration.ZERO);
            tooltip.setShowDuration(javafx.util.Duration.INDEFINITE);
            mapWidthText.setTooltip(tooltip);
        }
        System.out.println("ZLEEEEEE");

    }
}
