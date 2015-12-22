package android.atilim.tr.questionsbank;

import android.atilim.tr.questionsbank.ControllerObject;
import android.atilim.tr.questionsbank.Questions;
import android.atilim.tr.questionsbank.R;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class QuestionView extends AppCompatActivity {

    private int currentQuestionNumber;
    private int numberOfTrueQuestions;
    private int numberOfFalseQuestions;
    private ArrayList<Questions> questionsList;
    private int position;
    private Questions question;
    private int questionNumber;
    private TextView optionA;
    private TextView optionE;
    private TextView optionB;
    private TextView optionC;
    private TextView optionD;
    private int selectedOption;

    public int getCurrentQuestionNumber() {
        return currentQuestionNumber;
    }



    private String[] answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                position=0;
            } else {
                position= extras.getInt("position");
            }
        } else {
            position= (int) savedInstanceState.getSerializable("position");
        }

       final TextView questionText=(TextView) findViewById(R.id.question_text);
       final TextView currentQuestionText=(TextView) findViewById(R.id.question_number_text);
        final TextView falseNumberofQuestions=(TextView) findViewById(R.id.false_number_text );
        final TextView trueNumberofQuestions=(TextView) findViewById(R.id.true_number_text );
        final Button buttonNext=(Button)findViewById(R.id.button);

        optionA=(TextView) findViewById(R.id.a_text);
        optionB=(TextView) findViewById(R.id.b_text);
        optionC=(TextView) findViewById(R.id.c_text);
        optionD=(TextView) findViewById(R.id.d_text);
        optionE=(TextView) findViewById(R.id.e_text);




        ListView listView = (ListView) findViewById(R.id.listView);

        try {
            DatabaseHelper dBHelper = new DatabaseHelper(QuestionView.this);
            DBAdapter dbAdapter = new DBAdapter().GetWritableDatabase(dBHelper);
            dbAdapter = dbAdapter.GetWritableDatabase(dBHelper);
            Cursor cursor = dbAdapter.GetQuestionsByQuestionCategoryId(position+1);

            questionNumber=1;
            if (cursor != null) {
                cursor.moveToFirst();
                questionsList = new ArrayList<Questions>();

                String []optionsArray;
                int temp=0;
                while (!cursor.isAfterLast()) {
                    optionsArray=new String[5];
                    Cursor cursorOptions = dbAdapter.GetQuestionOptionsByQuestionId(questionNumber);
                    cursorOptions.moveToFirst();
                    String questionTextLine=cursor.getString(cursor.getColumnIndex("Question"));
                    String answerQuestion= cursor.getString(cursor.getColumnIndex("Answer"));
                    int answerId=0;
                    answerId=answerTextToId(answerQuestion);


                   for(int i=0;i<5;i++) {
                       int optionNumber=cursorOptions.getColumnIndex("Option");
                        optionsArray[i]=cursorOptions.getString(optionNumber);
                       cursorOptions.moveToNext();
                      // Log.d("ulas options",optionsArray[i]);
                    }
                    //Log.d("ulas",questionTextLine);
                    Log.d("ulas ", "ulas 0000 "+optionsArray[0]);
                    question=new Questions(cursor.getString(cursor.getColumnIndex("Question")),questionNumber++,30,optionsArray,answerId);
                    questionsList.add(question);
                   // Log.d("ulas ", "ulas 111   " + questionsList.get(temp).getAnswers()[1]);
                    temp++;
                    cursor.moveToNext();
                }
                /*for(int j=0;j<questionsList.size();j++){
                    Log.d("ulas", "ulas 000 arrayq "+questionsList.get(j).getAnswers()[1]);
                }*/

                cursor.close();

            }
        } catch (Exception e) {
            Log.e("tag", e.getMessage());
        }




        questionText.setText(questionsList.get(0).getQuestionText());
        currentQuestionText.setText("QST"+String.valueOf(questionsList.get(0).getIdQuestion()));
        falseNumberofQuestions.setText("False:0");
        trueNumberofQuestions.setText("True:0");
        optionA.setText("A)"+questionsList.get(0).getAnswers()[0]);
        optionB.setText("B)"+questionsList.get(0).getAnswers()[1]);
        optionC.setText("C)"+questionsList.get(0).getAnswers()[2]);
        optionD.setText("D)" + questionsList.get(0).getAnswers()[3]);
        optionE.setText("E)" + questionsList.get(0).getAnswers()[4]);

        optionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {setOthersWhite();
                optionA.setBackgroundColor(Color.GREEN);
                selectedOption=1;
            }
        });
        optionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {setOthersWhite();
                optionB.setBackgroundColor(Color.GREEN);selectedOption=2;
            }
        });
        optionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {setOthersWhite();
                optionC.setBackgroundColor(Color.GREEN);selectedOption=3;
            }
        });
        optionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {setOthersWhite();
                optionD.setBackgroundColor(Color.GREEN);selectedOption=4;
            }
        });
        optionE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {setOthersWhite();
                optionE.setBackgroundColor(Color.GREEN);selectedOption=5;
            }
        });
      /*  for(int j=0;j<questionsList.size();j++){
            Log.d("ulas", "ulas arrayq "+questionsList.get(j).getAnswers()[1]);
        }*/

        if(currentQuestionNumber-1==questionsList.size())
            buttonNext.setClickable(false);
        buttonNext.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                setOthersWhite();
                currentQuestionNumber++;
                int size=questionsList.size();
                if(currentQuestionNumber+1==questionsList.size())
                    buttonNext.setEnabled(false);


                if(questionsList.get(currentQuestionNumber).getRightAnswer()==selectedOption)
                    numberOfTrueQuestions++;
                else
                numberOfFalseQuestions++;
                questionText.setText(questionsList.get(currentQuestionNumber).getQuestionText());
                currentQuestionText.setText("QST: "+String.valueOf(questionsList.get(currentQuestionNumber).getIdQuestion()));
                falseNumberofQuestions.setText("False: " + String.valueOf(numberOfFalseQuestions));
                trueNumberofQuestions.setText("True: "+String.valueOf(numberOfTrueQuestions));
                //String a[]=questionsList.get(currentQuestionNumber).getAnswers();


               // Log.d("ulas","ozan 2222  "+currentQuestionNumber+" ---"+temp2+" ---"+questionsList.get(temp2).getAnswers()[0]);
                optionA.setText("A)" + questionsList.get(currentQuestionNumber).getAnswers()[0]);
                optionB.setText("B)" + questionsList.get(currentQuestionNumber).getAnswers()[1]);
                optionC.setText("C)"+questionsList.get(currentQuestionNumber).getAnswers()[2]);
                optionD.setText("D)"+questionsList.get(currentQuestionNumber).getAnswers()[3]);
                optionE.setText("E)"+questionsList.get(currentQuestionNumber).getAnswers()[4]);
            }
        });
    // currentQuestionText.setText(currentQuestionNumber);
       // falseNumberofQuestions.setText(getFalseQuestionNumber());
       // trueNumberofQuestions.setText(getTrueNumberofQuestions());




    }

    private int answerTextToId(String answerQuestion) {
        int answerId = 1;
        if(answerQuestion.equalsIgnoreCase("a"))
            answerId=1;
        else if(answerQuestion.equalsIgnoreCase("b"))
            answerId=2;
        else if(answerQuestion.equalsIgnoreCase("c"))
            answerId=3;
        else if(answerQuestion.equalsIgnoreCase("d"))
            answerId=4;
        else if(answerQuestion.equalsIgnoreCase("e"))
            answerId=5;
        return answerId;
    }

    private void setOthersWhite() {
        optionA.setBackgroundColor(Color.TRANSPARENT);
        optionB.setBackgroundColor(Color.TRANSPARENT);
        optionC.setBackgroundColor(Color.TRANSPARENT);
        optionD.setBackgroundColor(Color.TRANSPARENT);
        optionE.setBackgroundColor(Color.TRANSPARENT);
    }


}
