import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Vendor } from './vendor';
import { environment } from 'src/environments/environment';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private router: Router,private _httpService: HttpClient) { }

  getVendors(): Observable<Vendor[]>{
    return this._httpService.get<Vendor[]>(environment.APIUrl +'/vendor');
  }

  searchVendors(searchString: string): Observable<Vendor[]>{
    return this._httpService.get<Vendor[]>(environment.APIUrl +'/searchVendor/'+ searchString);
  }

  addVendors(vendor: Vendor)
  {
    console.log("Vendor : " +vendor);
    let body = JSON.stringify(vendor);
    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    let options = { headers: headers }

    console.log("Vendor Id : " +vendor.vendorId);
    if(vendor.vendorId)
    {
      return this._httpService.put(environment.APIUrl +'/saveVendor', body, options);
    }
    else
    {
      return this._httpService.post(environment.APIUrl +'/saveVendor', body, options);
    } 
  }

  getVendorById(vId: string): Observable<Vendor>{
    return this._httpService.get<Vendor>(environment.APIUrl +'/viewVendor/'+ vId);
  }

  disableVendor(vendorId:number): Observable<Vendor>{
    let body = JSON.stringify(vendorId);
    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    let options = { headers: headers }
    return this._httpService.put<Vendor>(environment.APIUrl +'/disableVendor/'+ vendorId,body,options);
  } 
}


