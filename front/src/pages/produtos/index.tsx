import Head from "next/head";
import styles from "./styles.module.scss";
import { Header } from "../../components/Header";
import { FormEvent, useState } from "react";
import { Input } from "@/components/ui/Input";
import { Button } from "@/components/ui/Button";
import { toast } from "react-toastify";
import { setupAPIClient } from "@/services/api";

import { canSSRAuth } from "../../utils/canSSRAuth";

export default function Produtos() {
  const [nome, setNome] = useState("");
  const [descricaoProduto, setDescricao] = useState("");
  const [preco, setPreco] = useState("");

  async function handleRegister(event: FormEvent) {
    event.preventDefault();

    if (nome === "" && descricaoProduto === "" && preco === "") {
      return;
    }

    const apiClient = setupAPIClient();
    await apiClient.post("/produtos", {
      nome: nome,
      descricaoProduto: descricaoProduto,
      preco: preco,
    });

    toast.success("Produto cadastrado com sucesso.");
    setNome("");
    setDescricao("");
    setPreco("");
  }
  return (
    <>
      <Head>
        <title>Novo Produto - Auto Center</title>
      </Head>
      <div>
        <Header />
        <main className={styles.containerCenter}>
          <div className={styles.cadastro}>
          <h1>Novo Produto</h1>

          <form  onSubmit={handleRegister}>
            <Input
              type="text"
              placeholder="Nome do Produto"
              value={nome}
              onChange={(e) => setNome(e.target.value)}
            />

            <textarea
              placeholder="Descreva o produto..."
              className={styles.input}  
                          value={descricaoProduto}
              onChange={(e) => setDescricao(e.target.value)}
            />

            <Input
              type="text"
              placeholder="Preço R$ 000,00 "
              value={preco}
              onChange={(e) => setPreco(e.target.value)}
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
