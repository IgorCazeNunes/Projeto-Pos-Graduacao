package com.ecommerce.pos;


import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.ecommerce.pos.model.Product;
import com.ecommerce.pos.view.ProductOverviewCon;

public class MainApp extends Application {
    
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Product> productData;

    public MainApp() {
        productData = FXCollections.observableArrayList();
        productData.add(new Product("Produto 01", 100.0));
        productData.add(new Product("Produto 02", 200.0));
        productData.add(new Product("Produto 03", 300.0));
        productData.add(new Product("Produto 04", 400.0));
        productData.add(new Product("Produto 05", 500.0));
        productData.add(new Product("Produto 06", 600.0));
        productData.add(new Product("Produto 07", 700.0));
        productData.add(new Product("Produto 08", 800.0));
        productData.add(new Product("Produto 09", 900.0));
        productData.add(new Product("Produto 10", 1000.0));
        productData.add(new Product("Produto 01", 100.0));
        productData.add(new Product("Produto 02", 200.0));
        productData.add(new Product("Produto 03", 300.0));
        productData.add(new Product("Produto 04", 400.0));
        productData.add(new Product("Produto 05", 500.0));
        productData.add(new Product("Produto 06", 600.0));
        productData.add(new Product("Produto 07", 700.0));
        productData.add(new Product("Produto 08", 800.0));
        productData.add(new Product("Produto 09", 900.0));
        productData.add(new Product("Produto 10", 1000.0));
    }
    
    public ObservableList<Product> getProductData() {
        return productData;
    }
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        
        initRootLayout();
        
        showProductOverview();
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) fxmlLoader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void showProductOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ProductOverview.fxml"));
            AnchorPane productOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(productOverview);

            // Give the controller access to the main app.
            ProductOverviewCon controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
