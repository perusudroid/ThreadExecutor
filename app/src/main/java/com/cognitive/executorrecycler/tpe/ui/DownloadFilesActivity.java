package com.cognitive.executorrecycler.tpe.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cognitive.executorrecycler.R;

import java.util.ArrayList;
import java.util.List;

public class DownloadFilesActivity extends AppCompatActivity {
    private static final String TAG = "DownloadFilesActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_files);

        RecyclerView fileRv = findViewById(R.id.files_rv);

        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        fileRv.setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                fileRv.getContext(),recyclerLayoutManager.getOrientation());
        fileRv.addItemDecoration(dividerItemDecoration);

        FilesRecyclerViewAdapter recyclerViewAdapter = new FilesRecyclerViewAdapter(
                getFilesList(), this);
        fileRv.setAdapter(recyclerViewAdapter);

        //storage write permission needed to save downloaded file on device
        writeStoragePermission();
    }
    private void writeStoragePermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);

        }
    }
    private List<String> getFilesList(){
        List<String> files = new ArrayList<String>();
        files.add("https://www2.isye.gatech.edu/~jjb/wh/book/editions/wh-sci-0.96.pdf");
        files.add("http://dummyyyy.com/storeOffers");
        files.add("http://dummyyyy.com/topCoupon");
        return files;
    }
}