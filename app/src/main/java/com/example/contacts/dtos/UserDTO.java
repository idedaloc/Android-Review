package com.example.contacts.dtos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class UserDTO implements Parcelable {
    private Long userId;
    private String userName;
    private String password;
    private boolean authorized;

    public UserDTO() {}

    public UserDTO(Long userId, String userName, String password, boolean authorized) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.authorized = authorized;
    }

    public UserDTO(Parcel in) {
        this.userId = in.readLong();
        this.password = in.readString();
        this.userName = in.readString();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return authorized == userDTO.authorized &&
                Objects.equals(userId, userDTO.userId) &&
                Objects.equals(userName, userDTO.userName) &&
                Objects.equals(password, userDTO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, password, authorized);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", authorized=" + authorized +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.userId);
        dest.writeString(this.userName);
        dest.writeString(this.password);
    }

    public static final Parcelable.Creator<UserDTO> CREATOR
            = new Parcelable.Creator<UserDTO>() {
        public UserDTO createFromParcel(Parcel in) {
            return new UserDTO(in);
        }

        public UserDTO[] newArray(int size) {
            return new UserDTO[size];
        }
    };
}