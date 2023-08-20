import Modal from "react-modal";
import styles from "./styles.module.scss";
import { useState, useEffect } from "react";
import { format } from "date-fns";
import "react-datepicker/dist/react-datepicker.css";
import Datepicker from "react-datepicker";
import { setupAPIClient } from "@/services/api";
import { Input } from "../ui/Input";

import { FiX } from "react-icons/fi";

type Veiculos = {
  id: string;
  marca: string;
  modelo: string;
  ano: string;
  placa: string;
  
};
import {

  Veiculo
} from "../../pages/veiculos";

type ModalProps = {
  modalAberto: boolean;
  modalFechado: () => void;
  veiculo: Veiculo[];
  veiculo_id: string | null;
  editar: (id: string, dadosEditados: Veiculos) => void; 
};
export function VeiculoModal({
  modalAberto,
  modalFechado,
  veiculo,
  veiculo_id,
  editar,
  
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

  const appElement = document.getElementById("root");
  Modal.setAppElement(appElement);

  const [editando, setEditando] = useState(false);
  const [dadosEditados, setDadosEditados] = useState({} as Veiculo);
  const veiculoSelecionado = veiculo.find((vei) => vei.id === veiculo_id);
  
  const edicao = () => {
    setDadosEditados(veiculoSelecionado);
    setEditando(true);
  };

  const lidarComSalvarEdicao = async () => {
    try {
      editar(veiculo_id, dadosEditados); 
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
      {veiculo && (
        <div>
          <section key={veiculoSelecionado.id} className={styles.containerItem}>
            {editando ? (
              <div className={styles.cadastro}>
                <h2>Editando Veiculo:</h2>
                <span className={styles.description}>

                  <strong>Marca: </strong>

                  <Input
                    placeholder=""
                    type="text"
                    value={dadosEditados.marca}
                    onChange={(e) =>
                      setDadosEditados({
                        ...dadosEditados,
                        marca: e.target.value,
                      })
                    }
                  />
                  <strong> Modelo: </strong>
                  <Input
                    placeholder=""
                    type="text"
                    value={dadosEditados.modelo}
                    onChange={(e) =>
                      setDadosEditados({
                        ...dadosEditados,
                        modelo: e.target.value,
                      })
                    }
                  />
                  <strong> Placa: </strong>
                  <Input
                    placeholder=""
                    type="text"
                    value={dadosEditados.placa}
                    onChange={(e) =>
                      setDadosEditados({
                        ...dadosEditados,
                        placa: e.target.value,
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

            <h2>Veiculo: </h2>
            <span className={styles.description}>
              <strong>Marca: </strong>
              {veiculoSelecionado.marca}
              <br />
              <strong>Modelo: </strong>
              {veiculoSelecionado.modelo}
              <br />
              <strong>Ano: </strong>
              {veiculoSelecionado.ano}
              <br />
              <strong>Placa: </strong>
              {veiculoSelecionado.placa}
              <br />
              <br />
            </span>
          </section>

          <button className={styles.buttonOrderEditar} onClick={edicao}>
            Editar cliente
          </button>
       
          
        </div>
      )}
    </Modal>
  );
}
