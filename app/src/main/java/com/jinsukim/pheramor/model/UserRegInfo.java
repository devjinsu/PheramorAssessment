package com.jinsukim.pheramor.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserRegInfo implements Parcelable {


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public UserRegInfo createFromParcel(Parcel in) {
            return new UserRegInfo(in);
        }

        public UserRegInfo[] newArray(int size) {
            return new UserRegInfo[size];
        }
    };

    private String mEmail;
    private String mPassword;
    private String mFirstName, mLastName;
    private int mZipcode;
    private int mHeight;
    private boolean mMan;
    private int mAgeMin, mAgeMax;
    private String mRace, mReligion;

    public UserRegInfo(String email) {
        mEmail = email;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public int getZipcode() {
        return mZipcode;
    }

    public void setZipcode(int zipcode) {
        mZipcode = zipcode;
    }

    public int getHeight() {
        return mHeight;
    }

    public void setHeight(int height) {
        mHeight = height;
    }

    public boolean isMan() {
        return mMan;
    }

    public void setMan(boolean man) {
        mMan = man;
    }

    public int getAgeMin() {
        return mAgeMin;
    }

    public void setAgeMin(int ageMin) {
        mAgeMin = ageMin;
    }

    public int getAgeMax() {
        return mAgeMax;
    }

    public void setAgeMax(int ageMax) {
        mAgeMax = ageMax;
    }

    public String getRace() {
        return mRace;
    }

    public void setRace(String race) {
        mRace = race;
    }

    public String getReligion() {
        return mReligion;
    }

    public void setReligion(String religion) {
        mReligion = religion;
    }


    public UserRegInfo(Parcel in){
        this.mEmail = in.readString();
        this.mPassword = in.readString();
        this.mFirstName = in.readString();
        this.mLastName = in.readString();
        this.mZipcode = in.readInt();
        this.mHeight = in.readInt();
        this.mMan = in.readByte() != 0;
        this.mAgeMin = in.readInt();
        this.mAgeMax = in.readInt();
        this.mRace = in.readString();
        this.mReligion = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mEmail);
        parcel.writeString(this.mPassword);
        parcel.writeString(this.mFirstName);
        parcel.writeString(this.mLastName);
        parcel.writeInt(this.mZipcode);
        parcel.writeInt(this.mHeight);
        parcel.writeByte((byte) (this.mMan ? 1 : 0));
        parcel.writeInt(this.mAgeMin);
        parcel.writeInt(this.mAgeMax);
        parcel.writeString(this.mRace);
        parcel.writeString(this.mReligion);
    }

    @Override
    public String toString() {
        return "UserRegInfo{" +
                "mEmail='" + mEmail + '\'' +
                ", mPassword='" + mPassword + '\'' +
                ", mFirstName='" + mFirstName + '\'' +
                ", mLastName='" + mLastName + '\'' +
                ", mZipcode=" + mZipcode +
                ", mHeight=" + mHeight +
                ", mMan=" + mMan +
                ", mAgeMin=" + mAgeMin +
                ", mAgeMax=" + mAgeMax +
                ", mRace='" + mRace + '\'' +
                ", mReligion='" + mReligion + '\'' +
                '}';
    }
}
