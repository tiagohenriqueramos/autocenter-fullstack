import { useState, FormEvent } from "react";
import Head from "next/head";
import Router from "next/router";
import { Header } from "@/components/Header";
import styles from "./styles.module.scss";
import { Input } from "@/components/ui/Input";
import { Button } from "@/components/ui/Button";
import { setupAPIClient } from "@/services/api";
import { toast } from "react-toastify";
import { canSSRAuth } from "@/utils/canSSRAuth";

export default function Clientes() {
  const [nome, setNome] = useState("");
  const [email, setEmail] = useState("");
  const [cpf, setCpf] = useState("");
  const [telefone, setTelefone] = useState("");

  async function handleRegister(event: FormEvent) {
    event.preventDefault();
    if (nome === "" || email === "" || cpf === "" || telefone === "") {
      toast.error("Preencha todos os campos!");
      return;
    }
    const apiClient = setupAPIClient();
    await apiClient.post("/clientes", {
      nome: nome,
      email: email,
      cpf: cpf,
      telefone: telefone,
    });

    toast.success("Cliente cadastrado com sucesso!");
    setNome(""), setEmail(""), setCpf(""), setTelefone("");
  }

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
              <Input
                placeholder="Digite o CPF"
                type="text"
                value={cpf}
                onChange={(e) => setCpf(e.target.value)}
              />

              <Input
                placeholder="Digite o Telefone"
                type="text"
                value={telefone}
                onChange={(e) => setTelefone(e.target.value)}
              />

              <Button type="submit">Cadastrar</Button>
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
