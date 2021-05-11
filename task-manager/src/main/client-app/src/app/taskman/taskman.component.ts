import { Component, OnInit } from '@angular/core';

@Component({
	selector: 'app-taskman',
	templateUrl: './taskman.component.html',
	styleUrls: ['./taskman.component.css']
})
export class TaskmanComponent implements OnInit {
	collapsed = true;

	constructor() { }

	ngOnInit(): void {

	}

	toggleCollapsed(): void {
		this.collapsed = !this.collapsed;
	}
	

}
