package com.concretepage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.concretepage.entity.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    List<Article> findByTitle(String title);

    List<Article> findDistinctByCategory(String category);

    List<Article> findByTitleAndCategory(String title, String category);

    // A custom Query for the DB
    // (Original Query) @Query("SELECT a FROM Article a WHERE a.title=:title and a.category=:category")
    // (Original method) List<Article> fetchArticles(@Param("title") String title, @Param("category") String category);
    // My Query:
    @Query("SELECT a FROM Article a WHERE a.title LIKE LOWER(CONCAT('%',:searchTerm, '%'))")
    List<Article> findBySearchTerm(@Param("searchTerm") String searchTerm);
}
