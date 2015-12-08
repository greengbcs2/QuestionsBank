package android.atilim.tr.questionsbank;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

public class Grammer extends AppCompatActivity {

    public static String[] mainMenuList={"ALL TENSES","CONDITINAL & WISH CLAUSES","THE PASSIVE VOICE","RELATIVE CLAUSE","ADVERBIALS"};
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView=(ListView) findViewById(R.id.listView);
        customAdapter=new CustomAdapter(this, mainMenuList);
        listView.setAdapter(customAdapter);

    }

}
