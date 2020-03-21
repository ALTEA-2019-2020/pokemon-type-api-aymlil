package com.miage.altea.tp.pokemon_type_api.service;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.repository.PokemonTypeRepository;
import com.miage.altea.tp.pokemon_type_api.repository.TranslationRepository;

import java.util.List;

public interface PokemonTypeService {
    PokemonType getPokemonType(int id);
    PokemonType getPokemonTypeByName(String name);
    List<PokemonType> getPokemonTypeByTypes(List<String> types);
    List<PokemonType> getAllPokemonTypes();
    void setTranslationRepository(TranslationRepository translationRepository);
    void setPokemonTypeRepository(PokemonTypeRepository pokemonTypeRepository);
}
