package com.github.abdurahmanovart.marvelworld.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Abdurakhmanov on 10.09.17
 */

class ResponseData implements Parcelable {

    public ResponseData() {
        //Empty constructor needed by Jackson
    }

    @JsonProperty("results")
    private List<MarvelCharacter> mCharacterList;

    public List<MarvelCharacter> getCharacterList() {
        return mCharacterList;
    }

    public static final ClassCreator CREATOR = new ClassCreator();

    public static final class ClassCreator implements Creator<ResponseData> {
        @Override
        public ResponseData createFromParcel(Parcel in) {
            return new ResponseData(in);
        }

        @Override
        public ResponseData[] newArray(int size) {
            return new ResponseData[size];
        }
    }

    protected ResponseData(Parcel in) {
        mCharacterList = new ArrayList<>();
        in.readList(mCharacterList, null);
    }

    @JsonIgnore
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(mCharacterList);
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
        ResponseData that = (ResponseData) o;
        return Objects.equal(mCharacterList, that.mCharacterList);
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hashCode(mCharacterList);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("mCharacterList", mCharacterList)
                .toString();
    }
}