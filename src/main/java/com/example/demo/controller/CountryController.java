package com.example.demo.controller;

import com.example.demo.model.Country;
import com.example.demo.service.CountryService;
import com.example.demo.service.FavoriteService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    int totalCountries = countryService.getTotalCountries(); 
    
    Pageable pageable = PageRequest.of(page - 1, size);  
    Page<Country> countriesPage = countryService.getAllCountries(pageable);

    List<Long> favoriteCountryIds = favoriteService.getAllFavorites().stream()
                                                    .map(favorite -> favorite.getCountry().getId())
                                                    .toList();

    model.addAttribute("countries", countriesPage.getContent());
    model.addAttribute("totalCountries", totalCountries);
    model.addAttribute("currentPage", page); 
    model.addAttribute("totalPages", countriesPage.getTotalPages());
    model.addAttribute("favoriteCountryIds", favoriteCountryIds); 

    return "countries";  
}



    @GetMapping("/welcome")
        public String welcome() {
            return "welcome"; 
    }


    @GetMapping("/single/{id}")
    public String getCountryById(@PathVariable("id") Long id, Model model) {
        Optional<Country> countryOpt = countryService.getCountryById(id);
        if (countryOpt.isPresent()) {
            model.addAttribute("country", countryOpt.get());
            return "single-country"; // the view that displays the single country
        } else {
            return "redirect:/all"; 
        }
    }

    
}

    
    




