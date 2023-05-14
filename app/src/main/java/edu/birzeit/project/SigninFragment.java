package edu.birzeit.project;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.Objects;


public class SigninFragment extends Fragment {
    View view;
    ItemViewModel viewModel;
    SharedPreferences sp;
    private static final String TOAST_TEXT = "No person exist with this Email";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_signin, container, false);

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);
        sp = requireActivity().getSharedPreferences("MyUserEmail", Context.MODE_PRIVATE);
        Button signin = view.findViewById(R.id.In);
        EditText email = (EditText) view.findViewById(R.id.nnn);
        EditText password = (EditText)view.findViewById(R.id.ttt);
        CheckBox Remember = view.findViewById(R.id.Remember);
        String remember = sp.getString("Remember", "");
        if (remember.equals("true")){
            String Email = sp.getString("Email", "");
            email.setText(Email);
        }
        SharedPreferences.Editor editor = sp.edit();
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String check;
                String Email = email.getText().toString();
                boolean flagEmail = false;
                int LengthEmail = Email.length();
                if (Email.contains("@")) {
                    if (Email.contains(".com")) {
                        if (Email.charAt(LengthEmail - 1) == 'm' && Email.charAt(LengthEmail - 2) == 'o'
                                && Email.charAt(LengthEmail - 3) == 'c' && Email.charAt(LengthEmail - 4) == '.') {
                            flagEmail = true;
                        }
                    }
                }
                if (!flagEmail) {
                    email.setError("Wrong Email");
                    email.setTextColor(Color.RED);
                    email.setBackgroundResource(R.drawable.input_error);
                }
                String Password = password.getText().toString();
                boolean flagPassword = false;
                int LengthPassword = Password.length();
                if (LengthPassword < 3) {
                    password.setError("Minimum 8 characters");
                    password.setTextColor(Color.RED);
                    password.setBackgroundResource(R.drawable.input_error);
                } else {
                    char ch;
                    boolean capitalFlag = false;
                    boolean lowerCaseFlag = false;
                    boolean numberFlag = false;
                    for (int i = 0; i < LengthPassword; i++) {
                        ch = Password.charAt(i);
                        if (Character.isDigit(ch)) {
                            numberFlag = true;
                        } else if (Character.isUpperCase(ch)) {
                            capitalFlag = true;
                        } else if (Character.isLowerCase(ch)) {
                            lowerCaseFlag = true;
                        }
                        if (numberFlag && capitalFlag && lowerCaseFlag)
                            flagPassword = true;
                    }
                    if (!flagPassword) {
                        password.setError("must contain at least one number, one lowercase letter, and one uppercase letter.");
                        password.setTextColor(Color.RED);
                        password.setBackgroundResource(R.drawable.input_error);
                    }
                }
                if (flagEmail && flagPassword) {
                    check = Email + "," + Password;
                    viewModel.setCheckPerson(check);
                    if (Remember.isChecked()) {
                        editor.putString("Remember", "true");
                        editor.putString("Email", email.getText().toString());
                    } else {
                        editor.putString("Remember", "false");
                        editor.putString("Email", email.getText().toString());
                    }
                    editor.commit();
                } else {
                    check = "Error,Error";
                    viewModel.setCheckPerson(check);
                }
                password.setText("");
            }
        });
    }
}