package com.example.blog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Post
 */
@Data
@AllArgsConstructor
@JsonIgnoreProperties
public class Post {

  private String id;
  private String title;
  private String content;
}