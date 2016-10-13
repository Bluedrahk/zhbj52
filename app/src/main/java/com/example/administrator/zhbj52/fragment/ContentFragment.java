package com.example.administrator.zhbj52.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.zhbj52.R;


public class ContentFragment extends BaseFragment {

    @Override
    public View initViews() {
        View view = View.inflate(mActivity,R.layout.fragment_content,null);
        return view;
    }
}
