package com.example.demo.dto;

import java.util.List;
import java.util.Map;

public class CountryDTO {

    private NameDTO name;
    private List<String> capital;
    private String region;
    private Integer area;
    private Integer population;
    private FlagsDTO flags;
    private Map<String, String> languages; // Added languages field

    public NameDTO getName() {
        return this.name;
    }

    public void setName(NameDTO name) {
        this.name = name;
    }

    public List<String> getCapital() {
        return this.capital;
    }

    public void setCapital(List<String> capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getArea() {
        return this.area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getPopulation() {
        return this.population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public FlagsDTO getFlags() {
        return this.flags;
    }

    public void setFlags(FlagsDTO flags) {
        this.flags = flags;
    }

    public Map<String, String> getLanguages() { // Added getter for languages
        return languages;
    }

    public void setLanguages(Map<String, String> languages) { // Added setter for languages
        this.languages = languages;
    }

    public static class NameDTO {
        private String common;
        private String official;

        public String getCommon() {
            return this.common;
        }

        public void setCommon(String common) {
            this.common = common;
        }

        public String getOfficial() {
            return this.official;
        }

        public void setOfficial(String official) {
            this.official = official;
        }

        public NameDTO() {
        }

        public NameDTO(String common, String official) {
            this.common = common;
            this.official = official;
        }
    }

    public static class FlagsDTO {
        private String png;
        private String svg;

        public String getPng() {
            return this.png;
        }

        public void setPng(String png) {
            this.png = png;
        }

        public String getSvg() {
            return this.svg;
        }

        public void setSvg(String svg) {
            this.svg = svg;
        }

        public FlagsDTO() {
        }

        public FlagsDTO(String png, String svg) {
            this.png = png;
            this.svg = svg;
        }
    }
}
