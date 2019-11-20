import { Component, OnInit } from '@angular/core';
import { Login } from '../login';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  login = new Login();
  sessionTokenUserName: string;
  sessionTokenRoleID: string;
  
  constructor(private router: Router, private _authservice: AuthService) { }

  ngOnInit() {

    this.resetForm()
    this.sessionTokenUserName = localStorage.getItem('token');
    console.log(this.sessionTokenUserName = localStorage.getItem('token'));
    this.sessionTokenRoleID = localStorage.getItem('tokenRoleId');
    console.log(this.sessionTokenRoleID = localStorage.getItem('tokenRoleId'));

  }

  resetForm(form?: NgForm) {
    if (form != null)
      form.resetForm();
    this._authservice.formData = {

      userId: null,
      username: '',
      password: ''
    }
  }
  OnSubmit(form: NgForm) {
    console.log("My form : " + form);
    this.loginUser(form);
  }

  loginUser(form: NgForm) 
  {
      console.log(form.value);
      this._authservice.verifyUser(form.value).subscribe((userData) => {
      this.login = userData;
      console.log(this.login);
      var username = this.login.username;
      var password = this.login.password;
      console.log(userData);
      localStorage.setItem('isLoggedIn', "true");
      localStorage.setItem('token', userData.username);
      localStorage.setItem('tokenRoleId', userData.userId);
    
      if(this.login.userId == 100)
      {
        this.router.navigate(['userdashboard']);
      }
      else
      {
        this.router.navigate(['userdashboard']);
      }

    }, (error) => {
      console.log(error);
    });
  }
}
