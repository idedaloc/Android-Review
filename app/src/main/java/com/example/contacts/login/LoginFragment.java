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

import com.example.contacts.R;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnSuccessfullyLoginListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    private String mUserName;
    private String mPassword;
    private EditText mUserNameET;
    private EditText mPasswordET;
    private Button mLoginB;

    private MessageDigest mMessageDigest;

    private OnSuccessfullyLoginListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
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
                        mUserName = mUserNameET. getText().toString();

                        mPassword = Hashing.sha256()
                                    .hashString(mPasswordET.getText().toString(), StandardCharsets.UTF_8)
                                    .toString();

                        mListener.onSuccessfullyLogin(mUserName);
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



    // TODO: Rename method, update argument and hook method into UI event


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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnSuccessfullyLoginListener {
        void onSuccessfullyLogin(String user);
    }
}
