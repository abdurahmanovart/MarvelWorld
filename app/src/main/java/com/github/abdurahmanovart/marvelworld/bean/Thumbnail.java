package com.github.abdurahmanovart.marvelworld.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.StringDef;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

/**
 * @author Abdurakhmanov on 10.09.17
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class Thumbnail implements Parcelable {

    @StringDef({STANDARD_MEDIUM,
            LANDSCAPE_AMAZING,
            PORTRAIT_FANTASTIC
    })

    public @interface ThumbnailSize {
    }

    public static final String STANDARD_MEDIUM = "standard_medium";
    public static final String LANDSCAPE_AMAZING = "landscape_amazing";
    public static final String PORTRAIT_FANTASTIC = "portrait_fantastic";

    public static final ClassCreator CREATOR = new ClassCreator();

    @JsonProperty("path")
    private String mPath;

    public String getPath() {
        return mPath;
    }

    @JsonProperty("extension")
    private String mExtension;

    public String getExtension() {
        return mExtension;
    }


    public Thumbnail() {
        //Empty constructor needed by Jackson
    }

    protected Thumbnail(Parcel in) {
        mPath = in.readString();
        mExtension = in.readString();
    }

    @JsonIgnore
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mPath);
        dest.writeString(mExtension);
    }

    @JsonIgnore
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @param size one of the supported sizes
     * @return full path to image
     */
    @JsonIgnore
    public String getFullPath(@ThumbnailSize String size) {
        return mPath + "/" + size + "." + mExtension;
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Thumbnail thumbnail = (Thumbnail) o;
        return Objects.equal(mPath, thumbnail.mPath) &&
                Objects.equal(mExtension, thumbnail.mExtension);
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hashCode(mPath, mExtension);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("mPath", mPath)
                .add("mExtension", mExtension)
                .toString();
    }

    private static final class ClassCreator implements Creator<Thumbnail> {
        @Override
        public Thumbnail createFromParcel(Parcel in) {
            return new Thumbnail(in);
        }

        @Override
        public Thumbnail[] newArray(int size) {
            return new Thumbnail[size];
        }
    }
}