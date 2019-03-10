package com.example.contacts.login.presenter;

import android.os.AsyncTask;

import com.example.contacts.dtos.UserDTO;
import com.example.contacts.login.LoginContract;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

public class LoginPresenter implements LoginContract.Presenter {

    LoginContract.View mLoginLoginView;

    public LoginPresenter(LoginContract.View loginLoginView) {
        this.mLoginLoginView = loginLoginView;

        loginLoginView.setPresenter(this);
    }


    @Override
    public void validateCredentials(UserDTO user) {

        UserDTO validatedUser = new UserDTO();

        try {
            validatedUser = new LoginAsyncTask().execute(user).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        if(validatedUser.isAuthorized())
            mLoginLoginView.continueSuccessfullyLogin(validatedUser);
        else
            mLoginLoginView.displayAuthenticationError();

    }

    class LoginAsyncTask extends AsyncTask<UserDTO, Void, UserDTO> {

        @Override
        protected UserDTO doInBackground(UserDTO... userDTOS) {

            UserDTO userDTO = userDTOS[0];


            UserDTO user1 = new UserDTO();
            UserDTO user2 = new UserDTO();
            user1.setUserId(1l);
            user1.setUserName("j");
            user1.setPassword(Hashing.sha256()
                    .hashString("j", StandardCharsets.UTF_8)
                    .toString());
            user1.setAuthorized(true);
            user2.setUserId(2l);
            user2.setUserName("k");
            user2.setPassword(Hashing.sha256()
                    .hashString("k", StandardCharsets.UTF_8)
                    .toString());
            user2.setAuthorized(true);

            switch (userDTO.getUserName()) {
                case "j":
                    if (userDTO.getPassword().equals(user1.getPassword()))
                        return user1;
                    break;
                case "k":
                    if (userDTO.getPassword().equals(user2.getPassword()))
                        return user2;
                    break;
                default:
                    userDTO.setAuthorized(false);
            }


            return userDTO;


        }

        @Override
        protected void onPostExecute(UserDTO userDTO) {
            super.onPostExecute(userDTO);
        }
    }

}

