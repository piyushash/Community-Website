import { Component, OnInit } from '@angular/core';
import { SearchService } from '../../service/search.service';
import { FormBuilder,FormGroup, Validators} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-post-review',
  templateUrl: './post-review.component.html',
  styleUrls: ['./post-review.component.css']
})
export class PostReviewComponent implements OnInit {

  reviewForm:FormGroup
  stars: number[] = [1, 2, 3, 4, 5];
  selectedValue: number=1;
  id:any;
  submitted=false;
  constructor(private searchService:SearchService, private formBuilder:FormBuilder, private router:Router ,private route:ActivatedRoute) { }
  
  createForm(){
    this.reviewForm = this.formBuilder.group({
      heading:['',Validators.required],
      review:['',Validators.compose([Validators.required, Validators.minLength(20), Validators.maxLength(400)])],
      rating:[0]
    })
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params.id;
    this.createForm();
  }

  countStar(star) {
    this.selectedValue = star;
    console.log('Value of star', star);
  }

  reviewSubmit(){
    this.submitted = true;

    if(this.reviewForm.invalid){
      return;
    }
    this.reviewForm.value.rating = this.selectedValue;
    this.searchService.postReview(this.reviewForm.value,this.id).subscribe(res=>{
      this.router.navigateByUrl("/show-more/"+this.id);
    })
  }

  get f(){
    return this.reviewForm.controls
  }

}
