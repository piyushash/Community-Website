import { Component, OnInit } from '@angular/core';
import { SearchService } from '../../service/search.service';
import { FormBuilder,FormGroup, Validators} from '@angular/forms';
import { Router } from '@angular/router';
@Component({
  selector: 'app-ask-review',
  templateUrl: './ask-review.component.html',
  styleUrls: ['./ask-review.component.css']
})
export class AskReviewComponent implements OnInit {
  addProduct:FormGroup;
  submitted=false;
  error:boolean=false;
  constructor(private searchService:SearchService, private formBuilder:FormBuilder, private router:Router) { }

  createForm(){
    this.addProduct = this.formBuilder.group({
      product_name:['',Validators.required],
      code:['',Validators.required],
      brand:['',Validators.required]
    
    })
  }

  ngOnInit(): void {
    this.createForm();
  }

  submit(){
    this.submitted=true;
    if(this.addProduct.invalid){
      return;
    }
    this.addProduct.value.code = this.addProduct.value.code.toLowerCase(); 
    this.searchService.addProduct(this.addProduct.value).subscribe(res=>{
      
      if(res[0]==0){                        // product already exist show error for 30s
        this.error = true;
        console.log("here",res[1], res)
        setTimeout(() => {
          this.router.navigateByUrl("show-more/"+res[1]);
        }, 30000);
        
        
      }else if(res[0]==1){
        this.router.navigateByUrl("show-more/"+res[1]);
      }
    })

  }
  get f(){
    return this.addProduct.controls;
  }


}
