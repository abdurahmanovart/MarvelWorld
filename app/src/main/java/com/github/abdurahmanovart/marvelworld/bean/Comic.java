package com.github.abdurahmanovart.marvelworld.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

/**
 * @author Abdurakhmanov on 10.09.17
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comic implements Parcelable {

    public static final ClassCreator CREATOR = new ClassCreator();

    @JsonProperty("resourceURI")
    private String mResourceURI;

    @JsonProperty("name")
    private String mName;

    public Comic() {
        //empty constructor needed by Jackson
    }

    protected Comic(Parcel in) {
        mResourceURI = in.readString();
        mName = in.readString();
    }

    @JsonIgnore
    @Override
    public int describeContents() {
        return 0;
    }

    @JsonIgnore
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mResourceURI);
        dest.writeString(mName);
    }

    @NonNull
    public String getResourceURI() {
        return mResourceURI;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    //region Equals, hashCode, toString

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comic comic = (Comic) o;
        return Objects.equal(mResourceURI, comic.mResourceURI) &&
                Objects.equal(mName, comic.mName);
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hashCode(mResourceURI,
                mName);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("mResourceURI", mResourceURI)
                .add("mName", mName)
                .toString();
    }

    //endregion

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
