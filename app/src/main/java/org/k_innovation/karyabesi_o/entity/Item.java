package org.k_innovation.karyabesi_o.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class data untuk Item Barang
 * Created by Sofyan Thayf on 01/12/18.
 */

public class Item implements Parcelable {
    /* kategori barang */
    Category kategori;

    /* kode barang */
    String id_barang;

    /* nama barang */
    String nama_barang;

    /* stock barang di gudang (warehouse) */
    Integer stock_wh;

    /* stock barang di toko */
    Integer stock_sh;


    public String getId_barang() {
        return id_barang;
    }

    public void setId_barang(String id_barang) {
        this.id_barang = id_barang;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
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
        dest.writeString(this.id_barang);
        dest.writeString(this.nama_barang);
        dest.writeValue(this.stock_wh);
        dest.writeValue(this.stock_sh);
    }

    public Item() {
    }

    protected Item(Parcel in) {
        this.id_barang = in.readString();
        this.nama_barang = in.readString();
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
