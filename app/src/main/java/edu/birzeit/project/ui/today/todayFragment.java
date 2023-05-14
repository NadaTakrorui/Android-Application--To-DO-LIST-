package edu.birzeit.project.ui.today;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import edu.birzeit.project.R;
import edu.birzeit.project.ui.DataBaseToDo;
import edu.birzeit.project.ui.TasksAdapter;
import edu.birzeit.project.ui.ToDo;

public class todayFragment extends Fragment {
    View view;
    SharedPreferences sp;
    RecyclerView Tasks;
    RecyclerView.LayoutManager layoutManager;
    TasksAdapter tasksAdapter;
    RecyclerView recyclerView;
    List<ToDo> TodoList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DataBaseToDo dataBaseToDo  =new DataBaseToDo(getActivity(),"TaskList",null,1);
        view = inflater.inflate(R.layout.fragment_today, container, false);
        sp = requireActivity().getSharedPreferences("MyUserEmail", Context.MODE_PRIVATE);
        String Email = sp.getString("Email", "");
        long millis=System.currentTimeMillis();
        Date date = new java.sql.Date(millis);
        TodoList = dataBaseToDo.getToDo(Email, date);
        recyclerView = view.findViewById(R.id.TodayTasks);
        tasksAdapter = new TasksAdapter(getActivity(),TodoList);
        recyclerView.setAdapter(tasksAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }


}
