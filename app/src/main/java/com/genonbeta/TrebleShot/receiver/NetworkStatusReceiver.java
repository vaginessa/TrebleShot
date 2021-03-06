package com.genonbeta.TrebleShot.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import com.genonbeta.TrebleShot.service.CommunicationService;

public class NetworkStatusReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent.hasExtra("networkInfo"))
            evaluateTheCondition((NetworkInfo) intent.getParcelableExtra("networkInfo"), context);
    }

    protected void evaluateTheCondition(NetworkInfo info, Context context)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        if (!preferences.getBoolean("serviceLock", false) && preferences.getBoolean("allow_autoconnect", true) && info.isConnected())
            context.startService(new Intent(context, CommunicationService.class));
    }
}
