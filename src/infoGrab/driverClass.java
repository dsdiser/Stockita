package infoGrab;

import java.awt.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
	 
public class driverClass extends Application{
	 
	@Override public void start(Stage stage) {
        stage.setTitle("Stock Analysis");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Timeline");
        yAxis.setLabel("Price of Stock ($)");
        //creating the chart
        final LineChart<Number,Number> lineChart = 
        		new LineChart<Number,Number>(xAxis,yAxis);
        Stock msft = new Stock();
        java.util.List<Entry> entries = msft.getEntries();
        lineChart.setTitle("Stock Monitoring, 2010");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");
        //populating the series with data
        for(int i = 0; i < entries.size(); i++) {
        	series.getData().add(new XYChart.Data(i, entries.get(i).getClose()));
        }
        
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
       
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
	 
	

}
