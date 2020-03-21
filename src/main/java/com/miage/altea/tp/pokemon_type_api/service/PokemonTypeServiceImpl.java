package com.miage.altea.tp.pokemon_type_api.service;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.repository.PokemonTypeRepository;
import com.miage.altea.tp.pokemon_type_api.repository.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService{

    @Autowired
    public PokemonTypeRepository pokemonTypeRepository;

    @Autowired
    public TranslationRepository translationRepository;

    public PokemonTypeServiceImpl(PokemonTypeRepository repo, TranslationRepository translationRepository){

        this.pokemonTypeRepository = repo;
        this.translationRepository = translationRepository;
    }

    @Override
    public PokemonType getPokemonType(int id) {
        PokemonType pok = this.pokemonTypeRepository.findPokemonTypeById(id);
        String name = translationRepository.getPokemonName(id, LocaleContextHolder.getLocale());
        pok.setName(name);
        return pok;
    }

    @Override
    public PokemonType getPokemonTypeByName(String name){
        return this.pokemonTypeRepository.findPokemonTypeByName(name);
    }

    @Override
    public List<PokemonType> getPokemonTypeByTypes(List<String> types){
        return this.pokemonTypeRepository.findPokemonTypeByTypes(types);
    }

    @Override
    public List<PokemonType> getAllPokemonTypes(){
        List<PokemonType> list = this.pokemonTypeRepository.findAllPokemonType();
        Collections.sort(list);
        for(PokemonType pok : list){
            String name = translationRepository.getPokemonName(pok.getId(), LocaleContextHolder.getLocale());
            pok.setName(name);
        }
        return list;
    }

    @Override
    public void setTranslationRepository(TranslationRepository translationRepository) {
        this.translationRepository = translationRepository;
    }

    @Override
    public void setPokemonTypeRepository(PokemonTypeRepository pokemonTypeRepository) {
        this.pokemonTypeRepository = pokemonTypeRepository;
    }
}
