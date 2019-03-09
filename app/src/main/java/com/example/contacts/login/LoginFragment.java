package com.example.contacts.login;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.contacts.R;
import com.example.contacts.dtos.UserDTO;
import com.example.contacts.login.presenter.LoginPresenter;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;


public class LoginFragment extends Fragment {

    private UserDTO mUserDTO;
    private EditText mUserNameET;
    private EditText mPasswordET;
    private Button mLoginB;

    private OnSuccessfullyLoginListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        mLoginB.setOnClickListener(

                //() -> System.out.print("**********************"));
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mUserDTO = new UserDTO();

                        String userName = mUserNameET.getText().toString();
                        String password = mPasswordET.getText().toString();

                        if(userName.equals("") || password.equals(""))
                            Toast.makeText(getActivity().getApplication(),R.string.required_fileds_error,Toast.LENGTH_SHORT).show();

                        mUserDTO.setUserName(userName);

                        mUserDTO.setPassword(Hashing.sha256()
                                    .hashString(password, StandardCharsets.UTF_8)
                                    .toString());

                        if(LoginPresenter.validateLogin(mUserDTO).isAuthorized())
                            mListener.onSuccessfullyLogin(mUserDTO);
                        else
                            Toast.makeText(getActivity().getApplicationContext(),R.string.user_authorization_error,Toast.LENGTH_SHORT).show();
                    }


                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mUserNameET = view.findViewById(R.id.editText_user);
        mPasswordET = view.findViewById(R.id.editText_password);
        mLoginB = view.findViewById(R.id.button_login);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnSuccessfullyLoginListener) {
            mListener = (OnSuccessfullyLoginListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnSuccessfullyLoginListener {
        void onSuccessfullyLogin(UserDTO userDTO);
    }


}
