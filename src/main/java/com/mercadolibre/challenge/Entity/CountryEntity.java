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
@Table(name = "country")
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

    @Setter
    @Getter
    @Entity
    @Table(name = "language")
    public static class LanguageEntity implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String code;
        private String name;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "country_id")
        private CountryEntity country;
    }

}
