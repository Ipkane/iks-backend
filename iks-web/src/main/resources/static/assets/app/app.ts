import {Component,OnInit} from 'angular2/core';
import { RouteConfig, ROUTER_DIRECTIVES, ROUTER_PROVIDERS } from 'angular2/router';
import { HTTP_PROVIDERS } from 'angular2/http';
import {Panel, Tree, TreeNode} from 'primeng/primeng';
import {NavService} from "./nav.service";

@Component({
    selector: 'app',
    templateUrl: 'assets/app/app.html',
    //template: '<div><ng-content></ng-content></div>',
    providers: [HTTP_PROVIDERS, NavService],
    directives: [Tree],
})
export class App implements OnInit {
    constructor(private _navService:NavService) {
    }

    navs:any[];

    ngOnInit() {
        this._navService.getNavs().then(navs=> {
            this.navs = navs;
        });
    }
}