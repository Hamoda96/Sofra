package com.hamoda.sofra.view.fragment.foodOrder.listOfOrder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.hamoda.sofra.R;

import butterknife.ButterKnife;


public class ListOfOrderFragment extends Fragment {

    public ListOfOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_of_order, container, false);
        ButterKnife.bind(this,view);

        return view;
    }

}
