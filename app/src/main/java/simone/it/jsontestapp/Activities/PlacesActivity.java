package simone.it.jsontestapp.Activities;

import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import simone.it.jsontestapp.Adapters.PlaceAdapter;
import simone.it.jsontestapp.Adapters.StudentsAdapter;
import simone.it.jsontestapp.Models.Place;
import simone.it.jsontestapp.R;
import simone.it.jsontestapp.Servicies.FoursquareAPI;

/**
 * Created by Simone on 02/03/2017.
 */

public class PlacesActivity extends AppCompatActivity{
    RecyclerView placesRv;
    LinearLayoutManager layoutManager;
    PlaceAdapter adapter;

    EditText searchPlaceEt;
    Button searchBtn;
    ProgressBar loading;
    EditText searchCityET;
    FoursquareAPI foursquareAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        placesRv = (RecyclerView) findViewById(R.id.search_rv);
        adapter = new PlaceAdapter();
        layoutManager = new LinearLayoutManager(this);
        placesRv.setLayoutManager(layoutManager);
        placesRv.setAdapter(adapter);

        searchPlaceEt = (EditText) findViewById(R.id.searchPlace_ET);
        searchBtn = (Button) findViewById(R.id.btn_searhPlaces);
        loading = (ProgressBar) findViewById(R.id.loading);
        searchCityET = (EditText) findViewById(R.id.searchCity_ET);

        searchBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                doSearch();
            }
        });
    }

    public void doSearch() {
        String query = searchCityET.getText().toString().toLowerCase()+"&query="+searchPlaceEt.getText().toString().toLowerCase();
        new FoursquareApiTask().execute(query);

    }

    private class FoursquareApiTask extends AsyncTask<String, Void,ArrayList<Place>>{
        private static final String RESPONSE = "response";
        private static final String VENUES = "venues";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<Place> doInBackground(String... strings) {
            ArrayList<Place> placeArrayList = new ArrayList<>();
            try{
                foursquareAPI = new FoursquareAPI();
                String url = foursquareAPI.getUrlString(strings[0]);

                JSONObject jsonResponse = foursquareAPI.getJSONObjectFromURL(url);
                JSONArray jsonPlaces = jsonResponse.getJSONObject(RESPONSE).getJSONArray(VENUES);

                for(int i = 0; i< jsonPlaces.length();i++){
                    placeArrayList.add(new Place(jsonPlaces.getJSONObject(i)));
                }
                return placeArrayList;

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return placeArrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<Place> places) {
            super.onPostExecute(places);
            adapter.setDataSet(places);
            loading.setVisibility(View.GONE);
        }
    }
}
