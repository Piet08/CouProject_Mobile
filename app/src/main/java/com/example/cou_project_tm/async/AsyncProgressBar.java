package com.example.cou_project_tm.async;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

public class AsyncProgressBar extends AsyncTask<Integer,Integer,Void> {

    private WeakReference<Callback> callback; // facilite la supression par le garbage collector

    public AsyncProgressBar(Callback callback){
        this.callback = new WeakReference<>(callback);
    }

    public interface Callback{
        void updateProgressBar(int currentValue);
        void onFinished();
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        int max = integers[0];

        for (int i = 0; i < max; i++) {
            publishProgress(i);//permet de notifier le thread principal
            //fait appel à onProgressUpdate
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null; //appel onPostExecute
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        callback.get().updateProgressBar(values[0]); //.get() à cause du WeakReference
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        callback.get().onFinished(); //définit à l'endroit ou en a besoin (ici MainActivity)
    }
}
