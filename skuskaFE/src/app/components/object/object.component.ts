import { Component, OnInit } from '@angular/core';
import { ObjectService } from 'src/app/common/object.service';

@Component({
  selector: 'app-object',
  templateUrl: './object.component.html',
  styleUrls: ['./object.component.css']
})
export class ObjectComponent implements OnInit {
  newObjectName = '';
  newObjectAge: number | undefined;
  objects: any[] = [];

  constructor(private objectService: ObjectService) {}

  ngOnInit(): void {
    // Load objects when the component initializes
    this.loadObjects();
  }

  onCreateObject(): void {
    if (this.newObjectName && this.newObjectAge !== undefined) {
      // Call the service method to create an object
      this.objectService.createObject(this.newObjectName, this.newObjectAge);

      // Clear the form fields
      this.newObjectName = '';
      this.newObjectAge = undefined;
    }
  }

  private loadObjects(): void {
    // Call the service method to get all objects
    this.objectService.getAllObjects().subscribe((data) => {
      this.objects = data;
    });
  }
}
