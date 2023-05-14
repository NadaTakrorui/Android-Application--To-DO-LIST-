package edu.birzeit.project.ui.profile;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.birzeit.project.DataBaseHelper;
import edu.birzeit.project.HomeActivity;
import edu.birzeit.project.MainActivity;
import edu.birzeit.project.Person;
import edu.birzeit.project.R;
import edu.birzeit.project.ui.DataBaseToDo;


public class profileFragment extends Fragment {

    View view;
    SharedPreferences sp;
    Dialog dialog;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DataBaseHelper dataBaseToDo  =new DataBaseHelper(getActivity(),"Project",null,1);
        Animation myan = AnimationUtils.loadAnimation(getActivity(), R.anim.anination2);
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        sp = requireActivity().getSharedPreferences("MyUserEmail", Context.MODE_PRIVATE);
        String Email = sp.getString("Email", "");
        Button Save = (Button) view.findViewById(R.id.SaveProfile);
        Button Edit = (Button) view.findViewById(R.id.EditProfile);
        EditText email = (EditText) view.findViewById(R.id.ProfileEmail);
        EditText firstname = (EditText) view.findViewById(R.id.ProfileFirstName);
        EditText lastname =  (EditText)view.findViewById(R.id.ProfileLastName);
        EditText password = (EditText)view.findViewById(R.id.ProfilePassword);
        firstname.setFocusable(false);
        lastname.setFocusable(false);
        password.setFocusable(false);
        Cursor p = dataBaseToDo.searchPerson(Email);
        while (p.moveToNext()) {
            email.setText(p.getString(0));
            firstname.setText(p.getString(1));
            lastname.setText(p.getString(2));
            password.setText(p.getString(3));
        }
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstname.setFocusableInTouchMode(true);
                lastname.setFocusableInTouchMode(true);
                password.setFocusableInTouchMode(true);
            }
        });
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                if (flagFirstName && flagLastName && flagPassword) {
                    Person p = new Person();
                    p.setmEmail(Email);
                    p.setmFirstName(FirstName);
                    p.setmLastName(LastName);
                    p.setmPassword(Password);
                    dataBaseToDo.UpdatePerson(p);
                    firstname.setFocusable(false);
                    lastname.setFocusable(false);
                    password.setFocusable(false);
                } else{
                    dialog = new Dialog(getActivity());
                    dialog.setContentView(R.layout.custom_dialog);
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.setCancelable(false);
                    Button Okay = dialog.findViewById(R.id.btn_okay);
                    Okay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            }
        });
        return view;
    }
}
