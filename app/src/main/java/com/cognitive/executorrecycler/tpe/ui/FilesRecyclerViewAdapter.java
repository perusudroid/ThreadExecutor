package com.cognitive.executorrecycler.tpe.ui;
import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cognitive.executorrecycler.tpe.MyThreadPoolExecutor;
import com.cognitive.executorrecycler.tpe.DownloadResultUpdateTask;
import com.cognitive.executorrecycler.tpe.DownloadTask;
import com.cognitive.executorrecycler.R;

import java.util.List;

public class FilesRecyclerViewAdapter extends
        RecyclerView.Adapter<FilesRecyclerViewAdapter.ViewHolder> {

    private List<String> fileList;
    private Context context;

    private String DOWNLOAD_DIR = Environment.getExternalStoragePublicDirectory
            (Environment.DIRECTORY_DOWNLOADS).getPath();

    public FilesRecyclerViewAdapter(List<String> list, Context ctx) {
        fileList = list;
        context = ctx;
    }
    @Override
    public int getItemCount() {
        return fileList.size();
    }

    @Override
    public FilesRecyclerViewAdapter.ViewHolder
    onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.file_item, parent, false);

        FilesRecyclerViewAdapter.ViewHolder viewHolder =
                new FilesRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final FilesRecyclerViewAdapter.ViewHolder holder, int position) {
        final int itemPos = position;
        final String fileName = fileList.get(position);
        holder.fileName.setText(fileName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadFile(fileName, holder.downloadStatus, holder.tvProgress);
            }
        });
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView fileName;
        public TextView downloadStatus,tvProgress;

        public ViewHolder(View view) {
            super(view);
            fileName = view.findViewById(R.id.file_name_i);
            tvProgress = view.findViewById(R.id.tvProgress);
            downloadStatus = view.findViewById(R.id.download_file_status);
        }

    }
    private String getFileName(String url){
        return url.substring(url.lastIndexOf("/")+1);
    }

    private void downloadFile(String url, TextView downloadStatus, TextView progress){
        String localFile = DOWNLOAD_DIR+"/"+getFileName(url);
        DownloadResultUpdateTask drUpdateTask = new DownloadResultUpdateTask(downloadStatus,progress);

        DownloadTask downloadTask = new DownloadTask(url, localFile, drUpdateTask);

        MyThreadPoolExecutor.getIntsance().getDownloadThreadPool().execute(downloadTask);

    }
}