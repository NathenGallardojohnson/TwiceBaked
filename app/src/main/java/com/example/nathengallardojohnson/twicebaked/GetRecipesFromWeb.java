package com.example.nathengallardojohnson.twicebaked;

import android.util.Log;

import com.example.nathengallardojohnson.twicebaked.model.Recipe;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

class GetRecipesFromWeb {
    OkHttpClient client = new OkHttpClient();
    String responseString;

    void run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);
                    else {
                        responseString = response.body().string();
                        Log.d("responseString: ", responseString);

                        Gson gson = new Gson();
                        Type recipeType = new TypeToken<Collection<Recipe>>(){}.getType();
                        Baking.recipeList = gson.fromJson(responseString, recipeType);
                    }

                }
            }
        });
    }
    public static void main() throws IOException {
        GetRecipesFromWeb getRecipesFromWeb = new GetRecipesFromWeb();
        getRecipesFromWeb.run("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");
    }
}
