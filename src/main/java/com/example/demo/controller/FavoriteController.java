package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import com.example.demo.model.Country;
import com.example.demo.model.Favorite;
import com.example.demo.service.FavoriteService;
import com.example.demo.service.CountryService;

@Controller
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private CountryService countryService;

    @PostMapping("/add")
    public String addFavorite(@RequestParam("countryId") Long countryId, Model model) {
        Optional<Country> countryOptional = countryService.getCountryById(countryId);

        if (countryOptional.isPresent()) {
            Country country = countryOptional.get();
            favoriteService.addFavorite(country);
            model.addAttribute("message", "Country added to favorites!");
        } else {
            model.addAttribute("error", "Country not found!");
        }
        return "redirect:/faves"; 
    }

    @GetMapping("/faves")
        public String getAllFavorites(Model model) {
        List<Favorite> favorites = favoriteService.getAllFavorites(); 
        model.addAttribute("favorites", favorites);
        
        System.out.println("Favorites: " + favorites);
        
        return "favorites"; 
    }


    @PostMapping("/toggleFavorite")
        public String toggleFavorite(@RequestParam("countryId") Long countryId, Model model) {
        Optional<Country> countryOptional = countryService.getCountryById(countryId);

        if (countryOptional.isPresent()) {
            Country country = countryOptional.get();
            
            if (favoriteService.contains(countryId)) {
                favoriteService.removeFavorite(countryId);
                model.addAttribute("message", "Country removed from favorites!");
            } else {
                favoriteService.addFavorite(country);
                model.addAttribute("message", "Country added to favorites!");
            }
        } else {
            model.addAttribute("error", "Country not found!");
        }

        return "redirect:/all"; 
    }


    @PostMapping("/remove")
    public String removeFavorite(@RequestParam("countryId") Long countryId, Model model) {
    Optional<Country> countryOptional = countryService.getCountryById(countryId);

    if (countryOptional.isPresent()) {
        favoriteService.removeFavorite(countryId);
        model.addAttribute("message", "Country removed from favorites!");
    } else {
        model.addAttribute("error", "Country not found!");
    }

    return "redirect:/faves"; 
}


}
