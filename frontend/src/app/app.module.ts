import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { RouterModule,Routes } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { LoginComponent } from './components/login/login.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { SearchResultComponent } from './components/search-result/search-result.component';
import { RegisterComponent } from './components/register/register.component';
import { ShowMoreComponent } from './components/show-more/show-more.component';
import { PostReviewComponent } from './components/post-review/post-review.component';
import { AuthGuardService as AuthGuard } from './service/auth-guard.service';
import { RoleguardServiceService as RoleGuard } from './service/roleguard-service.service';
import { AdminPannelComponent } from './components/admin-pannel/admin-pannel.component';
import { AskReviewComponent } from './components/ask-review/ask-review.component';


const appRoutes:Routes=[
    {
      path:'',component:HomepageComponent
    },
    {
        path:'searchresult',component:SearchResultComponent,
        canActivate:[AuthGuard]
    },
    {
        path:'login',component:LoginComponent
    },
    {
        path:'register',component:RegisterComponent
    },
    {
        path:'show-more/:id',component:ShowMoreComponent,
        canActivate:[AuthGuard]
    },
    {
        path:'postReview/:id',component:PostReviewComponent,
        canActivate:[AuthGuard]
    },
    {
        path:'admin',component:AdminPannelComponent,
        canActivate:[RoleGuard]
    },
    {
        path:'ask-review',component:AskReviewComponent,
        canActivate:[AuthGuard]
    },
    {
        path:'**',redirectTo:''
    }
  ]

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    LoginComponent,
    HomepageComponent,
    SearchResultComponent,
    RegisterComponent,
    ShowMoreComponent,
    PostReviewComponent,
    AdminPannelComponent,
    AskReviewComponent,
    
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
