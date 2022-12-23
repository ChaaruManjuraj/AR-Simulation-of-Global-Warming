package com.example.globalwarmingusingar;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.animation.ModelAnimator;
import com.google.ar.sceneform.rendering.AnimationData;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;


public class after100 extends AppCompatActivity {

    private ArFragment arFragment;
    private ModelRenderable modelRenderable;
    private ModelAnimator modelAnimator;
    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after100);

        ImageView trunk = (ImageView) findViewById(R.id.trunk);
        ImageView skull = (ImageView) findViewById(R.id.skull);
        ImageView Ambulance = (ImageView) findViewById(R.id.Ambulance);
        ImageView bigboy = (ImageView) findViewById(R.id.horse);
        ImageView dark = (ImageView) findViewById(R.id.dark);
        ImageView cat = (ImageView) findViewById(R.id.cat);
        ImageView mask = (ImageView) findViewById(R.id.mask);
        ImageView trial = (ImageView) findViewById(R.id.trial);


        trunk.setOnClickListener(view -> {setUpModel("treeStump.sfb");setUpPlane();});
        skull.setOnClickListener(view -> {setUpModel("scene.sfb");setUpPlane();});
        Ambulance.setOnClickListener(view -> {setUpModel("Ambulance.sfb");setUpPlane();});
        bigboy.setOnClickListener(view -> {setUpModel("Triceratops_horridus_Marsh_1889-150k-4096.sfb");setUpPlane();});
        dark.setOnClickListener(view -> {setUpModel("dark_no_ground-1.sfb");setUpPlane();});
        cat.setOnClickListener(view -> {setUpModel("cat_skull.sfb");setUpPlane();});
        mask.setOnClickListener(view -> {setUpModel("uploads_files_2425065_N9GasMask.sfb");setUpPlane();});
        trial.setOnClickListener(view -> {setUpModel("Factory.sfb");setUpPlane();});



        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ar_fragment);

    }
    private void setUpModel(String object) {
        String s = object;
        ModelRenderable.builder()
                .setSource(this,Uri.parse(s))
                .build()
                .thenAccept(renderable -> modelRenderable = renderable)
                .exceptionally(throwable -> {
                    Toast.makeText(after100.this, "Model cannot be loaded", Toast.LENGTH_LONG).show();
                    return null;
                });
    }

    private void setUpPlane() {
        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
                Anchor anchor = hitResult.createAnchor();
                AnchorNode anchorNode = new AnchorNode(anchor);
                anchorNode.setParent(arFragment.getArSceneView().getScene());
                createModel(anchorNode);
            }
        });
    }
    private void createModel(AnchorNode anchorNode ) {
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        node.setParent(anchorNode);
        node.setRenderable(modelRenderable);
        node.select();
        animateModel(modelRenderable);

    }
    private void animateModel(ModelRenderable modelRenderable) {

        //if(modelAnimator != null && modelAnimator.isRunning())
        //modelAnimator.end();
        int animationCount = modelRenderable.getAnimationDataCount();
        if(animationCount != 0) {
            if (i == animationCount)
                i = 0;
            AnimationData animationData = modelRenderable.getAnimationData(i);
            modelAnimator = new ModelAnimator(animationData, modelRenderable);
            modelAnimator.start();
            modelAnimator.setRepeatCount(20);
            i++;

        }


    }
}