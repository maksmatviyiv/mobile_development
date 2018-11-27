package com.example.maksy.mobile_development;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_view_author_photo);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);

        Call<List<Photo>> call = api.getPhoto();
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                List<Photo> authorPhoto = response.body();

                for (Photo a : authorPhoto){
                    List<Photo> authorList = response.body();
                    Log.d("name", a.getAuthor());

                    String[] author = new String[authorList.size()];

                    for (int i = 0; i < authorList.size(); i++) {
                        author[i] = authorList.get(i).getAuthor();
                    }
                    listView.setAdapter(new ArrayAdapter(getApplicationContext(),
                                                       android.R.layout.simple_list_item_1, author));
                }
            }
            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

