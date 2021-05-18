package com.taskman.service;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.taskman.entity.Hero;

@Validated
public interface HeroService {
	
	@NotNull
	Iterable<Hero> getHeroes();

	Hero create(@NotNull(message = "The hero cannot be null.") @Valid Hero hero);
	
	Optional <Hero> findById(Long id);
	
	Iterable <Hero> findByName(String name);
	
	void delete(@NotNull(message = "The id cannot be null.") @Valid Long id);	
	
	Hero update(@NotNull(message = "The hero cannot be null.") @Valid Hero hero);	

}
