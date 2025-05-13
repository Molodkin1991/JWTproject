package org.example.jwtproject.controllers;

import org.example.jwtproject.dto.PostDto;
import org.example.jwtproject.endpoint.Post;
import org.example.jwtproject.endpoint.UserDetailsImpl;
import org.example.jwtproject.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody PostDto dto, @AuthenticationPrincipal UserDetailsImpl principal) {
        return ResponseEntity.ok(postService.createPost(dto, principal.getUser()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> update(@PathVariable Long id, @RequestBody PostDto dto, @AuthenticationPrincipal UserDetailsImpl principal) {
        return ResponseEntity.ok(postService.updatePost(id, dto, principal.getUser()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl principal) {
        postService.deletePost(id, principal.getUser());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAll(@AuthenticationPrincipal UserDetailsImpl principal) {
        return ResponseEntity.ok(postService.getAllForUser(principal.getUser()));
    }
}
