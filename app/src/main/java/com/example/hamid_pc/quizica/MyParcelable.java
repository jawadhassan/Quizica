package com.example.hamid_pc.quizica;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Hamid-PC on 4/12/2017.
 */

public class MyParcelable implements Parcelable {

    public static final Parcelable.Creator<MyParcelable> CREATOR = new Parcelable.Creator<MyParcelable>() {
        @Override
        public MyParcelable createFromParcel(Parcel source) {
            return new MyParcelable(source);
        }

        @Override
        public MyParcelable[] newArray(int size) {
            return new MyParcelable[size];
        }


    };
    private String name;
    private String id;
    private String uuid;

    private MyParcelable(Parcel in) {
        name = in.readString();
        id = in.readString();
        uuid = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(id);
        dest.writeString(uuid);
    }


}
