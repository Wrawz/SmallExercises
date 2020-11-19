package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.io.IOException;

public class Controller {

    @FXML
    private TextField monthValuesTextField;
    @FXML
    private TextField dateTextField;

    public void generateCSV() throws IOException {
        try (FileWriter fileWriter = new FileWriter("fgtsMulta.csv")){
            fileWriter.write("competencia,valor,multa,total\n");
            double[] monthValues = readMonthValues(monthValuesTextField.getText().toString());
            //
            // CALCULAR COMO ESCREVER OS MESES AQUI

            String[] months = {"jan", "fev", "mar", "abr", "mai", "jun", "jul", "ago", "set", "out", "nov", "dez"};
            String date = dateTextField.getText().toString();
            String[] dayAndMonth = date.split("/");
            int numberOfMonths = monthValues.length;
            int dateDay = Integer.parseInt(dayAndMonth[0]);
            int dateMonthIndex = Integer.parseInt(dayAndMonth[1]) - 1;
//            if (dateMonthIndex == -1) dateMonthIndex = 11;
            String[] monthsWithFine = new String[numberOfMonths];
            boolean hasPassedJanuary = false;
            for (int i = 0; i < numberOfMonths; i++) {
                if (dateMonthIndex == 0 || dateMonthIndex == -1) {
                    dateMonthIndex = 11;
                    hasPassedJanuary = true;
                }
                if (i == 0) {
                    if (dateDay >= 8) {
                        monthsWithFine[i] = months[dateMonthIndex];
                    } else {
                        monthsWithFine[i] = months[dateMonthIndex-1];
                        dateMonthIndex--;
                    }
                }
                if (hasPassedJanuary) {
                    monthsWithFine[i] = months[dateMonthIndex];
                    dateMonthIndex--;
                    continue;
                }
                dateMonthIndex--;
                monthsWithFine[i] = months[dateMonthIndex];

            }

            //
            double fineTotal = 0.0;
            double finePercentage = 1.05;
            for (int i = 0; i < monthValues.length; i++) {
                if (i == 0) {
                    fileWriter.write(monthsWithFine[i] + "," + monthValues[i] + "," + String.format("%.2f", monthValues[i]*finePercentage) + "\n");
                    fineTotal += monthValues[i] * finePercentage;
                    finePercentage = 1.11;
                    continue;
                }
                if (i == 1) {
                    fileWriter.write(monthsWithFine[i] + "," + monthValues[i] + "," + String.format("%.2f", monthValues[i]*finePercentage) + "\n");
                    fineTotal += monthValues[i] * finePercentage;
                    continue;
                }
                finePercentage += 0.005;
                fineTotal += monthValues[i] * finePercentage;
                fileWriter.write(monthsWithFine[i] + "," + monthValues[i] + "," + String.format("%.2f", monthValues[i]*finePercentage) + "\n");
            }
            fileWriter.write(",,," + fineTotal);
        }
    }

    public double[] readMonthValues(String monthValues) {
        String[] monthValues2 = monthValues.split(",");
        double[] finalValues = new double[monthValues2.length];
        for (int i = 0; i < finalValues.length; i++) finalValues[i] = Double.parseDouble(monthValues2[i]);
        return finalValues;
    }

//    public double calculateFine(double[] monthValueList) {
//        double fineTotal = 0.0;
//        double finePercentage = 1.05;
//        for (int i = 0; i < monthValueList.length; i++) {
//            if (i == 1) {
//                fineTotal += monthValueList[i] * finePercentage;
//                finePercentage = 1.11;
//                continue;
//            }
//            if (i == 2) {
//                fineTotal += monthValueList[i] * finePercentage;
//                continue;
//            }
//            finePercentage += 0.005;
//            fineTotal += monthValueList[i] * finePercentage;
//        }
//        return Math.round(fineTotal * 100.0) / 100.0;
//    }


}
