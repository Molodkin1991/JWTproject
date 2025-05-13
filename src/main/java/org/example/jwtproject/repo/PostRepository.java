package org.example.jwtproject.repo;

import org.example.jwtproject.endpoint.Post;
import org.example.jwtproject.endpoint.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(User author);
}
