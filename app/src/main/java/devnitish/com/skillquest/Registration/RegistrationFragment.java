package devnitish.com.skillquest.Registration;


import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import devnitish.com.skillquest.Database.DatabaseAccess;
import devnitish.com.skillquest.R;
import devnitish.com.skillquest.Session;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {

    View view;

    EditText mUserName,mPassword,mPhone;
    String userName,password,phone;
    MaterialButton submit;


    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_register, container, false);


        init();
        setUpSubmitListener();

        return view;
    }

    private void init(){

        mUserName = view.findViewById(R.id.name);
        mPassword = view.findViewById(R.id.password);
        mPhone = view.findViewById(R.id.phone);

        submit = view.findViewById(R.id.submit);

    }

    private void setUpSubmitListener(){

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validate();
            }
        });

    }

    private void validate(){


        userName = mUserName.getText().toString().trim();
        password = mPassword.getText().toString().trim();
        phone = mPhone.getText().toString().trim();

        boolean isValid  = true;

        if(userName.equals("")){
            isValid = false;
            mUserName.setError("Pleaes enter your name");
        }

        if(phone.length()!=10){
            isValid= false;
            mPhone.setError("Please provide valid 10 digit number");
        }

        if(password.equals("")){
            isValid = false;
            mPassword.setError("Please enter your password");
        }

        if(isValid){
            DatabaseAccess.registerUser(getContext(),userName,password,phone);
            Toast.makeText(getContext(),"User Registered Successfully",Toast.LENGTH_SHORT).show();

            Session.PHONE = phone;
            Session.PASSWORD = password;
            Session.USERNAME = userName;

            getActivity().finish();
        }
    }



}
