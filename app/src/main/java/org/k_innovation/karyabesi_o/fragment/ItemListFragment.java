package org.k_innovation.karyabesi_o.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.k_innovation.karyabesi_o.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemListFragment extends Fragment {


    public ItemListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_item_list, container, false);
    }

}
