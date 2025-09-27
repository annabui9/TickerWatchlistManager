package com.example.tickerwatchlistmanager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoWebFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoWebFragment extends Fragment {

    WebView infoWebWidget;

    public InfoWebFragment() {
        // Required empty public constructor
    }

    public static InfoWebFragment newInstance(String param1, String param2) {
        InfoWebFragment fragment = new InfoWebFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info_web, container, false);
        infoWebWidget = view.findViewById(R.id.infoWeb);
        infoWebWidget.getSettings().setJavaScriptEnabled(true);
        infoWebWidget.loadUrl("https://seekingalpha.com");

        return view;
    }
}