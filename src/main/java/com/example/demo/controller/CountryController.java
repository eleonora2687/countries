package com.example.demo.controller;

import com.example.demo.model.Country;
import com.example.demo.service.CountryService;
import com.example.demo.service.FavoriteService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Pageable;


@Controller
public class CountryController {
    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private CountryService countryService;
    

    @GetMapping("/fetch-countries")
    public String fetchCountries() {
        countryService.fetchAndStoreCountries();
        return "fetch-countries";
    }


    @GetMapping("/all")
    public String getAllCountries(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int size) {
    // Adjust the `page` parameter to be 0-based for Spring's Pageable
    int totalCountries = countryService.getTotalCountries(); 
    
    Pageable pageable = PageRequest.of(page - 1, size);  // Subtract 1 from page to make it 0-based internally
    Page<Country> countriesPage = countryService.getAllCountries(pageable);

    List<Long> favoriteCountryIds = favoriteService.getAllFavorites().stream()
                                                    .map(favorite -> favorite.getCountry().getId())
                                                    .toList();

    // Model attributes
    model.addAttribute("countries", countriesPage.getContent());
    model.addAttribute("totalCountries", totalCountries);
    model.addAttribute("currentPage", page); // 1-based page number
    model.addAttribute("totalPages", countriesPage.getTotalPages());
    model.addAttribute("favoriteCountryIds", favoriteCountryIds); 

    return "countries";  
}



    @GetMapping("/welcome")
    public String welcome() {
        return "welcome"; 
    }


    
    
}



