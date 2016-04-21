import {Injectable} from 'angular2/core';
import {Http, Response} from 'angular2/http';
import {Observable}     from 'rxjs/Rx';
import {Tree} from 'primeng/primeng';
import {TreeNode} from 'primeng/primeng';

@Injectable()
export class NavService {
    constructor(private http:Http) {
    }

    private _navUrl = 'api/core/getNav';  // URL to web api

    getNavs():Observable<any[]> {
        return this.http.get(this._navUrl)
            .toPromise()
            .then(res => <any[]> res.json().data.nav)
            .then(nav=>{
                var nodes = [];
                for (var i in nav) {
                    nodes.push({label:nav[i], data: nav[i]});
                }
                return nodes;
            });
    }

    private extractData(res:Response) {
        if (res.status < 200 || res.status >= 300) {
            throw new Error('Bad response status: ' + res.status);
        }
        let body = res.json();
        return body.data || {};
    }

    private handleError(error:any) {
        // In a real world app, we might send the error to remote logging infrastructure
        let errMsg = error.message || 'Server error';
        console.error(errMsg); // log to console instead
        return Observable.throw(errMsg);
    }
}