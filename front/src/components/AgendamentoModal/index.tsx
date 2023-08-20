import Modal from "react-modal";
import styles from "./styles.module.scss";
import { useState, useEffect } from "react";
import { format } from "date-fns";
import "react-datepicker/dist/react-datepicker.css";
import Datepicker from "react-datepicker";
import { setupAPIClient } from "@/services/api";
import { Input } from "../ui/Input";

import { FiX } from "react-icons/fi";

import {
  Agendamento,
  Cliente,
  Produto,
  Servico,
  Veiculo,
} from "../../pages/dashboard";

type ModalProps = {
  modalAberto: boolean;
  modalFechado: () => void;
  agendamento: Agendamento[];
  cliente: Cliente[];
  veiculo: Veiculo[];
  servico: Servico[];
  produto: Produto[];
  agendamentoId: string | null;
  finalizarAgendamento: (id: string) => void;
  editar: (id: string, dadosEditados: Agendamento) => void;
  deletar: (id: string) => void;
};
export function AgendamentoModal({
  modalAberto,
  modalFechado,
  agendamento,
  cliente,
  produto,
  servico,
  veiculo,
  agendamentoId,
  finalizarAgendamento,
  editar,
  deletar,
}: ModalProps) {
  const customStyles = {
    content: {
      top: "50%",
      bottom: "auto",
      left: "50%",
      right: "auto",
      padding: "30px",
      transform: "translate(-50%, -50%)",
      backgroundColor: "#ffffff",
    },
  };

  const [editando, setEditando] = useState(false);
  const [dadosEditados, setDadosEditados] = useState({} as Agendamento);

  const edicao = () => {
    setDadosEditados(agendamentoSelecionado);
    setEditando(true);
  };

  const agendamentoSelecionado = agendamento.find(
    (ag) => ag.id === agendamentoId
  );

  const lidarComSalvarEdicao = async () => {
    try {
      editar(agendamentoId, dadosEditados);
      setEditando(false);
      modalFechado();
    } catch (error) {
      console.error("Erro ao editar o agendamento:", error);
    }
  };

  function formatInputAsDate(inputValue) {
    const numericValue = inputValue.replace(/\D/g, ""); 
    const day = numericValue.substring(0, 2);
    const month = numericValue.substring(2, 4);
    const year = numericValue.substring(4, 8);

    if (numericValue.length > 4) {
      return `${day}/${month}/${year}`;
    } else if (numericValue.length > 2) {
      return `${day}/${month}`;
    } else {
      return numericValue;
    }
  }

  function formatInputAsTime(inputValue) {
    const numericValue = inputValue.replace(/\D/g, ""); 
    const hours = numericValue.substring(0, 2);
    const minutes = numericValue.substring(2, 4);

    if (numericValue.length > 2) {
      return `${hours}:${minutes}`;
    } else {
      return numericValue;
    }
  }

  return (
    <Modal
      isOpen={modalAberto}
      onRequestClose={modalFechado}
      style={customStyles}
    >
      <button
        type="button"
        onClick={modalFechado}
        className="react-modal-close"
        style={{ background: "transparent", border: 0 }}
      >
        <FiX size={45} color="#f34748" />
      </button>
      {cliente && (
        <div>
          <section
            key={agendamentoSelecionado.id}
            className={styles.containerItem}
          >
            {editando ? (
              <div className={styles.cadastro}>
                <h2>Editando Agendamento:</h2>
                <span className={styles.description}>
                  <strong>Data: </strong>

                  <Input
                    placeholder="Nova data"
                    type="text"
                    value={dadosEditados.data}
                    onChange={(e) => {
                      const inputValue = e.target.value;
                      const formattedValue = formatInputAsDate(inputValue);
                      setDadosEditados({
                        ...dadosEditados,
                        data: formattedValue,
                      });
                    }}
                  />
                  <strong> Horário: </strong>
                  <Input
                    placeholder="Novo horário"
                    type="text"
                    value={dadosEditados.hora}
                    onChange={(e) => {
                      const inputValue = e.target.value;
                      const formattedValue = formatInputAsTime(inputValue);
                      setDadosEditados({
                        ...dadosEditados,
                        hora: formattedValue,
                      });
                    }}
                  />
                  <strong> Cliente: </strong>
                  <Input
                    placeholder=""
                    type="text"
                    value={dadosEditados.cliente.nome}
                    onChange={(e) =>
                      setDadosEditados({
                        ...dadosEditados,
                        cliente: {
                          ...dadosEditados.cliente,
                          nome: e.target.value,
                        },
                      })
                    }
                    disabled
                  />
                  <strong> Placa: </strong>
                  <Input
                    type="text"
                    value={dadosEditados.veiculo.placa}
                    onChange={(e) =>
                      setDadosEditados({
                        ...dadosEditados,
                        veiculo: {
                          ...dadosEditados.veiculo,
                          placa: e.target.value,
                        },
                      })
                    }
                    disabled
                  />
                  <strong> Serviço: </strong>

                  <Input
                    type="text"
                    value={dadosEditados.servico.descricaoServico}
                    onChange={(e) =>
                      setDadosEditados({
                        ...dadosEditados,
                        servico: {
                          ...dadosEditados.servico,
                          descricaoServico: e.target.value,
                        },
                      })
                    }
                    disabled
                  />
                </span>
                <button
                  className={styles.buttonOrder}
                  onClick={lidarComSalvarEdicao}
                >
                  Salvar
                </button>
              </div>
            ) : (
              <></>
            )}
            <h2>Agendamento:</h2>
            <span className={styles.description}>
              <strong>Data: </strong>
              {agendamentoSelecionado.data}
              <br />
              <strong>Horario: </strong>
              {agendamentoSelecionado.hora}
              <br />
              <br />
            </span>

            <h2>Cliente: </h2>
            <span className={styles.description}>
              <strong>Nome: </strong>
              {agendamentoSelecionado.cliente.nome}
              <br />
              <strong>CPF/CNPJ: </strong>
              {agendamentoSelecionado.cliente.cpf}
              <br />
              <strong>Email: </strong>
              {agendamentoSelecionado.cliente.email}
              <br />
              <strong>Telefone: </strong>
              {agendamentoSelecionado.cliente.telefone}
              <br />
              <br />
            </span>

            <h2>Veiculo: </h2>
            <span className={styles.description}>
              <strong>Modelo: </strong>
              {agendamentoSelecionado.veiculo.modelo}
              <br />
              <strong>Marca: </strong>
              {agendamentoSelecionado.veiculo.marca}
              <br />
              <strong>Ano: </strong>
              {agendamentoSelecionado.veiculo.ano}
              <br />
              <strong>Placa: </strong>
              {agendamentoSelecionado.veiculo.placa}
              <br />
              <br />
            </span>

            <h2> Serviço:</h2>
            <span className={styles.description}>
              <strong> Descrição: </strong>
              {agendamentoSelecionado.servico.descricaoServico}
              <br />

              <strong> Mão de Obra: </strong>
              {agendamentoSelecionado.servico.maoDeObra}
              <br />
            </span>

            <br />
            <h2> Produto:</h2>
            <span className={styles.description}>
              <strong> Nome: </strong>
              {agendamentoSelecionado.servico.produto.nome}
              <br />
              <strong> Descrição: </strong>
              {agendamentoSelecionado.servico.produto.descricaoProduto}
              <br />
              <strong> Preço: </strong> R$:
              {agendamentoSelecionado.servico.produto.preco}
            </span>
          </section>
          <button
            className={styles.buttonOrderDelete}
            onClick={() => finalizarAgendamento(agendamentoId)}
          >
            Concluir agendamento
          </button>
          <button className={styles.buttonOrderEditar} onClick={edicao}>
            Editar agendamento
          </button>
          <button
            className={styles.buttonOrder}
            onClick={() => deletar(agendamentoId)}
          >
            Deletar agendamento
          </button>
        </div>
      )}
    </Modal>
  );
}
