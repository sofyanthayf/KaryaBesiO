package karyabesi_object;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sofyan Thayf on 01/12/18.
 */

public class Item implements Parcelable {
    String id_item;
    String nama_item;
    Integer stock_wh;
    Integer stock_sh;

    public String getId_item() {
        return id_item;
    }

    public void setId_item(String id_item) {
        this.id_item = id_item;
    }

    public String getNama_item() {
        return nama_item;
    }

    public void setNama_item(String nama_item) {
        this.nama_item = nama_item;
    }

    public Integer getStock_wh() {
        return stock_wh;
    }

    public void setStock_wh(Integer stock_wh) {
        this.stock_wh = stock_wh;
    }

    public Integer getStock_sh() {
        return stock_sh;
    }

    public void setStock_sh(Integer stock_sh) {
        this.stock_sh = stock_sh;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id_item);
        dest.writeString(this.nama_item);
        dest.writeValue(this.stock_wh);
        dest.writeValue(this.stock_sh);
    }

    public Item() {
    }

    protected Item(Parcel in) {
        this.id_item = in.readString();
        this.nama_item = in.readString();
        this.stock_wh = (Integer) in.readValue(Integer.class.getClassLoader());
        this.stock_sh = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
