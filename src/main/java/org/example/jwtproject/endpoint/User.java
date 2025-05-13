package org.example.jwtproject.endpoint;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "my_users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Role role;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

}
