import { Component, OnInit } from '@angular/core';
import { SearchService } from 'src/app/service/search.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-pannel',
  templateUrl: './admin-pannel.component.html',
  styleUrls: ['./admin-pannel.component.css']
})
export class AdminPannelComponent implements OnInit {
  reviews:any=[];
  constructor(private searchService:SearchService,private router:Router ) { }

  ngOnInit(): void {
    this.getReview()
  }

getReview(){
  this.searchService.getReview().subscribe(res=>{
    this.reviews = res;
    console.log(res,"here")
  })
}

approve(id){
  this.searchService.approve(id).subscribe(res=>{
    this.getReview();
  });
}
reject(id){
    this.searchService.disApprove(id).subscribe(res=>{
      this.getReview();
    });
}

}
