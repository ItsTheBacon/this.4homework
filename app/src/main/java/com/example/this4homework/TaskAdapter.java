package com.example.this4homework;

import android.app.AlertDialog;
import android.content.ContentProvider;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TackViewHolder> {
    public List<TaskModel> list = new ArrayList<>();
    ItemClickList onitemClickList;



    public void setItemClickList(ItemClickList itemClickList) {
        this.onitemClickList = itemClickList;
    }

    public void addData(TaskModel taskModel) {
        list.add(taskModel);
        notifyDataSetChanged();

    }

    public void  editData(TaskModel model, int pos){
        list.set(pos , model);
        notifyDataSetChanged();


    }



    public void deleteTask(int position) {
        list.remove(position);
        notifyDataSetChanged();
    }
//    public TaskAdapter(Context context) {
//        this.inflater = LayoutInflater.from(context);
//
//    }

    public TackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TackViewHolder(view);


    }

    @Override
    public void onBindViewHolder(TaskAdapter.TackViewHolder holder, int position) {

        holder.bind(list.get(position));
       // holder.txtDescription.setText(list.get(position).getDescriptoin());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TackViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtDescription;


        public TackViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.item_title_txt);
            txtDescription = itemView.findViewById(R.id.item_description_txt);
            //  txtDate = itemView.findViewById(R.id.item_data_txt);


        }

        public void bind(TaskModel taskModel) {
            txtTitle.setText(taskModel.getTitle());
            txtDescription.setText(taskModel.getDescriptoin());
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog dialog = new AlertDialog.Builder(itemView.getContext()).create();
                    dialog.setTitle("Внимание!");
                    dialog.setMessage("Вы  точно хотите удалить ???");

                    dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Нет", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Да", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteTask(getAdapterPosition());
                        }
                    });
                    dialog.show();
                    return false;
                }
            });
            itemView.setOnClickListener(v -> {
                onitemClickList.CLickItem(getAdapterPosition());
            });


        }
    }
}
