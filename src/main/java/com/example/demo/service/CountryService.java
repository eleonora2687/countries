package com.example.demo.service;

import com.example.demo.dto.CountryDTO;
import com.example.demo.model.Capital;
import com.example.demo.model.Country;
import com.example.demo.repository.CapitalRepository;
import com.example.demo.repository.CountryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CapitalRepository capitalRepository;

    private static final String API_URL = "https://restcountries.com/v3.1/all";

    

    public void fetchAndStoreCountries() {
        CountryDTO[] countries = restTemplate.getForObject(API_URL, CountryDTO[].class);

        if (countries != null) {
            for (CountryDTO countryDTO : countries) {
                Country country = mapToEntity(countryDTO);

                countryRepository.save(country);

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
