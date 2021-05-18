package com.taskman.service;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.taskman.entity.Hero;
import com.taskman.repository.HeroRepository;

@Service
@Transactional
public class HeroServiceImpl implements HeroService {
	
	private HeroRepository heroRepository;
	
	public HeroServiceImpl(HeroRepository heroRepository) {
		this.heroRepository = heroRepository;
	}

	@Override
	public @NotNull Iterable<Hero> getHeroes() {
		return heroRepository.findAll();
	}

	@Override
	public Hero create(@NotNull(message = "The hero cannot be null.") @Valid Hero hero) {
		return heroRepository.save(hero);
	}

	@Override
	public Optional<Hero> findById(Long id) {
		return heroRepository.findById(id);
	}

	@Override
	public void delete(@NotNull(message = "The id cannot be null.") @Valid Long id) {
		heroRepository.deleteById(id);
	}

	@Override
	public Hero update(@NotNull(message = "The hero cannot be null.") @Valid Hero hero) {
		return heroRepository.save(hero);
	}

	@Override
	public Iterable<Hero> findByName(String name) {
		return heroRepository.findByNameContains(name);
	}

}
