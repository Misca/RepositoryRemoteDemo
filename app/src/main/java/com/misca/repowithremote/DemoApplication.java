package com.misca.repowithremote;

import android.app.Application;

import com.misca.data.di.RepoModule;

public class DemoApplication extends Application {

    //move along, will be replaced with Dagger later
    private static RepoModule repoModule;

    @Override
    public void onCreate() {
        super.onCreate();
        repoModule = new RepoModule(this);
    }

    public static RepoModule getRepoProvider() {
        return repoModule;
    }
}
