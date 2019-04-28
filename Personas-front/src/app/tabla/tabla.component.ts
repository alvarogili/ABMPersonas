import { Component, OnInit } from '@angular/core';
import { Ng2SmartTableModule, LocalDataSource } from 'ng2-smart-table';
import { HttpClientModule } from '@angular/common/http';
import { RestApiService } from '../shared/rest-api.service';
import { Persona } from '../Persona';
import 'rxjs/operators/map';

@Component({
  selector: 'app-tabla',
  templateUrl: './tabla.component.html',
  styleUrls: ['./tabla.component.css']
})
export class TablaComponent implements OnInit {

  settings = {
    columns: {
      perId: {
        title: 'Id',
        editable: false,
        addable: false,
      },
      perNombre: {
        title: 'Nombre'
      },
      perApellido: {
        title: 'Apellido'
      },
      perFechaNacimiento: {
        title: 'Fecha de nacimiento'
      },
      perTipoDocumento: {
        title: 'Tipo de documento'
      },
      perNumeroDocumento: {
        title: 'Número de documento'
      }
    }
  };

  source: any;

  constructor(protected restApi: RestApiService) {
    this.source = new LocalDataSource();

    this.loadAll();
}


  ngOnInit() {
    this.loadAll();
  }

  loadAll() {
    this.source = this.restApi.getPersonas().subscribe((data) => {
      this.source = data;
    });
  }

}
