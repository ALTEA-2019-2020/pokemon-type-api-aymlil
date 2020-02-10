package com.miage.altea.tp.pokemon_type_api.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class PokemonTypeRepositoryImpl implements PokemonTypeRepository{
    private List<PokemonType> pokemons;

    public PokemonTypeRepositoryImpl() {
        try {
            var pokemonsStream = new ClassPathResource("pokemons.json").getInputStream();

            var objectMapper = new ObjectMapper();
            var pokemonsArray = objectMapper.readValue(pokemonsStream, PokemonType[].class);
            this.pokemons = Arrays.asList(pokemonsArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PokemonType findPokemonTypeById(int id) {
        System.out.println("Loading Pokemon information for Pokemon id " + id);

        for(PokemonType pt : pokemons){
            if(pt.getId() == id){
                return pt;
            }
        }
        return new PokemonType();
    }

    @Override
    public PokemonType findPokemonTypeByName(String name) {
        System.out.println("Loading Pokemon information for Pokemon name " + name);

        for(PokemonType pt : pokemons){
            if(pt.getName().equals(name)){
                return pt;
            }
        }
        return new PokemonType();
    }

    @Override
    public List<PokemonType> findPokemonTypeByTypes(List<String> types) {
        System.out.println("Loading Pokemon information for Pokemon types");
        List<PokemonType> pokeTypes = new ArrayList<>();

        for(PokemonType pt : pokemons){
            boolean tmp = true;
            for(String t : types)
                if(!pt.getTypes().contains(t)){
                    tmp = false;
                }
            if(tmp){
                pokeTypes.add(pt);
            }
        }
        return pokeTypes;
    }

    @Override
    public List<PokemonType> findAllPokemonType() {
        List<PokemonType> res = new ArrayList<PokemonType>() ;
        for(PokemonType pt : pokemons){
            res.add(pt);
        }
        return res;
    }
}
