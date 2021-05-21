import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HeroesComponent } from './heroes/heroes.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HeroDetailComponent } from './hero-detail/hero-detail.component';
import { UsersComponent } from './users/users.component';
import { TasksComponent } from './tasks/tasks.component';
import { TaskDetailComponent } from './task-detail/task-detail.component';
import { UserDetailComponent } from './user-detail/user-detail.component';
import { HeroFormComponent } from './hero-form/hero-form.component';
import { TaskFormComponent } from './task-form/task-form.component';
import { UserFormComponent } from './user-form/user-form.component';


const routes: Routes = [
  { path: 'heroes', component: HeroesComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: '', redirectTo: '/users', pathMatch: 'full' },
  { path: 'heroes/detail/:id', component: HeroFormComponent },  
  { path: 'users', component: UsersComponent },
  { path: 'users/detail/:id', component: UserFormComponent },   
  { path: 'tasks', component: TasksComponent },
  { path: 'tasks/detail/:id', component: TaskDetailComponent },  
  { path: 'heroes/create', component: HeroFormComponent },
  { path: 'tasks/create', component: TaskFormComponent },
  { path: 'users/create', component: UserFormComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }