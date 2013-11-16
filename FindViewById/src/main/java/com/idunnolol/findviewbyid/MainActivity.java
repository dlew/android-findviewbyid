package com.idunnolol.findviewbyid;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

public class MainActivity extends ActionBarActivity {

    private Random mRandom;

    private ViewGroup mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRandom = new Random();

        setContentView(R.layout.activity_main);

        mContainer = (ViewGroup) findViewById(R.id.test_container);

        findViewById(R.id.add_depth_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDepth(mContainer);
            }
        });
        findViewById(R.id.add_child_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        findViewById(R.id.run_test_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        findViewById(R.id.reset_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    // Recursively visit each ViewGroup in the hierarchy; if it has no children,
    // add a child layout to it.
    private void addDepth(ViewGroup view) {
        int childCount = view.getChildCount();
        if (childCount == 0) {
            // Change layout params - depend on child to define basic width/height
            ViewGroup.LayoutParams params = view.getLayoutParams();
            params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;

            view.addView(inflateChildLayout(view));
        }
        else {
            for (int a = 0; a < childCount; a++) {
                addDepth((ViewGroup) view.getChildAt(a));
            }
        }
    }

    private ViewGroup inflateChildLayout(ViewGroup parent) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.child, parent, false);

        // Give it a random bg color so we can distinguish it in the UI
        int color = Color.rgb(mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
        viewGroup.setBackgroundColor(color);

        return viewGroup;
    }
}
