package org.example.ntn15pln.scheduletracker;

import android.app.DownloadManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

public class MainActivity extends AppCompatActivity {
    //TEMP BUTTON
    private ImageView image;
    private Button cButton;
    private ListView suggestionsList;
    private EditText searchText;
    private Handler guiThread;
    private ExecutorService suggestionThread;
    private Runnable updateTask;
    private Future<?> suggestionPending;
    private List<String> items;
    private ArrayAdapter<String> adapter;
    private KronoxParser cdlh = new KronoxParser();
    private DownloadManager downloadManager;

    //schema.hig.se/setup/jsp/SchemaICAL.ics?startDatum=idag&intervallTyp=m&intervallAntal=6&sprak=SV&sokMedAND=true&forklaringar=true&resurser=p.TGDAY.19104.16
    //http://schema.hig.se/setup/jsp/Schema.jsp?startDatum=idag&intervallTyp=m&intervallAntal=6&sprak=SV&sokMedAND=true&forklaringar=true&resurser=p.TGDAY.19104.16
    private String startDate = "idag";
    private String programCode = "TGDAY.19104.16";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initThreading();
        findViews();
        setListeners();
        setAdapters();

    }

    public String generateURL() {
        String scheduleURL = "http://schema.hig.se/setup/jsp/SchemaICAL.ics?startDatum=";
        scheduleURL += startDate + "&intervallTyp=m&intervallAntal=6&sprak=SV&sokMedAND=true&forklaringar=true&resurser=p." + programCode;

        return scheduleURL;
    }

    public void downloadSchedule() {

        //Fixa så att man frågas efter permission att lagra fil.
        Uri calURI = Uri.parse(generateURL());
        /*File myDir = new File(Environment.getExternalStorageDirectory() + "/Download/temp/");
        myDir.mkdir();*/

        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(path + "SC1444.ics");
        boolean deleted = file.delete();
        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(calURI);
        request.setTitle("SC1444");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
        request.setDestinationInExternalPublicDir(path, "SC1444.ics");
        downloadManager.enqueue(request);
    }

    @Override
    protected void onDestroy() {
        suggestionThread.shutdownNow();
        super.onDestroy();
    }

    private void findViews() {
        image = findViewById(R.id.header);
        searchText = findViewById(R.id.search);
        suggestionsList = findViewById(R.id.suggestion_list);
    }

    private void setListeners() {

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoadingScreen.class);
                downloadSchedule();
                startActivity(intent);

            }
        });
        TextWatcher textWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                queueUpdate(1000);
            }
            public void afterTextChanged(Editable s) {

            }
        };

        searchText.addTextChangedListener(textWatcher);

        AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String query = (String) parent.getItemAtPosition(position);
                doSearch(query);
            }
        };

        suggestionsList.setOnItemClickListener(clickListener);
    }

    private void doSearch(String query) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, query);
        startActivity(intent);
    }

    private void setAdapters() {
        items = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        suggestionsList.setAdapter(adapter);
    }

    private void initThreading() {
        guiThread = new Handler();
        suggestionThread = Executors.newSingleThreadExecutor();

        updateTask = new Runnable() {
            public void run() {
                String original = searchText.getText().toString().trim();

                if (suggestionPending != null)
                    suggestionPending.cancel(true);

                if (original.length() != 0) {
                    setText(R.string.loading_text);

                    try {
                        SuggestCourse suggestTask = new SuggestCourse(MainActivity.this, original);
                        suggestionPending = suggestionThread.submit(suggestTask);
                    } catch (RejectedExecutionException e) {
                        setText(R.string.error_text);
                    }
                }
            }
        };
    }

    private void queueUpdate(long delayMillis) {
        guiThread.removeCallbacks(updateTask);
        guiThread.postDelayed(updateTask, delayMillis);
    }

    public void setSuggestions(List<String> suggestions) {
        guiSetList(suggestionsList, suggestions);
    }

    private void guiSetList(final ListView view,
                            final List<String> list) {
        guiThread.post(new Runnable() {
            public void run() {
                setList(list);
            }

        });
    }

    private void setText(int id) {
        adapter.clear();
        adapter.add(getResources().getString(id));
    }

    private void setList(List<String> list) {
        adapter.clear();
        adapter.addAll(list);
    }
}
