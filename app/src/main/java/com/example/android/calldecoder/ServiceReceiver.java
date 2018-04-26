package com.example.android.calldecoder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.Locale;

public class ServiceReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            String phoneNumber = intent.getExtras().getString(Intent.EXTRA_PHONE_NUMBER);

            PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
            try {
                Phonenumber.PhoneNumber number = phoneNumberUtil.parse(phoneNumber, Locale.getDefault().getCountry());
                if (phoneNumberUtil.isValidNumber(number)) {
                    Log.d("TAG", "Country Code: " + phoneNumber);
                } else {
                    Log.d("TAG", "Invalid number format: " + phoneNumber);
                }

            } catch (NumberParseException e) {
                Log.d("TAG", "Unable to parse phone number " + e.toString());
            }
        }
    }
}
