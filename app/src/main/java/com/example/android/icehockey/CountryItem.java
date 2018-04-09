package com.example.android.icehockey;

/**
 * Created by YuliaGoshev on 2/28/18.
 */

public class CountryItem {
    private String mCountryName;
    private int mFlagImage;

    public CountryItem(String countryName, int flagImage) {
        mCountryName = countryName;
        mFlagImage = flagImage;
    }

    public String getCountryName() {
        return mCountryName;
    }

    public int getFlagImage (){
        return mFlagImage;
    }

}
