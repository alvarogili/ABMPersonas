import { Component, OnInit } from '@angular/core';
import { RestApiService } from '../shared/rest-api.service';
import { Location } from '@angular/common';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-persona',
  templateUrl: './persona.component.html',
  styleUrls: ['./persona.component.css']
})
export class PersonaComponent implements OnInit {


  personaForm = new FormGroup({
    perNombre: new FormControl(''),
    perApellido: new FormControl(''),
    perFechaNacimiento: new FormControl(''),
    perTipoDocumento: new FormControl(''),
    perNumeroDocumento: new FormControl(''),
  });

  constructor(private location: Location, protected restApi: RestApiService) { }

  ngOnInit() {
  }

  submit() {
    this.restApi.savePersona(this.personaForm.value).subscribe( (data: any) => {
      this.location.back();
    },
    (err: any) => {
      this.location.back();
    }
    );
  }

  back() {
    this.location.back();
  }

}
