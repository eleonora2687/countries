package com.example.demo.service;

import com.example.demo.dto.CountryDTO;
import com.example.demo.model.Capital;
import com.example.demo.model.Country;
import com.example.demo.model.Language;
import com.example.demo.repository.CapitalRepository;
import com.example.demo.repository.CountryRepository;
import com.example.demo.repository.LanguageRepository; // Added import

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class CountryService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CapitalRepository capitalRepository;

    @Autowired
    private LanguageRepository languageRepository; // Added LanguageRepository

    private static final String API_URL = "https://restcountries.com/v3.1/all";

    public void fetchAndStoreCountries() {
        CountryDTO[] countries = restTemplate.getForObject(API_URL, CountryDTO[].class);
    
        if (countries != null) {
            for (CountryDTO countryDTO : countries) {
                Country country = mapToEntity(countryDTO);
    
                // Create a Set to store languages for the current country
                Set<Language> languageSet = new HashSet<>();
    
                // Get languages from the countryDTO
                Map<String, String> languages = countryDTO.getLanguages();
                if (languages != null) {
                    for (Map.Entry<String, String> entry : languages.entrySet()) {
                        String langCode = entry.getKey();
                        String langName = entry.getValue();
    
                        // Check if language already exists
                        Language language = languageRepository.findByCode(langCode);
                        if (language == null) {
                            // Create new language if it doesn't exist
                            language = new Language();
                            language.setCode(langCode);
                            language.setName(langName);
                            languageRepository.save(language);
                        }
    
                        // Associate language with the country
                        languageSet.add(language);
                    }
                }
    
                // Set the languages for the country
                country.setLanguages(languageSet); // Ensure this works without exceptions
                countryRepository.save(country); // Save country after setting languages
    
                // Save capitals as before
                if (countryDTO.getCapital() != null) {
                    for (String capitalName : countryDTO.getCapital()) {
                        Capital capital = new Capital(capitalName, country);
                        capitalRepository.save(capital);
                    }
                }
            }
        }
    }
    
    
    private Country mapToEntity(CountryDTO countryDTO) {
        Country country = new Country();
        Country.Name name = new Country.Name(countryDTO.getName().getCommon(), countryDTO.getName().getOfficial());
        Country.Flags flags = new Country.Flags(countryDTO.getFlags().getPng(), countryDTO.getFlags().getSvg());
    
        country.setName(name);
        country.setRegion(countryDTO.getRegion());
        country.setArea(countryDTO.getArea());
        country.setPopulation(countryDTO.getPopulation());
        country.setFlags(flags);
    
        return country;
    }

    public Page<Country> getAllCountries(Pageable pageable) {
        return countryRepository.findAll(pageable);
    }

    public Optional<Country> getCountryById(Long id) {
        return countryRepository.findById(id); 
    }

    public int getTotalCountries() {
        return (int) countryRepository.count(); 
    }

    
    
}
