package edu.birzeit.project.ui.search;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.birzeit.project.R;
import edu.birzeit.project.ui.DataBaseToDo;
import edu.birzeit.project.ui.TasksAdapter;
import edu.birzeit.project.ui.ToDo;
import edu.birzeit.project.ui.newtask.NewTaskViewModel;


public class searchFragment extends Fragment {
    View view;
    SharedPreferences sp;
    TextView startdate;
    TextView enddate;
    DatePickerDialog.OnDateSetListener setListener1;
    DatePickerDialog.OnDateSetListener setListener2;
    RecyclerView recyclerView;
    TasksAdapter tasksAdapter;
    List<ToDo> TodoList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DataBaseToDo dataBaseToDo  =new DataBaseToDo(getActivity(),"TaskList",null,1);
        sp = requireActivity().getSharedPreferences("MyUserEmail", Context.MODE_PRIVATE);
        String Email = sp.getString("Email", "");
        Button Search = view.findViewById(R.id.SearchTask);
        startdate = view.findViewById(R.id.StartDateSearchTask);
        enddate = view.findViewById(R.id.EndDateSearchTask);
        Calendar calendar1 = Calendar.getInstance();
        final int startyear = calendar1.get(Calendar.YEAR);
        final int startmonth = calendar1.get(Calendar.MONTH);
        final int startdayOfMonth = calendar1.get(Calendar.DAY_OF_MONTH);
        Calendar calendar2 = Calendar.getInstance();
        final int endyear = calendar2.get(Calendar.YEAR);
        final int endmonth = calendar2.get(Calendar.MONTH);
        final int enddayOfMonth = calendar2.get(Calendar.DAY_OF_MONTH);
        recyclerView = view.findViewById(R.id.SearchTasks);
        startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener1
                        , startyear, startmonth, startdayOfMonth);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        setListener1 = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int startyear, int startmonth, int startdayOfMonth) {
                startmonth = startmonth + 1;
                String datetodo = String.valueOf(startyear) + "-" + String.valueOf(startmonth) + "-" + String.valueOf(startdayOfMonth);
                startdate.setText(datetodo);
            }
        };
        enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener2
                        , endyear, endmonth, enddayOfMonth);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        setListener2 = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int endyear, int endmonth, int enddayOfMonth) {
                endmonth = endmonth + 1;
                String datetodo = String.valueOf(endyear) + "-" + String.valueOf(endmonth) + "-" + String.valueOf(enddayOfMonth);
                enddate.setText(datetodo);
            }
        };
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag = true;
                if (startdate.getText().toString().equals("Start Date")){
                    startdate.setError("Select Start Date");
                    flag = false;
                }
                if (enddate.getText().toString().equals("End Date")){
                    enddate.setError("Select End Date");
                    flag = false;
                }
                if (flag) {
                    TodoList = dataBaseToDo.getWeekToDo(Email, Date.valueOf(startdate.getText().toString()), Date.valueOf(enddate.getText().toString()));
                    tasksAdapter = new TasksAdapter(getActivity(), TodoList);
                    recyclerView.setAdapter(tasksAdapter);
                    startdate.setError(null);
                    enddate.setError(null);
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
