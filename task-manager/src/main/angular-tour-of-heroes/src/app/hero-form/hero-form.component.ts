import { Component, OnInit } from '@angular/core';
import { Hero } from '../hero';
import { HeroService } from '../hero.service';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-hero-form',
  templateUrl: './hero-form.component.html',
  styleUrls: ['./hero-form.component.css']
})
export class HeroFormComponent {

  powers = ['Really Smart', 'Super Flexible',
            'Super Hot', 'Weather Changer', 'Mind Reader', 'Persistence'];

  model = new Hero(18, 'Dr IQ', this.powers[0], 'Chuck Overstreet');

  submitted = false;

  title = "";

  constructor(
    private heroService: HeroService,
    private router: Router,
    private location: Location,
    private route: ActivatedRoute
    ) { }  

  ngOnInit(): void {
    this.newHero(); 
  }  

  goBack(): void {
    this.location.back();
  }

  onSubmit() { 
    this.submitted = true;
    this.add(this.model);
  }

  add(hero: Hero): void {
    this.heroService.addHero(hero)
      .subscribe();
      this.router.navigateByUrl('heroes');
  }   

  // TODO: Remove this when we're done
  get diagnostic() { return JSON.stringify(this.model); }

  newHero() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if(id > 0) {
      this.title = "Edit Hero";
      this.heroService.getHero(id)
      .subscribe(hero => this.model = hero);
    }
    else {
      this.title = "Create Hero";
      this.model = new Hero(42, '', '');      
    }
  }

  skyDog(): Hero {
    const myHero =  new Hero(42, 'SkyDog',
                           'Fetch any object at any distance',
                           'Leslie Rollover');
    console.log('My hero is called ' + myHero.name); // "My hero is called SkyDog"
    return myHero;
  }

  //////// NOT SHOWN IN DOCS ////////

  // Reveal in html:
  //   Name via form.controls = {{showFormControls(heroForm)}}
  showFormControls(form: any) {
    return form && form.controls.name &&
    form.controls.name.value; // Dr. IQ
  }

  /////////////////////////////

}
