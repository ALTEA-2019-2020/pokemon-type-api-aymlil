package com.miage.altea.tp.pokemon_type_api.service;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.repository.PokemonTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService{

    @Autowired
    public PokemonTypeRepository pokemonTypeRepository;

    public PokemonTypeServiceImpl(PokemonTypeRepository repo){
        this.pokemonTypeRepository = repo;
    }

    @Override
    public PokemonType getPokemonType(int id) {
        return this.pokemonTypeRepository.findPokemonTypeById(id);
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
        return this.pokemonTypeRepository.findAllPokemonType();
    }
}
