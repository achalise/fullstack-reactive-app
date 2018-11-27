import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ArticlesHomeComponent } from './articles-home/articles-home.component';

const routes: Routes = [
    { path: 'articles', component: ArticlesHomeComponent },
    { path: '', component: ArticlesHomeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
