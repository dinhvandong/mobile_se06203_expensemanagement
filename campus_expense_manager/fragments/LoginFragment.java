package com.btec.fpt.campus_expense_manager.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.btec.fpt.campus_expense_manager.HomeActivity;
import com.btec.fpt.campus_expense_manager.R;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginFragment extends Fragment {

    public LoginFragment() {
        // Required empty public constructor
    }
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_login, container, false);

        // Find buttons
        Button loginButton = view.findViewById(R.id.login_button);
        Button registerButton = view.findViewById(R.id.goto_register_button);
        Button forgotPasswordButton = view.findViewById(R.id.goto_forgot_password_button);


        EditText edtEmail = view.findViewById(R.id.edt_email);
        EditText edtPassword = view.findViewById(R.id.edt_password);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = edtEmail.getText().toString();
                String pwd = edtPassword.getText().toString();

                if(!email.isEmpty() && !pwd.isEmpty())
                {


                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);

                }else {

                    showMessage("Email or password is null. Please fill email and password");
                }



            }
        });

        // Set up button to go to RegisterFragment
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new RegisterFragment());
            }
        });

        // Set up button to go to ForgotPasswordFragment
        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //showAlertDialog();
                showCustomAlertDialog();
            }
        });

        return view;
    }

    private void showAlertDialog() {
        // Create an AlertDialog.Builder instance
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        // Set the title of the dialog
        builder.setTitle("Forgot password");

        // Set the message to be displayed
        builder.setMessage("Are you sure reset your password?");

        // Set a positive button with an onClick listener
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Action to take when the user clicks OK
                Toast.makeText(getContext(), "You clicked OK", Toast.LENGTH_SHORT).show();

                loadFragment(new ForgotPasswordFragment());
            }
        });

        // Set a negative button with an onClick listener
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Action to take when the user clicks Cancel
                dialog.dismiss();  // Dismiss the dialog
            }
        });

        // Create and show the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    void showMessage(String mes){

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, view.findViewById(R.id.custom_toast_layout));

// Set the icon
        ImageView icon = layout.findViewById(R.id.toast_icon);
        icon.setImageResource(R.drawable.icon_x);  // Set your desired icon

// Set the text
        TextView text = layout.findViewById(R.id.toast_message);
        text.setText(mes);

// Create and show the toast
        Toast toast = new Toast(getContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();

        //Toast.makeText(getContext(), mes, Toast.LENGTH_LONG).show();
    }


    private void showCustomAlertDialog() {
        // Create an instance of LayoutInflater
        LayoutInflater inflater = getLayoutInflater();

        // Inflate the custom layout/view
        View customView = inflater.inflate(R.layout.dialog_custom, null);

        // Build the AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(customView);  // Set the custom layout

        // Get references to the views in the custom layout
        final EditText input = customView.findViewById(R.id.dialog_input);
        TextView title = customView.findViewById(R.id.dialog_title);
        Button okButton = customView.findViewById(R.id.dialog_button_ok);

        // Optionally customize the title
        title.setText("Forgot password!");

        // Create and show the dialog
        AlertDialog dialog = builder.create();

        // Handle the OK button click event
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the input text and show it in a Toast
                String userInput = input.getText().toString();
                Toast.makeText(getContext(), "Input: " + userInput, Toast.LENGTH_SHORT).show();

                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        // Show the dialog
        dialog.show();
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

