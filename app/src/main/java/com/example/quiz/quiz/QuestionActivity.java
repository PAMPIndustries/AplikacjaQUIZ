package com.example.quiz.quiz;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class QuestionActivity extends ActionBarActivity implements View.OnClickListener {


    Button buttonA;
    Button buttonB;
    Button buttonC;
    Button buttonD;
    TextView nrquestion;
    TextView question;

    Random randomGenerator = new Random();
    double score = 0;
    int cnt = 0;
    Question CurrentQuestion = null;

    ArrayList<Question> Questions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Questions = new ArrayList<Question>()
        { {
                add(new Question()
                { {
                        QuestionText = "Ulubione jedzenie Studentów WAT?";
                        Answers = new ArrayList<String>() { { add("Zapieksa w ŻAK"); add("Ziemniaki z cebulą"); add("Kebab"); add("Cebula z ziemniakami"); } };
                        CorrectAnswerIndex = 0;
                    }});
                add(new Question()
                { {
                        QuestionText = "Co się robi ze sprzętem elektronicznym na WAT?";
                        Answers = new ArrayList<String>() { { add("Włancza"); add("Włącza"); add("Włącza"); add("Nie Włancza, bo nie działa"); } };
                        CorrectAnswerIndex = 3;

                    }});
                add(new Question()
                { {
                        QuestionText = "Wyposażenie łazienek kobiet na WAT?";
                        Answers = new ArrayList<String>() { { add("Umywalki, Sedesy"); add("Umywalki, Sedesy, Prysznice"); add("Umywalki, Sedesy, Pisuary"); add("Sedesy"); } };
                        CorrectAnswerIndex = 2;
                    }});
                add(new Question()
                { {
                        QuestionText = "Co robi się podczas przerwy na Wacie?";
                        Answers = new ArrayList<String>() { { add("Przygotowujesz się do ćwiczeń"); add("Czytasz notatki"); add("Uczysz się"); add("Jesz zapiekanke"); } };
                        CorrectAnswerIndex = 3;
                    }});
                add(new Question()
                { {
                        QuestionText = "Jak nazywa się maskotka WAT?";
                        Answers = new ArrayList<String>() { { add("Waciak"); add("Janusz"); add("Mariusz"); add("Kazimierz"); } };
                        CorrectAnswerIndex = 0;
                    }});
            } };

        buttonA = (Button) findViewById(R.id.buttonA);
        buttonB = (Button) findViewById(R.id.buttonB);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonD = (Button) findViewById(R.id.buttonD);
        question = (TextView) findViewById(R.id.PytanieView);
        nrquestion = (TextView) findViewById(R.id.nrPytaniaView);

        SetQuestionAndAnswers();

        buttonA.setOnClickListener(this);
        buttonB.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonD.setOnClickListener(this);


    }

    private Question GetRandomQuestionAndRemove()
    {
        int randomInt = randomGenerator.nextInt(Questions.size());

        Question question = Questions.get(randomInt);

        Questions.remove(question);

        return question;
    }

    private  void SetQuestionAndAnswers()
    {
        Question newQuestion = GetRandomQuestionAndRemove();

        nrquestion.setText("Pytanie nr " + (cnt+1));
        question.setText(newQuestion.QuestionText);
        buttonA.setText(newQuestion.Answers.get(0));
        buttonB.setText(newQuestion.Answers.get(1));
        buttonC.setText(newQuestion.Answers.get(2));
        buttonD.setText(newQuestion.Answers.get(3));

        CurrentQuestion = newQuestion;

        cnt++;
    }

    @Override
    public void onClick(View v) {
        String senderText = ((Button)v).getText().toString();

        if (senderText == CurrentQuestion.Answers.get(CurrentQuestion.CorrectAnswerIndex))
        {
            score++;
        }

        if (Questions.size() == 0) {
            Intent iinent= new Intent(QuestionActivity.this ,WynikActivity.class);
            iinent.putExtra("SCORE", score);

            startActivity(iinent);
        } else {
            SetQuestionAndAnswers();
        }
    }
}
