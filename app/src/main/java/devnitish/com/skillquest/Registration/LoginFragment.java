package devnitish.com.skillquest.Registration;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import devnitish.com.skillquest.Constants;
import devnitish.com.skillquest.Database.DatabaseAccess;
import devnitish.com.skillquest.R;
import devnitish.com.skillquest.Session;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    View view;

    EditText mPhone,mPassword;
    MaterialButton submit;
    CheckBox mkeepSignedIn;

    String phone,password;
    boolean keepSignedIn;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_login, container, false);


        init();
        setUpOnSubmitListener();

        return view;
    }

    private void init(){

        sharedPreferences = getActivity().getSharedPreferences(Constants.SHARED,
                Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        mPassword = view.findViewById(R.id.password);
        mPhone = view.findViewById(R.id.phone);
        submit  = view.findViewById(R.id.submit);
        mkeepSignedIn = view.findViewById(R.id.keepSignIn);

    }

    private void setUpOnSubmitListener(){

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validate();
            }
        });
    }

    private void validate(){

        phone = mPhone.getText().toString().trim();
        password  = mPassword.getText().toString().trim();
        keepSignedIn = mkeepSignedIn.isChecked();

        boolean isValid = true;

        if(phone.length()!=10){
            isValid = false;
            mPhone.setError("Please Enter 10 digits phone number");

        }

        if(password.equals("")){
            isValid = false;
            mPassword.setError("Please provide your password");
        }

        if(isValid){

            String userName = DatabaseAccess.checkCredentials(getContext(),
                    phone,password);


            if(userName == null){
                Toast.makeText(getContext(),"Invalid user id and password",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getContext(),"Login Successful",Toast.LENGTH_SHORT).show();

                Session.USERNAME = userName;
                Session.PASSWORD = password;
                Session.PHONE = phone;

                if(keepSignedIn){

                    editor.putString(Constants.USERNAME,userName);
                    editor.putString(Constants.PHONENO,phone);
                    editor.putString(Constants.PASSWORD,password);
                    editor.putBoolean(Constants.AUTO_LOGIN,true);

                    editor.commit();


                }

                else{

                    editor.putBoolean(Constants.AUTO_LOGIN,true);
                    editor.commit();
                }

                getActivity().finish();
            }
        }

    }



}
