package com.ecommerce.pos.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import com.ecommerce.pos.MainApp;
import com.ecommerce.pos.service.ProductDAO;
import com.ecommerce.pos.model.Product;

public class ProductOverviewController {

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

    private final ProductDAO productDAO = new ProductDAO();

    public ProductOverviewController() {
    }

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        valueColumn.setCellValueFactory(cellData -> cellData.getValue().valueProperty().asObject());

        showProductDetails(null);

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
        idLabel.setText("Nenhum produto selecionado...");
        nameLabel.setText("Nenhum produto selecionado...");
        valueLabel.setText("Nenhum produto selecionado...");
        descriptionLabel.setText("Nenhum produto selecionado...");
        categoryLabel.setText("Nenhum produto selecionado...");

        if (product != null) {
            idLabel.setText(product.getId());
            nameLabel.setText(product.getName());
            valueLabel.setText(Double.toString(product.getValue()));
            descriptionLabel.setText(product.getDescription());
            categoryLabel.setText(product.getCategory());
        }
    }

    public final void alertErrorProductSelect() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Sem Conexão ao Banco");
        alert.setHeaderText("Problemas ao conectar com o banco de dados postgres.");
        alert.setContentText("Por favor verifique a seu container do banco de dados.");
        alert.showAndWait();
    }

    @FXML
    private void handleDeleteProduct() {
        int selectedIndex = productTable.getSelectionModel().getSelectedIndex();
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();

        try {
            productDAO.removeById(selectedProduct.getId());
            productTable.getItems().remove(selectedIndex);
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            alertErrorProductSelect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleNewProduct() {
        Product tempProduct = new Product();

        boolean okClicked = mainApp.showProductEditDialog(tempProduct);

        if (okClicked) {
            mainApp.getProductData().add(tempProduct);
        }
    }

    @FXML
    private void handleEditProduct() {
        try {
            Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
            
            boolean okClicked = mainApp.showProductEditDialog(selectedProduct);

            if (okClicked) {
                showProductDetails(selectedProduct);
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            alertErrorProductSelect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
