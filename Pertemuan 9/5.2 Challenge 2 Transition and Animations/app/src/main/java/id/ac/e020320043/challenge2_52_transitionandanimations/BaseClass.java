package id.ac.e020320043.challenge2_52_transitionandanimations;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseClass extends AppCompatActivity {

    private static final String TRANSITION_TYPE = "Transition Type";

    private static final String ANDROID_TRANSITION = "switchAndroid";
    private static final String BLUE_TRANSITION = "switchBlue";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().hasExtra(TRANSITION_TYPE)) {
            switch (getIntent().getStringExtra(TRANSITION_TYPE)) {
                case "Explode":
                    getWindow().setEnterTransition(new Explode());
                    break;
                case "Slide":
                    getWindow().setEnterTransition(new Slide());
                    break;
                case "Fade":
                    getWindow().setEnterTransition(new Fade());
                    break;
                default:
                    break;
            }
        }
    }

    protected void explodeTransition(final Context context,
                                     ImageView imageView) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Relaunch the activity with the transition information.
                Intent intent = new Intent(context,context.getClass());
                intent.putExtra(TRANSITION_TYPE,"Explode");
                getWindow().setExitTransition(new Explode());
                startActivity(intent, ActivityOptions.
                        makeSceneTransitionAnimation(
                                (Activity)context).toBundle());
            }
        });
    }

    protected void fadeTransition(final Context context, ImageView imageView) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,context.getClass());
                intent.putExtra(TRANSITION_TYPE,"Fade");
                getWindow().setExitTransition(new Fade());
                startActivity(intent, ActivityOptions.
                        makeSceneTransitionAnimation(
                                (Activity)context).toBundle());
            }
        });
    }

    /**
     * Animate an ImageView 360 degrees and back.
     *
     * @param imageView The ImageView that was clicked.
     */
    protected void rotateView(ImageView imageView){
        final ObjectAnimator animator = ObjectAnimator.ofFloat(
                imageView, View.ROTATION, 0f, 360f);
        animator.setDuration(1000);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animator.start();
            }
        });
    }

    protected void switchAnimation(final ImageView androidImage,
                                   final ImageView otherImage,
                                   final Intent intent,
                                   final Context context){
        androidImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the transition details and start the second activity.
                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation((Activity)context,
                                Pair.create((View)androidImage,
                                        ANDROID_TRANSITION),
                                Pair.create((View)otherImage,
                                        BLUE_TRANSITION));

                startActivity(intent,options.toBundle());
            }
        });
    }
}
