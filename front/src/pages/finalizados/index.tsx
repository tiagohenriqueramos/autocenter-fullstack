import { useState, useEffect } from "react";
import { canSSRAuth } from "../../utils/canSSRAuth";
import Head from "next/head";
import { Header } from "../../components/Header";
import styles from "./styles.module.scss";
import { FiRefreshCcw } from "react-icons/fi";
import { setupAPIClient } from "@/services/api";
import { FinalizadorModal } from "../../components/FinalizadorModal";
import Modal from "react-modal";
import React from "react";
import { Input } from "@/components/ui/Input";
import { StatusAgendamentos } from "@/enum/StatusAgendamentos";

export type Agendamento = {
  id: string;
  data: string;
  hora: string;
  status: StatusAgendamentos; 
  cliente: Cliente;
  servico: Servico;
  veiculo: Veiculo;

};

export type Cliente = {
  id: string;
  nome: string;
  cpf: string;
  email: string;
  telefone: string;
};

export type Veiculo = {
  id: string;
  marca: string;
  modelo: string;
  ano: string;
  placa:string;
  cliente_id: string;
};

export type Servico = {
  id: string;
  descricaoServico: string;
  maoDeObra: string;
  produto: Produto;
};

export type Produto = {
  id: string;
  nome: string;
  descricaoProduto: string;
  preco: string;
};

export default function Finalizados() {
  const [cliente, setCliente] = useState<Cliente[]>([]);
  const [agendamentoList, setAgendamentoList] = useState<Agendamento[]>([]);
  const [servico, setServico] = useState<Servico[]>([]);
  const [produto, setProduto] = useState<Produto[]>([]);
  const [veiculo, setVeiculo] = useState<Veiculo[]>([]);
  const [modalAberto, setModalAberto] = useState(false);
  const [clienteSelecionado, setClienteSelecionado] = useState<Cliente | null>(
    null
  );
  const [agendamentoSelecionadoId, setAgendamentoSelecionadoId] = useState<
    string | null
  >(null);
  const [showFiltros, setShowFiltros] = useState(false); 
  const [showSearchCliente, setShowSearchCliente] = useState(false);
  const [showSearchData, setShowSearchData] = useState(false);
  const [showSearchHorario, setShowSearchHorario] = useState(false);

  const [busca, setBusca] = useState("");
  const [filtroData, setFiltroData] = useState("");
  const [filtroHorario, setFiltroHorario] = useState("");
  const apiClient = setupAPIClient();

  useEffect(() => {
    const fetchProduto = async () => {
      const response = await apiClient
        .get("/produtos")
        .catch((err) => console.log(err));
      if (response) {
        const produto: Produto[] = response.data;
        setProduto(produto);
      }
    };

    const fetchCliente = async () => {
      const response = await apiClient
        .get("/clientes")
        .catch((err) => console.log(err));
      if (response) {
        const cliente: Cliente[] = response.data;
        setCliente(cliente);
      }
    };

    const fetchServico = async () => {
      const response = await apiClient
        .get("/servicos")
        .catch((err) => console.log(err));
      if (response) {
        const servico: Servico[] = response.data;
        setServico(servico);
      }
    };

    const fetchAgendamento = async () => {
      const response = await apiClient
        .get("/agendamentos/concluidos")
        .catch((err) => console.log(err));
      if (response) {
        const agendamento: Agendamento[] = response.data;
        setAgendamentoList(agendamento);
        console.log(response)
      }
    };

    const fetchVeiculo = async () => {
      const response = await apiClient
        .get("/veiculos")
        .catch((err) => console.log(err));
      if (response) {
        const veiculo: Veiculo[] = response.data;
        setVeiculo(veiculo);
      }
    };

    fetchProduto();
    fetchCliente();
    fetchServico();
    fetchAgendamento();
    fetchVeiculo();
  }, []);

  const selecionarAgendamento = async (id: string) => {
    setAgendamentoSelecionadoId(id);
    console.log(id);

    try {
      const response = await apiClient.get(`/clientes/${id}`);
      const cliente: Cliente = response.data;
      setClienteSelecionado(cliente);
    } catch (error) {
      console.log(error);
    }

    abrirModal();
  };

  const abrirModal = () => {
    setModalAberto(true);
  };

  const fecharModal = () => {
    setModalAberto(false);
    setClienteSelecionado(null);
  };


  

  Modal.setAppElement("#__next");
  return (
    <>
      <Head>
        <title>Painel - Auto Center</title>
      </Head>

      <div>
        <Header />
        <main className={styles.container}>
          <h1>Agendamentos Concluidos</h1>
          <div className={`${styles.containerHeader} ${styles.filtros}`}>
            <label>
              <input
                type="checkbox"
                checked={showFiltros}
                onChange={() => setShowFiltros(!showFiltros)}
              />
              Filtro de busca
            </label>

            {showFiltros && (
              <div className={styles.filtroContainer}>
                <div className={styles.filtroItem}>
                  <label>
                    <input
                      type="checkbox"
                      checked={showSearchCliente}
                      onChange={() => setShowSearchCliente(!showSearchCliente)}
                    />
                    Buscar por Nome
                  </label>
                  {showSearchCliente && (
                    <Input
                      type="search"
                      value={busca}
                      onChange={(e) => setBusca(e.target.value)}
                      placeholder="Filtrar por nome"
                    />
                  )}
                </div>

                <div className={styles.filtroItem}>
                  <label>
                    <input
                      type="checkbox"
                      checked={showSearchData}
                      onChange={() => setShowSearchData(!showSearchData)}
                    />
                    Buscar por Data
                  </label>
                  {showSearchData && (
                    <Input
                      type="text"
                      value={filtroData}
                      onChange={(e) => setFiltroData(e.target.value)}
                      placeholder="Filtrar por data"
                    />
                  )}
                </div>

                <div className={styles.filtroItem}>
                  <label>
                    <input
                      type="checkbox"
                      checked={showSearchHorario}
                      onChange={() => setShowSearchHorario(!showSearchHorario)}
                    />
                    Buscar por Horario
                  </label>
                  {showSearchHorario && (
                    <Input
                      type="text"
                      value={filtroHorario}
                      onChange={(e) => setFiltroHorario(e.target.value)}
                      placeholder="Filtrar por horario"
                    />
                  )}
                </div>
              </div>
            )}
          </div>

          <article className={styles.listAgendamentos}>
            {agendamentoList.map((item) => (
              <section key={item.id} className={styles.agendamento}>
                <button onClick={() => selecionarAgendamento(item.id)}>
                  <div className={styles.tag}></div>
                  <span>Cliente: {item.cliente.nome}</span>
                </button>
              </section>
            ))}
          </article>
        </main>
        {modalAberto && (
          <FinalizadorModal
          modalAberto={modalAberto}
          modalFechado={fecharModal}
          agendamento={agendamentoList}
          cliente={cliente}
          veiculo={veiculo}
          servico={servico}
          produto={produto}
          agendamentoId={agendamentoSelecionadoId}
         
            />
            )}
          
      </div>

    </>
  );
}

export const getServerSideProps = canSSRAuth(async (context) => {
  return {
    props: {},
  };
});