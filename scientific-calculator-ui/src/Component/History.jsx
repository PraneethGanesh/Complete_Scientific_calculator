import React, { useEffect, useState } from "react";
import axios from "axios";

const API_BASE = "http://localhost:8080/api/calculations";

export default function History({ userId = 1 }) {
  const [page, setPage] = useState(0);
  const [data, setData] = useState(null);
  const [error, setError] = useState(null);

  const load = async p => {
    try {
      setError(null);
      const res = await axios.get(`${API_BASE}/history/${userId}`, {
        params: { page: p, size: 5 }
      });
      setData(res.data);
      setPage(p);
    } catch (err) {
      const errorMsg = err.message === "Network Error" ? "Backend server is not running. Please start the backend on port 8080." : "Error loading history";
      setError(errorMsg);
      setData({ content: [] });
    }
  };

  useEffect(() => {
    load(0);
  }, []);

  if (!data) return <div>Loading...</div>;

  return (
    <div>
      <h3>Calculation History</h3>
      {error && <div style={{ color: "red", marginBottom: "10px" }}>{error}</div>}
      <table>
        <thead>
          <tr>
            <th>Operand1</th>
            <th>Operand2</th>
            <th>Operation</th>
            <th>Result</th>
            <th>Time</th>
          </tr>
        </thead>
        <tbody>
          {data.content.map(c => (
            <tr key={c.id}>
              <td>{c.operand1}</td>
              <td>{c.operand2}</td>
              <td>{c.operation}</td>
              <td>{c.result}</td>
              <td>{c.timestamp}</td>
            </tr>
          ))}
        </tbody>
      </table>

      <button disabled={page === 0} onClick={() => load(page - 1)}>
        Prev
      </button>
      <button
        disabled={data.last}
        onClick={() => load(page + 1)}
      >
        Next
      </button>
    </div>
  );
}