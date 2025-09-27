package com.example.tickerwatchlistmanager;

import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
// 2 fragments, one is a listview, the other is a webview

public class MainActivity extends AppCompatActivity {

    private FragmentManager fg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            fg = getSupportFragmentManager();
            FragmentTransaction trans = fg.beginTransaction();
            TickerListFragment tlf = new TickerListFragment();
            trans.add(R.id.TickerSymbolsFCV, tlf, "tickerListFrag");
            InfoWebFragment iwf = new InfoWebFragment();
            trans.add(R.id.InfoWebFCV, iwf, "infoWebFrag");
            trans.commit();
            Log.d("","anna" + findViewById(R.id.InfoWebFCV).toString());


        }

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }
}