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

    public Ingredients(Ingredients mIngredient) {
        super();
        this.quantity = mIngredient.getQuantity();
        this.measure = mIngredient.getMeasure();
        this.ingredient = mIngredient.getIngredient();
    }


    public String getIngredient() {
        return this.ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public float getQuantity() {
        return this.quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return this.measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

}
