import Head from "next/head";
import styles from "./styles.module.scss";
import { Header } from "../../components/Header";
import { Input } from "@/components/ui/Input";
import { FormEvent, useState, useEffect } from "react";
import { Button } from "@/components/ui/Button";
import { canSSRAuth } from "../../utils/canSSRAuth";
import { toast } from "react-toastify";
import { setupAPIClient } from "@/services/api";
import { VeiculoModal } from "@/components/VeiculoModal";
import Modal from "react-modal";
import InputMask from "react-input-mask";


type Clientes = {
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
  placa: string;
  cliente: Clientes;
};
export default function Veiculos() {
  const [marca, setMarca] = useState("");
  const [modelo, setModelo] = useState("");
  const [ano, setAno] = useState("");
  const [placa, setPlaca] = useState("");

  const [clientes, setClientes] = useState<Clientes[]>([]);
  const [clientesSelecionado, setClientesSelecionado] = useState("");

  const [veiculo, setVeiculo] = useState<Veiculo[]>([]);
  const [veiculoSelecionado, setVeiculoSelecionado] = useState<Veiculo | null>(
    null
  );

  const [modalAberto, setModalAberto] = useState(false);
  const [busca, setBusca] = useState("");

  const apiClient = setupAPIClient();

  const selecionarVeiculo = async (id: string) => {
    try {
      const response = await apiClient.get(`/veiculos/${id}`);
      const veiculo: Veiculo = response.data;
      setVeiculoSelecionado(veiculo);
    } catch (error) {
      console.log(error);
    }

    abrirModal();
  };


  const fetchCliente = async () => {
    const response = await apiClient
      .get("/clientes")
      .catch((err) => console.log(err));

    if (response) {
      const clientes: Clientes[] = response.data;
      setClientes(clientes);
    }
  };

  useEffect(() => {
    fetchCliente();
  }, []);

  const fetchVeiculo = async () => {
    const response = await apiClient
      .get("/veiculos")
      .catch((err) => console.log(err));

    if (response) {
      const veiculo: Veiculo[] = response.data;
      setVeiculo(veiculo);
    }
  };

  useEffect(() => {
    fetchVeiculo();
  }, []);

  async function handleChangeClientes(event) {
    setClientesSelecionado(event.target.value);
    console.log(clientes[event.target.value]);
  }

  async function handleRegister(event: FormEvent) {
    event.preventDefault();

    if (marca === "" && modelo === "" && ano === "" && placa === "") {
      return;
    }

    await apiClient.post("/veiculos", {
      marca: marca,
      modelo: modelo,
      ano: ano,
      placa: placa,
      cliente: {
        id: clientes[clientesSelecionado].id,
      },
    });

    toast.success("Veiculo cadastrado com sucesso.");
    setMarca("");
    setModelo("");
    setAno("");
    setPlaca("");
    setClientesSelecionado("");
    const response = await apiClient.get("/veiculos");
    const veiculo: Veiculo[] = response.data;
    setVeiculo(veiculo);


  }

  const abrirModal = () => {
    setModalAberto(true);
  };

  const fecharModal = () => {
    setModalAberto(false);
    setVeiculoSelecionado(null);
  };
 
  const editar = async (id, dadosEditados) => {
    try {
      const apiClient = setupAPIClient();

      await apiClient.put(`/veiculos/${id}`, dadosEditados);

      const response = await apiClient.get("/veiculos");
      const veiculo: Veiculo[] = response.data;
      toast.success("Editado com sucesso!");
      console.log("Dados que serão enviados:", response);

      console.log("Chamando função editar com ID:", id);
      console.log("Dados editados:", dadosEditados);

      setVeiculo(veiculo);
    } catch (error) {
      console.error("Erro ao editar o agendamento:", error);
    }

    setModalAberto(false);
  };

  const deletar = async (id) => {
    try {
      const apiClient = setupAPIClient();
      await apiClient.delete(`/veiculos/${id}`);
      const response = await apiClient.get("/veiculos");
      const veiculo: Veiculo[] = response.data;

      toast.error("Deletado com sucesso!");

      setVeiculo(veiculo);
    } catch (error) {
      console.error("Erro ao deletar o agendamento:", error);
    }

    setModalAberto(false);
  };

  const handlePlacaChange = (e) => {
    setPlaca(e.target.value.toUpperCase());
  };
  Modal.setAppElement("#__next");

  return (
    <>
      <Head>
        <title>Veiculos - Auto Center</title>
      </Head>
      <div>
        <Header />
        <main className={styles.containerCenter}>
          <div className={styles.cadastro}>
            <h1>Cadastrar Veiculos</h1>

            <form onSubmit={handleRegister}>
              <select
                value={clientesSelecionado}
                onChange={handleChangeClientes}
              >
                <option value="" disabled defaultValue="">
                  Selecione um cliente
                </option>
                
                {clientes.map((item, index) => {
                  return (
                    <option key={item.id} value={index}>
                      {item.nome}
                    </option>
                  );
                })}
              </select>
              <Input
                type="text"
                placeholder="Digite a marca"
                value={marca}
                onChange={(e) => setMarca(e.target.value)}
              />

              <Input
                type="text"
                placeholder="Modelo"
                value={modelo}
                onChange={(e) => setModelo(e.target.value)}
              />

              <Input
                type="text"
                placeholder="Ano"
                value={ano}
                onChange={(e) => setAno(e.target.value)}
              />

              <InputMask
                placeholder="Placa"
                mask="aaa-9999"
                value={placa}
                onChange={handlePlacaChange}
                >
                {(inputProps) => <Input {...inputProps} />}
              </InputMask>


              <Button type="submit">Cadastrar</Button>
            </form>
            <main className={styles.containerCenter}>
              <div className={styles.cadastro}>
                <h1>Veiculos</h1>
                <Input
                  type="search"
                  value={busca}
                  onChange={(e) => setBusca(e.target.value)}
                  placeholder="Filtro de Veiculo"
                />
                <article className={styles.listClientes}>
                  {veiculo
                    .filter((item) =>
                      item.marca.toLowerCase().includes(busca.toLowerCase())
                    )
                    .map((item) => (
                      <section key={item.id} className={styles.clientes}>
                        <button onClick={() => selecionarVeiculo(item.id)}>
                          <div className={styles.tag}></div>
                          <span>
                            Veiculo: {item.marca} {item.modelo}. Cliente:{" "}
                            {item.cliente.nome}
                          </span>
                        </button>
                      </section>
                    ))}
                </article>
                {modalAberto && (
                  <VeiculoModal
                    modalAberto={modalAberto}
                    modalFechado={fecharModal}
                    veiculo={veiculo}
                    veiculo_id={veiculoSelecionado.id}
                    editar={editar}
                  />
                )}
              </div>
            </main>
          </div>
        </main>
      </div>
    </>
  );
}

export const getServerSideProps = canSSRAuth(async (ctx) => {
  return {
    props: {},
  };
});
