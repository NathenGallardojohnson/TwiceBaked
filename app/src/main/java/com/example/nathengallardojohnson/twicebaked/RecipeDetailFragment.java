package com.example.nathengallardojohnson.twicebaked;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class RecipeDetailFragment extends Fragment {

    private static final String ARG_STEP_ID = "step";
    private static final String ARG_RECIPE_ID = "recipe";
    static int mRecipeId;
    static int mStepId;
    static Steps mStep;
    static String mDescription = " ";
    static String mVideoURL = " ";
    PlayerView playerView;
    SimpleExoPlayer player;

    public RecipeDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecipeId = getArguments().getInt(ARG_RECIPE_ID, 0);
        mStepId = getArguments().getInt(ARG_STEP_ID, 0);
        mStep = Baking.recipeList.get(mRecipeId).getStep(mStepId);
        mDescription = mStep.getDescription();
        mVideoURL = mStep.getVideoURL();

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        TextView descriptionTextView = rootView.findViewById(R.id.description_text_view);
        descriptionTextView.setText(mDescription);
        playerView = rootView.findViewById(R.id.player_view);
        return rootView;
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
            player = ExoPlayerFactory.newSimpleInstance(getActivity());
            playerView.setUseController(true);
            playerView.setShowBuffering(PlayerView.SHOW_BUFFERING_ALWAYS);
            playerView.requestFocus();
            playerView.setPlayer(player);
            player.setPlayWhenReady(true);
        }
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(
                getActivity(), Util.getUserAgent(getContext(), getString(R.string.app_name)));
        HttpProxyCacheServer proxy = Baking.getProxy(getActivity());
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
