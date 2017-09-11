package com.example.aff02.expansemanager.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aff02.expansemanager.Database.DatabaseHelper;
import com.example.aff02.expansemanager.Model.DataModel;
import com.example.aff02.expansemanager.R;
import com.example.aff02.expansemanager.Activity.ViewActivity;

import java.util.List;

/**
 * Created by AFF02 on 23-Aug-17.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    List<DataModel> dataModelList;
    private Context context;
    private boolean flag;
    DataModel model = new DataModel();
    private DatabaseHelper databaseHelper;


    public RecycleAdapter(Context context,List<DataModel> dataModelList,boolean flag) {
        this.dataModelList = dataModelList;
        this.flag = flag;
        this.context= context;
        databaseHelper = new DatabaseHelper(context);
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtname,txtdesc,txtrupees,txtpayment,txtdate;
        ImageView imgDelete,imgUpdate;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            txtname = (TextView)itemView.findViewById(R.id.txtName);
            txtdesc = (TextView)itemView.findViewById(R.id.txtdesc);
            txtrupees = (TextView)itemView.findViewById(R.id.txtRupees);
            txtpayment = (TextView)itemView.findViewById(R.id.txtpayment);
            txtdate = (TextView)itemView.findViewById(R.id.txtdate);

            imgDelete = (ImageView)itemView.findViewById(R.id.form_delete);
            imgUpdate = (ImageView)itemView.findViewById(R.id.update);

            cardView = (CardView)itemView.findViewById(R.id.cardview);

        }
    }

    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecycleAdapter.ViewHolder holder, final int position) {
        final DataModel model = dataModelList.get(position);
        holder.txtname.setText(model.getName());
        holder.txtdesc.setText(model.getDesc());
        holder.txtrupees.setText(Integer.toString(model.getRupees()));
        holder.txtpayment.setText(model.getPayment());
        holder.txtdate.setText(model.getDate());

        if (flag){
            holder.imgDelete.setVisibility(View.VISIBLE);
        }else {
            holder.imgDelete.setVisibility(View.GONE);
        }

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseHelper.deleteRow(dataModelList.get(position).getName());

               ((Activity)context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,ViewActivity.class);
              //  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Name",dataModelList.get(position).getName());
                intent.putExtra("Payment",dataModelList.get(position).getPayment());
                intent.putExtra("Rupees",dataModelList.get(position).getRupees());
                intent.putExtra("Date",dataModelList.get(position).getDate());
                intent.putExtra("Desc",dataModelList.get(position).getDesc());
                context.startActivity(intent);
            }
        });

        holder.imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                notifyItemChanged(position);
                updateData(model);

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public void updateData(final DataModel dataModel)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.activity_update, null);

        final EditText updateName = (EditText)subView.findViewById(R.id.updateName);
        final EditText updateDesc = (EditText)subView.findViewById(R.id.updateDesc);
        final EditText updateRupees = (EditText)subView.findViewById(R.id.updateRupees);
        final EditText updateDate = (EditText)subView.findViewById(R.id.updateDate);
        final EditText updatePayment = (EditText)subView.findViewById(R.id.updatePayment);

        if(dataModel != null){
            updateName.setText(dataModel.getName());
            updateDesc.setText(dataModel.getDesc());
            updateRupees.setText(String.valueOf(dataModel.getRupees()));
            updateDate.setText(dataModel.getDate());
            updatePayment.setText(dataModel.getPayment());
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit product");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("EDIT DETAILS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String name = updateName.getText().toString();
                final String payment = updatePayment.getText().toString();
                final String date = updateDate.getText().toString();
                final String desc = updateDesc.getText().toString();
                final int rupees = Integer.parseInt(updateRupees.getText().toString());

                if(TextUtils.isEmpty(name) || rupees <= 0){
                    Toast.makeText(context, "Something went wrong. Check your input values", Toast.LENGTH_LONG).show();
                }
                else{
                    databaseHelper.updateRow(new DataModel(model.getId(),name,desc,rupees,date,payment));
                    //refresh the activity
                    ((Activity)context).finish();
                    context.startActivity(((Activity)context).getIntent());
                }
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Task cancelled", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
    }
