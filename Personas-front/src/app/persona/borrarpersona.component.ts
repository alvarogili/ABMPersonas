import { Component, Input } from '@angular/core';
import { RestApiService } from '../shared/rest-api.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-borrar-persona-component',
  template: `
  <div class="modal-header">
  <h4 class="modal-title">Eliminar Persona!</h4>
  <button type="button" class="close" aria-label="Close" (click)="activeModal.dismiss('Cross click')">
    <span aria-hidden="true">&times;</span>
  </button>
</div>
<div class="modal-body">
  <p>¿Desea eliminar a {{persona.perNombre}} {{persona.perApellido}}?</p>
</div>
<div class="modal-footer">
  <button type="button" class="btn btn-danger" (click)="delete(persona.perId)" >Eliminar</button>
</div>
`
})
export class BorrarPersonaComponent {

  @Input() persona;

constructor(public activeModal: NgbActiveModal, protected restApi: RestApiService) {}

  delete(id: any) {
    this.restApi.deletePersona(id).subscribe(
      (data: any) => {
        this.activeModal.dismiss();
        this.restApi.actualizarMensaje('borrado');
      },
      (err: any) => {
        this.activeModal.dismiss();
        this.restApi.actualizarMensaje('borrado');
      }
    );
  }

}
