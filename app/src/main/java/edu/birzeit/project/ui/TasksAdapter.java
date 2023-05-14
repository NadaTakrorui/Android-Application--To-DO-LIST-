package edu.birzeit.project.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.birzeit.project.DataBaseHelper;
import edu.birzeit.project.MainActivity3;
import edu.birzeit.project.R;
import edu.birzeit.project.ui.ToDo;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.MyViewHolder> {

    List<ToDo> tasks;
    Context context;
    public TasksAdapter(Context ct, List<ToDo> tasks){
        this.context  = ct;
        this.tasks = tasks;
    }


    @NonNull
    @Override

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DataBaseToDo dataBaseToDo = new DataBaseToDo(context, "TaskList", null, 1);
        holder.t.setText(tasks.get(position).getToDo());
        holder.Date.setText(tasks.get(position).getDate().toString());
        if(tasks.get(position).getComplete() == 1){
            holder.v.setChecked(true);
        }
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.v.isChecked()) {
                    tasks.get(position).setComplete(1);
                    dataBaseToDo.UpdateComplete(tasks.get(position).getToDoId(), 1);
                    if (tasks.get(position).getComplete() == 1) {
                        holder.v.setChecked(true);
                    }
                    long millis = System.currentTimeMillis();
                    Date date = new java.sql.Date(millis);
                    if(tasks.get(position).getDate().toString().equals(date.toString())) {
                        boolean isAllDone = true;
                        for (ToDo t : tasks) {
                            if (t.getDate().toString().equals(date.toString())) {
                                if (t.getComplete() == 0) {
                                    isAllDone = false;
                                    break;
                                }
                            }
                        }
                        if (isAllDone) {
                            Toast toast = Toast.makeText(context, "Congratulations All Tasks Are Done!", Toast.LENGTH_SHORT);
                            toast.show();
                            Intent intent = new Intent(context, MainActivity3.class);
                            context.startActivity(intent);
                        }
                    }
                }
                else{
                    tasks.get(position).setComplete(0);
                    dataBaseToDo.UpdateComplete(tasks.get(position).getToDoId(),0);
                }
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                dataBaseToDo.deleteTask(tasks.get(position).getToDoId());
                tasks.remove(position);
                notifyItemRemoved(position);
                notifyDataSetChanged();
            }
        });
        holder.gmail.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("IntentReset")
            @Override
            public void onClick(View view) {
                Intent gmailIntent =new Intent();
                gmailIntent.setAction(Intent.ACTION_SENDTO);
                gmailIntent.setType("message/rfc822");
                gmailIntent.setData(Uri.parse("mailto:"));
                gmailIntent.putExtra(Intent.EXTRA_EMAIL,tasks.get(position).getEmail());
                gmailIntent.putExtra(Intent.EXTRA_SUBJECT,"ToDo");
                gmailIntent.putExtra(Intent.EXTRA_TEXT,tasks.get(position).getToDo());
                context.startActivity(gmailIntent);
            }
        });

        holder.Edit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("IntentReset")
            @Override
            public void onClick(View view) {
                if (View.NOT_FOCUSABLE == holder.t.getFocusable()) {
                    holder.t.setFocusableInTouchMode(true);
                } else{
                    holder.t.setFocusable(false);
                }
                holder.t.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        dataBaseToDo.changeTextOfTask(tasks.get(position).getToDoId(),holder.t.getText().toString());
                        tasks.get(position).setToDo(holder.t.getText().toString());
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {
                    }

                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        EditText t;
        CheckBox v;
        TextView Date;
        ImageButton delete;
        ImageButton gmail;
        ImageButton Edit;
        public MyViewHolder (@NonNull View itemView){
            super(itemView);
            t = itemView.findViewById(R.id.textView5);
            v = itemView.findViewById(R.id.checkBox);
            Edit = itemView.findViewById(R.id.editButton);
            Date = itemView.findViewById(R.id.DateTextView);
            delete = itemView.findViewById(R.id.deleteButton);
            gmail = itemView.findViewById(R.id.gmailbutton);
        }
    }
}