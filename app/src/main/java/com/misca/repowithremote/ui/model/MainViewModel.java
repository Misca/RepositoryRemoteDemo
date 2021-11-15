package com.misca.repowithremote.ui.model;

import android.annotation.SuppressLint;
import android.app.Application;

import com.misca.data.NewsRepository;
import com.misca.data.features.news.model.Article;
import com.misca.repowithremote.R;
import com.misca.repowithremote.ui.reactive.SingleLiveEvent;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainViewModel extends AndroidViewModel {

    private final static String LINK = "https://newsapi.org/";
    private final NewsRepository repo;

    public final ObservableBoolean isLoading;
    public final ObservableField<String> resultText;
    public final SingleLiveEvent<Throwable> error;
    public final SingleLiveEvent<String> openLink;

    public MainViewModel(Application application, NewsRepository repo) {
        super(application);
        this.repo = repo;
        this.isLoading = new ObservableBoolean();
        this.resultText = new ObservableField<>();
        this.error = new SingleLiveEvent<>();
        this.openLink = new SingleLiveEvent<>();
    }

    @SuppressLint("CheckResult")
    public void refreshData() {
        isLoading.set(true);
        repo.getNewsArticles()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                    this::onNewsArticlesReceived,
                    this::onNewsArticlesError
            );
    }

    private void onNewsArticlesReceived(@NonNull List<Article> articles) {
        isLoading.set(false);
        resultText.set(getApplication().getString(R.string.results, articles.size()));
    }

    private void onNewsArticlesError(Throwable throwable) {
        isLoading.set(false);
        error.setValue(throwable);
    }

    public void onPoweredBySelected() {
        openLink.setValue(LINK);
    }
}