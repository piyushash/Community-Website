import { Component, OnInit } from '@angular/core';
import { SearchService } from '../../service/search.service';
import { FormBuilder,FormGroup, Validators} from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  searchForm: FormGroup;
  stats:any=[];

  constructor(private searchService :SearchService, private formBuilder:FormBuilder, private router:Router ) { }

  createForm(){
    this.searchForm = this.formBuilder.group({
      search:['',Validators.required]
    })
  }

  ngOnInit(): void {
    this.createForm();
    this.getStats();
  }

  search() {
    this.searchService.storeQuery(this.searchForm.value);
    this.router.navigateByUrl("searchresult");
  }
  privacy(){
    if(this.searchService.currentUser){return true}
  }

  getStats(){
    this.searchService.getStats().subscribe(res=>{
      this.stats = res;
    })
  }




}
