package com.example.osaka_trip;

public class Preparation_Item {

    boolean checked;
    String ItemString;

    Preparation_Item(boolean b, String t) {
        checked = b;
        ItemString = t;
    }

    public boolean isChecked() {
        return checked;
    }
}
