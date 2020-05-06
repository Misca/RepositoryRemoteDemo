package com.misca.repowithremote.ui.model.factory;

import android.app.Application;

import com.misca.data.NewsRepository;
import com.misca.repowithremote.DemoApplication;
import com.misca.repowithremote.ui.model.MainViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final Application application;

    public ViewModelFactory(Application application) {
        this.application = application;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {

        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            NewsRepository repo = DemoApplication.getRepoProvider().provideNewsRepository();
            return (T) new MainViewModel(application, repo);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}