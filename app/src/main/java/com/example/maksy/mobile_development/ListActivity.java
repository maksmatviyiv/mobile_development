package com.example.maksy.mobile_development;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ListView listViewInfo = findViewById(R.id.listView);

        SharedPreferences sp = getSharedPreferences("app_settings", Context.MODE_PRIVATE);
        String entryListString = sp.getString("entry_list", "");

        if (entryListString.isEmpty())
            return;
        String[] entryList = entryListString.split("&");
        for (int i = 0; i < entryList.length; i++) {
            String[] data = entryList[i].split("\\|");
            entryList[i] = data[0] + ", " + data[1] + ", " + data[2];
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                                                    android.R.layout.simple_list_item_1, entryList);
        listViewInfo.setAdapter(adapter);
    }
}
