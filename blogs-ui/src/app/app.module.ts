import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ArticlesHomeComponent } from './articles-home/articles-home.component';

@NgModule({
    declarations: [
        AppComponent,
        ArticlesHomeComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule
    ],
    providers: [],
    bootstrap: [ AppComponent ]
})
export class AppModule {
}
