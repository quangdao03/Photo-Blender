package com.example.photoblender;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Background implements Parcelable {
    private int image;

    public Background() {
    }

    public Background(int image) {
        this.image = image;
    }

    protected Background(Parcel in) {
        image = in.readInt();
    }


    public static final Creator<Background> CREATOR = new Creator<Background>() {
        @Override
        public Background createFromParcel(Parcel in) {
            return new Background(in);
        }

        @Override
        public Background[] newArray(int size) {
            return new Background[size];
        }
    };

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Background{" +
                "image=" + image +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(image);
    }
}
