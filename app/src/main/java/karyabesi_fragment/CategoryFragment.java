package karyabesi_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.k_innovation.karyabesi_o.R;

import java.util.ArrayList;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;
import karyabesi_object.Category;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference categoryRef;
    static DatabaseReference subcategoryRef;
    List<Category> categories = new ArrayList<>();
    List<Category> subcategories = new ArrayList<>();

    SectionedRecyclerViewAdapter sectionAdapter;

    CategoryListAdapter adapter;
//    CategoryListSectionAdapter adapter;
    private RecyclerView rvListCategory;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        sectionAdapter = new SectionedRecyclerViewAdapter();

        categoryRef = database.getReference("kategori_main");
        subcategoryRef = database.getReference("kategori");

        categoryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Category category = postSnapshot.getValue( Category.class );
                    categories.add(category);
                    sectionAdapter.notifyDataSetChanged();
//                    adapter.notifyDataSetChanged();
                }
//                sectionAdapter.addSection( new CategoryListSectionAdapter(categories));

                subcategoryRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                            Category category = postSnapshot.getValue( Category.class );
                            subcategories.add(category);
                            sectionAdapter.notifyDataSetChanged();
//                    adapter.notifyDataSetChanged();
                        }

                        for(int i=0; i < categories.size(); i++){
                            List<Category> subc = getSubCategoryByCategory( categories.get(i).getId_kategori() );

                            sectionAdapter.addSection(new CategoryListSectionAdapter(categories.get(i), subc));

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        rvListCategory = (RecyclerView) view.findViewById(R.id.rvCategory);
        rvListCategory.setHasFixedSize(true);
        rvListCategory.setLayoutManager(new LinearLayoutManager(getContext()));
        rvListCategory.setAdapter( sectionAdapter );

//        adapter = new CategoryListAdapter(getActivity(), categories, false);
////        adapter = new CategoryListSectionAdapter(categories);
//        rvListCategory.setAdapter(adapter);
//
        return view;
    }

    private List<Category> getSubCategoryByCategory( String id_cat ){
        List<Category> subcat = new ArrayList<>();

        for (int i=0; i < subcategories.size(); i++){
            Log.d(">>> ID CAT >>>", subcategories.get(i).getId_kategori().substring(0,2));
            if( subcategories.get(i).getId_kategori().substring(0,2).equals( id_cat )){
                subcat.add( subcategories.get(i));
            }
        }
        return subcat;
    }



    private class CategoryListSectionAdapter extends StatelessSection {

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
                    sectionAdapter.notifyDataSetChanged();
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
}


//https://stackoverflow.com