package com.github.abdurahmanovart.marvelworld.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

/**
 * @author Abdurakhmanov on 10.09.17
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class MarvelCharacter implements Parcelable {

    public static final ClassCreator CREATOR = new ClassCreator();

    @JsonProperty("name")
    private String mName;

    public String getName() {
        return mName;
    }

    @JsonProperty("thumbnail")
    private Thumbnail mThumbnail;

    public Thumbnail getThumbnail() {
        return mThumbnail;
    }

    @JsonProperty("description")
    private String mDescription;

    public String getDescription() {
        return mDescription;
    }

    @JsonProperty("resourceURI")
    private String mResourceUri;

    public String getResourceUri() {
        return mResourceUri;
    }

    @JsonProperty("comics")
    private Comics mComics;

    public Comics getComics() {
        return mComics;
    }

    public MarvelCharacter() {
        //Empty constructor needed by Jackson
    }

    protected MarvelCharacter(Parcel in) {
        mName = in.readString();
        mDescription = in.readString();
        mThumbnail = in.readParcelable(Thumbnail.class.getClassLoader());
        mResourceUri = in.readString();
        mComics = in.readParcelable(Comics.class.getClassLoader());
    }

    @JsonIgnore
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mDescription);
        dest.writeParcelable(mThumbnail, flags);
        dest.writeString(mResourceUri);
        dest.writeParcelable(mComics, flags);
    }

    @JsonIgnore
    @Override
    public int describeContents() {
        return 0;
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarvelCharacter that = (MarvelCharacter) o;
        return Objects.equal(mName, that.mName) &&
                Objects.equal(mThumbnail, that.mThumbnail) &&
                Objects.equal(mDescription, that.mDescription) &&
                Objects.equal(mResourceUri, that.mResourceUri) &&
                Objects.equal(mComics, that.mComics);
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hashCode(mName,
                mThumbnail,
                mDescription,
                mResourceUri,
                mComics);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("mName", mName)
                .add("mThumbnail", mThumbnail)
                .add("mDescription", mDescription)
                .add("mResourceUri", mResourceUri)
                .add("mComics", mComics)
                .toString();
    }

    public static final class ClassCreator implements Creator<MarvelCharacter> {
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