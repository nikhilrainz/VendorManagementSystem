import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { UserdashboardComponent } from './userdashboard/userdashboard.component';
import { AddVendorComponent } from './add-vendor/add-vendor.component';
import { AuthGuard } from './guards/auth.guard';


const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent, },
  { path: 'userdashboard', component:UserdashboardComponent, canActivate: [AuthGuard]},
  { path: 'addVendor', component: AddVendorComponent, canActivate: [AuthGuard]},
  { path: 'addVendor/:vId', component: AddVendorComponent, canActivate: [AuthGuard] }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
