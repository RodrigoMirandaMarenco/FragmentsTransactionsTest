package com.rodrigomirandamarenco.fragmentstransactionstest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * Created by rodrigomiranda on 1/11/18.
 */

public class TestDialogFragment extends DialogFragment implements LoaderManager.LoaderCallbacks<String> {

    private static final int TEST_LOADER_ID = 0;

    private Button mStartLoaderButton;
    private ProgressBar mProgressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_test, container, false);

        mStartLoaderButton = view.findViewById(R.id.start_loader_button);
        mProgressBar = view.findViewById(R.id.progressBar);

        mStartLoaderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                mStartLoaderButton.setEnabled(false);
                getLoaderManager().restartLoader(TEST_LOADER_ID, null, TestDialogFragment.this);
            }
        });

        return view;
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new TestAsyncTask(getContext());
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        mProgressBar.setVisibility(View.INVISIBLE);
        mStartLoaderButton.setEnabled(true);
        //TODO: Fix this crash
        dismiss();
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    static class TestAsyncTask extends AsyncTaskLoader<String> {

        TestAsyncTask(Context context) {
            super(context);
        }

        @Override
        protected void onStartLoading() {
            super.onStartLoading();
            forceLoad();
        }

        @Override
        public String loadInBackground() {
            try {
                //Simulate a background task
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

}
