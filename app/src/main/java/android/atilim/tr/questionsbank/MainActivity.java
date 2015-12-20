package android.atilim.tr.questionsbank;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static String[] mainMenuList={"VOCABULARY","GRAMMER","YDS SORULARI","SÖZLÜK","AYARLAR"};
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listView=(ListView) findViewById(R.id.listView);
        customAdapter=new CustomAdapter(this, mainMenuList);
        listView.setAdapter(customAdapter);
        // Item Click Listener for the listview
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View container, int position, long id) {
                if(position==0){
                 Intent intent = new Intent(MainActivity.this, Grammer.class);
                    intent.putExtra("position",position);
                 startActivity(intent);
                }
                else if(position==1){
                    Intent intent = new Intent(MainActivity.this, Grammer.class);
                    intent.putExtra("position",position);
                    startActivity(intent);
                }
                else if(position==2){
                    Intent intent = new Intent(MainActivity.this, Grammer.class);
                    intent.putExtra("position",position);
                    startActivity(intent);
                }
                else if(position==3){
                    Intent intent = new Intent(MainActivity.this, Grammer.class);
                    intent.putExtra("position",position);
                    startActivity(intent);
                }
                else if(position==4){
                    Intent intent = new Intent(MainActivity.this, Grammer.class);
                    intent.putExtra("position",position);
                    startActivity(intent);
                }
            }
        };

        // Setting the item click listener for the listview
        listView.setOnItemClickListener(itemClickListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
