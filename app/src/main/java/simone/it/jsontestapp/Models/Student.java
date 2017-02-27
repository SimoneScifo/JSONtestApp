package simone.it.jsontestapp.Models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Simone on 27/02/2017.
 */

public class Student {
    private static final String NAME_KEY = "nome";
    private static final String EMAIL_KEY = "email";
    private static final String GITHUB_KEY = "github";
    private static final String COURSE_KEY = "corso";




    private Corso course;
    public String name, email, github;

    public Student(JSONObject jsonStudent) {
        try {
            name = jsonStudent.getString(NAME_KEY);
            email = jsonStudent.getString(EMAIL_KEY);
            github = jsonStudent.optString(GITHUB_KEY, "").replace("@", "https://github.com/");
            course = new Corso(jsonStudent.getJSONObject(COURSE_KEY));
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }


    public String getGithub() {
        return github;
    }
    public Corso getCourse() {
        return course;
    }
}
