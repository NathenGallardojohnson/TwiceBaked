package com.example.nathengallardojohnson.twicebaked;

import android.app.Application;

import com.example.nathengallardojohnson.twicebaked.model.Recipe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Baking extends Application {
    private static Baking singleton = new Baking();
    public static List<Recipe> recipeList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        try {
            GetRecipesFromWeb.main();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static Baking getInstance() {
        return singleton;
    }
}
