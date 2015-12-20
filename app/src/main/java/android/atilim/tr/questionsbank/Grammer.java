package android.atilim.tr.questionsbank;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Grammer extends AppCompatActivity {

    public static String[] mainMenuList={"ALL TENSES","CONDITINAL & WISH CLAUSES","THE PASSIVE VOICE","RELATIVE CLAUSE","ADVERBIALS"};
    CustomAdapter customAdapter;
    private ArrayList<String> questionCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int position;
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

        try {
            DatabaseHelper dBHelper = new DatabaseHelper(Grammer.this);
            DBAdapter dbAdapter = new DBAdapter().GetWritableDatabase(dBHelper);
            dbAdapter = dbAdapter.GetWritableDatabase(dBHelper);
            Cursor cursor = dbAdapter.GetQuestionCategories();

            if (cursor != null) {
                cursor.moveToFirst();
                questionCategoryList = new ArrayList<String>();
                int i=0;
                while (!cursor.isAfterLast()) {

                    questionCategoryList.add(cursor.getString(cursor.getColumnIndex("Question")));
                    Log.d("", "onCreate: " + questionCategoryList.get(i++));
                    cursor.moveToNext();
                }

                cursor.close();
            }
        } catch (Exception e) {
            Log.e("tag", e.getMessage());
        }
        
        ListView listView=(ListView) findViewById(R.id.listView);
        //String[] tempArray = questionCategoryList.toArray(new String[questionCategoryList.size()]);

        String[] array = new String[questionCategoryList.size()];
        for(int i = 0; i < questionCategoryList.size(); i++) array[i] = questionCategoryList.get(i);

        customAdapter=new CustomAdapter(this, array);
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
