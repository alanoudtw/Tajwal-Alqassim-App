package com.example.eventprojectforcomputing;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapterLayan2 extends FirebaseRecyclerAdapter<MainModelLayan1,MainAdapterLayan2.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapterLayan2(@NonNull FirebaseRecyclerOptions<MainModelLayan1> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, final int position, @NonNull MainModelLayan1 model) {
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


        holder.btnEdit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus2 = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup1))
                        .setExpanded(true,1800)
                        .create();

                //dialogPlus1.show();

                View view = dialogPlus2.getHolderView();

                EditText eventname2 = view.findViewById(R.id.txteventname1);
                EditText available2 = view.findViewById(R.id.txtavailable1);
                EditText evaluation2 = view.findViewById(R.id.txtevaluation1);
                EditText location2 = view.findViewById(R.id.txtlocation1);
                EditText pictureurl2 = view.findViewById(R.id.txtpictureurl1);

                Button btnUpdate = view.findViewById(R.id.btnupdate1);


                eventname2.setText(model.getEventname());
                available2.setText(model.getAvailable());
                evaluation2.setText(model.getEvaluation());
                location2.setText(model.getLocation());
                pictureurl2.setText(model.getPicture());

                dialogPlus2.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("available",available2.getText().toString());
                        map.put("evaluation",evaluation2.getText().toString());
                        map.put("eventname",eventname2.getText().toString());
                        map.put("location",location2.getText().toString());
                        map.put("picture",pictureurl2.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Gym")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.eventname.getContext(), "تم التحديث بنجاح", Toast.LENGTH_SHORT).show();
                                        dialogPlus2.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.eventname.getContext(), "حصل خطأ خلال التحديث", Toast.LENGTH_SHORT).show();
                                        dialogPlus2.dismiss();
                                    }
                                });
                    }
                });

            }
        });

        holder.btnDelete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder2 = new AlertDialog.Builder(holder.eventname.getContext());
                builder2.setTitle("هل أنت متأكد من الحذف؟");
                builder2.setMessage("لايمكن التراجع عن الحذف");

                builder2.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Gym")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.eventname.getContext(), "Canceled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder2.show();
            }
        });


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_itemlayan2,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView eventname,available,evaluation,location;

        Button btnEdit2, btnDelete2;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView) itemView.findViewById(R.id.img2);
            eventname = (TextView) itemView.findViewById(R.id.eventnamegym);
            available = (TextView) itemView.findViewById(R.id.availablegym);
            evaluation = (TextView) itemView.findViewById(R.id.evaluationgym);
            location = (TextView) itemView.findViewById(R.id.locationgym);

            btnEdit2 = (Button) itemView.findViewById(R.id.btnedit1);
            btnDelete2 = (Button) itemView.findViewById(R.id.btndelete1);
        }
    }
}
