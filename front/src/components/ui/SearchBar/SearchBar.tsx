import React, { useState } from "react";

const SearchBar = ({ onSearch }) => {
  const [clienteId, setClienteId] = useState("");
  const [servicoId, setServicoId] = useState("");
  const [veiculoId, setVeiculoId] = useState("");

  const handleSearch = () => {
    const searchData = {
      clienteId,
      servicoId,
      veiculoId,
    };

    onSearch(searchData);
  };

  return (
    <div>
      <input
        type="text"
        placeholder="ID do cliente"
        value={clienteId}
        onChange={(e) => setClienteId(e.target.value)}
      />

      <input
        type="text"
        placeholder="ID do serviço"
        value={servicoId}
        onChange={(e) => setServicoId(e.target.value)}
      />

      <input
        type="text"
        placeholder="ID do veículo"
        value={veiculoId}
        onChange={(e) => setVeiculoId(e.target.value)}
      />

      <button onClick={handleSearch}>Buscar</button>
    </div>
  );
};

export default SearchBar;
