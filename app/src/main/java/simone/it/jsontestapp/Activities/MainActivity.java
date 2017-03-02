package simone.it.jsontestapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

import simone.it.jsontestapp.Adapters.StudentsAdapter;
import simone.it.jsontestapp.Models.Student;
import simone.it.jsontestapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public Button btnStudents;
    public Button btnPlaces;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStudents = (Button)findViewById(R.id.btnStudents);
        btnPlaces = (Button) findViewById(R.id.btnPlaces);
        btnPlaces.setOnClickListener(this);
        btnStudents.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnStudents) {
            startActivity(new Intent(this,StudentActivity.class));
        }
        else if (v.getId() == R.id.btnPlaces){
            startActivity(new Intent(this,PlacesActivity.class));
        }
    }
}