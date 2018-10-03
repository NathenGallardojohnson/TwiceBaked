package com.example.nathengallardojohnson.twicebaked.model;

import java.util.List;

public class Recipe {
    private int id;
    private String name = " ";
    private int servings;
    private String image = " ";
    private List<Ingredients> ingredients;
    private List<Steps> steps;

    public Recipe() {
    }

    public Recipe(int id, String name, int servings, String image) {
        super();
        this.id = id;
        this.name = name;
        this.servings = servings;
        this.image = image;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public Ingredients getIngredient(int ingredient_number) {
        List<Ingredients> ingredientsList = getIngredients();
        return ingredientsList.get(ingredient_number);
    }

    public List<Steps> getSteps() {
        return steps;
    }

    public void setSteps(List<Steps> steps) {
        this.steps = steps;
    }

    public Steps getStep(int step_number) {
        List<Steps> stepsList = getSteps();
        return stepsList.get(step_number);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getServings() {
        return this.servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }
}
