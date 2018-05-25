import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserService } from './shared-service/user.service';
import { HttpModule } from '@angular/http';
import { URLSearchParams } from '@angular/http';

import { AppComponent } from './app.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { UserFormComponent } from './components/user-form/user-form.component';

const appRoutes:Routes=[
  {path:'', component:WelcomeComponent},
  {path:'login', component:UserFormComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    UserFormComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }