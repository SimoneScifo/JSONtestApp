package simone.it.jsontestapp.Models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Simone on 27/02/2017.
 */

public class Corso{
    private static final String NAME_COURSE_KEY = "nome";
    private static final String ID_KEY = "id";
    private int id;
        private String courseName;

        Corso(JSONObject jsonCourse){
            try{
                id = jsonCourse.getInt(ID_KEY);
                courseName = jsonCourse.getString(NAME_COURSE_KEY);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        public int getId() {
            return this.id;
        }

        public String getCourseName() {
            return this.courseName;
        }

    }
