package com.example.administrator.zhbj52.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.zhbj52.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LeftMenuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LeftMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeftMenuFragment extends BaseFragment {
    @Override
    public View initViews() {
        View view = View.inflate(mActivity,R.layout.fragment_left_menu,null);
        return view;
    }
}
