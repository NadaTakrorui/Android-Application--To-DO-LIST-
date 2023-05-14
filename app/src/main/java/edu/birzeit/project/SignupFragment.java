package edu.birzeit.project;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class SignupFragment extends Fragment {
    View view;
    ItemViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_signup, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);
        Button signup = view.findViewById(R.id.UP);
        EditText email = (EditText) view.findViewById(R.id.editEmail);
        EditText firstname = (EditText) view.findViewById(R.id.editFirst);
        EditText lastname =  (EditText)view.findViewById(R.id.editLast);
        EditText password = (EditText)view.findViewById(R.id.editPassword);
        EditText confirmPassword = (EditText) view.findViewById(R.id.editConfirmPassword);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                    email.setError("Must be in a correct email format");
                    email.setTextColor(Color.RED);
                    email.setBackgroundResource(R.drawable.input_error);
                }
                String FirstName = firstname.getText().toString();
                boolean flagFirstName = false;
                int LengthFirstName = FirstName.length();
                if (LengthFirstName < 3) {
                    firstname.setError("Minimum 3 characters");
                    firstname.setTextColor(Color.RED);
                    firstname.setBackgroundResource(R.drawable.input_error);
                } else if (!FirstName.matches("[a-zA-Z]+")) {
                    firstname.setError("First Name must be Alphabets only");
                    firstname.setTextColor(Color.RED);
                    firstname.setBackgroundResource(R.drawable.input_error);
                } else {
                    flagFirstName = true;
                }
                String LastName = lastname.getText().toString();
                boolean flagLastName = false;
                int LengthLastName = LastName.length();
                if (LengthLastName < 3) {
                    lastname.setError("Minimum 3 characters");
                    lastname.setTextColor(Color.RED);
                    lastname.setBackgroundResource(R.drawable.input_error);
                } else if (!LastName.matches("[a-zA-Z]+")) {
                    lastname.setError("First Name must be Alphabets only");
                    lastname.setTextColor(Color.RED);
                    lastname.setBackgroundResource(R.drawable.input_error);
                } else {
                    flagLastName = true;
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
                String ConfirmPassword = confirmPassword.getText().toString();
                boolean flagConfirmPassword = false;
                if (!Password.equals(ConfirmPassword)){
                    confirmPassword.setError("Password don't match");
                    confirmPassword.setTextColor(Color.RED);
                    confirmPassword.setBackgroundResource(R.drawable.input_error);
                } else{
                    flagConfirmPassword = true;
                }
                Person p = new Person();
                if (flagEmail && flagFirstName && flagLastName && flagPassword && flagConfirmPassword){
                    p.setmEmail(Email);
                    p.setmFirstName(FirstName);
                    p.setmLastName(LastName);
                    p.setmPassword(Password);
                } else {
                    p.setmEmail("Error");
                }
                viewModel.setAddPerson(p);
            }
        });
    }
}