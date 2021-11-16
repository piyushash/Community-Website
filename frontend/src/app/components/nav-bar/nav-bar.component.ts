import { Component, OnInit } from '@angular/core';
import { SearchService } from '../../service/search.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  
  constructor(public service:SearchService ) { }

  ngOnInit(): void {
  }

  logout(){
    sessionStorage.removeItem("currentUser");
    this.service.currentUser = null;
  }

  isAdmin(){
    if(this.service.currentUser == null){
      return false;
    }
    var n = this.service.currentUser.roles.length;
    for(let i=0; i<n; i++){
      if(this.service.currentUser.roles[i].name == "ADMIN")return true;
    }
    return false;
    
  }

}
