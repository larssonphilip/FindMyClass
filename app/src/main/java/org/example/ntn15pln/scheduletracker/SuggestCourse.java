package org.example.ntn15pln.scheduletracker;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

public class SuggestCourse implements Runnable{
    private static final String TAG = "SuggestCourse";
    private final MainActivity suggest;
    private final String searchText;

    SuggestCourse(MainActivity context, String searchText) {
        this.suggest = context;
        this.searchText = searchText;
    }

    @Override
    public void run() {
        List<String> suggestions = doSuggest(searchText);
        suggest.setSuggestions(suggestions);
    }

    private List<String> doSuggest(String original) {
        List<String> messages = new LinkedList<String>();
        String error = null;
        HttpURLConnection con = null;
        Log.d(TAG, "doSuggest(" + original + ")");

        try {
            if (Thread.interrupted())
                throw new InterruptedException();

            String q = URLEncoder.encode(original, "UTF-8");
            URL url = new URL(
                    "http://google.com/complete/search?output=toolbar&q="
                            + q);
            con = (HttpURLConnection) url.openConnection();
            con.setReadTimeout(10000);
            con.setConnectTimeout(15000);
            con.setRequestMethod("GET");
            con.addRequestProperty("Referer",
                    "http://www.pragprog.com/book/eband4");
            con.setDoInput(true);

            con.connect();

            if (Thread.interrupted())
                throw new InterruptedException();

            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(con.getInputStream(), null);
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String name = parser.getName();
                if (eventType == XmlPullParser.START_TAG
                        && name.equalsIgnoreCase("suggestion")) {
                    for (int i = 0; i < parser.getAttributeCount(); i++) {
                        if (parser.getAttributeName(i).equalsIgnoreCase(
                                "data")) {
                            messages.add(parser.getAttributeValue(i));
                        }
                    }
                }
                eventType = parser.next();
            }

            if (Thread.interrupted())
                throw new InterruptedException();

        } catch (IOException e) {
            Log.e(TAG, "IOException", e);
            error = suggest.getResources().getString(R.string.error_text)
                    + " " + e.toString();
        } catch (XmlPullParserException e) {
            Log.e(TAG, "XmlPullParserException", e);
            error = suggest.getResources().getString(R.string.error_text)
                    + " " + e.toString();
        } catch (InterruptedException e) {
            Log.d(TAG, "InterruptedException", e);
            error = suggest.getResources().getString(
                    R.string.interrupted_text);
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }

        if (error != null) {
            messages.clear();
            messages.add(error);
        }

        if (messages.size() == 0) {
            messages.add(suggest.getResources().getString(
                    R.string.no_results));
        }

        Log.d(TAG, "   -> returned " + messages);
        return messages;
    }
}
