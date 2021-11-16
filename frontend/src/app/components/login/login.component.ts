import { Component, OnInit } from '@angular/core';
import { FormBuilder,FormGroup, Validators} from '@angular/forms';
import { SearchService } from '../../service/search.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm:FormGroup;
  currUser:any;
  submitted=false;
  msg="";

  constructor(private searchService:SearchService,private formBuilder:FormBuilder, private router:Router ) { }

  createForm(){
    this.loginForm = this.formBuilder.group({
      username:['',Validators.required],
      password:['',Validators.required]
    })
  }

  ngOnInit(): void {
    this.createForm();
  }

  login(){
    this.submitted = true;

    if(this.loginForm.invalid){
      return;
    }
    this.currUser = this.searchService.login(this.loginForm.value);
  }
  get f(){
    return this.loginForm.controls
  }

  invMsg(){
    setTimeout(() => {
      this.msg="Invalid Credentials!"
    }, 2000);
    return true
  
  }

}
