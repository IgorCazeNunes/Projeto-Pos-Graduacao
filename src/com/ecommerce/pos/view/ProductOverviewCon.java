package com.ecommerce.pos.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.ecommerce.pos.MainApp;
import com.ecommerce.pos.model.Product;

public class ProductOverviewCon {

    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, Double> valueColumn;

    @FXML
    private Label idLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label valueLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label categoryLabel;

    private MainApp mainApp;

    public ProductOverviewCon() {
    }

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        valueColumn.setCellValueFactory(cellData -> cellData.getValue().valueProperty().asObject());

        // Clear person details.
        showProductDetails(null);

        // Listen for selection changes and show the person details when changed.
        productTable
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> showProductDetails(newValue)
                );
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        productTable.setItems(mainApp.getProductData());
    }

    private void showProductDetails(Product product) {
        if (product != null) {
            // Fill the labels with info from the person object.
            idLabel.setText(product.getId());
            nameLabel.setText(product.getName());
            valueLabel.setText(Double.toString(product.getValue()));
            descriptionLabel.setText(product.getDescription());
            categoryLabel.setText(product.getCategory());
        } else {
            // Person is null, remove all the text.
            nameLabel.setText("");
            valueLabel.setText("");
            descriptionLabel.setText("");
            categoryLabel.setText("");
        }
    }

    @FXML
    private void handleDeleteProduct() {
        int selectedIndex = productTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            productTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Sem Seleção");
            alert.setHeaderText("Nenhum produto selecionado...");
            alert.setContentText("Por favor, selecione um produto na tabela.");

            alert.showAndWait();
        }
    }

//    @FXML
//    private void handleNewProduct() {
//        Product tempProduct = new Product();
//        boolean okClicked = mainApp.showPersonEditDialog(tempProduct);
//        if (okClicked) {
//            mainApp.getPersonData().add(tempProduct);
//        }
//    }
//    @FXML
//    private void handleEditPerson() {
//        Person selectedPerson = productTable.getSelectionModel().getSelectedItem();
//        if (selectedPerson != null) {
//            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
//            if (okClicked) {
//                showProductDetails(selectedPerson);
//            }
//
//        } else {
//            // Nothing selected.
//            Alert alert = new Alert(AlertType.WARNING);
//            alert.initOwner(mainApp.getPrimaryStage());
//            alert.setTitle("No Selection");
//            alert.setHeaderText("No Person Selected");
//            alert.setContentText("Please select a person in the table.");
//
//            alert.showAndWait();
//        }
//    }
}