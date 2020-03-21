package com.miage.altea.tp.pokemon_type_api.repository;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;

import java.util.List;
import java.util.Locale;

public interface TranslationRepository {
    String getPokemonName(int id, Locale locale);
}
