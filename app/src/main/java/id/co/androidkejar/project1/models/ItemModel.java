package id.co.androidkejar.project1.models;

import android.graphics.drawable.Drawable;

/**
 * Created by imammagribi on 5/7/17.
 */

public class ItemModel {
    String name;
    Drawable image;


    public ItemModel(String name, Drawable image) {
        this.name = name;
        this.image = image;

    }


    public String getName() {
        return name;
    }

    public Drawable getImage() {
        return image;
    }


}
