import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RoleguardServiceService  {

  constructor(private authService:AuthService, private router:Router) { }

  canActivate():boolean{
    var f=false
    if(this.authService.isAuthenticated()){
      const user = JSON.parse(sessionStorage.getItem("currentUser"));
      user.roles.forEach(role => {
        if(role.name=="ADMIN") 
        f=true;
        
      });
    }
    if(f)return true;
    this.router.navigateByUrl("login");
    return false;
  }
}
