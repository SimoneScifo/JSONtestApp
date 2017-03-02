package simone.it.jsontestapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import simone.it.jsontestapp.Models.Place;
import simone.it.jsontestapp.R;

/**
 * Created by Simone on 02/03/2017.
 */

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> implements View.OnClickListener {
    ArrayList<Place> dataSet = new ArrayList<>();
    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_places, parent, false);
        return new PlaceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {
        Place place = dataSet.get(position);
        holder.placeName_TV.setText(place.getName());
        holder.placeAddress_TV.setText(place.getAddress());
        holder.placeCity_TV.setText(place.getCity());
        holder.btncall.setOnClickListener(this);
;
    }
    @Override
    public void onClick(View v) {
        if (v.getId()== R.id.btncall){

        }
        else if (v.getId() == R.id.btnmaps){

        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void setDataSet(ArrayList<Place> dataSet){
        this.dataSet = dataSet;
    }


    class PlaceViewHolder extends RecyclerView.ViewHolder {
        public TextView placeName_TV;
        public TextView placeAddress_TV;
        public TextView placeCity_TV;
        public ImageButton btncall;
        public ImageButton btnmaps;

        public PlaceViewHolder(View itemView) {
            super(itemView);
            placeName_TV = (TextView) itemView.findViewById(R.id.placeName_TV);
            placeAddress_TV = (TextView) itemView.findViewById(R.id.placeAddress_TV);
            placeCity_TV = (TextView) itemView.findViewById(R.id.placeCity_TV);
            btncall = (ImageButton) itemView.findViewById(R.id.btncall);
            btnmaps = (ImageButton) itemView.findViewById(R.id.btnmaps);
        }
    }
}
