import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  template: `
    
    <div style="margin-left: 100px">
      <h2>
        {{counter}}
      </h2>
    
      <div>
        <button (click)="incrementCounter()" (mouseout)="resetCounter()" class="style-button">Click</button>
      </div>
    </div>

    `,
  styles: [`
  .style-button {
    display: inline-block;
    border-radius: 4px;
    background-color: #f4511e;
    border: none;
    color: #FFFFFF;
    text-align: center;
    font-size: 28px;
    padding: 20px;
    width: 200px;
    transition: all 0.5s;
    cursor: pointer;
    margin: 5px;
  }
  `]
})
export class AppComponent implements OnInit {
  
  private counter : number;

  constructor() {
  }

  ngOnInit(): void {
    this.resetCounter();
  }

  public resetCounter() {
    this.counter = 42;
  }

  public incrementCounter() {
    this.counter++;
  }

}
