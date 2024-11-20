package org.example.newsarticlemanagementsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.newsarticlemanagementsystem.ApiResponse.ApiResponse;
import org.example.newsarticlemanagementsystem.Model.NewsArticle;
import org.example.newsarticlemanagementsystem.Service.NewsArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/news-article-management-system")
@RequiredArgsConstructor
public class NewsArticleController {

    private final NewsArticleService newsArticleService;

    @GetMapping("/get")
    public ResponseEntity getNewsArticles(){
        ArrayList<NewsArticle> newsArticles = newsArticleService.getNewsArticles();
        return ResponseEntity.status(200).body(newsArticles);
    }

    @PostMapping("/add")
    public ResponseEntity addNewsArticle(@RequestBody @Valid NewsArticle newsArticle, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        newsArticleService.addNewsArticle(newsArticle);
        return ResponseEntity.status(200).body(new ApiResponse("News article added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateNewsArticle(@PathVariable String id, @RequestBody @Valid NewsArticle newsArticle,Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        boolean isUpdated= newsArticleService.updateNewsArticle(id,newsArticle);

        if (isUpdated)
            return ResponseEntity.status(200).body(new ApiResponse("News article updated successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("ID not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteNewsArticle(@PathVariable String id){
        boolean isDeleted= newsArticleService.deleteNewsArticle(id);

        if (isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse("News article deleted successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("ID not found"));
    }

    @PutMapping("/publish/{id}")
    public ResponseEntity publishNewsArticle(@PathVariable String id){
        boolean isPublished = newsArticleService.publishNewsArticle(id);

        if (isPublished)
            return ResponseEntity.status(200).body(new ApiResponse("News article published successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("ID not found"));
    }

    @GetMapping("/get-all-published")
    public ResponseEntity getPublishedArticles(){
        ArrayList<NewsArticle> published = newsArticleService.getPublishedArticles();
        return ResponseEntity.status(200).body(published);
    }

    @GetMapping("search-by-category/{category}")
    public ResponseEntity searchByCategory(@PathVariable String category){
        if (!category.matches("(?i)(politics|sports|technology)")){
            return ResponseEntity.status(400).body(new ApiResponse("category can only be politics, sports or technology"));
        }

        ArrayList<NewsArticle> articlesByCategory = newsArticleService.searchByCategory(category);

        return ResponseEntity.status(200).body(articlesByCategory);
    }

}
