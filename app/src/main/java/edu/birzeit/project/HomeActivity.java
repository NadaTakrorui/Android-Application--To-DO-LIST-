package edu.birzeit.project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import edu.birzeit.project.ui.Home2Activity;

public class HomeActivity extends AppCompatActivity  {
    RadioButton SignUp, SignIn;
    TextView welcomeMass;
    Dialog dialog;
    private static final String TOAST_TEXT = "No person exist with this Email";
    private static final String TOAST_TEXT2 = "The Password is wrong";
    private static final String TOAST_TEXT3 = "The Email is registered";
    DataBaseHelper dataBaseHelper =new DataBaseHelper(HomeActivity.this,"Project",null,1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SignUp = findViewById(R.id.SignUp);
        SignIn = findViewById(R.id.In);
        welcomeMass = findViewById(R.id.Welcome);
        ActionBar action = getSupportActionBar();
        action.hide();
        // default selected
        updateRadioGroup(SignIn);
        ItemViewModel viewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        viewModel.getSelectedPerson().observe(this, item ->{
            if (item.getmEmail().equals("Error")){
                dialog = new Dialog(HomeActivity.this);
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
            } else {
                Cursor p = dataBaseHelper.searchPerson(item.getmEmail());
                if (p.getCount() > 0) {
                    Toast toast = Toast.makeText(HomeActivity.this, TOAST_TEXT3, Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    updateRadioGroup(SignIn);
                    replaceFragment(new SigninFragment());
                    dataBaseHelper.insertPerson(item);
                }
            }
        });
        viewModel.getSelectedCheckPerson().observe(this, item -> {
            String[] input = item.split(",");
            if (input[0].equals("Error") && input[1].equals("Error")) {
                dialog = new Dialog(HomeActivity.this);
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
            } else {
                Cursor p = dataBaseHelper.searchPerson(input[0]);
                if (p.getCount() == 0) {
                    Toast toast = Toast.makeText(HomeActivity.this, TOAST_TEXT, Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    boolean flag = false;
                    while (p.moveToNext()) {
                        if (p.getString(3).equals(input[1])) {
                            flag = true;
                            Intent intent = new Intent(HomeActivity.this, MainActivity2.class);
                            HomeActivity.this.startActivity(intent);
                            finish();
                        }
                    }
                    if(!flag){
                        Toast toast = Toast.makeText(HomeActivity.this, TOAST_TEXT2, Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
        });
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRadioGroup(SignIn);
                replaceFragment(new SigninFragment());
                welcomeMass.setText("Welcome\nHave an account? Sign In");

            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRadioGroup(SignUp);
                replaceFragment(new SignupFragment());
                welcomeMass.setText("Welcome\nNew here? Sign Up");
            }
        });
    }

    public void radioTapped(View view) {
        int selectedID = view.getId();

        if (selectedID == R.id.SignUp) {
            updateRadioGroup(SignUp);
        } else if (selectedID == R.id.In) {
            updateRadioGroup(SignIn);
        }
    }

    private void updateRadioGroup(RadioButton selected)
    {
        SignUp.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.radio_off));
        SignIn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.radio_off));

        selected.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.radio_on));
    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView,fragment);
        fragmentTransaction.commit();

    }
}