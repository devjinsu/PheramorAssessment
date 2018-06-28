package com.jinsukim.pheramor.model;

import android.graphics.Bitmap;
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
    private String mZipcode;
    private int mHeight;
    private boolean mMan;
    private int mAgeMin, mAgeMax;
    private String mRace, mReligion;
    private String mDOB;
    private int mInterest;

    public UserRegInfo() {
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

    public String getZipcode() {
        return mZipcode;
    }

    public void setZipcode(String zipcode) {
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

    public String getDOB() {
        return mDOB;
    }

    public void setDOB(String DOB) {
        mDOB = DOB;
    }

    public int getInterest() {
        return mInterest;
    }

    public void setInterest(int interest) {
        mInterest = interest;
    }


    public UserRegInfo(Parcel in){
        this.mEmail = in.readString();
        this.mPassword = in.readString();
        this.mFirstName = in.readString();
        this.mLastName = in.readString();
        this.mZipcode = in.readString();
        this.mHeight = in.readInt();
        this.mMan = in.readByte() != 0;
        this.mAgeMin = in.readInt();
        this.mAgeMax = in.readInt();
        this.mRace = in.readString();
        this.mReligion = in.readString();
        this.mDOB = in.readString();
        this.mInterest = in.readInt();
        //this.mProfile = in.readParcelable(Bitmap.class.getClassLoader());
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
        parcel.writeString(this.mZipcode);
        parcel.writeInt(this.mHeight);
        parcel.writeByte((byte) (this.mMan ? 1 : 0));
        parcel.writeInt(this.mAgeMin);
        parcel.writeInt(this.mAgeMax);
        parcel.writeString(this.mRace);
        parcel.writeString(this.mReligion);
        parcel.writeString(this.mDOB);
        parcel.writeInt(this.mInterest);
        //parcel.writeValue(this.mProfile);
    }

    @Override
    public String toString() {
        return "UserRegInfo{" +
                "mEmail='" + mEmail + '\'' +
                ", mPassword='" + mPassword + '\'' +
                ", mFirstName='" + mFirstName + '\'' +
                ", mLastName='" + mLastName + '\'' +
                ", mZipcode='" + mZipcode + '\'' +
                ", mHeight=" + mHeight +
                ", mMan=" + mMan +
                ", mAgeMin=" + mAgeMin +
                ", mAgeMax=" + mAgeMax +
                ", mRace='" + mRace + '\'' +
                ", mReligion='" + mReligion + '\'' +
                ", mDOB='" + mDOB + '\'' +
                '}';
    }
}
