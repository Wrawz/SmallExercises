package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class Controller {

    @FXML
    private TextField monthValuesTextField;
    @FXML
    private GridPane gridPaneId;
    @FXML
    private DatePicker paymentDayDatePicker;

    @FXML
    public void generateCSV() throws IOException {
        String values = monthValuesTextField.getText().toString();
        if (!confirmValues(values)) {
            showInvalidValueDialog();
            return;
        }
        String date = paymentDayDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if (!confirmDateType(date)) {
            showInvalidDateDialog();
            return;
        }
        String path = getPathToSaveFile();
        try (FileWriter fileWriter = new FileWriter(path)) {
            double[] monthValues = readMonthValues(monthValuesTextField.getText().toString());
            String[] dayAndMonthAndYear = date.split("/");
            int[] years = getYears(date, monthValues.length);
            if (years == null) {
                showInvalidDateDialog();
                return;
            }
            int dateDay = Integer.parseInt(dayAndMonthAndYear[0]);
            int dateMonth = Integer.parseInt(dayAndMonthAndYear[1]);
            int dateYear = Integer.parseInt(dayAndMonthAndYear[2]);
            fileWriter.write("Data:," + dateDay + "/" + dateMonth + "/" + dateYear + "\n");
            fileWriter.write("Competencia,Valor,Multa,Total\n");
            String[] monthsWithFine = getMonths(dateDay, dateMonth, monthValues.length);
            double fineTotal = 0.0;
            double finePercentage = dateDay <= 7 ? 1.105 : 1.055;
            for (int i = 0; i < monthValues.length; i++) {
                if (i == 0) {
                    fileWriter.write(monthsWithFine[i] + "/" + years[i] + "," + monthValues[i] + "," + String.format("%.2f", monthValues[i] * finePercentage) + "\n");
                    fineTotal += monthValues[i] * finePercentage;
                    finePercentage = 1.11;
                    continue;
                }
                if (i == 1) {
                    fileWriter.write(monthsWithFine[i] + "/" + years[i] + "," + monthValues[i] + "," + String.format("%.2f", monthValues[i] * finePercentage) + "\n");
                    fineTotal += monthValues[i] * finePercentage;
                    continue;
                }
                finePercentage += 0.005;
                fineTotal += monthValues[i] * finePercentage;
                fileWriter.write(monthsWithFine[i] + "/" + years[i] + "," + monthValues[i] + "," + String.format("%.2f", monthValues[i] * finePercentage) + "\n");
            }
            fileWriter.write(",,," + String.format("%.2f", fineTotal));
        }
        successfulDialog();
    }

    private double[] readMonthValues(String monthValues) {
        String[] monthValues2 = monthValues.split(",");
        double[] finalValues = new double[monthValues2.length];
        for (int i = 0; i < finalValues.length; i++) finalValues[i] = Double.parseDouble(monthValues2[i]);
        return finalValues;
    }

    private String[] getMonths(int day, int month, int numberOfMonths) {
        String[] months = {"jan", "fev", "mar", "abr", "mai", "jun", "jul", "ago", "set", "out", "nov", "dez"};
        String[] fineMonths = new String[numberOfMonths];
        int monthIndex = month - 1;
        if (day > 7) {
            for (int i = 0; i < numberOfMonths; i++) {
                if (monthIndex == 0) monthIndex = 12;
                fineMonths[i] = months[monthIndex - 1];
                monthIndex--;
            }
            return fineMonths;
        }
        for (int i = 0; i < numberOfMonths; i++) {
            if (monthIndex == 1) monthIndex = 13;
            if (monthIndex == 0) monthIndex = 12;
            fineMonths[i] = months[monthIndex - 2];
            monthIndex--;
        }
        return fineMonths;
    }

    private int[] getYears(String date, int howManyMonths) {
        if (!date.matches("^[0-9]{2}/[0-9]{2}/[0-9]{4}$")) return null;
        String[] numbers = date.split("/");
        int day = Integer.parseInt(numbers[0]);
        int month = Integer.parseInt(numbers[1]);
        int year = Integer.parseInt(numbers[2]);
        int[] years = new int[howManyMonths];
        int startMonth = day <= 7 && month == 1 ? 11 : day > 7 && month == 1 || day <= 7 && month == 2 ? 12 : day > 7 ? month - 1 : month - 2;
        int startYear = day <= 7 && month == 1 ? year - 1 : day > 7 && month == 1 || day <= 7 && month == 2 ? year - 1 : year;
        for (int i = 0; i < howManyMonths; i++) {
            years[i] = startYear;
            startMonth--;
            if (startMonth == 0) {
                startMonth = 12;
                startYear--;
            }
        }
        return years;
    }

    private boolean confirmDateType(String date) {
        if (!date.matches("^[0-9]{2}/[0-9]{2}/[0-9]{4}$")) return false;
        String[] dayAndMonth = date.split("/");
        int day = Integer.parseInt(dayAndMonth[0]);
        int month = Integer.parseInt(dayAndMonth[1]);
        if (day < 1 || day > 31 || month < 1 || month > 12) return false;
        if (month == 2 || month == 4 || month == 6 || month == 9 || month == 11)
            if (day == 31) return false; // here, "(month ... && day == 31)" didn't work
        if (month == 2) return day != 30;
        return true;
    }

    private void showInvalidDateDialog() {
        System.out.println(paymentDayDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM")));
        Dialog<ButtonType> buttonTypeDialog = new Dialog<>();
        buttonTypeDialog.initOwner(gridPaneId.getScene().getWindow());
        buttonTypeDialog.setTitle("Erro!");
        buttonTypeDialog.setContentText("Por favor insira uma data válida.");
        buttonTypeDialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        buttonTypeDialog.show();
    }

    private boolean confirmValues(String values) {
        if (values.matches(".*\\.\\..*") || values.matches(".*,,.*") || values.matches(".*(?i)[a-z].*") ||
                values.matches(".*\\.[0-9]+\\..*")) return false;
        return values.matches("^[0-9]+(\\.[0-9]{1,2})?(,[0-9]+(\\.[0-9]{1,2})?)*$");
    }

    private void showInvalidValueDialog() {
        Dialog<ButtonType> buttonTypeDialog = new Dialog<>();
        buttonTypeDialog.initOwner(gridPaneId.getScene().getWindow());
        buttonTypeDialog.setTitle("Erro!");
        buttonTypeDialog.setContentText("Por favor, insira valores válidos.");
        buttonTypeDialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        buttonTypeDialog.show();
    }

    public void successfulDialog() {
        Dialog<ButtonType> buttonTypeDialog = new Dialog<>();
        buttonTypeDialog.initOwner(gridPaneId.getScene().getWindow());
        buttonTypeDialog.setTitle("Calculador de multa de FGTS");
        buttonTypeDialog.setContentText("Arquivo criado com sucesso!");
        buttonTypeDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        buttonTypeDialog.show();
    }

    private String getPathToSaveFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salve o seu arquivo.");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Arquivo Excel", "*.csv"));
        return fileChooser.showSaveDialog(gridPaneId.getScene().getWindow()).getAbsolutePath();
    }

}