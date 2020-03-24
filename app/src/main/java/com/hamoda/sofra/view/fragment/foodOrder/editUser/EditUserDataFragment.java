package com.hamoda.sofra.view.fragment.foodOrder.editUser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.hamoda.sofra.R;

import butterknife.ButterKnife;


public class EditUserDataFragment extends Fragment {

    public EditUserDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_user_data, container, false);
        ButterKnife.bind(this,view);

        return view;
    }

}
