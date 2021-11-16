import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  currentUser = JSON.parse(sessionStorage.getItem("currentUser"));
  constructor(private httpClient : HttpClient, private router: Router) {}
    
  storeQuery(data){
    sessionStorage.setItem("searchQuery",JSON.stringify(data));
  }

  getQuery(){
    console.log(JSON.parse(localStorage.getItem("searchQuery")),"here");
    return JSON.parse(sessionStorage.getItem("searchQuery"));
  }

  searchData(query){
      return this.httpClient.post("http://localhost:8081/product/search",query);
  }
  
  login(credentials){
    this.httpClient.post("http://localhost:8081/users/login",credentials).subscribe(res=>{
      sessionStorage.setItem("currentUser",JSON.stringify(res));
      if(res){
        this.currentUser = res;
        this.router.navigateByUrl("/");
      }
      else{
        this.router.navigateByUrl("login");
      }
      console.log(res)
    });
    
    return JSON.parse(sessionStorage.getItem("currentUser"));
  }

  register(data){
    return this.httpClient.post("http://localhost:8081/users/register",data);
  }
  getProductById(id){
    return this.httpClient.get("http://localhost:8081/product/"+id);
  }
  postReview(body,id){
    return this.httpClient.post("http://localhost:8081/review/post/"+id,body);
  }
  getStats(){
    return this.httpClient.get("http://localhost:8081/stats");
  }

  getReview(){
    return this.httpClient.get("http://localhost:8081/admin/review/get");
  }

  approve(id){
    return this.httpClient.put("http://localhost:8081/admin/review/approve/",id);
  }

  disApprove(id){
    return this.httpClient.delete("http://localhost:8081/admin/review/delete/"+id);
  }
  addProduct(data){
    return this.httpClient.post("http://localhost:8081/product/add/",data);
  }

}


