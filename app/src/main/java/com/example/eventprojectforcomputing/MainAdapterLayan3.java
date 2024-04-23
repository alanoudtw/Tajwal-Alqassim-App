package com.example.eventprojectforcomputing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapterLayan3 extends FirebaseRecyclerAdapter<MainModelLayan1,MainAdapterLayan3.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapterLayan3(@NonNull FirebaseRecyclerOptions<MainModelLayan1> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull MainModelLayan1 model) {
        holder.eventname.setText(model.getEventname());
        holder.available.setText(model.getAvailable());
        holder.evaluation.setText(model.getEvaluation());
        holder.location.setText(model.getLocation());

        Glide.with(holder.img.getContext())
                .load(model.getPicture())
                .placeholder(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.google.firebase.database.collection.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_itemlayan3,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView eventname,available,evaluation,location;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView) itemView.findViewById(R.id.img3);
            eventname = (TextView) itemView.findViewById(R.id.eventnamehorse);
            available = (TextView) itemView.findViewById(R.id.availablehorse);
            evaluation = (TextView) itemView.findViewById(R.id.evaluationhorse);
            location = (TextView) itemView.findViewById(R.id.locationhorse);
        }
    }
}
