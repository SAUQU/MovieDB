package com.example.segundoauqui.moviedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ShowPix extends AppCompatActivity {

    ImageView ivSmall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pix);

        ivSmall = (ImageView) findViewById(R.id.ivSmall);

        Intent intent = getIntent();

        Glide.with(this).load(intent.getStringExtra("image").toString()).into(ivSmall);




    }
}
