package com.example.android.credify;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class userAdapter extends ArrayAdapter<user> {
    public userAdapter(Activity context, ArrayList<user> Users) {
        super(context, 0, Users);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        user currentUser = getItem(position);

        TextView name = (TextView) listItemView.findViewById(R.id.name);
        name.setText(currentUser.getName());

        TextView emailTextView = (TextView) listItemView.findViewById(R.id.email);
        emailTextView.setText(currentUser.getEmail());

        TextView creditView = (TextView) listItemView.findViewById(R.id.credits);
        creditView.setText(String.valueOf(currentUser.getCredit()));

        return listItemView;
    }
}
