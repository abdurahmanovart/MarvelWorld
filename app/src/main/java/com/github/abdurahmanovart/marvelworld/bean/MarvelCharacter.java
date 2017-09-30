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
public class MarvelCharacter implements Parcelable {

    public static final ClassCreator CREATOR = new ClassCreator();

    @JsonProperty("name")
    private String mName;

    @JsonProperty("description")
    private String mDescription;

    @JsonProperty("thumbnail")
    private Thumbnail mThumbnail;

    @JsonProperty("comics")
    private Comics mComics;

    @JsonProperty("urls")
    private List<MarvelUrl> mUrls;

    public MarvelCharacter() {
        //empty constructor needed by Jackson
    }

    protected MarvelCharacter(Parcel in) {
        mName = in.readString();
        mDescription = in.readString();
        mThumbnail = in.readParcelable(Thumbnail.class.getClassLoader());
        mComics = in.readParcelable(Comics.class.getClassLoader());
        mUrls = new ArrayList<>();
        in.readTypedList(mUrls, MarvelUrl.CREATOR);
    }

    @JsonIgnore
    @Override
    public int describeContents() {
        return 0;
    }

    @JsonIgnore
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mDescription);
        dest.writeParcelable(mThumbnail, flags);
        dest.writeParcelable(mComics, flags);
        dest.writeTypedList(mUrls);
    }

    //region Getters

    @NonNull
    public String getName() {
        return mName;
    }

    @NonNull
    public String getDescription() {
        return mDescription;
    }

    @NonNull
    public Thumbnail getThumbnail() {
        return mThumbnail;
    }

    @NonNull
    public Comics getComics() {
        return mComics;
    }

    public List<MarvelUrl> getUrls() {
        return mUrls;
    }

    //endregion

    //region Equals, hashCode, toString

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarvelCharacter that = (MarvelCharacter) o;
        return Objects.equal(mName, that.mName) &&
                Objects.equal(mDescription, that.mDescription) &&
                Objects.equal(mThumbnail, that.mThumbnail) &&
                Objects.equal(mComics, that.mComics) &&
                Objects.equal(mUrls, that.mUrls);
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hashCode(mName,
                mDescription,
                mThumbnail,
                mComics,
                mUrls);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("mName", mName)
                .add("mDescription", mDescription)
                .add("mThumbnail", mThumbnail)
                .add("mComics", mComics)
                .add("mUrls", mUrls)
                .toString();
    }

    //endregion

    private static final class ClassCreator implements Creator<MarvelCharacter> {
        @Override
        public MarvelCharacter createFromParcel(Parcel in) {
            return new MarvelCharacter(in);
        }

        @Override
        public MarvelCharacter[] newArray(int size) {
            return new MarvelCharacter[size];
        }
    }
}
