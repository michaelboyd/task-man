import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserListComponent } from './taskman/user-list/user-list.component';
import { TaskListComponent } from './taskman/task-list/task-list.component';
import { TaskFormComponent } from './taskman/task-form/task-form.component';

const routes: Routes = [
	{ path: 'users', component: UserListComponent },
	{ path: 'tasks', component: TaskListComponent },
	{ path: 'tasks/1', component: TaskListComponent },
	{ path: 'tasks/2', component: TaskListComponent },
	{ path: 'tasks/3', component: TaskListComponent },
	{ path: 'task/edit', component: TaskFormComponent },
	{path: '', redirectTo: '/tasks', pathMatch: 'full'},	
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
