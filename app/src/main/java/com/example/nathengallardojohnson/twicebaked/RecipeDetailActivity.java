package com.example.nathengallardojohnson.twicebaked;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.danikula.videocache.HttpProxyCacheServer;
import com.example.nathengallardojohnson.twicebaked.model.Steps;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class RecipeDetailActivity extends AppCompatActivity {

    private static final String ARG_STEP_ID = "step";
    public static String ARG_RECIPE_ID  = "recipe";
    static int mRecipeId;
    static int mStepId;
    static Steps mStep;
    static String mTitle = " ";
    private static String mVideoURL = "";
    PlayerView playerView;
    SimpleExoPlayer player;
    Context context;
    private String mDescription = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        context = getApplicationContext();

        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        //Set variables
        mRecipeId = getIntent().getIntExtra(ARG_RECIPE_ID, 0);
        mStepId = getIntent().getIntExtra(ARG_STEP_ID, 0);
        mStep = Baking.recipeList.get(mRecipeId).getStep(mStepId);
        mDescription = mStep.getDescription();
        mVideoURL = mStep.getVideoURL();
        mTitle = mStep.getShortDescription();

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(mTitle);
        }

        TextView descriptionTextView = findViewById(R.id.description_text_view);
        descriptionTextView.setText(mDescription);

        playerView = findViewById(R.id.player_view);

    }
    @Override
    public void onStart() {
        super.onStart();
        if (!mVideoURL.isEmpty()) {
            getPlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!mVideoURL.isEmpty()) {
            getPlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }

    private void getPlayer() {
        if (player == null) {
            player = ExoPlayerFactory.newSimpleInstance(context);
            playerView.setUseController(true);
            playerView.setShowBuffering(PlayerView.SHOW_BUFFERING_ALWAYS);
            playerView.requestFocus();
            playerView.setPlayer(player);
            player.setPlayWhenReady(true);
        }
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(
                context, Util.getUserAgent(context, getString(R.string.app_name)));
        HttpProxyCacheServer proxy = Baking.getProxy(context);
        String proxyUrl = proxy.getProxyUrl(mVideoURL);
        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(proxyUrl));
        player.prepare(videoSource);
    }

    private void releasePlayer() {
        player.release();
        player = null;
    }

}
