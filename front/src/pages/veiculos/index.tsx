import Head from "next/head";
import styles from "./styles.module.scss";
import { Header } from "../../components/Header";
import { Input } from "@/components/ui/Input";
import { FormEvent, useState, useEffect } from "react";
import { Button } from "@/components/ui/Button";
import { canSSRAuth } from "../../utils/canSSRAuth";
import { toast } from "react-toastify";
import { setupAPIClient } from "@/services/api";


type Clientes = {
  id: string;
  nome: string;
  cpf: string;
  email: string;
  telefone: string;

};
export default function Veiculos() {
  const [marca, setMarca] = useState("");
  const [modelo, setModelo] = useState("");
  const [ano, setAno] = useState("");
  const [clientes, setClientes] = useState<Clientes[]>([]);
  const [clientesSelecionado, setClientesSelecionado] = useState("");

  const apiClient = setupAPIClient();

  const fetchClientes = async () => {
    const response = await apiClient
      .get("/clientes")
      .catch((err) => console.log(err));

    if (response) {
      const clientes: Clientes[] = response.data;
      setClientes(clientes);
    }
  };

  useEffect(() => {
    fetchClientes();
  }, []);

  async function handleChangeClientes(event) {
    setClientesSelecionado(event.target.value);
    console.log(clientes[event.target.value]);

  }
  
  async function handleRegister(event: FormEvent) {
    event.preventDefault();

    if (marca === "" && modelo === "" && ano === "") {
      return;
    }

    const apiClient = setupAPIClient();
    
    await apiClient.post("/veiculos", {
      marca: marca,
      modelo: modelo,
      ano: ano,
      cliente_Id: clientes[clientesSelecionado].id,
    });

    toast.success("Veiculo cadastrado com sucesso.");
    setMarca("");
    setModelo("");
    setAno("");
  }

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

              <select value={clientesSelecionado} onChange={handleChangeClientes}>
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

            
              <Button  type="submit">
                Cadastrar
              </Button>
            </form>
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
