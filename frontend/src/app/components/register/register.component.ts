import { Component, OnInit } from '@angular/core';
import { FormBuilder,FormGroup, Validators} from '@angular/forms';
import { SearchService } from '../../service/search.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm:FormGroup;
  submitted=false;
 

  constructor(private searchService:SearchService,private formBuilder:FormBuilder, private router:Router) { }

  createForm(){
    this.registerForm = this.formBuilder.group({
      username:['',Validators.compose([Validators.required,Validators.email])],
      firstname:[''],
      lastname:[''],
      password:['',Validators.compose([Validators.required,Validators.minLength(8)])],
      confirmpassword:['',Validators.required]

    })
  }

  

  ngOnInit(): void {
    this.createForm();
  }

  register(){
    this.submitted=true;
    if(this.registerForm.invalid){
      return
    }
    this.searchService.register(this.registerForm.value).subscribe(res=>{
      console.log(res);
      if(res=='USER_ALREADY_EXISTS'){
        alert("USER ALREADY EXISTS")
      }else if(res=='CONFIRM_PASSWORD_MISMATCH'){
        alert("Confirm Password Mismatch")
      }
      if(res=="SUCCESS")
      this.router.navigateByUrl("login")
    })
  }

  get f(){
    return this.registerForm.controls
  }

}
