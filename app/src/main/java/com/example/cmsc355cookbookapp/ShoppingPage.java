package com.example.cmsc355cookbookapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShoppingPage extends AppCompatActivity {
    ListView listView;
    ArrayList<String> items;
    ArrayAdapter<String> adapter;

    EditText input;
    ImageView enter;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_page);

        listView = findViewById(R.id.listview);
        input = findViewById(R.id.input);
        enter = findViewById(R.id.add);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = items.get(i);
                makeToast(name);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                makeToast("Just removed: " + items.get(i) + "from the list!");
                removeItem(i);
                return false;
            }
        });

        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = input.getText().toString();
                if(text == null || text.length() == 0) {
                    makeToast("Enter an item you'd like to add to the cart: ");
                }else{
                    addItem(text);
                    input.setText("");
                    makeToast("Just added: " + text + "to the cart!");
                }
            }
        });
    }
    public void removeItem(int remove){
        items.remove(remove);
        adapter.notifyDataSetChanged();
    }

    public void addItem(String item){
        items.add(item);
        adapter.notifyDataSetChanged();
    }

    Toast t;

    private void makeToast(String s){
        if(t != null) t.cancel();
        t = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
        t.show();
    }
}