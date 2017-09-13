package org.svcetedu.www.csemobileapp.StudentRegistration;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.svcetedu.www.csemobileapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MobileComputing extends Fragment {


    public MobileComputing() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mobile_computing, container, false);
    }

}
