package com.example.segundoauqui.moviedb;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.segundoauqui.moviedb.model.Example;
import com.example.segundoauqui.moviedb.model.Result;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    final Context context = this;
    private Button btnSearch;
    private EditText et1;
    private static final String TAG = "MainActivity";
    private RetrofitHelper retrofitHelper;
    RecyclerView rvReprofit;
    RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        rvReprofit = (RecyclerView) findViewById(R.id.rvRetrofit);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        rvReprofit = (RecyclerView) findViewById(R.id.rvRetrofit);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        rvReprofit.setLayoutManager(layoutManager);
        rvReprofit.setItemAnimator(itemAnimator);

        getRetrofit();


        btnSearch = (Button) findViewById(R.id.btnSearch);
        et1 = (EditText) findViewById(R.id.et1);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompts, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Submit",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        //et1.setText(userInput.getText());
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });
    }



    public void getRetrofit() {
        retrofit2.Call<Example> exampleDataCall = RetrofitHelper.getExampleCall();
        exampleDataCall.enqueue(new retrofit2.Callback<Example>() {
            @Override
            public void onFailure(Call<Example> call, Throwable t) {
            }

            @Override
            public void onResponse(Call<Example> call, final Response<Example> response) {
                //  Log.d(TAG, "onResponse: " + response.body().getItems());
                ArrayList<Result> example = (ArrayList<Result>) response.body().getResults();
                RetrofitAdapter randomsListAdapter = new RetrofitAdapter(example);
                rvReprofit.setAdapter(randomsListAdapter);
                randomsListAdapter.notifyDataSetChanged();
            }
        });

    }
}
