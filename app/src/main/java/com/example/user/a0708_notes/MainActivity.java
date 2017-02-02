package com.example.user.a0708_notes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public static SharedPreferences notesData;
    public static ArrayList<String> note = new ArrayList<>();
    public static ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notesData = this.getSharedPreferences("Preferences", MODE_PRIVATE);

        // load data from sharedPreferences

        for(String s:notesData.getAll().keySet()){
            note.add(s);
        }


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, note);
        ListView view = (ListView) findViewById(R.id.listView);
        view.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(getApplicationContext(), EditNoteActivity.class);
                it.putExtra("Text", note.get(i));
                startActivity(it);
            }
        });

        view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Title")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setMessage("Do you want to remove this element?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int j) {
                                notesData.edit().remove(note.get(i)).commit();
                                note.remove(i);
                                adapter.notifyDataSetChanged();

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                return;
                            }
                        })
                        .show();
            return false;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.addItem){
            Intent it = new Intent(this,EditNoteActivity.class);
            it.putExtra("Nowy","");
            startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }
}

