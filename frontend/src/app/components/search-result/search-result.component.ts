import { analyzeAndValidateNgModules } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { SearchService } from '../../service/search.service';
import { FormBuilder,FormGroup, Validators} from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.css']
})
export class SearchResultComponent implements OnInit {
  query:any;
  data:any=[];
  searchForm: FormGroup;
  filterForm :FormGroup;
  constructor(private searchService:SearchService, private formBuilder:FormBuilder, private router:Router ) { }
  
  createForm(){
    this.searchForm = this.formBuilder.group({
      search:['',Validators.required]
    })
  }

  createFilterForm(){
    this.filterForm = this.formBuilder.group({
      search:[this.query.search,Validators.required],
      pdt_name:[false,Validators.required],
      pdt_brand:[false,Validators.required],
      pdt_code:[false,Validators.required]
    })
  }

  ngOnInit(): void {
    this.query = this.searchService.getQuery();
    this.createForm();
    this.createFilterForm();
    this.getProductResult();

  }

  getProductResult(){
    this.query = this.searchService.getQuery();
    this.searchService.searchData(this.query).subscribe(res=>{
      console.log(res)
      this.data = res; 
    })
  }


  search() {
    this.searchService.storeQuery(this.searchForm.value);
    this.getProductResult();
    // console.log(this.searchForm.value)
  }

  search2(){
    this.filterForm.value.search = this.query.search
    // console.log(this.filterForm.value.search)
    this.searchService.searchData(this.filterForm.value).subscribe(res =>{
      this.data = res;
    })

  }

  calAvg(pdt){
    console.log(pdt.reviews,"here")
    var sum=0;
    var avg=0;
    pdt.reviews.forEach(ele => {
      if(ele.approval)
      sum+=ele.rating;
      
    });
    avg=sum/pdt.reviews.length;
    return avg.toFixed(1);
  }


}
