import { Component, OnInit } from '@angular/core';
import { SearchService } from '../../service/search.service';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-show-more',
  templateUrl: './show-more.component.html',
  styleUrls: ['./show-more.component.css']
})
export class ShowMoreComponent implements OnInit {
  data:any = {};
  id: any;

  constructor(private searchService: SearchService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params.id;
    this.getData();
  }

  getData() {
    this.searchService.getProductById(this.id).subscribe(res => {
      console.log(res)
      this.data = res;
    })
  }
}
