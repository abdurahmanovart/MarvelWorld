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
public class ResponseData<T> implements Parcelable {

    public static final ClassCreator CREATOR = new ClassCreator();

    @JsonProperty("results")
    private List<T> mCharacterList;

    public ResponseData() {
        //empty constructor needed by Jackson
    }

    protected ResponseData(Parcel in) {
        mCharacterList = new ArrayList<>();
        in.readList(mCharacterList, null);
    }

    @JsonIgnore
    @Override
    public int describeContents() {
        return 0;
    }

    @JsonIgnore
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(mCharacterList);
    }

    @NonNull
    public List<T> getCharacterList() {
        return mCharacterList;
    }

    //region Equals, hashCode, toString

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

    //endregion

    private static final class ClassCreator implements Creator<ResponseData> {
        @Override
        public ResponseData createFromParcel(Parcel in) {
            return new ResponseData(in);
        }

        @Override
        public ResponseData[] newArray(int size) {
            return new ResponseData[size];
        }
    }
}
