package com.example.quizgame;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.HashSet;

public class QuizActivity extends AppCompatActivity {
TextView txtcorrect,txtincorrect,txtempty,txtquestion;
ImageView imglogo,imgnext;
Button btA,btB,btC,btD;

private FlagsDatabase fdatabase;
private  ArrayList<FlagsModle> questionsList;

int correct = 0;
int incorrect = 0;
int empty = 0;
int question = 0;

private FlagsModle correctFlag;
private  ArrayList<FlagsModle> incorrectOptionList;
HashSet<FlagsModle> mixOption = new HashSet<>();
ArrayList<FlagsModle> options=new ArrayList<>();

boolean buttonControl=false;
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

     txtcorrect=findViewById(R.id.textViewCorrect);
     txtincorrect=findViewById(R.id.textViewIncorrect);
     txtempty=findViewById(R.id.textViewEmpty);
     txtquestion=findViewById(R.id.textViewQuestion);

        imglogo=findViewById(R.id.imageViewLogo);
        imgnext=findViewById(R.id.imageViewNext);

        btA=findViewById(R.id.buttonA);
        btB=findViewById(R.id.buttonB);
        btC=findViewById(R.id.buttonC);
        btD=findViewById(R.id.buttonD);

        fdatabase = new FlagsDatabase(QuizActivity.this);
        questionsList= new FlagsDAO().getRandomTenQuestion(fdatabase);

        loadQuestion();

      btA.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              answerControl(btA);
          }
      });

     btB.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             answerControl(btB);
         }
     });

     btC.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             answerControl(btC);
         }
     });

     btD.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
                answerControl(btD);
         }
     });

     imgnext.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             question++;
            if(!buttonControl && question<10)
            {
                empty++;
                txtempty.setText("Empty : "+empty);
                loadQuestion();
            }
            else if(buttonControl && question<10)
            {
                loadQuestion();

                btA.setClickable(true);
                btB.setClickable(true);
                btC.setClickable(true);
                btD.setClickable(true);

                btA.setBackgroundColor(getResources().getColor(R.color.btpurple));
                btB.setBackgroundColor(getResources().getColor(R.color.btpurple));
                btC.setBackgroundColor(getResources().getColor(R.color.btpurple));
                btD.setBackgroundColor(getResources().getColor(R.color.btpurple));
            }
             else if(question == 10)
            {
                Intent i = new Intent(QuizActivity.this, ResultActivity.class);
                i.putExtra("correct",correct);
                i.putExtra("incorrect",incorrect);
                i.putExtra("empty",empty);
                startActivity(i);
                finish();
            }
             buttonControl = false;
         }
     });
    }
public  void  loadQuestion()
{
    txtquestion.setText("Question : "+(question+1));
    correctFlag =  questionsList.get(question);

    imglogo.setImageResource(getResources().getIdentifier(correctFlag.getFlag_image(),"drawable",getPackageName()));

    incorrectOptionList= new FlagsDAO().getRandomThreeOption(fdatabase,correctFlag.getFlag_id());
    mixOption.clear();
    mixOption.add(correctFlag);
    mixOption.add(incorrectOptionList.get(0));
    mixOption.add(incorrectOptionList.get(1));
    mixOption.add(incorrectOptionList.get(2));

    options.clear();
    for (FlagsModle flg : mixOption)
    {
        options.add(flg);
    }
    btA.setText(options.get(0).getFlag_name());
    btB.setText(options.get(1).getFlag_name());
    btC.setText(options.get(2).getFlag_name());
    btD.setText(options.get(3).getFlag_name());
}
public void answerControl(Button bt)
{
    String btText = bt.getText().toString();
    String correctAnswer = correctFlag.getFlag_name();

    if(btText.equals(correctAnswer))
    {
        bt.setBackgroundColor(Color.GREEN);
        correct++;
    }
    else
    {
        //bt.setBackgroundColor(Color.BLACK);
        bt.setBackgroundColor(Color.RED);
        incorrect++;


        if(btA.getText().toString().equals(correctAnswer))
        {
            btA.setBackgroundColor(Color.GREEN);
        }
        if(btB.getText().toString().equals(correctAnswer))
        {
            btB.setBackgroundColor(Color.GREEN);
        }
        if(btC.getText().toString().equals(correctAnswer))
        {
            btC.setBackgroundColor(Color.GREEN);
        }

        if(btD.getText().toString().equals(correctAnswer))
        {
            btD.setBackgroundColor(Color.GREEN);
        }
    }
    btA.setClickable(false);
    btB.setClickable(false);
    btC.setClickable(false);
    btD.setClickable(false);
    txtcorrect.setText("Correct : "+correct);
    txtincorrect.setText("Incorrect : "+incorrect);

    buttonControl=true;
}
}