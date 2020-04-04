package com.miage.altea.tp.pokemon_type_api.controller;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.service.PokemonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemon-types")
public class PokemonTypeController {

    @Autowired
    private PokemonTypeService pokemonTypeService;

    public PokemonTypeController(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService = pokemonTypeService;
    }

    @GetMapping("/{id}")
    PokemonType getPokemonTypeFromId(@PathVariable int id){
        return this.pokemonTypeService.getPokemonType(id);
    }

    @GetMapping("/")
    public List<PokemonType> getAllPokemonTypes() {
        return this.pokemonTypeService.getAllPokemonTypes();
    }

    @GetMapping(value="/", params="name")
    public PokemonType getPokemonTypeFromName(@RequestParam(value = "name", required = false) String name){
        return this.pokemonTypeService.getPokemonTypeByName(name);
    }

    @GetMapping(value="/", params="types")
    public List<PokemonType> getPokemonTypeFromTypes(@RequestParam(value = "types", required = false) List<String> types){
        return this.pokemonTypeService.getPokemonTypeByTypes(types);
    }
}
