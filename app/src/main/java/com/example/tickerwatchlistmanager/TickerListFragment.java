package com.example.tickerwatchlistmanager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TickerListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class TickerListFragment extends Fragment {

    ListView tickerListWidget;

    public static TickerListFragment newInstance(String param1, String param2) {
        TickerListFragment fragment = new TickerListFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_ticker_list, container, false);
        tickerListWidget = view.findViewById(R.id.tickerSymbols);
        return view;
    }
}