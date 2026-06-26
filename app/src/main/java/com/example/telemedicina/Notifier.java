package com.example.telemedicina;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Notifier {
    public static void callPhone(Context ctx, String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phone));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);
    }

    public static void sendEmail(Context ctx, String to, String subject, String body) {
        Intent email = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + to));
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, body);
        email.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(email);
    }
}
