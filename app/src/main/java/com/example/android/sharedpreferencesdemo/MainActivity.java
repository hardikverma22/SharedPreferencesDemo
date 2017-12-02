package com.example.android.sharedpreferencesdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.android.sharedpreferencesdemo", Context.MODE_PRIVATE);
        //sharedPreferences.edit().putString("username","hardik").apply();
        //Log.i("Fetched Username",sharedPreferences.getString("username",""));
        ArrayList<String> friends = new ArrayList<>();
        friends.add("Mounika");
        friends.add("Sexy Joey");

        try {
            sharedPreferences.edit().putString("friends",ObjectSerializer.serialize(friends)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ArrayList<String> newFriends =
            (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences
                    .getString("friends",ObjectSerializer.serialize(new ArrayList<String>())));

            //if friends does not exist, then blank string will be returned() by sharedPrefernces
            // and since ObjectSerializer.deserialize is used above
            //thereby we have serialize the "" so that in case it  is returned deserialize will not throw any error

            Log.i("newFriends",newFriends.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
