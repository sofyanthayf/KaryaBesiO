package org.k_innovation.karyabesi_o;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import karyabesi_fragment.CategoryFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        CategoryFragment categoryFragment = new CategoryFragment();
        fragmentTransaction.add(R.id.frameContainer, categoryFragment, CategoryFragment.class.getSimpleName());

        fragmentTransaction.commit();
    }
}
