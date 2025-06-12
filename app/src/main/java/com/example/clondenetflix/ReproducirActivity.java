package com.example.clondenetflix;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ReproducirActivity extends AppCompatActivity {
    private com.google.android.exoplayer2.ui.PlayerView playerView;
    private com.google.android.exoplayer2.ExoPlayer exoPlayer;
    private boolean isFullscreen = false;
    private int originalHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproducir);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
        String firebaseId = getIntent().getStringExtra("firebaseId");

        String VIDEO_URL = "";

        if (firebaseId.equals("-OQLd8mnQH-uBoA1UDQb")) {
            VIDEO_URL = "https://firebasestorage.googleapis.com/v0/b/netflixclone-61496.firebasestorage.app/o/VerVanHelsing.mp4?alt=media&token=58ecbbae-6135-4340-9a5b-eb8ad15e2055";
        } else if (firebaseId.equals("-1QLd8mnQH-uBoA1UDQb")) {
            VIDEO_URL = "https://firebasestorage.googleapis.com/v0/b/netflixclone-61496.firebasestorage.app/o/Life%20(2017)%20-%20Escena%20-%20Calvin%20ataca%20a%20Hugh.mp4?alt=media&token=8193bbc0-ceb9-4b9e-a3d7-460425f2d039";
        } else if (firebaseId.equals("-3QLd8mnQH-uBoA1UDQb")) {
            VIDEO_URL = "https://firebasestorage.googleapis.com/v0/b/netflixclone-61496.firebasestorage.app/o/Anaconda%202%20%20En%20busca%20de%20la%20orquídea%20sangrienta%20parte%2015.mp4?alt=media&token=437c56a0-fecd-45fa-a6bb-6aa84b76a042";
        } else if (firebaseId.equals("-OQLd8n4D5_VYyMiJ21_")) {
            VIDEO_URL = "https://firebasestorage.googleapis.com/v0/b/netflixclone-61496.firebasestorage.app/o/AdolescenciaVer.mp4?alt=media&token=720d4dfc-0e52-4539-a1df-11671a3097f0";
        } else if (firebaseId.equals("-2QLd8mnQH-uBoA1UDQb")) {
            VIDEO_URL = "https://firebasestorage.googleapis.com/v0/b/netflixclone-61496.firebasestorage.app/o/TombRaiderver.mp4?alt=media&token=55c3a8b0-60b6-4ae6-aa6d-940c12167ec2";
        } else if (firebaseId.equals("-7QLd8mnQH-uBoA1UDQb")) {
            VIDEO_URL = "https://firebasestorage.googleapis.com/v0/b/netflixclone-61496.firebasestorage.app/o/El%20ejército%20de%20los%20ladrones%20-%20escena%20de%20las%20cajas%20-%20semifinal%20español%20latino.mp4?alt=media&token=5a4e740d-e66e-431d-833f-51c418564113";
        } else if (firebaseId.equals("-4QLd8mnQH-uBoA1UDQb")) {
            VIDEO_URL = "https://firebasestorage.googleapis.com/v0/b/netflixclone-61496.firebasestorage.app/o/Bastardossingloria.mp4?alt=media&token=fa05d5b4-2fbc-4983-ab58-38f3bf034d5a";
        } else if (firebaseId.equals("-OQLd8n3MmuHAhdkmoA_")) {
            VIDEO_URL = "https://firebasestorage.googleapis.com/v0/b/netflixclone-61496.firebasestorage.app/o/Inframundover.mp4?alt=media&token=bf30890b-a8cd-4a13-b1c7-dc366bf38e79";
        }


        playerView = findViewById(R.id.playerView);

        exoPlayer = new com.google.android.exoplayer2.ExoPlayer.Builder(this).build();
        playerView.setPlayer(exoPlayer);

        playerView.setControllerShowTimeoutMs(3000);
        playerView.showController();

        com.google.android.exoplayer2.MediaItem mediaItem = com.google.android.exoplayer2.MediaItem.fromUri(Uri.parse(VIDEO_URL));
        exoPlayer.setMediaItem(mediaItem);
        exoPlayer.prepare();
        exoPlayer.setPlayWhenReady(true);

        playerView.post(() -> originalHeight = playerView.getHeight());

        View fullscreenButton = playerView.findViewById(R.id.exo_fullscreen_button);
        if (fullscreenButton != null) {
            fullscreenButton.setOnClickListener(v -> toggleFullscreen());
        }
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

    }

    private void toggleFullscreen() {
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) playerView.getLayoutParams();
        if (!isFullscreen) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            params.width = ConstraintLayout.LayoutParams.MATCH_PARENT;
            params.height = ConstraintLayout.LayoutParams.MATCH_PARENT;
            playerView.setLayoutParams(params);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
            isFullscreen = true;
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            params.width = ConstraintLayout.LayoutParams.MATCH_PARENT;
            params.height = originalHeight > 0 ? originalHeight : (int) (200 * getResources().getDisplayMetrics().density);
            playerView.setLayoutParams(params);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            isFullscreen = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (exoPlayer != null) {
            exoPlayer.release();
            exoPlayer = null;
        }
    }
}