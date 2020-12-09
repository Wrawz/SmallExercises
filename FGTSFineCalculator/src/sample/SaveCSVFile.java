package sample;

import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class SaveCSVFile extends Service<ObservableList<CSVContent>> {


    @Override
    protected Task<ObservableList<CSVContent>> createTask() {
        return null;
    }
}
