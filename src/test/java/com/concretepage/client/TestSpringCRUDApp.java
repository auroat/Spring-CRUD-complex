package com.concretepage.client;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.concretepage.entity.Article;

/**
 * I changed the original class from the tutorial, by simply adding the @Test annotation across the methods, in order to
 * NOT need a main() method. The main() method is kept only as a past reference.
 * 
 * @author apintea
 */

@Testable
public class TestSpringCRUDApp {

    @Test
    public void getArticleByIdDemo() {
	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);
	RestTemplate restTemplate = new RestTemplate();
	String url = "http://localhost:8080/user/article/{id}";
	HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
	ResponseEntity<Article> responseEntity = restTemplate
		.exchange(url, HttpMethod.GET, requestEntity, Article.class, 10);
	Article article = responseEntity.getBody();
	System.out
		.println("Id:" + article.getArticleId() + ", Title:" + article.getTitle() + ", Category:"
			+ article.getCategory());
    }

    @Test
    public void getAllArticlesDemo() {
	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);
	RestTemplate restTemplate = new RestTemplate();
	String url = "http://localhost:8080/user/articles";
	HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
	ResponseEntity<Article[]> responseEntity = restTemplate
		.exchange(url, HttpMethod.GET, requestEntity, Article[].class);
	Article[] articles = responseEntity.getBody();
	for (Article article : articles) {
	    System.out
		    .println("Id:" + article.getArticleId() + ", Title:" + article.getTitle() + ", Category: "
			    + article.getCategory());
	}
    }

    /**
     * This test is special, as it uses a customized Query from ArticleRepository.
     * Note: I used the POST method, because the "RequestBody" from the
     * ArticleController implies that its controller method is POST
     * 
     */
    @Test
    public void getAllArticlesBySearchTermDemo() {
	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);
	RestTemplate restTemplate = new RestTemplate();
	String url = "http://localhost:8080/user/getAllArticlesBySearchTerm";
	String searchTerm = "Java";

	HttpEntity<String> requestEntity = new HttpEntity<String>(searchTerm, headers);
	ResponseEntity<Article[]> responseEntity = restTemplate
		.exchange(url, HttpMethod.POST, requestEntity, Article[].class, searchTerm);
	Article[] articles = responseEntity.getBody();
	for (Article article : articles) {
	    System.out
		    .println("Id:" + article.getArticleId() + ", Title:" + article.getTitle() + ", Category: "
			    + article.getCategory());
	}
    }

    @Test
    public void addArticleDemo() {
	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);
	RestTemplate restTemplate = new RestTemplate();
	String url = "http://localhost:8080/user/article";
	Article objArticle = new Article();
	objArticle.setTitle("Spring REST Security using Hibernate and USA, and Uanada");
	objArticle.setCategory("Spring");
	HttpEntity<Article> requestEntity = new HttpEntity<Article>(objArticle, headers);
	URI uri = restTemplate.postForLocation(url, requestEntity);
	System.out.println(uri.getPath());
    }

    @Test
    public void updateArticleDemo() {
	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);
	RestTemplate restTemplate = new RestTemplate();
	String url = "http://localhost:8080/user/article";

	Article objArticle = new Article();
	objArticle.setArticleId(1);
	objArticle.setTitle("Update:Java Concurrency");
	objArticle.setCategory("Java");

	HttpEntity<Article> requestEntity = new HttpEntity<Article>(objArticle, headers);
	restTemplate.put(url, requestEntity);
    }

    @Test
    public void deleteArticleDemo() {
	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);
	RestTemplate restTemplate = new RestTemplate();
	String url = "http://localhost:8080/user/article/{id}";
	HttpEntity<Article> requestEntity = new HttpEntity<Article>(headers);
	restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 11);
    }

    public static void main(String args[]) {
	TestSpringCRUDApp testSpringCRUDApp = new TestSpringCRUDApp();
	// testSpringCRUDApp.getArticleByIdDemo();
	// testSpringCRUDApp.addArticleDemo();
	// testSpringCRUDApp.updateArticleDemo();
	// testSpringCRUDApp.deleteArticleDemo();
	testSpringCRUDApp.getAllArticlesDemo();
    }
}
