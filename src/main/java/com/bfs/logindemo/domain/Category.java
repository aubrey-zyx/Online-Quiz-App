package com.bfs.logindemo.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "category")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(nullable = false, unique = true)
    private String name;
}

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//public class Category {
//    private int categoryId;
//    private String name;
//}
