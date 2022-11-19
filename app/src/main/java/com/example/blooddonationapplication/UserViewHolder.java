package com.example.blooddonationapplication;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class UserViewHolder extends RecyclerView.ViewHolder{
    TextView username;
    TextView phone;
    TextView email;
    TextView address;
    TextView bloodType;
    TextView id;
    Button btnUpdate;
    Button btnDel;
    View v;
    public UserViewHolder(@NonNull View itemView, UserClickListener listener) {
        super(itemView);
        id = (TextView) itemView.findViewById(R.id.tv_id);
        username = (TextView) itemView.findViewById(R.id.tv_username);
        phone = (TextView) itemView.findViewById(R.id.tv_phone);
        email = (TextView) itemView.findViewById(R.id.tv_email);
        address = (TextView) itemView.findViewById(R.id.tv_address);
        bloodType = (TextView) itemView.findViewById(R.id.tv_blood_type);
        btnUpdate = (Button) itemView.findViewById(R.id.btn_update);
        btnDel = (Button) itemView.findViewById(R.id.btn_del);

        btnDel.setOnClickListener(view -> {
            listener.onUserDelClick(username.getText().toString());
        });

        btnUpdate.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), UpdateUserActivity.class);
            i.putExtra(UpdateUserActivity.ID, id.getText().toString());
            i.putExtra(UpdateUserActivity.USERNAME, username.getText().toString());
            i.putExtra(UpdateUserActivity.PHONE, phone.getText().toString());
            i.putExtra(UpdateUserActivity.ADDRESS, address.getText().toString());
            view.getContext().startActivity(i);

        });

        v = itemView;

    }
}
