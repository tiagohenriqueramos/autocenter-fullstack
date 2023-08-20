import { useState, useEffect, FormEvent } from "react";
import Head from "next/head";
import { Header } from "@/components/Header";
import styles from "./styles.module.scss";
import { Input } from "@/components/ui/Input";
import { Button } from "@/components/ui/Button";
import { setupAPIClient } from "@/services/api";
import { toast } from "react-toastify";
import { canSSRAuth } from "@/utils/canSSRAuth";
import { ClienteModal } from "@/components/ClienteModal";
import React from "react";
import Modal from "react-modal";
import InputMask from "react-input-mask";

export type Cliente = {
  id: string;
  nome: string;
  cpf: string;
  email: string;
  telefone: string;
};

export default function Clientes() {
  const [nome, setNome] = useState("");
  const [email, setEmail] = useState("");
  const [cpf, setCpf] = useState("");
  const [telefone, setTelefone] = useState("");

  const [cliente, setCliente] = useState<Cliente[]>([]);
  const [clienteSelecionado, setClienteSelecionado] = useState<Cliente | null>(
    null
  );

  const [modalAberto, setModalAberto] = useState(false);
  const [busca, setBusca] = useState("");

  const apiClient = setupAPIClient();
  
  const selecionarCliente = async (id: string) => {
    try {
      const response = await apiClient.get(`/clientes/${id}`);
      const cliente: Cliente = response.data;
      setClienteSelecionado(cliente);
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
      const cliente: Cliente[] = response.data;
      setCliente(cliente);
    }
  };

  useEffect(() => {
    fetchCliente();
  }, []);

  async function handleRegister(event: FormEvent) {
    event.preventDefault();
    if (nome === "" || email === "" || cpf === "" || telefone === "") {
      toast.error("Preencha todos os campos!");
      return;
    }
    await apiClient.post("/clientes", {
      nome: nome,
      email: email,
      cpf: cpf,
      telefone: telefone,
    });

    toast.success("Cliente cadastrado com sucesso!");

    setNome(""), setEmail(""), setCpf(""), setTelefone("");
    const response = await apiClient.get("/clientes");
    const cliente: Cliente[] = response.data;
    setCliente(cliente);
  }

  const abrirModal = () => {
    setModalAberto(true);
  };

  const fecharModal = () => {
    setModalAberto(false);
    setClienteSelecionado(null);
  };

  const editar = async (id, dadosEditados) => {
    try {
      const apiClient = setupAPIClient();

      await apiClient.put(`/clientes/${id}`, dadosEditados);

      const response = await apiClient.get("/clientes");
      const cliente: Cliente[] = response.data;
      toast.success("Editado com sucesso!");
      console.log("Dados que serão enviados:", response);

      console.log("Chamando função editar com ID:", id);
      console.log("Dados editados:", dadosEditados);

      setCliente(cliente);
    } catch (error) {
      console.error("Erro ao editar o agendamento:", error);
    }

    setModalAberto(false);
  };

  const deletar = async (id) => {
    try {
      const apiClient = setupAPIClient();
      await apiClient.delete(`/clientes/${id}`);
      const response = await apiClient.get("/clientes");
      const cliente: Cliente[] = response.data;

      toast.error("Deletado com sucesso!");

      setCliente(cliente);
    } catch (error) {
      console.error("Erro ao deletar o agendamento:", error);
    }

    setModalAberto(false);
  };

  Modal.setAppElement("#__next");
  return (
    <>
      <Head>
        <title>Cliente - Auto Center</title>
      </Head>
      <div>
        <Header />
        <main className={styles.containerCenter}>
          <div className={styles.cadastro}>
            <h1>Cadastrar Cliente</h1>

            <form onSubmit={handleRegister}>
              <Input
                placeholder="Digite seu nome"
                type="text"
                value={nome}
                onChange={(e) => setNome(e.target.value)}
              />
              <Input
                placeholder="Digite o email"
                type="text"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
              <InputMask
                placeholder="Digite o CPF"
                mask="999.999.999-99"
                value={cpf}
                onChange={(e) => setCpf(e.target.value)}
              >
                {(inputProps) => <Input {...inputProps} />}
              </InputMask>

              <InputMask
                placeholder="Digite o Telefone"
                mask="(99) 9 9999-9999"
                value={telefone}
                onChange={(e) => setTelefone(e.target.value)}
              >
                {(inputProps) => <Input {...inputProps} />}
              </InputMask>

              <Button type="submit">Cadastrar</Button>
            </form>
            <main className={styles.containerCenter}>
              <div className={styles.cadastro}>
                <h1>Clientes</h1>
                <Input
                  type="search"
                  value={busca}
                  onChange={(e) => setBusca(e.target.value)}
                  placeholder="Filtro de nome"
                />
                <article className={styles.listClientes}>
                  {cliente
                    .filter((item) =>
                      item.nome.toLowerCase().includes(busca.toLowerCase())
                    )
                    .map((item) => (
                      <section key={item.id} className={styles.clientes}>
                        <button onClick={() => selecionarCliente(item.id)}>
                          <div className={styles.tag}></div>
                          <span>Cliente: {item.nome}</span>
                        </button>
                      </section>
                    ))}
                </article>

                {modalAberto && (
                  <ClienteModal
                    modalAberto={modalAberto}
                    modalFechado={fecharModal}
                    cliente={cliente}
                    cliente_id={clienteSelecionado.id}
                    editar={editar}
                    deletar={deletar}
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
