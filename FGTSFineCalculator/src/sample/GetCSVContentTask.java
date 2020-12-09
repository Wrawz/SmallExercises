package sample;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class GetCSVContentTask extends Task<ObservableList<CSVContent>> {

    @Override
    protected ObservableList<CSVContent> call() throws Exception {
        for (int i = 0; i <= 1000; i++) {
            updateProgress(i, 1000);
            Thread.sleep(10);
        }
        return null;
    }
}
