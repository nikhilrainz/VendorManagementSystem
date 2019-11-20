import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Vendor } from '../vendor';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-vendor',
  templateUrl: './add-vendor.component.html',
  styleUrls: ['./add-vendor.component.scss']
})
export class AddVendorComponent implements OnInit {

  vendor = new Vendor();
  searchString: string;

  constructor(private route: ActivatedRoute,private router: Router,private _userService: UserService) { }

  ngOnInit() {
    this.route.params.subscribe(params => this.getVendorById(params['vId']));
  }

  private reset() {
    this.vendor.vendorId = null;
    this.vendor.vendorName = '';
    this.vendor.address = '';
    this.vendor.location = '';
    this.vendor.service = '';
    this.vendor.pincode = null;
    this.vendor.contactName = '';
    this.vendor.department = '';
    this.vendor.email = '';
    this.vendor.phone = null;
  }

  back()
  {
    this.router.navigate(['userdashboard']);
  }
  
  saveVendors(): void {
    console.log(this.vendor);
    this._userService.addVendors(this.vendor)
      .subscribe((response) => {
        console.log(response);
        this.reset;
        this.router.navigate(['userdashboard']);
      }, (error) => {
        console.log(error);
      });
  }

  getVendorById(vId: string) {
    console.log(vId);
    this._userService.getVendorById(vId)
      .subscribe((vendorData) => {
        this.vendor = vendorData;
        console.log(vendorData);
      }, (error) => {
        console.log(error);
      });
  }

  updateVendor(): void {
    console.log(this.vendor);
    this._userService.addVendors(this.vendor)
      .subscribe((response) => {
        console.log(response);
        this.router.navigate(['userdashboard'])
      }, (error) => {
        console.log(error);
      });
  }

}
