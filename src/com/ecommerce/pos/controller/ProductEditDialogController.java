package com.ecommerce.pos.controller;

import com.ecommerce.pos.service.ProductDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import com.ecommerce.pos.model.Product;

public class ProductEditDialogController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField valueField;
    @FXML
    private TextField categoryField;
    @FXML
    private TextField descriptionField;

    private Stage dialogStage;
    private Product product;

    private boolean okClicked = false;
    
    private final ProductDAO productDAO = new ProductDAO();

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setProductField(Product product) {
        this.product = product;

        nameField.setText(product.getName());
        valueField.setText(Double.toString(product.getValue()));
        categoryField.setText(product.getCategory());
        descriptionField.setText(product.getDescription());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (nameField.getText() == null || nameField.getText().length() <= 3) {
            errorMessage += "Nome deve ter pelo menos 3 caracteres!\n\n";
        }

        if (valueField.getText() == null || valueField.getText().length() == 0) {
            errorMessage += "Valor é Obrigatorio!\n";
        } else {
            try {
                Double.parseDouble(valueField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "O valor precisar ser um numero!\nEx: 40.10\n\n";
            }
        }

        if (categoryField.getText() == null || categoryField.getText().length() == 0) {
            errorMessage += "Categoria é Obrigatoria!\n\n";
        }

        if (descriptionField.getText() == null || descriptionField.getText().length() <= 10) {
            errorMessage += "Descrição deve ter pelo menos 10 caracteres!\n\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Campos Invalidos");
            alert.setHeaderText("Por favor corrija os seguintes campos.");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
    
    @FXML
    private void handleOk() {
        boolean isNewProduct = false;
        
        if (isInputValid()) {
            if (product.getId().equals("")) {
                isNewProduct = true;
                product.setGeneratedUUID();
            }
            
            product.setName(nameField.getText());
            product.setValue(new Double(valueField.getText()));
            product.setCategory(categoryField.getText());
            product.setDescription(descriptionField.getText());
            
            if (isNewProduct) {
                productDAO.save(product);
            } else {
                productDAO.update(product);
            }

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

}
