package com.example.quizgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
TextView txtcorrectans,txtincorrectans,txtemptyans,txtsuccess;
Button btquit,btagain;
int correct, incorrect, empty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        txtcorrectans=findViewById(R.id.textViewcorrectans);
        txtincorrectans=findViewById(R.id.textViewincorrectans);
        txtemptyans=findViewById(R.id.textViewemptyans);
        txtsuccess=findViewById(R.id.textViewsuccess);

        btquit=findViewById(R.id.buttonQuit);
        btagain=findViewById(R.id.buttonAgain);

        correct = getIntent().getIntExtra("correct",0);
        incorrect = getIntent().getIntExtra("incorrect",0);
        empty = getIntent().getIntExtra("empty",0);

        txtcorrectans.setText("Total Correct Answer : "+correct);
        txtincorrectans.setText("Total Incorrect Answer : "+incorrect);
        txtemptyans.setText("Total Empty Answer : "+empty);
        txtsuccess.setText("Success Rate : "+(correct*10)+"%");

        btquit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newintent = new Intent(Intent.ACTION_MAIN);
                newintent.addCategory(Intent.CATEGORY_HOME);
                newintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(newintent);
                finish();
            }
        });

        btagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ResultActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}