package com.ecommerce.pos.model;

import java.util.UUID;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Product {

    private final StringProperty id;
    private final StringProperty name;
    private final DoubleProperty value;
    private final StringProperty description;
    private final StringProperty category;

    public Product() {
        this.id = new SimpleStringProperty("");
        this.name = new SimpleStringProperty("");
        this.value = new SimpleDoubleProperty(new Double(0));
        this.category = new SimpleStringProperty("");
        this.description = new SimpleStringProperty("");
    }

    public Product(String name, Double value) {
        this.name = new SimpleStringProperty(name);
        this.value = new SimpleDoubleProperty(value);
        
        // Some initial dummy data, just for convenient testing.
        this.id = new SimpleStringProperty(UUID.randomUUID().toString());
        this.description = new SimpleStringProperty("Descrição Teste");
        this.category = new SimpleStringProperty("Categoria Teste");
    }

    public Product(String id, String name, Double value, String description, String category) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.value = new SimpleDoubleProperty(value);
        this.description = new SimpleStringProperty(description);
        this.category = new SimpleStringProperty(category);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String value) {
        id.set(value);
    }

    public StringProperty idProperty() {
        return id;
    }
    
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }
    
    public double getValue() {
        return value.get();
    }

    public void setValue(Double value) {
        this.value.set(value);
    }

    public DoubleProperty valueProperty() {
        return value;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public String getCategory() {
        return category.get();
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public StringProperty categoryProperty() {
        return category;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", value=" + value + ", description=" + description + ", category=" + category + '}';
    }
    
}
