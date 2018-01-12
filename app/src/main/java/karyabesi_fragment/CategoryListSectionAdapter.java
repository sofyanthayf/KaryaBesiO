package karyabesi_fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.k_innovation.karyabesi_o.R;

import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;
import karyabesi_object.Category;

/**
 * https://stackoverflow.com/questions/39228288/how-to-show-hierarchical-data-structure-with-recycleview
 * https://github.com/luizgrp/SectionedRecyclerViewAdapter
 * https://github.com/luizgrp/SectionedRecyclerViewAdapter/blob/master/app/src/main/java/io/github/luizgrp/sectionedrecyclerviewadapter/demo/Example4Fragment.java
 */

public class CategoryListSectionAdapter extends StatelessSection {

    Category main_cat;
    List<Category> list;
    boolean expanded = false;

    CategoryListSectionAdapter( Category main_cat, List<Category> list) {
        super(new SectionParameters.Builder(R.layout.card_subcategory)
                .headerResourceId(R.layout.card_category)
                .build());

        this.main_cat = main_cat;
        this.list = list;
    }

    @Override
    public int getContentItemsTotal() {
        return expanded ? list.size() : 0;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ItemViewHolder itemHolder = (ItemViewHolder) holder;

        Category category = list.get(position);

        itemHolder.tvCategoryID.setText(category.getNama_id());
        itemHolder.tvCategoryEN.setText(category.getNama_en());

        itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        final HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

        headerHolder.tvCategoryID.setText(main_cat.getNama_id());
        headerHolder.tvCategoryEN.setText(main_cat.getNama_en());

        headerHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expanded = !expanded;
//                sectionAdapter.notifyDataSetChanged();
            }
        });
    }


    class HeaderViewHolder extends RecyclerView.ViewHolder {

        View rootView;
        TextView tvCategoryID, tvCategoryEN;

        HeaderViewHolder(View view) {
            super(view);

            rootView = view;
            tvCategoryID = (TextView) view.findViewById(R.id.tvCategoryID);
            tvCategoryEN = (TextView) view.findViewById(R.id.tvCategoryEN);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        View rootView;
        TextView tvCategoryID, tvCategoryEN;

        ItemViewHolder(View view) {
            super(view);

            rootView = view;
            tvCategoryID = (TextView) view.findViewById(R.id.tvCategoryID);
            tvCategoryEN = (TextView) view.findViewById(R.id.tvCategoryEN);
        }
    }

}