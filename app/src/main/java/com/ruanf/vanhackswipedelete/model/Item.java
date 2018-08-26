package com.ruanf.vanhackswipedelete.model;

import com.google.gson.annotations.SerializedName;

public class Item {
    public Item(String name, String language, String currency) {
        this.name = name;
        this.language = language;
        this.currency = currency;
    }

   public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @SerializedName("name")
    String name;

    @SerializedName("language")
    String language;

    @SerializedName("currency")
    String currency;

    @Override
    public String toString() {
        return "Wafer{" +
                "name='" + name + '\'' +
                ", language='" + language + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }

}
