package com.example.app2prestapi;
import com.example.app2prestapi.adapters.PostAdapter;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app2prestapi.config.RestApiMethods;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListPlaceHolder extends AppCompatActivity {

    private RequestQueue requestQueue;
    private ListView listView;
    private ArrayList<String> titlesList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_place_holder); // Asegúrate de que este layout existe

        // Referencia al ListView en el layout
        listView = findViewById(R.id.listViewPosts); // Debe coincidir con el ID en activity_list_place_holder.xml
        titlesList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titlesList);
        listView.setAdapter(adapter);

        // Llamar al método para hacer la petición a la API
        SendData();
    }

    private void SendData() {
        requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, RestApiMethods.EndpointPosts,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            titlesList.clear();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String title = jsonObject.getString("title");
                                titlesList.add(title);
                            }

                            // Usar el nuevo adaptador personalizado
                            adapter = new PostAdapter(ListPlaceHolder.this, titlesList);
                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            Log.e("JSON_ERROR", "Error procesando JSON: " + e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("API_ERROR", "Error en la petición: " + error.getMessage());
                        Toast.makeText(ListPlaceHolder.this, "No se pudo obtener los datos", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(stringRequest);
    }


}

