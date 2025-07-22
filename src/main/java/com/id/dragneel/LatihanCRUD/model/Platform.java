package com.id.dragneel.LatihanCRUD.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "platforms")
@Data
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
}
