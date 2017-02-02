package com.example.user.a0708_notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;

public class EditNoteActivity extends AppCompatActivity {
    public EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(this.getIntent().getStringExtra("Nowy")==""){
        }else {
            et = (EditText) findViewById(R.id.editText);
            et.setText(this.getIntent().getStringExtra("Str"));
        }

    }

    //way to move back to parent activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(et.getText().toString().equals("")!=true){
            switch (item.getItemId()) {
                case android.R.id.home:
                    MainActivity.note.add(et.getText().toString());
                    MainActivity.notesData.edit().putString(et.getText().toString(),et.getText().toString()).commit();
                    MainActivity.adapter.notifyDataSetChanged();
                    // app icon in action bar clicked; goto parent activity.
                    // MainActivity.note.
                    this.finish();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }else {
            switch (item.getItemId()) {
                case android.R.id.home:
                    // MainActivity.note.
                    this.finish();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }
        }
}
