package simone.it.jsontestapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

public class MainActivity extends AppCompatActivity {
    RecyclerView studentsRv;
    LinearLayoutManager layoutManager;
    StudentsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studentsRv = (RecyclerView) findViewById(R.id.students_RV);
        layoutManager = new LinearLayoutManager(this);
        adapter = new  StudentsAdapter();
        studentsRv.setLayoutManager(layoutManager);
        studentsRv.setAdapter(adapter);
        fetchStudentsFromJSON();
    }

    private void fetchStudentsFromJSON() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(readLocalJson());
            JSONArray studentsJsonArray = object.getJSONArray("students");
            for (int i = 0; i < studentsJsonArray.length(); i++) {
                JSONObject jsonStudent = studentsJsonArray.getJSONObject(i);
                students.add(new Student(jsonStudent));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        // add dataset to adapter
        adapter.setDataSet(students);

    }


    private String readLocalJson() {

        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try (InputStream is = getResources().openRawResource(R.raw.students_v2)) {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return writer.toString();
    }
}