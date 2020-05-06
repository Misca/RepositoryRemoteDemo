package com.misca.repowithremote.ui.navigator;

import android.content.Context;

import com.misca.repowithremote.R;

import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import io.reactivex.annotations.NonNull;

/**
 * Created by mihai.mecea on 05.May.2020
 */
public class AlertNavigator {

    private final FragmentManager fragmentManager;
    private final Context context;

    public AlertNavigator(@NonNull FragmentManager fragmentManager, @NonNull Context context) {
        this.fragmentManager = fragmentManager;
        this.context = context;
    }

    public void showErrorFor(@NonNull Throwable throwable) {
        if (throwable instanceof SocketTimeoutException || throwable instanceof UnknownHostException || throwable instanceof UnsupportedEncodingException) {
            showAlert(context.getString(R.string.network_error, throwable.getMessage()));
        }
    }

    public void showAlert(@NonNull String message) {
        new AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.error_title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, (dialogInterface, i) -> {

                })
                .show();
    }
}
