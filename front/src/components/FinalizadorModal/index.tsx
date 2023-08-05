import Modal from "react-modal";
import styles from "./styles.module.scss";
import NotaFiscal from "../NotaFiscal";
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
};
export function FinalizadorModal({
  modalAberto,
  modalFechado,
  agendamento,
  cliente,
  produto,
  servico,
  veiculo,
  agendamentoId,
  
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
  const agendamentoSelecionado = agendamento.find(
    (ag) => ag.id === agendamentoId
  );

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
            <h2>Dados do Agendamento</h2>
            <br />

            <h2>Agendamento:</h2>
            <span className={styles.description}>
              <strong>Data: </strong>
              {agendamentoSelecionado.data}
              <br />
              <strong>Horario: </strong>
              {agendamentoSelecionado.horario}
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
         
        </div>
      )}
      <NotaFiscal   notaFiscalId={agendamentoId}/>
    </Modal>
  );
}
