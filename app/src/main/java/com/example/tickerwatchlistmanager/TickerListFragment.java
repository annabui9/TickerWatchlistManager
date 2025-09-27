package com.example.tickerwatchlistmanager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TickerListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class TickerListFragment extends Fragment {

    private ListView tickerListWidget;
    private ArrayList<String> tickerList;
    private ArrayAdapter<String> adapter;
    private int maxEntries;

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

        onClickListener();
        creatingList();
        return view;
    }

    private void creatingList(){

        tickerList = new ArrayList<>();
        tickerList.add("NEE");
        tickerList.add("AAPL");
        tickerList.add("DIS");

        adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_list_item_1, tickerList);
        tickerListWidget.setAdapter(adapter);
    }


    private void onClickListener(){

        tickerListWidget.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                String ticker = (String) parent.getItemAtPosition(position);
                InfoWebFragment webFragment = (InfoWebFragment) getParentFragmentManager()
                        .findFragmentByTag("infoWebFrag");
                if(webFragment != null){
                    webFragment.loadTickerInfo(ticker);
                }
            }
        });
    }

    private void addTickers(String newTicker){ //ensures there are only 6 elements on list, + adds new to 6th, not used
        if(tickerList.size() < maxEntries){
            tickerList.add(newTicker);
        }else {
            tickerList.set(5, newTicker);
        }
        adapter.notifyDataSetChanged();
    }

    //testing
//     private void useStringResource(){
//        String[] content = getResources().getStringArray(R.array.tickerStrings);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
//                android.R.layout.simple_list_item_1, content);
//        tickerListWidget.setAdapter(adapter);
//    }
}