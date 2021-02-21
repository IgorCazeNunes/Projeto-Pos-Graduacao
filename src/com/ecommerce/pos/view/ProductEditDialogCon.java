package com.ecommerce.pos.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import com.ecommerce.pos.model.Product;

public class ProductEditDialogCon {

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

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setProduct(Product product) {
        this.product = product;

        nameField.setText(product.getName());
        valueField.setText(Double.toString(product.getValue()));
        categoryField.setText(product.getCategory());
        descriptionField.setText(product.getDescription());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            product.setName(nameField.getText());
            product.setValue(new Double(valueField.getText()));
            product.setCategory(categoryField.getText());
            product.setDescription(descriptionField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
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

}
