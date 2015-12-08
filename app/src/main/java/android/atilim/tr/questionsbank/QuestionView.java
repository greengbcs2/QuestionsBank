package android.atilim.tr.questionsbank;

import android.atilim.tr.questionsbank.ControllerObject;
import android.atilim.tr.questionsbank.Questions;
import android.atilim.tr.questionsbank.R;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionView extends AppCompatActivity {
    private  ArrayList<Questions> arrayOfQuestions;
    private int currentQuestionNumber;
    private int trueNumberofQuestions;
    private int falseQuestionNumber;

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

        TextView questionText=(TextView) findViewById(R.id.question_text);
        TextView currentQuestionText=(TextView) findViewById(R.id.question_number_text);
        TextView falseNumberofQuestions=(TextView) findViewById(R.id.false_number_text );
        TextView trueNumberofQuestions=(TextView) findViewById(R.id.true_number_text );
        answers = arrayOfQuestions.get(currentQuestionNumber).getAnswers();
        String questionString=arrayOfQuestions.get(currentQuestionNumber).getQuestionText();
        currentQuestionNumber=arrayOfQuestions.get(currentQuestionNumber).getIdQuestion();

        questionText.setText(questionString);
        currentQuestionText.setText(currentQuestionNumber);
        falseNumberofQuestions.setText(getFalseQuestionNumber());
        trueNumberofQuestions.setText(getTrueNumberofQuestions());


    }

}
