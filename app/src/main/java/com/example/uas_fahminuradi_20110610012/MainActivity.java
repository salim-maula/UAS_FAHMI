package com.example.uas_fahminuradi_20110610012;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AdapterList adapterList;
    private List<ModelList> modelListItems;
    private RequestQueue requestQueue;
    public static final String JSON_URL = "https://65aa829c081bd82e1d97206f.mockapi.io/uas/makanan"; // Replace with your actual API endpoint


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerTemp);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        modelListItems = new ArrayList<>();
        adapterList = new AdapterList(modelListItems, this, requestQueue);
        recyclerView.setAdapter(adapterList);

        requestDataFromServer();
    }

    private void requestDataFromServer() {
        @SuppressLint("NotifyDataSetChanged")
        JsonArrayRequest requestImageList = new JsonArrayRequest(Request.Method.GET, JSON_URL, null,
                response -> {
                    Log.d("JSONResponse", response.toString());
                    try {
                        modelListItems.clear(); // Clear existing data

                        // Parse JSON response
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            String id = jsonObject.getString("id");
                            String title = jsonObject.getString("title");
                            String keterangan = jsonObject.getString("keterangan");
                            String img = jsonObject.getString("image");
                            img = img.replace("\\/", "/");
                            img = img.replace("\\/", "");
                            ModelList modelList = new ModelList(id, title, keterangan, img);
                            modelListItems.add(modelList);
                        }

                        // Notify adapter about the changes
                        adapterList.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Error parsing JSON data", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    error.printStackTrace();
                    Log.e("VolleyError", "Error during JSON request", error);
                    Toast.makeText(getApplicationContext(), "Error retrieving data", Toast.LENGTH_SHORT).show();
                });

        requestQueue.add(requestImageList);
    }

}
