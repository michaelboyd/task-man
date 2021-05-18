package com.taskman.controller;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskman.entity.Hero;
import com.taskman.service.HeroService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class HeroController {

	private HeroService heroService;

	public HeroController(HeroService heroService) {
		this.heroService = heroService;
	}

	@GetMapping(value = { "", "/heroes" })
	public @NotNull Iterable<Hero> getHeroes() {
		return heroService.getHeroes();
	}

	@GetMapping(value = { "", "/heroes/{id}" })
	public @NotNull ResponseEntity<Hero> findById(@PathVariable("id") Long id) {
		Optional<Hero> hero = heroService.findById(id);
		if (hero.isPresent()) {
			return new ResponseEntity<Hero>(hero.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Hero>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/heroes", params = "name")
	public Iterable<Hero> findByName(@RequestParam String name) {
		return heroService.findByName(name);
	}

	@PutMapping(value = { "", "/heroes/{id}" })
	public ResponseEntity<Hero> update(@PathVariable("id") long id, @RequestBody Hero hero) {
		Optional<Hero> heroData = heroService.findById(id);
		if (heroData.isPresent()) {
			Hero _hero = heroData.get();
			_hero.setName(hero.getName());
			return new ResponseEntity<>(heroService.update(_hero), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = { "", "/heroes" })
	public ResponseEntity<Hero> add(@RequestBody Hero hero) {
		HttpStatus status = HttpStatus.CREATED;
		Hero created = heroService.create(hero);
		return new ResponseEntity<>(created, status);
	}

	@DeleteMapping(value = { "", "/heroes/{id}" })
	public ResponseEntity<Hero> delete(@PathVariable("id") long id) {
		heroService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
