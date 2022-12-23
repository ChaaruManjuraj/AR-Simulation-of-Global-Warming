package com.example.globalwarmingusingar;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
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


public class ArActivity extends AppCompatActivity {

    private ArFragment arFragment;
    private ModelRenderable modelRenderable;
    private ModelAnimator modelAnimator;
    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);
        ImageView cow = (ImageView) findViewById(R.id.cow);
        ImageView elephant = (ImageView) findViewById(R.id.elephant) ;
        ImageView tree = (ImageView) findViewById(R.id.tree);
        ImageView factor= (ImageView) findViewById(R.id.factory);
        ImageView car= (ImageView) findViewById(R.id.car);
        ImageView tree2= (ImageView) findViewById(R.id.treesathish);
        ImageView plant= (ImageView) findViewById(R.id.plant);
        ImageView grass2 = (ImageView) findViewById(R.id.grass2);
        ImageView grass1 = (ImageView) findViewById(R.id.grass1);
        ImageView grass = (ImageView) findViewById(R.id.grass);
        ImageView trial = (ImageView) findViewById(R.id.trial);
        ImageView human = (ImageView) findViewById(R.id.human);



        cow.setOnClickListener(view -> {setUpModel("cow.sfb");setUpPlane();});
        elephant.setOnClickListener(view -> {setUpModel("elephant.sfb");setUpPlane();});
        tree.setOnClickListener(view -> {setUpModel("QueenPalmTree.sfb");setUpPlane();});
        factor.setOnClickListener(view -> {setUpModel("CUPIC_FACTORy.sfb");setUpPlane();});
        car.setOnClickListener(view -> {setUpModel("Convertible.sfb");setUpPlane();});
        tree2.setOnClickListener(view -> {setUpModel("TreeSathish.sfb");setUpPlane();});
        plant.setOnClickListener(view -> {setUpModel("Plant.sfb");setUpPlane();});
        grass2.setOnClickListener(view -> {setUpModel("model.sfb");setUpPlane();});
        grass1.setOnClickListener(view -> {setUpModel("Tuft of grass.sfb");setUpPlane();});
        grass.setOnClickListener(view -> {setUpModel("PUSHILIN_grass.sfb");setUpPlane();});
        trial.setOnClickListener(view -> {setUpModel("Factory.sfb");setUpPlane();});
        human.setOnClickListener(view -> {setUpModel("rp_nathan_animated_003_walking.sfb");setUpPlane();});






        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ar_fragment);
//        setUpModel();
//        setUpPlane();
    }
    private void setUpModel(String object) {
        String s = object;
        ModelRenderable.builder()
                .setSource(this,Uri.parse(s))
                .build()
                .thenAccept(renderable -> modelRenderable = renderable)
                .exceptionally(throwable -> {
                    Toast.makeText(ArActivity.this, "Model cannot be loaded", Toast.LENGTH_LONG).show();
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
//        Button button = findViewById(R.id.button);
//                  button.setOnClickListener(view -> animateModel(modelRenderable));

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