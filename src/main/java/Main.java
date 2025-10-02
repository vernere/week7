import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private TextField inputField = new TextField();
    private Label resultLabel = new Label();
    private double fahrenheit;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        inputField.setPromptText("Enter temperature value");

        ComboBox<String> conversionMenu = new ComboBox<>();
        conversionMenu.getItems().addAll(
                "Celsius to Fahrenheit",
                "Fahrenheit to Celsius",
                "Kelvin to Celsius",
                "Celsius to Kelvin");
        conversionMenu.setPromptText("Select conversion type");

        Button convertButton = new Button("Convert");
        convertButton.setOnAction(e -> {
            String selected = conversionMenu.getValue();
            if (selected == null) {
                resultLabel.setText("Please select a conversion type");
                return;
            }
            switch (selected) {
                case "Celsius to Fahrenheit":
                    cToF();
                    break;
                case "Fahrenheit to Celsius":
                    fToC();
                    break;
                case "Kelvin to Celsius":
                    kToC();
                    break;
                case "Celsius to Kelvin":
                    cToK();
                    break;
            }
        });

        Button saveButton = new Button("Save to DB");
        saveButton.setOnAction(e -> Database.saveTemperature(
                Double.parseDouble(inputField.getText()), fahrenheit, resultLabel));

        VBox root = new VBox(10, inputField, conversionMenu, convertButton, resultLabel, saveButton);
        Scene scene = new Scene(root, 300, 200);

        stage.setTitle("Temperature Converter");
        stage.setScene(scene);
        stage.show();
    }

    private void cToF() {
        try {
            double celsius = Double.parseDouble(inputField.getText());
            fahrenheit = (celsius * 9 / 5) + 32;
            resultLabel.setText(String.format("Fahrenheit: %.2f", fahrenheit));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input!");
        }
    }

    private void fToC() {
        try {
            double fahrenheitInput = Double.parseDouble(inputField.getText());
            double celsius = (fahrenheitInput - 32) / 1.8;
            resultLabel.setText(String.format("Celsius: %.2f", celsius));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input!");
        }
    }

    private void kToC() {
        try {
            double kelvin = Double.parseDouble(inputField.getText());
            double celsius = kelvin - 273.15;
            resultLabel.setText(String.format("Celsius: %.2f", celsius));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input!");
        }
    }

    private void cToK() {
        try {
            double celsius = Double.parseDouble(inputField.getText());
            double kelvin = celsius + 273.15;
            resultLabel.setText(String.format("Kelvin: %.2f", kelvin));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input!");
        }
    }
}