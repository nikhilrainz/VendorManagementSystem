import { Injectable } from '@angular/core';
import { Login } from './login';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  formData: Login;

  constructor(private router: Router, private _httpService: HttpClient) { }

  verifyUser(formData: any): any {
    return this._httpService.get<Login>(environment.APIUrl + '/login/' + formData.username + '/' + formData.password);
  }

  logout() :void 
  {    
    localStorage.setItem('isLoggedIn','false');    
    localStorage.removeItem('token');  
    localStorage.removeItem('tokenRoleId');
    this.router.navigate(['/login']);   
  }
  
}
