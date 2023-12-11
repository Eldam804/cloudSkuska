import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ObjectService {
  private baseUrl = 'http://localhost:8080'; // Update with your Spring Boot backend URL
  private topicName = '/topic/object-exchange';

  private ws!: WebSocket;

  constructor(private http: HttpClient) {
    this.initializeWebSocketConnection();
  }

  private initializeWebSocketConnection() {
    this.ws = new WebSocket('ws://localhost:15672/ws'); // RabbitMQ WebSocket URL

    this.ws.onopen = () => {
      // WebSocket is connected, you can subscribe to your topic here if needed
      this.ws.send(JSON.stringify({ command: 'subscribe', destination: this.topicName }));
    };

    this.ws.onmessage = (event) => {
      // Handle incoming messages from RabbitMQ here
      console.log('Received message:', event.data);
    };

    this.ws.onerror = (error) => {
      // Handle WebSocket errors here
      console.error('WebSocket error:', error);
    };

    this.ws.onclose = () => {
      // Handle WebSocket close event here if needed
      console.log('WebSocket closed');
    };
  }

  getAllObjects(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/api/objects`);
  }

  createObject(name: string, age: number): void {
    const objectData = { name, age };
    this.http.post(`${this.baseUrl}/api/object`, objectData).subscribe(() => {
      // After successfully creating the object, you can send a message to RabbitMQ topic here
      // Example: this.ws.send(JSON.stringify({ command: 'send', destination: this.topicName, body: 'object-created' }));
    });
  }
}