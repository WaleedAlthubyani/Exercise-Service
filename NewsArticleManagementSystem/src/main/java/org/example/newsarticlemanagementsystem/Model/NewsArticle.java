package org.example.newsarticlemanagementsystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NewsArticle {

    @NotEmpty(message = "Please provide an id")
    private String id;

    @NotEmpty(message = "Please provide a title")
    @Size(max = 100,message = "The title is too long. 100 characters is the maximum")
    private String title;

    @NotEmpty(message = "Please provide the author's name")
    @Size(min = 5,max = 20,message = "Author name's length should be between 5 and 20")
    private String author;

    @NotEmpty(message = "Please write the content of this article")
    @Size(min = 201,message = "Content can't be less than 200 characters long")
    private String content;

    @NotEmpty(message = "Please provide a category")
    @Pattern(regexp = "(politics|sports|technology)",message = "category can only be politics, sports or technology")
    private String category;

    @NotEmpty(message = "Please write the URL of your image")
    @URL(message = "Please enter a valid URL")
    private String imgURL;

    @AssertFalse(message = "A new article can't be already published")
    private boolean isPublished;

    @NotNull(message = "Please provide the publishing date")
    private LocalDate publishDate;
}
