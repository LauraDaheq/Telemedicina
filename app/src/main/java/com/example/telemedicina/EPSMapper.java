package com.example.telemedicina;

import android.content.Context;
import android.content.res.AssetManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class EPSMapper {

    public static class Contact {
        public String phone;
        public String email;
        public Contact(String p, String e) { phone = p; email = e; }
    }

    // Try to load from assets/eps.json; fall back to hardcoded map
    public static Contact getContact(Context ctx, String epsName) {
        Map<String, Contact> map = loadFromAssets(ctx);
        if (map.containsKey(epsName)) return map.get(epsName);
        // fallback
        Map<String, Contact> fallback = new HashMap<>();
        fallback.put("SaludTotal", new Contact("+5712345678","contacto@saludtotal.com"));
        fallback.put("Sura", new Contact("+5719876543","atencion@sura.com"));
        fallback.put("Coomeva", new Contact("+5715555555","soporte@coomeva.com"));
        return fallback.get(epsName);
    }

    private static Map<String, Contact> loadFromAssets(Context ctx) {
        Map<String, Contact> map = new HashMap<>();
        AssetManager am = ctx.getAssets();
        try (InputStream is = am.open("eps.json"); BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) sb.append(line);
            JSONObject root = new JSONObject(sb.toString());
            for (String key : JSONObject.getNames(root)) {
                JSONObject o = root.getJSONObject(key);
                String phone = o.optString("phone", null);
                String email = o.optString("email", null);
                map.put(key, new Contact(phone, email));
            }
        } catch (IOException | JSONException e) {
            // ignore and return empty map
        }
        return map;
    }
}
