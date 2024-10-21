package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "languages")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;  // e.g., "fra"
    private String name;  // e.g., "French"

    @ManyToMany(mappedBy = "languages")
    private Set<Country> countries = new HashSet<>();
}
