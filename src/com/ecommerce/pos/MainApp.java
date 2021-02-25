package com.ecommerce.pos;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import com.ecommerce.pos.model.Product;
import com.ecommerce.pos.service.ProductDAO;
import com.ecommerce.pos.controller.ProductOverviewController;
import com.ecommerce.pos.controller.ProductEditDialogController;
import javafx.scene.control.Alert;
import org.postgresql.util.PSQLException;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Product> productData;
    private ProductDAO productDAO = new ProductDAO();

    public MainApp() {
        try {
            productData = productDAO.listAll();
        } catch (PSQLException e) {
            alertErrorDataBase();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("E-Commerce [CRUD Produto]");

        initRootLayout();

        showProductOverview();
    }

    public void initRootLayout() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) fxmlLoader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showProductOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ProductOverview.fxml"));
            AnchorPane productOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(productOverview);

            ProductOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showProductEditDialog(Product product) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ProductEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle(product.getId().equals("") ? "Inserção de Produto" : "Edição de Produto");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ProductEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProductField(product);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public final void alertErrorDataBase() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        
        alert.initOwner(primaryStage);
        alert.setTitle("Sem Conexão ao Banco");
        alert.setHeaderText("Problemas ao conectar com o banco de dados postgres.");
        alert.setContentText("Por favor verifique a seu container do banco de dados.");
        alert.showAndWait();
        
        System.exit(0);
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public ObservableList<Product> getProductData() {
        return productData;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
