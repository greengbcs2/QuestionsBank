package android.atilim.tr.questionsbank;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
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
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View container, int position, long id) {
                if(position==0){
                    Intent intent = new Intent(Grammer.this, QuestionView.class);
                    startActivity(intent);
                }
            }
        };
        listView.setOnItemClickListener(itemClickListener);

    }

}
