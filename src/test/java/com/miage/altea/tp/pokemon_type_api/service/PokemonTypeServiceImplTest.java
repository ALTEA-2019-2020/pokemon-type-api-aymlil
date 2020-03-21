package com.miage.altea.tp.pokemon_type_api.service;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.repository.PokemonTypeRepository;
import com.miage.altea.tp.pokemon_type_api.repository.TranslationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class PokemonTypeServiceImplTest {

    /*@Test
    void pokemonTypeRepository_shouldBeCalled_whenFindById(){
        var pokemonTypeRepository = mock(PokemonTypeRepository.class);

        var pokemonTypeService = new PokemonTypeServiceImpl(pokemonTypeRepository);

        pokemonTypeService.getPokemonType(25);

        verify(pokemonTypeRepository).findPokemonTypeById(25);
    }

    @Test
    void pokemonTypeRepository_shouldBeCalled_whenFindAll(){
        var pokemonTypeRepository = mock(PokemonTypeRepository.class);
        var pokemonTypeService = new PokemonTypeServiceImpl(pokemonTypeRepository);

        pokemonTypeService.getAllPokemonTypes();

        verify(pokemonTypeRepository).findAllPokemonType();
    }*/

    @Test
    void pokemonTypeRepository_shouldBeCalled_whenFindByName(){
        var pokemonTypeRepository = mock(PokemonTypeRepository.class);
        var translationRepository = mock(TranslationRepository.class);

        var pokemonTypeService = new PokemonTypeServiceImpl(pokemonTypeRepository, translationRepository);

        pokemonTypeService.getPokemonTypeByName("pikachu");

        verify(pokemonTypeRepository).findPokemonTypeByName("pikachu");
    }

    @Test
    void pokemonTypeRepository_shouldBeCalled_whenFindByType(){
        var pokemonTypeRepository = mock(PokemonTypeRepository.class);
        var translationRepository = mock(TranslationRepository.class);

        var pokemonTypeService = new PokemonTypeServiceImpl(pokemonTypeRepository, translationRepository);
        List<String> types = new ArrayList<String>();
        types.add("electric");

        pokemonTypeService.getPokemonTypeByTypes(types);

        verify(pokemonTypeRepository).findPokemonTypeByTypes(types);
    }

    @Test
    void applicationContext_shouldLoadPokemonTypeService(){
        var context = new AnnotationConfigApplicationContext("com.miage.altea.tp.pokemon_type_api");
        var serviceByName = context.getBean("pokemonTypeServiceImpl");
        var serviceByClass = context.getBean(PokemonTypeService.class);

        assertEquals(serviceByName, serviceByClass);
        assertNotNull(serviceByName);
        assertNotNull(serviceByClass);
    }

    @Test
    void pokemonTypeRepository_shouldBeAutowired_withSpring(){
        var context = new AnnotationConfigApplicationContext("com.miage.altea.tp.pokemon_type_api");
        var service = context.getBean(PokemonTypeServiceImpl.class);
        assertNotNull(service.pokemonTypeRepository);
    }

    @Test
    void pokemonNames_shouldBeTranslated_usingLocaleResolver(){
        var pokemonTypeRepository = mock(PokemonTypeRepository.class);
        var translationRepository = mock(TranslationRepository.class);

        var pokemonTypeService = new PokemonTypeServiceImpl(pokemonTypeRepository, translationRepository);

        pokemonTypeService.setPokemonTypeRepository(pokemonTypeRepository);
        when(pokemonTypeRepository.findPokemonTypeById(25)).thenReturn(new PokemonType());

        pokemonTypeService.setTranslationRepository(translationRepository);
        when(translationRepository.getPokemonName(25, Locale.FRENCH)).thenReturn("Pikachu");

        LocaleContextHolder.setLocale(Locale.FRENCH);

        var pikachu = pokemonTypeService.getPokemonType(25);

        assertEquals("Pikachu", pikachu.getName());
        verify(translationRepository).getPokemonName(25, Locale.FRENCH);
    }

    @Test
    void allPokemonNames_shouldBeTranslated_usingLocaleResolver(){
        var pokemonTypeRepository = mock(PokemonTypeRepository.class);
        var translationRepository = mock(TranslationRepository.class);

        var pokemonTypeService = new PokemonTypeServiceImpl(pokemonTypeRepository, translationRepository);

        pokemonTypeService.setPokemonTypeRepository(pokemonTypeRepository);

        var pikachu = new PokemonType();
        pikachu.setId(25);
        var raichu = new PokemonType();
        raichu.setId(26);
        when(pokemonTypeRepository.findAllPokemonType()).thenReturn(List.of(pikachu, raichu));

        pokemonTypeService.setTranslationRepository(translationRepository);
        when(translationRepository.getPokemonName(25, Locale.FRENCH)).thenReturn("Pikachu");
        when(translationRepository.getPokemonName(26, Locale.FRENCH)).thenReturn("Raichu");

        LocaleContextHolder.setLocale(Locale.FRENCH);

        var pokemonTypes = pokemonTypeService.getAllPokemonTypes();

        assertEquals("Pikachu", pokemonTypes.get(0).getName());
        assertEquals("Raichu", pokemonTypes.get(1).getName());
        verify(translationRepository).getPokemonName(25, Locale.FRENCH);
        verify(translationRepository).getPokemonName(26, Locale.FRENCH);
    }

}