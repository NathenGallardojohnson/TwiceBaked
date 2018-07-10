package com.example.nathengallardojohnson.twicebaked.model;

public class Ingredients {
    private float quantity;
    private String measure = " ";
    private String ingredient = " ";

    public Ingredients() {
    }

    public Ingredients(float quantity,
                       String measure, String ingredient) {
        super();
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public Ingredients(Ingredients ingredient) {
        super();
        this.quantity = ingredient.getQuantity();
        this.measure = ingredient.getMeasure();
        this.ingredient = ingredient.getIngredient();
    }


    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

}
