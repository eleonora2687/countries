package com.example.demo.model;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<Capital> capitals; 

    private String region;
    private Integer area;
    private Integer population;

    @Embedded
    private Name name;

    @Embedded
    private Flags flags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public List<Capital> getCapitals() {
        return capitals;
    }

    public void setCapitals(List<Capital> capitals) {
        this.capitals = capitals;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Flags getFlags() {
        return flags;
    }

    public void setFlags(Flags flags) {
        this.flags = flags;
    }

    public Country() {
    }

    @Embeddable
    public static class Name {
        private String common;
        private String official;

        public Name() {
        }

        public Name(String common, String official) {
            this.common = common;
            this.official = official;
        }

        public String getCommon() {
            return common;
        }

        public void setCommon(String common) {
            this.common = common;
        }

        public String getOfficial() {
            return official;
        }

        public void setOfficial(String official) {
            this.official = official;
        }
    }

    @Embeddable
    public static class Flags {
        private String png;
        private String svg;

        public Flags() {
        }

        public Flags(String png, String svg) {
            this.png = png;
            this.svg = svg;
        }

        public String getPng() {
            return png;
        }

        public void setPng(String png) {
            this.png = png;
        }

        public String getSvg() {
            return svg;
        }

        public void setSvg(String svg) {
            this.svg = svg;
        }
    }

    public Country(Long id, List<Capital> capitals, String region, Integer area, Integer population, Name name,
            Flags flags) {
        this.id = id;
        this.capitals = capitals;
        this.region = region;
        this.area = area;
        this.population = population;
        this.name = name;
        this.flags = flags;
        
    }
    
}
