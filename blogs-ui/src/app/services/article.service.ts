import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/index';
import { map } from 'rxjs/internal/operators';
import { Article } from '../models/article.model';

@Injectable({
    providedIn: 'root'
})
export class ArticleService {

    constructor(private httpClient: HttpClient) {
    }

    retrieveAllArticles(): Observable<Article[]> {
        return this.httpClient.get<Article[]>(`api/articles`).pipe(map(d => this.buildFromBody(d)));
    }

    private buildFromBody(body: any[]) {
        let reduced = body.reduce((accum, current: Article) => {
            accum.push(new Article(current.id, current.title, current.userId, current.createdOn, current.summary));
            return accum;
        }, []);
        return reduced;
    }
}
