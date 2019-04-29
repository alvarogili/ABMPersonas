import { Component, OnInit, ViewChild } from '@angular/core';
import { RestApiService } from '../shared/rest-api.service';
import { Filtro } from '../Filtro';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { BorrarPersonaComponent } from '../persona/borrarpersona.component';
import { Persona } from '../Persona';

@Component({
  selector: 'app-tabla',
  templateUrl: './tabla.component.html',
  styleUrls: ['./tabla.component.css']
})
export class TablaComponent implements OnInit {


  dataSource: any;

  constructor(protected restApi: RestApiService, private ngbModal: NgbModal) {

    this.loadAll();
}


  ngOnInit() {
    this.restApi.accion.subscribe(x => {
      if (x === 'borrado') {
        this.loadAll();
      }
    });
    this.loadAll();
  }

  loadAll() {
    this.restApi.getPersonas(new Filtro('', '')).subscribe((data) => {
      this.dataSource = data;
    });
  }

  openModal(persona: Persona) {
    const modal = this.ngbModal.open(BorrarPersonaComponent);
    modal.componentInstance.persona = persona;
    this.loadAll();
  }

}
