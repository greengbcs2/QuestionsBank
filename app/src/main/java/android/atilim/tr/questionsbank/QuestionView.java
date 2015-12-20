package android.atilim.tr.questionsbank;

import android.atilim.tr.questionsbank.ControllerObject;
import android.atilim.tr.questionsbank.Questions;
import android.atilim.tr.questionsbank.R;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionView extends AppCompatActivity {
    private  ArrayList<Questions> arrayOfQuestions;
    private int currentQuestionNumber;
    private int trueNumberofQuestions;
    private int falseQuestionNumber;
    private ArrayList<Questions> questionsList;
    private int position;
    private Questions question;
    private int questionNumber;

    public int getCurrentQuestionNumber() {
        return currentQuestionNumber;
    }

    public int getTrueNumberofQuestions() {
        return trueNumberofQuestions;
    }

    public int getFalseQuestionNumber() {
        return falseQuestionNumber;
    }

    private String[] answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ControllerObject controllerObject=new ControllerObject();
        arrayOfQuestions=controllerObject.getQuestions();

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

       TextView questionText=(TextView) findViewById(R.id.question_text);
         /*TextView currentQuestionText=(TextView) findViewById(R.id.question_number_text);
        TextView falseNumberofQuestions=(TextView) findViewById(R.id.false_number_text );
        TextView trueNumberofQuestions=(TextView) findViewById(R.id.true_number_text );
        answers = arrayOfQuestions.get(currentQuestionNumber).getAnswers();
        String questionString=arrayOfQuestions.get(currentQuestionNumber).getQuestionText();
        currentQuestionNumber=arrayOfQuestions.get(currentQuestionNumber).getIdQuestion();*/


        ListView listView = (ListView) findViewById(R.id.listView);

        try {
            DatabaseHelper dBHelper = new DatabaseHelper(QuestionView.this);
            DBAdapter dbAdapter = new DBAdapter().GetWritableDatabase(dBHelper);
            dbAdapter = dbAdapter.GetWritableDatabase(dBHelper);
            Cursor cursor = dbAdapter.GetQuestionsByQuestionCategoryId(position);

            if (cursor != null) {
                cursor.moveToFirst();
                questionsList = new ArrayList<Questions>();
                int i=0;
                while (!cursor.isAfterLast()) {
                    question=new(cursor.getString(cursor.getColumnIndex("Question")),questionNumber +1,30,);
                    questionsList.add(question);
                    cursor.moveToNext();
                }

                cursor.close();
               
            }
        } catch (Exception e) {
            Log.e("tag", e.getMessage());
        }



        questionText.setText((CharSequence) questionCategoryList.get(1));
      //  currentQuestionText.setText(currentQuestionNumber);
       // falseNumberofQuestions.setText(getFalseQuestionNumber());
       // trueNumberofQuestions.setText(getTrueNumberofQuestions());


    }

}
