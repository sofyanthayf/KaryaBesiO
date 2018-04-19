package org.k_innovation.karyabesi_o.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.k_innovation.karyabesi_o.R;

import java.util.List;

import org.k_innovation.karyabesi_o.entity.Category;

/**
 * Created by Sofyan Thayf on 01/09/18.
 */

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryHolder> {

    List<Category> categories;
    private LayoutInflater inflater;
    private Context context;
    private boolean isSubCategory = false;

    public CategoryListAdapter(Context context, List<Category> categories, boolean subcategory){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.categories = categories;
        this.isSubCategory = subcategory;
    }

    @Override
    public CategoryListAdapter.CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if( this.isSubCategory ){
            view = inflater.inflate(R.layout.card_subcategory, parent, false);
        } else {
            view = inflater.inflate(R.layout.card_category, parent, false);
        }

        CategoryHolder holder = new CategoryHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CategoryListAdapter.CategoryHolder holder, int position) {
        Category current = categories.get(position);
        holder.setData(current, position);
        holder.setListener();
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryHolder extends RecyclerView.ViewHolder {

        TextView tvCategoryID, tvCategoryEN;
        int position;
        Category current;
        View itemView;

        public CategoryHolder(View itemView) {
            super(itemView);

            this.itemView = itemView;

            tvCategoryID = (TextView) itemView.findViewById(R.id.tvCategoryID);
            tvCategoryEN = (TextView) itemView.findViewById(R.id.tvCategoryEN);

        }

        public void setData(Category current, int position) {

            tvCategoryID.setText(current.getNama_id() + " - " + current.getId_kategori() );
            tvCategoryEN.setText(current.getNama_en());

        }

        public void setListener() {

        }
    }
}
