import Modal from "react-modal";
import styles from "./styles.module.scss";
import { useState, useEffect } from "react";
import { format } from "date-fns";
import "react-datepicker/dist/react-datepicker.css";
import Datepicker from "react-datepicker";
import { setupAPIClient } from "@/services/api";
import { Input } from "../ui/Input";

import { FiX } from "react-icons/fi";
type Clientes = {
  id: string;
  nome: string;
  cpf: string;
  email: string;
  telefone: string;
};
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
  cliente: Cliente[];
  cliente_id: string | null;
  editar: (id: string, dadosEditados: Clientes) => void; // Atualize o tipo aqui
  deletar: (id: string) => void;
};
export function ClienteModal({
  modalAberto,
  modalFechado,
  cliente,
  cliente_id,
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
  const [dadosEditados, setDadosEditados] = useState({} as Cliente);
  const clienteSelecionado = cliente.find((cli) => cli.id === cliente_id);
  
  const edicao = () => {
    setDadosEditados(clienteSelecionado);
    setEditando(true);
  };

  const lidarComSalvarEdicao = async () => {
    try {
      editar(cliente_id, dadosEditados); 
      setEditando(false);
      modalFechado();
    } catch (error) {
      console.error("Erro ao editar o cliente:", error);
    }
  };


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
          <section key={clienteSelecionado.id} className={styles.containerItem}>
            {editando ? (
              <div className={styles.cadastro}>
                <h2>Editando Cliente:</h2>
                <span className={styles.description}>
                  <strong>Nome: </strong>

                  <Input
                    placeholder=""
                    type="text"
                    value={dadosEditados.nome}
                    onChange={(e) =>
                      setDadosEditados({
                        ...dadosEditados,
                        nome: e.target.value,
                      })
                    }
                  />
                  <strong> CPF: </strong>
                  <Input
                    placeholder=""
                    type="text"
                    value={dadosEditados.cpf}
                    onChange={(e) =>
                      setDadosEditados({
                        ...dadosEditados,
                        cpf: e.target.value,
                      })
                    }
                  />
                  <strong> Email: </strong>
                  <Input
                    placeholder=""
                    type="text"
                    value={dadosEditados.email}
                    onChange={(e) =>
                      setDadosEditados({
                        ...dadosEditados,
                        email: e.target.value,
                      })
                    }
                  />
                  <strong> Telefone: </strong>
                  <Input
                    placeholder=""
                    type="text"
                    value={dadosEditados.telefone}
                    onChange={(e) =>
                      setDadosEditados({
                        ...dadosEditados,
                        telefone: e.target.value,
                      })
                    }
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

            <h2>Cliente: </h2>
            <span className={styles.description}>
              <strong>Nome: </strong>
              {clienteSelecionado.nome}
              <br />
              <strong>CPF/CNPJ: </strong>
              {clienteSelecionado.cpf}
              <br />
              <strong>Email: </strong>
              {clienteSelecionado.email}
              <br />
              <strong>Telefone: </strong>
              {clienteSelecionado.telefone}
              <br />
              <br />
            </span>
          </section>

          <button className={styles.buttonOrderEditar} onClick={edicao}>
            Editar cliente
          </button>
          <button
            className={styles.buttonOrder}
            onClick={() => deletar(cliente_id)}
          >
            Deletar cliente
          </button>
        </div>
      )}
    </Modal>
  );
}
