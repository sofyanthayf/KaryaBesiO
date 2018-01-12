package karyabesi_object;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sofyan Thayf on 01/09/18.
 */

public class Category implements Parcelable {

    private String id_kategori;
    private Integer jml_brand;
    private Integer jml_item;
    private String nama_en;
    private String nama_id;

    public String getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(String id_kategori) {
        this.id_kategori = id_kategori;
    }

    public Integer getJml_brand() {
        return jml_brand;
    }

    public void setJml_brand(Integer jml_brand) {
        this.jml_brand = jml_brand;
    }

    public Integer getJml_item() {
        return jml_item;
    }

    public void setJml_item(Integer jml_item) {
        this.jml_item = jml_item;
    }

    public String getNama_en() {
        return nama_en;
    }

    public void setNama_en(String nama_en) {
        this.nama_en = nama_en;
    }

    public String getNama_id() {
        return nama_id;
    }

    public void setNama_id(String nama_id) {
        this.nama_id = nama_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id_kategori);
        dest.writeValue(this.jml_brand);
        dest.writeValue(this.jml_item);
        dest.writeString(this.nama_en);
        dest.writeString(this.nama_id);
    }

    public Category() {
    }

    protected Category(Parcel in) {
        this.id_kategori = in.readString();
        this.jml_brand = (Integer) in.readValue(Integer.class.getClassLoader());
        this.jml_item = (Integer) in.readValue(Integer.class.getClassLoader());
        this.nama_en = in.readString();
        this.nama_id = in.readString();
    }

    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
