package com.example.exam;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class WebFragment extends Fragment {


    @BindView(R.id.web_item)
    WebView webItem;

    public WebFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web, null);
        ButterKnife.bind(this,view);

        //跳转百度页面，
        webItem.setWebViewClient(new WebViewClient());
        webItem.loadUrl("https://www.baidu.com/");

        return view;
    }

}
