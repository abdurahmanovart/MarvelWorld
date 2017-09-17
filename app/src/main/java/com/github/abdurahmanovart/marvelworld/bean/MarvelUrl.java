package com.github.abdurahmanovart.marvelworld.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

/**
 * @author Abdurakhmanov on 17.09.17
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarvelUrl implements Parcelable {

    public static final ClassCreator CREATOR = new ClassCreator();

    @JsonProperty("type")
    private String mType;

    public String getType() {
        return mType;
    }

    @JsonProperty("url")
    private String mUrl;

    public String getUrl() {
        return mUrl;
    }

    public MarvelUrl() {
    }

    protected MarvelUrl(Parcel in) {
        mType = in.readString();
        mUrl = in.readString();
    }

    @JsonIgnore
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mType);
        dest.writeString(mUrl);
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
        MarvelUrl marvelUrl = (MarvelUrl) o;
        return Objects.equal(CREATOR, marvelUrl.CREATOR) &&
                Objects.equal(mType, marvelUrl.mType) &&
                Objects.equal(mUrl, marvelUrl.mUrl);
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hashCode(CREATOR, mType, mUrl);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("CREATOR", CREATOR)
                .add("mType", mType)
                .add("mUrl", mUrl)
                .toString();
    }

    private static final class ClassCreator implements Creator<MarvelUrl> {

        @Override
        public MarvelUrl createFromParcel(Parcel in) {
            return new MarvelUrl(in);
        }

        @Override
        public MarvelUrl[] newArray(int size) {
            return new MarvelUrl[size];
        }

    }
}
