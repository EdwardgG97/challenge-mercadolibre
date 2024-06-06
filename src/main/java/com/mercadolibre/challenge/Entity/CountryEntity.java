package com.mercadolibre.challenge.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "country", indexes = @Index(name = "idx_distance", columnList = "distance"))
public class CountryEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ip;

    private LocalDateTime create;

    private Double distance;

    private String countryCode;

    private String countryName;

    private Double latitude;

    private Double longitude;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LanguageEntity> languagesEntity;

}
