package com.concretepage.service;

import java.util.List;

import com.concretepage.entity.Article;

public interface IArticleService {
    List<Article> getAllArticles();

    List<Article> getAllArticlesBySearchTerm(String searchTerm);

    Article getArticleById(long articleId);

    boolean addArticle(Article article);

    void updateArticle(Article article);

    void deleteArticle(int articleId);
}
