import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TablaComponent } from './tabla/tabla.component';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { HttpClientModule } from '@angular/common/http';
import { RestApiService } from './shared/rest-api.service';
import { PersonaComponent } from './persona/persona.component';
import { ReactiveFormsModule } from '@angular/forms';
import { BorrarPersonaComponent } from './persona/borrarpersona.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    AppComponent,
    TablaComponent,
    PersonaComponent,
    BorrarPersonaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    Ng2SmartTableModule,
    HttpClientModule,
    ReactiveFormsModule,
    NgbModule.forRoot(),
  ],
  entryComponents: [
    BorrarPersonaComponent
  ],
  providers: [RestApiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
