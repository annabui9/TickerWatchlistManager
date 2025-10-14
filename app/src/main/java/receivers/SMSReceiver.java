package receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.example.tickerwatchlistmanager.MainActivity;

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        final Bundle bundle = intent.getExtras();
        if(intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)){
            if(bundle != null){
                Object[] pdusObj = (Object[]) bundle.get("pdus");
                String format = bundle.getString("format").toString();
                String message = "";

                for (Object o : pdusObj) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) o, format);
                    message += currentMessage.getDisplayMessageBody();
                }

                Intent launchIntent = new Intent(context, MainActivity.class);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                launchIntent.putExtra("sms", message);

                if(message.startsWith("Ticker:<<") && message.endsWith(">>")) {
                    String ticker = message.substring(9, message.length() - 2).trim().toUpperCase();

                    if(ticker.matches("[A-Z]+")){

                        launchIntent.putExtra("validTicker", ticker);
                        launchIntent.putExtra("isTickerValid", true);
                    }else {
                        launchIntent.putExtra("isTickerValid", false);
                    }

                }
                context.startActivity(launchIntent);

            }
        }
    }
}
