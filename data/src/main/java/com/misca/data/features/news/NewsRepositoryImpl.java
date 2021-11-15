package com.misca.data.features.news;

import com.misca.data.NewsRepository;
import com.misca.data.features.news.model.Article;
import com.misca.data.features.news.remote.NewsRemoteSource;
import com.misca.data.features.news.remote.mapper.NewsDtoToNewsMapper;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

/**
 * Created by mihai.mecea on 03.May.2020
 */
public class NewsRepositoryImpl implements NewsRepository {

    private final NewsRemoteSource remoteSource;

    public NewsRepositoryImpl(NewsRemoteSource remoteSource) {
        this.remoteSource = remoteSource;
    }

    @Override
    @NonNull
    public Single<List<Article>> getNewsArticles() {
        return remoteSource.getNewsArticles();
    }

}
