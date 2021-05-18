package com.taskman.repository;

import org.springframework.data.repository.CrudRepository;

import com.taskman.entity.Hero;

public interface HeroRepository extends CrudRepository<Hero, Long> {
	
	Iterable <Hero> findByNameContains(String name);

}
