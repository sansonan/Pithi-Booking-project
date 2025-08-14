import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ProductListComponent } from './components/product-list/product-list.component';
import { HttpClientModule } from '@angular/common/http';
import { ProductService } from './services/product.service';
import { Route } from '@angular/router';
import {Routes, RouterModule} from '@angular/router';
import { from } from 'rxjs';
import { ProductCategoryMenuComponent } from './components/product-category-menu/product-category-menu.component';
import { SearchComponent } from './components/search/search.component';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import{ NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BookStatusComponent } from './components/book-status/book-status.component';
import { BookDetailsComponent } from './components/book-details/book-details.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './components/login/login.component';
import { LoginStatusComponent } from './components/login-status/login-status.component';


import {
  OktaAuthModule,
  OktaCallbackComponent,
  OKTA_CONFIG 
} from '@okta/okta-angular';

import { OktaAuth } from '@okta/okta-auth-js';

import myAppConfig from './config/my-app-config';
import { BookMeComponent } from './components/book-me/book-me.component';

const oktaConfig = myAppConfig.oidc;

const oktaAuth = new OktaAuth(oktaConfig);

const routes: Routes = [
  {path: 'login/callback', component: OktaCallbackComponent},
  {path: 'login', component: LoginComponent},

  {path:'checkout', component: CheckoutComponent},
  {path:'book-details', component: BookDetailsComponent},
  {path:'products/:id', component: ProductDetailsComponent},
  {path: 'search/:keyword', component: ProductListComponent},
  {path: 'category/:id', component: ProductListComponent},
  {path: 'category', component: ProductListComponent},
  {path: 'products', component: ProductListComponent},
  {path: '', redirectTo:'/products', pathMatch: 'full'},
  {path: '**', redirectTo:'/products', pathMatch: 'full'}
];



@NgModule({
  declarations: [
    AppComponent,
    ProductListComponent,
    ProductCategoryMenuComponent,
    SearchComponent,
    ProductDetailsComponent,
    BookStatusComponent,
    BookDetailsComponent,
    CheckoutComponent,
    LoginComponent,
    LoginStatusComponent,
    BookMeComponent
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    HttpClientModule,
    NgbModule,
    ReactiveFormsModule,
    OktaAuthModule
  ],
  providers: [ProductService, { provide: OKTA_CONFIG, useValue: { oktaAuth }}],
  bootstrap: [AppComponent]
})
export class AppModule { }
