package com.bfs.logindemo.domain;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "contact") // Maps to the database table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Integer contactId;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDateTime time;
}

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//public class Contact {
//    private int contactId;
//    private String subject;
//    private String message;
//    private String email;
//    private LocalDateTime time;
//}
