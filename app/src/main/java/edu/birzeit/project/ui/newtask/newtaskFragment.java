package edu.birzeit.project.ui.newtask;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.sql.Date;
import java.util.Calendar;

import edu.birzeit.project.ItemViewModel;
import edu.birzeit.project.R;
import edu.birzeit.project.ui.ToDo;

public class newtaskFragment extends Fragment {
    View view;
    NewTaskViewModel newTodoviewModel;
    SharedPreferences sp;
    TextView date;
    DatePickerDialog.OnDateSetListener setListener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_newtask, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newTodoviewModel = new ViewModelProvider(requireActivity()).get(NewTaskViewModel.class);
        sp = requireActivity().getSharedPreferences("MyUserEmail", Context.MODE_PRIVATE);
        String Email = sp.getString("Email", "");
        Button Add = view.findViewById(R.id.ADDTask);
        EditText TodoName = (EditText) view.findViewById(R.id.ToDoName);
        date = view.findViewById(R.id.DateTask);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener
                        , year, month, dayOfMonth);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String datetodo = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(dayOfMonth);
                date.setText(datetodo);
            }
        };
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag = true;
                if (TodoName.getText().toString().isEmpty()){
                    TodoName.setError("Enter Task Name");
                    flag = false;
                }
                if (date.getText().toString().equals("Select Date")){
                    date.setError("Select Date");
                    flag = false;
                }
                if (flag) {
                    ToDo newTodo = new ToDo();
                    newTodo.setToDo(TodoName.getText().toString());
                    newTodo.setDate(Date.valueOf(date.getText().toString()));
                    newTodo.setEmail(Email);
                    newTodo.setComplete(0);
                    newTodoviewModel.newTask(newTodo);
                    TodoName.setText("");
                    date.setError(null);
                    TodoName.setError(null);
                    date.setText("Select Date");
                }
            }
        });
    }

}
