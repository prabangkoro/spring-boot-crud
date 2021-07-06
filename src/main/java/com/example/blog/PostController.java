package com.example.blog;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * PostController
 */
@RestController
public class PostController {

  private List<Post> posts = Stream.of(new Post("1", "Title one", "Content one"),
      new Post("2", "Title two", "Content two"),
      new Post("3", "Title three", "Content three"),
      new Post("4", "Title four", "Content four")).collect(Collectors.toList());

  @GetMapping("/ping")
  public String getPing() {
    return "ping";
  }

  @GetMapping("/posts/{id}")
  public Post getPost(@PathVariable String id) {
    return posts.stream().filter(post -> post.getId().equals(id)).findFirst().orElse(null);
  }

  @GetMapping("/posts")
  public List<Post> getPosts() {
    return posts;
  }

  @PostMapping("/posts")
  public Object addPost(@RequestBody Post post) {
    post.setId(UUID.randomUUID().toString());
    posts.add(post);

    return new Object() {
      public final boolean success = true;
      public final Post response = post;
    };
  }

  @PutMapping("/posts/{id}")
  public Object editPost(@PathVariable String id, @RequestBody Post editPost) {
    Post post = posts.stream().filter(i -> i.getId().equals(id)).findFirst().orElse(null);
    post.setContent(editPost.getContent());
    post.setTitle(editPost.getTitle());

    return new Object() {
      public final boolean success = true;
      public final Post response = post;
    };
  }

  @DeleteMapping("/posts/{id}")
  public boolean deletePost(@PathVariable String id) {
    posts = posts.stream().filter(i -> !i.getId().equals(id)).collect(Collectors.toList());

    return true;
  }
}