//noinspection TypeScriptCheckImport
import {Component} from 'angular2/core';
@Component({
    // Declare the tag name in index.html to where the component attaches
    selector: 'app',
    // Location of the template for this component
    templateUrl: 'assets/app/hello_world.html'
})
export class App {
    // Declaring the variable for binding with initial value
    yourName: string = '';
}