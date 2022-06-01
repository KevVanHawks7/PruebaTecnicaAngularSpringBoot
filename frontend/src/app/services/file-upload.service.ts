import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {
  private baseUrl = 'http://localhost:8080/api/productos/img';

  constructor(private http: HttpClient) { }

  upload(file: File){
    const formData: FormData = new FormData();

    console.log(file)
    console.log(file.name)
    formData.append('name', file);
    console.log('working')
     return this.http.post(`${this.baseUrl}`, formData)
      .subscribe(res => {
        console.log(res);
        alert('Uploaded Successfully.');
      })

  }
}
