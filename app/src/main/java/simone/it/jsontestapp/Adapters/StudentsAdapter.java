package simone.it.jsontestapp.Adapters;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import simone.it.jsontestapp.ImageDownloaderTask;
import simone.it.jsontestapp.Models.Student;
import simone.it.jsontestapp.R;

/**
 * Created by Simone on 27/02/2017.
 */

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentViewHolder> {
    private ArrayList<Student> dataSet = new ArrayList<>();


    @Override
    public StudentsAdapter.StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_students,parent,false);
        StudentViewHolder holder = new StudentViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(StudentsAdapter.StudentViewHolder holder, int position) {
        Student currentStudent = dataSet.get(position);
        holder.studentNameTV.setText(currentStudent.getNome());
        holder.studentEmailTV.setText(currentStudent.getEmail());
        new ImageDownloaderTask(holder.btn_image).execute(currentStudent.getImageUrl());
        //holder.studentCourseTV.setText(currentStudent.getCourse().getCourseName());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void setDataSet (ArrayList<Student> students){
        dataSet = students;
        notifyDataSetChanged();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView studentNameTV;
        public TextView studentEmailTV;
        public ImageButton btn_github;
        //public TextView studentCourseTV;
        public ImageView btn_image;

        public StudentViewHolder(View itemView) {
            super(itemView);
            studentNameTV = (TextView) itemView.findViewById(R.id.student_nameTV);
            studentEmailTV = (TextView) itemView.findViewById(R.id.student_emailTV);
            //studentCourseTV = (TextView) itemView.findViewById(R.id.student_courseTV);
            btn_github = (ImageButton) itemView.findViewById(R.id.btn_github);
            btn_image = (ImageView) itemView.findViewById(R.id.imgProfile);

            btn_github.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_VIEW);
            i.setData(Uri.parse(dataSet.get(getAdapterPosition()).getGithub()));
            v.getContext().startActivity(i);
        }
    }
}

