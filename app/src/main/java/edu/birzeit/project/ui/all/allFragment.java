package edu.birzeit.project.ui.all;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.birzeit.project.R;
import edu.birzeit.project.ui.DataBaseToDo;
import edu.birzeit.project.ui.TasksAdapter;
import edu.birzeit.project.ui.ToDo;


public class allFragment extends Fragment {
    View view;
    SharedPreferences sp;
    RecyclerView Tasks;
    RecyclerView.LayoutManager layoutManager;
    TasksAdapter tasksAdapter;
    List<ToDo> TodoList = new ArrayList<>();
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DataBaseToDo dataBaseToDo = new DataBaseToDo(getActivity(),"TaskList",null,1);
        view = inflater.inflate(R.layout.fragment_all, container, false);
        sp = requireActivity().getSharedPreferences("MyUserEmail", Context.MODE_PRIVATE);
        String Email = sp.getString("Email", "");
        TodoList = dataBaseToDo.getAllToDo(Email);
        recyclerView = view.findViewById(R.id.AllTasks);
        tasksAdapter = new TasksAdapter(getActivity(),TodoList);
        recyclerView.setAdapter(tasksAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

}
