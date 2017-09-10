package com.github.abdurahmanovart.marvelworld.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

/**
 * @author Abdurakhmanov on 10.09.17
 */
public class Comic implements Parcelable {

    public static final ClassCreator CREATOR = new ClassCreator();

    @JsonProperty("resourceURI")
    public String mResourceUri;

    public String getResourceUri() {
        return mResourceUri;
    }

    @JsonProperty("name")
    public String mName;

    public String getName() {
        return mName;
    }

    public Comic() {
    }

    protected Comic(Parcel in) {
        mResourceUri = in.readString();
        mName = in.readString();
    }

    @JsonIgnore
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mResourceUri);
        dest.writeString(mName);
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
        Comic comic = (Comic) o;
        return Objects.equal(mResourceUri, comic.mResourceUri) &&
                Objects.equal(mName, comic.mName);
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hashCode(mResourceUri, mName);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("mResourceUri", mResourceUri)
                .add("mName", mName)
                .toString();
    }

    private static final class ClassCreator implements Creator<Comic> {
        @Override
        public Comic createFromParcel(Parcel in) {
            return new Comic(in);
        }

        @Override
        public Comic[] newArray(int size) {
            return new Comic[size];
        }
    }
}