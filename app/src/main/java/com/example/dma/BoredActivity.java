package com.example.dma;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import android.view.Window;
import android.view.WindowManager;
import org.json.JSONException;
import org.json.JSONObject;


public class BoredActivity extends AppCompatActivity {
    TextView Activity;
    Button GenerateActivity;
    ProgressBar progressBar;

    private String url;
    private String randomurl = "https://www.boredapi.com/api/activity";
    private RequestQueue requestQueue;
    private int key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bored_api_view);
        Window window = BoredActivity.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        Activity = findViewById(R.id.editTextTextMultiLine);
        progressBar = findViewById(R.id.progressBar);

        ReceiveCustomization();
    }


    void ReceiveCustomization() {
        url = "https://www.boredapi.com/api/activity";
        String type = getIntent().getStringExtra("type");

        if (type != null) {
            url += "?type=" + type;
            requestQueue = Volley.newRequestQueue(this);
            LoadURL(url);
        }
    }


    void LoadURL(String url){
        progressBar.setVisibility(View.VISIBLE);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    progressBar.setVisibility(View.INVISIBLE);
                    String activityresult = response.getString("activity");
                    key = response.getInt("key");
                    Activity.setText(activityresult);
                }
                catch (JSONException e){
                    progressBar.setVisibility(View.INVISIBLE);
                    Log.e("JSON", "Error Fetching URL");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.e("JSON", "Volley Error", error);
            }
        });
        requestQueue.add(request);
    }

    /*
    Генерация случайной активности
    по нажатию на кнопку.
     */
    public void GenerateRandomActivity(View view) {
        requestQueue = Volley.newRequestQueue(this);
        LoadURL(randomurl);
    }
}

