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
    protected void onBindViewHolder(@NonNull myViewHolder holder,final int position, @NonNull MainModelLayan1 model) {
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


        holder.btnEdit3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus3 = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup3))
                        .setExpanded(true,1800)
                        .create();

                //dialogPlus1.show();

                View view = dialogPlus3.getHolderView();

                EditText eventname3 = view.findViewById(R.id.txteventname3);
                EditText available3 = view.findViewById(R.id.txtavailable3);
                EditText evaluation3 = view.findViewById(R.id.txtevaluation3);
                EditText location3 = view.findViewById(R.id.txtlocation3);
                EditText pictureurl3 = view.findViewById(R.id.txtpictureurl3);

                Button btnUpdate = view.findViewById(R.id.btnupdate3);


                eventname3.setText(model.getEventname());
                available3.setText(model.getAvailable());
                evaluation3.setText(model.getEvaluation());
                location3.setText(model.getLocation());
                pictureurl3.setText(model.getPicture());

                dialogPlus3.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("available", available3.getText().toString());
                        map.put("evaluation", evaluation3.getText().toString());
                        map.put("eventname", eventname3.getText().toString());
                        map.put("location", location3.getText().toString());
                        map.put("picture", pictureurl3.getText().toString());

                        String itemKey = getRef(position).getKey(); // Get the key of the item being edited

                        FirebaseDatabase.getInstance().getReference().child("Horse")
                                .child(itemKey).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.eventname.getContext(), "تم التحديث بنجاح", Toast.LENGTH_SHORT).show();
                                        dialogPlus3.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.eventname.getContext(), "حصل خطأ خلال التحديث", Toast.LENGTH_SHORT).show();
                                        dialogPlus3.dismiss();
                                    }
                                });
                    }
                });
            }
        });

        holder.btnDelete3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder2 = new AlertDialog.Builder(holder.eventname.getContext());
                builder2.setTitle("هل أنت متأكد من الحذف؟");
                builder2.setMessage("لايمكن التراجع عن الحذف");

                builder2.setPositiveButton("تم الحذف", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Horse")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder2.setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.eventname.getContext(), "تم الإلغاء", Toast.LENGTH_SHORT).show();
                    }
                });
                builder2.show();
            }
        });


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

        Button btnEdit3, btnDelete3;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView) itemView.findViewById(R.id.img3);
            eventname = (TextView) itemView.findViewById(R.id.eventnamehorse);
            available = (TextView) itemView.findViewById(R.id.availablehorse);
            evaluation = (TextView) itemView.findViewById(R.id.evaluationhorse);
            location = (TextView) itemView.findViewById(R.id.locationhorse);

            btnEdit3 = (Button) itemView.findViewById(R.id.btnedit3);
            btnDelete3 = (Button) itemView.findViewById(R.id.btndelete3);
        }
    }
}
