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
            int width = Integer.parseInt(mapWidthText.getText());
            int height = Integer.parseInt(mapHeightText.getText());
            int grassNumber = Integer.parseInt(grassNumberText.getText());
            int animalsNumber = Integer.parseInt(startAnimalsText.getText());
            int grassEnergy = Integer.parseInt(grassEnergyText.getText());
            int newGrassNumber = Integer.parseInt(newGrassText.getText());
            int newAnimalEnergy = Integer.parseInt(newAnimalEnergyText.getText());
            int genomeLen = Integer.parseInt(genomeLenText.getText());
            int minReproductionEnergy = Integer.parseInt(minReproductionEnergyText.getText());
            int useReproductionEnergy = Integer.parseInt(useReproductionEnergyText.getText());

            application.startNewSimulation(grassNumber, animalsNumber, width, height, newAnimalEnergy, grassEnergy,
                    newGrassNumber, genomeLen, minReproductionEnergy, useReproductionEnergy);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
