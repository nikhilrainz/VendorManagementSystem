import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Vendor } from '../vendor';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-userdashboard',
  templateUrl: './userdashboard.component.html',
  styleUrls: ['./userdashboard.component.scss']
})
export class UserdashboardComponent implements OnInit {

  vendorlist: Vendor[];
  vendor = new Vendor();
  searchString: string;

  constructor(private router: Router, private _authService: AuthService,private _userService: UserService) { }

  ngOnInit() {

    this.getAllVendors();

  }

  viewAll(): void
  {
    this.getAllVendors();
  }

  addVendors(): void
  {
    this.router.navigate(['addVendor']);
  }

  editVendor(vendorId: number): void
  {
    this.router.navigate(['addVendor/'+vendorId]);
  }

  logout() {  
    console.log('logout');  
    this._authService.logout();  
  }

  getAllVendors(): void {
    this._userService.getVendors()
      .subscribe((userData) => {
        this.vendorlist = userData,
          console.log(userData);
      }, (error) => {
        console.log(error);
      });
  }

  searchVendors(searchString: string) 
  {
    if(searchString!=null)
    {
      console.log(searchString);
      this._userService.searchVendors(searchString)
        .subscribe((response) => 
        {
          console.log(response);
          this.vendorlist = response
          console.log(this.vendorlist);
          this.searchString = undefined
        }, (error) => 
        {
          console.log(error);
        });
    }
  }
  
    disableVendor(vendorId: number): void {
      console.log(vendorId);
      this._userService.disableVendor(vendorId)
        .subscribe((disableData) => {
          //console.log(response);
          this.vendor = disableData
          console.log(this.vendor);
          this.getAllVendors();
        }, (error) => {
          console.log(error);
        });
    }
  }
