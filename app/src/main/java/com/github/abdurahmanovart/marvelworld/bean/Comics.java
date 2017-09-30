package com.github.abdurahmanovart.marvelworld.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Abdurakhmanov on 10.09.17
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comics implements Parcelable {

    public static final ClassCreator CREATOR = new ClassCreator();

    @JsonProperty("collectionURI")
    private String mCollectionURI;

    @JsonProperty("items")
    private List<Comic> mComicList;

    public Comics() {
        //empty constructor needed by Jackson
    }

    protected Comics(Parcel in) {
        mCollectionURI = in.readString();
        mComicList = new ArrayList<>();
        in.readTypedList(mComicList, Comic.CREATOR);
    }

    @JsonIgnore
    @Override
    public int describeContents() {
        return 0;
    }

    @JsonIgnore
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mCollectionURI);
        dest.writeTypedList(mComicList);
    }

    @NonNull
    public String getCollectionURI() {
        return mCollectionURI;
    }

    @NonNull
    public List<Comic> getComicList() {
        return mComicList;
    }

    //region Equals, hashCode, toString

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comics comics = (Comics) o;
        return Objects.equal(mCollectionURI, comics.mCollectionURI) &&
                Objects.equal(mComicList, comics.mComicList);
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hashCode(mCollectionURI,
                mComicList);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("mCollectionURI", mCollectionURI)
                .add("mComicList", mComicList)
                .toString();
    }

    //endregion

    private static final class ClassCreator implements Creator<Comics> {
        @Override
        public Comics createFromParcel(Parcel in) {
            return new Comics(in);
        }

        @Override
        public Comics[] newArray(int size) {
            return new Comics[size];
        }
    }
}
