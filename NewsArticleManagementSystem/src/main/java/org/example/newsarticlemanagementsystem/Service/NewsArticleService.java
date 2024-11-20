package org.example.newsarticlemanagementsystem.Service;

import org.example.newsarticlemanagementsystem.Model.NewsArticle;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class NewsArticleService {

    ArrayList<NewsArticle> newsArticles = new ArrayList<>();

    public ArrayList<NewsArticle> getNewsArticles(){
        return newsArticles;
    }

    public void addNewsArticle(NewsArticle newsArticle){
        newsArticles.add(newsArticle);
    }

    public boolean updateNewsArticle(String id, NewsArticle newsArticle){
        for (int i=0;i<newsArticles.size();i++){
            if (newsArticles.get(i).getId().equals(id)){
                newsArticles.set(i,newsArticle);
                return true;
            }
        }
        return false;
    }

    public boolean deleteNewsArticle(String id){
        for (NewsArticle n: newsArticles){
            if (n.getId().equals(id)){
                newsArticles.remove(n);
                return true;
            }
        }
        return false;
    }

    public boolean publishNewsArticle(String id){
        for (NewsArticle newsArticle : newsArticles) {
            if (newsArticle.getId().equals(id)) {
                newsArticle.setPublished(true);
                newsArticle.setPublishDate(LocalDate.now());
                return true;
            }
        }
        return false;
    }

    public ArrayList<NewsArticle> getPublishedArticles(){
        ArrayList<NewsArticle> published = new ArrayList<>();

        for(NewsArticle n : newsArticles){
            if (n.isPublished()){
                published.add(n);
            }
        }

        return published;
    }

    public ArrayList<NewsArticle> searchByCategory(String category){
        ArrayList<NewsArticle> articlesByCategory =new ArrayList<>();

        for (NewsArticle n : newsArticles){
            if (n.getCategory().equalsIgnoreCase(category)){
                articlesByCategory.add(n);
            }
        }

        return articlesByCategory;
    }
}
