package com.example.blooddonationapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserViewHolder> {
    List<User> users;
    Context c;
    UserClickListener listener;

    public UserListAdapter(List<User> userList, UserClickListener listener) {
        this.users = userList;
        this.c = c;
        this.listener = listener;
    }

    public void filterList(List<User> filterlist) {
        // below line is to add our filtered
        // list in our course array list.
        users = filterlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the layout

        View v = inflater.inflate(R.layout.user_item, parent, false);

        UserViewHolder viewHolder = new UserViewHolder(v, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
//        int idx = holder.getAdapterPosition();
        holder.username.setText(users.get(position).username);
        holder.phone.setText(users.get(position).phone);
        holder.address.setText(users.get(position).address);
        holder.email.setText(users.get(position).email);
        holder.bloodType.setText(users.get(position).bloodType);
        holder.id.setText(users.get(position).id);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
