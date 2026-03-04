import './App.css';
import Calculator from './Component/calculator';
import History from './Component/History';

function App() {
  return (
    <div className="App">
      <div className="calculator-container">
        <Calculator />
        <History />
      </div>
    </div>
  );
}

export default App;
