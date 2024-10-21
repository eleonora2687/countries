package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Country;
import com.example.demo.model.Favorite;
import com.example.demo.repository.FavoriteRepository;

import jakarta.transaction.Transactional;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    public Favorite addFavorite(Country country) {
        Favorite favorite = new Favorite();
        favorite.setCountry(country);
        // Save to the database and return the favorite object
        return favoriteRepository.save(favorite);
    }


    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }

    @Transactional
    public void removeFavorite(Long countryId) {
        favoriteRepository.deleteByCountryId(countryId);
    }

    
    public boolean contains(Long countryId) {
        return favoriteRepository.existsByCountryId(countryId);
    }
    
}
