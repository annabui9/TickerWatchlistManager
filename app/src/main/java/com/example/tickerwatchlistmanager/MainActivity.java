package com.example.tickerwatchlistmanager;


import android.content.Intent;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


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
    private TickerListFragment tlf;
    InfoWebFragment iwf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null){


            fg = getSupportFragmentManager();
            FragmentTransaction trans = fg.beginTransaction();


            tlf = new TickerListFragment();
            trans.add(R.id.TickerSymbolsFCV, tlf, "tickerListFrag");


            iwf = new InfoWebFragment();
            trans.add(R.id.InfoWebFCV, iwf, "infoWebFrag");


            trans.commit();
        } else{
            fg = getSupportFragmentManager();
            tlf = (TickerListFragment) fg.findFragmentByTag("tickerListFrag");
            iwf = (InfoWebFragment) fg.findFragmentByTag("infoWebFrag");
        }


        handleIntent(getIntent());
    }


    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent);
    }


    private void handleIntent(Intent intent){
        boolean isTickerValid = intent.getBooleanExtra("isTickerValid", false);
        String ticker = intent.getStringExtra("validTicker");
        String sms = intent.getStringExtra("sms");


        if (isTickerValid && ticker != null){
            Toast.makeText(this, "Added " + ticker + " to watchlist", Toast.LENGTH_LONG).show();


            if (tlf != null){
                tlf.addTickers(ticker);
            }
            if (iwf != null){
                iwf.loadTickerInfo(ticker);
            }
        }else if ( sms != null && sms.startsWith("Ticker:<<")){
            Toast.makeText(this, "Invalid ticker received", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "No valid watchlist entry found", Toast.LENGTH_LONG).show();
        }


    }


}
