import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs/index';
import { takeUntil } from 'rxjs/internal/operators';
import { Article } from '../models/article.model';
import { ArticleService } from '../services/article.service';

@Component({
    selector: 'app-articles-home',
    templateUrl: './articles-home.component.html',
    styleUrls: [ './articles-home.component.scss' ]
})
export class ArticlesHomeComponent implements OnInit, OnDestroy {
    articles: Article[];
    cancel = new Subject<void>();

    constructor(private articleService: ArticleService) {
    }

    ngOnInit() {
        this.articleService.retrieveAllArticles().pipe(takeUntil(this.cancel)).subscribe(
            articles => this.articles = articles,
            err => console.log(`Error in retrieving articles`),
            () => console.log(`Completed articles retrieval`, this.articles)
        );
    }

    ngOnDestroy() {
        this.cancel.next();
    }

}
