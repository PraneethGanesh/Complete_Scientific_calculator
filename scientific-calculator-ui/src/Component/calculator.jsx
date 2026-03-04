import React, { useState } from "react";
import axios from "axios";

const API_BASE = "http://localhost:8080/api/calculations";

export default function Calculator() {
  const [operand1, setOperand1] = useState("");
  const [operand2, setOperand2] = useState("");
  const [operation, setOperation] = useState("ADD");
  const [result, setResult] = useState(null);
  const [error, setError] = useState(null);

  const handleCalculate = async () => {
    try {
      setError(null);
      const response = await axios.post(API_BASE, {
        operand1: parseFloat(operand1),
        operand2: operand2 === "" ? null : parseFloat(operand2),
        operation: operation,
        userId: 1   // demo user id
      });
      setResult(response.data.result);
    } catch (err) {
      setError(err.message === "Network Error" ? "Backend server is not running. Please start the backend on port 8080." : err.response?.data || "Error calling API");
    }
  };

  return (
    <div>
      <h2>Scientific Calculator</h2>

      <input
        type="number"
        value={operand1}
        onChange={e => setOperand1(e.target.value)}
        placeholder="Operand 1"
      />

      {/* Hide second operand for unary operations */}
      {["SIN", "COS", "TAN", "SQRT"].includes(operation) ? null : (
        <input
          type="number"
          value={operand2}
          onChange={e => setOperand2(e.target.value)}
          placeholder="Operand 2"
        />
      )}

      <select value={operation} onChange={e => setOperation(e.target.value)}>
        <option value="ADD">+</option>
        <option value="SUB">−</option>
        <option value="MUL">×</option>
        <option value="DIV">÷</option>
        <option value="SIN">sin</option>
        <option value="COS">cos</option>
        <option value="TAN">tan</option>
        <option value="SQRT">√</option>
      </select>

      <button onClick={handleCalculate}>Calculate</button>

      {result !== null && <div>Result: {result}</div>}
      {error && <div style={{ color: "red" }}>{error}</div>}
    </div>
  );
}