package org.example.jwtproject.services;

import org.example.jwtproject.dto.PostDto;
import org.example.jwtproject.endpoint.Post;
import org.example.jwtproject.endpoint.User;
import org.example.jwtproject.exeption.ResourceNotFoundException;
import org.example.jwtproject.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepo;

    public Post createPost(PostDto dto, User user) {
        Post post = new Post();
        post.setTitle(dto.title());
        post.setContent(dto.content());
        post.setAuthor(user);
        return postRepo.save(post);
    }

    public Post updatePost(Long id, PostDto dto, User user) {
        Post post = postRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
        if (!post.getAuthor().getId().equals(user.getId()));
        post.setTitle(dto.title());
        post.setContent(dto.content());
        return postRepo.save(post);
    }

    public void deletePost(Long id, User user) {
        Post post = postRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
        if (!post.getAuthor().getId().equals(user.getId()));
        postRepo.delete(post);
    }

    public List<Post> getAllForUser(User user) {
        return postRepo.findByAuthor(user);
    }
}
