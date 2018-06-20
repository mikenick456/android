package ntut.zxjte9411.hw9;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RecordActivity extends AppCompatActivity {
    private final String TAG = "Debug";
    private Intent intent, mediaIntent;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        registerForContextMenu(findViewById(R.id.record_linear_layout));
        listView = (ListView) findViewById(R.id.listView);
        intent = getIntent();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        arrayAdapter.addAll(intent.getStringArrayListExtra("data"));
        mediaIntent = new Intent(this, MediaService.class);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected");
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about_this_app:
                createAlertDialog();
                return true;
            case R.id.over:
                finish();
                return true;
            case R.id.play_background_music:
                startService(mediaIntent);
                return true;
            case R.id.stop_play_background_music:
                stopService(mediaIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        Log.d(TAG, "onCreateContextMenu");
        if (v.getId() == R.id.listView) {
            getMenuInflater().inflate(R.menu.menu, menu);
        }

        else{
            getMenuInflater().inflate(R.menu.menu, menu);
        }
}

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Log.d(TAG, "onContextItemSelected");
        onOptionsItemSelected(item);
        return super.onContextItemSelected(item);
    }

    public void createAlertDialog() {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setTitle(R.string.about_this_app);
        builder.setIcon(R.drawable.animal);
        builder.setMessage(R.string.choose_example);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //not thing to do
            }
        });
        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
