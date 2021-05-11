import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TaskmanComponent } from './taskman/taskman.component';
import { UserListComponent } from './taskman/user-list/user-list.component';
import { TaskListComponent } from './taskman/task-list/task-list.component';
import { TaskFormComponent } from './taskman/task-form/task-form.component';

@NgModule({
  declarations: [
    AppComponent,
    TaskmanComponent,
    UserListComponent,
    TaskListComponent,
    TaskFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule        
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
