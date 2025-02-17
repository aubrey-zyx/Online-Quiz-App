package com.bfs.logindemo.domain;

import lombok.*;

import javax.persistence.*;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//public class User {
//    private int id;
//    private String email;
//    private String password;
//    private String firstName;
//    private String lastName;
//    private boolean isActive;
//    private boolean isAdmin;
//}

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "is_admin")
    private boolean isAdmin;
}
