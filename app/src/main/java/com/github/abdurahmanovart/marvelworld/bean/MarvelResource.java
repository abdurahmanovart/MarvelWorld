package com.github.abdurahmanovart.marvelworld.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import java.util.List;

/**
 * @author Abdurakhmanov on 17.09.17
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarvelResource implements Parcelable {

    public static final ClassCreator CREATOR = new ClassCreator();

    @JsonProperty("title")
    private String mTitle;

    @JsonProperty("description")
    private String mDescription;

    @JsonProperty("thumbnail")
    private Thumbnail mThumbnail;

    @JsonProperty("images")
    private List<Thumbnail> mImages;

    @JsonProperty("resourceURI")
    private String mResourceURI;

    public MarvelResource() {
        //Empty constructor needed by Jackson
    }

    protected MarvelResource(Parcel in) {
        mTitle = in.readString();
        mDescription = in.readString();
        mThumbnail = in.readParcelable(Thumbnail.class.getClassLoader());
        mImages = in.createTypedArrayList(Thumbnail.CREATOR);
        mResourceURI = in.readString();
    }

    //region Getters

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public Thumbnail getThumbnail() {
        return mThumbnail;
    }

    public List<Thumbnail> getImages() {
        return mImages;
    }

    public String getResourceURI() {
        return mResourceURI;
    }

    //endregion

    //region Equals, hashCode, toString

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarvelResource that = (MarvelResource) o;
        return Objects.equal(mTitle, that.mTitle) &&
                Objects.equal(mDescription, that.mDescription) &&
                Objects.equal(mThumbnail, that.mThumbnail) &&
                Objects.equal(mImages, that.mImages) &&
                Objects.equal(mResourceURI, that.mResourceURI);
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hashCode(mTitle,
                mDescription,
                mThumbnail,
                mImages,
                mResourceURI);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("mName", mTitle)
                .add("mDescription", mDescription)
                .add("mThumbnail", mThumbnail)
                .add("mImages", mImages)
                .add("mResourceURI", mResourceURI)
                .toString();
    }

    //endregion

    @JsonIgnore
    @Override
    public int describeContents() {
        return 0;
    }

    @JsonIgnore
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mDescription);
        dest.writeParcelable(mThumbnail, flags);
        dest.writeTypedList(mImages);
        dest.writeString(mResourceURI);
    }

    private static final class ClassCreator implements Creator<MarvelResource> {
        @Override
        public MarvelResource createFromParcel(Parcel in) {
            return new MarvelResource(in);
        }

        @Override
        public MarvelResource[] newArray(int size) {
            return new MarvelResource[size];
        }
    }
}
