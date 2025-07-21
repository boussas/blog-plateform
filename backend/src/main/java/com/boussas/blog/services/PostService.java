package com.boussas.blog.services;

import com.boussas.blog.entities.dtos.CreatePostRequest;
import com.boussas.blog.entities.dtos.UpdatePostRequest;
import com.boussas.blog.entities.Post;
import com.boussas.blog.entities.User;

import java.util.List;
import java.util.UUID;

public interface PostService {
    Post getPost(UUID id);
    List<Post> getAllPosts(UUID categoryId, UUID tagId);
    List<Post> getDraftPosts(User user);
    Post createPost(User user, CreatePostRequest createPostRequest);
    Post updatePost(UUID id, UpdatePostRequest updatePostRequest);
    void deletePost(UUID id);
}
