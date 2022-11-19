package com.example.blooddonationapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity implements UserClickListener {

    RecyclerView recyclerView;
    ImageView imageView;
    UserListAdapter adapter;

    List<User> users = new ArrayList<>();
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        dbHelper = new DatabaseHelper(this);

        recyclerView =  findViewById(R.id.rv_user_list);
        imageView = findViewById(R.id.iv_no_data);

        users = dbHelper.getAllUsers();

        if(users.isEmpty()){
            imageView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else{
            imageView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

        adapter = new UserListAdapter(users, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
             /*   if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(MainActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }*/
                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter().filter(newText);
                filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onUserDelClick(String username) {
        dbHelper.deleteUser(username);
        users = dbHelper.getAllUsers();
        adapter = new UserListAdapter(users, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    private void filter(String text) {
        // creating a new array list to filter our data.
        List<User> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (User item : users) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getUsername().toLowerCase().contains(text.toLowerCase()) ||
            item.getEmail().toLowerCase().contains(text.toLowerCase()) ||
            item.getBloodType().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            imageView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            imageView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            // at last we are passing that filtered
            // list to our adapter class.
            adapter.filterList(filteredlist);
        }
    }
}