package com.example.eventprojectforcomputing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapterLayan1 extends FirebaseRecyclerAdapter<MainModelLayan1,MainAdapterLayan1.myViewHolder>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapterLayan1(@NonNull FirebaseRecyclerOptions<MainModelLayan1> options) {
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
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.google.firebase.database.collection.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

        holder.btnEdit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus1 = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup1))
                        .setExpanded(true,1800)
                        .create();

                dialogPlus1.show();

            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_itemlayan1,parent,false);
        return new myViewHolder((view));
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView eventname,available,evaluation,location;

        Button btnEdit1, btnDelete1;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView) itemView.findViewById(R.id.img1);
            eventname = (TextView) itemView.findViewById(R.id.eventnamesport);
            available = (TextView) itemView.findViewById(R.id.availablesport);
            evaluation = (TextView) itemView.findViewById(R.id.evaluationsport);
            location = (TextView) itemView.findViewById(R.id.locationsport);

            btnEdit1 = (Button) itemView.findViewById(R.id.btnedit1);
            btnDelete1 = (Button) itemView.findViewById(R.id.btndelete1);




        }
    }
}
