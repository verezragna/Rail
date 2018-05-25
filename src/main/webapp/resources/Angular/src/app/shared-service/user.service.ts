import { Injectable } from '@angular/core';
import { Http, Headers, Response, RequestOptions } from '@angular/http';
import { URLSearchParams } from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class UserService {
  private baseUrl:string = 'http://localhost:8080/';
  private headers = new Headers({'Content-type':'application/x-www-form-urlencoded'});
  private options = new RequestOptions({headers:this.headers});

  constructor(private http:Http) { }

  login(){
    let params = new URLSearchParams();
    params.append("j_username", "4");
    params.append("j_password", "12345678");
    let body = params.toString();
    return this.http.post('http://localhost:8080/login', body ,this.options);
  }
}
