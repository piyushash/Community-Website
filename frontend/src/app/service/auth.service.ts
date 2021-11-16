import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  public isAuthenticated():boolean{
    	const user = sessionStorage.getItem("currentUser")
      if(user==null){
        return false
      }
      return true

  }

}
