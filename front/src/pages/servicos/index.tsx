import {
  useState,
  FormEvent,

  useEffect,
} from "react";
import Head from "next/head";
import { Header } from "@/components/Header";
import { Input } from "@/components/ui/Input";
import styles from "./styles.module.scss";
import { Button } from "@/components/ui/Button";
import { setupAPIClient } from "@/services/api";
import { toast } from "react-toastify";
import { canSSRAuth } from "@/utils/canSSRAuth";


type Produto = {
  id: number,
  nome: string,
  descricaoProduto: string,
  preco: number;

}



export default function Servicos() {
  
  const [descricaoServico, setDescricaoServico] = useState('');
  const [maoDeObra, setMaoDeObra] = useState('');
  const [produto, setProduto] = useState<Produto[]>([]);
  const [produtoSelecionado, setProdutoSelecionado] = useState("");
  
 

  const apiClient = setupAPIClient();

  const fetchProduto = async() =>{
    const response = await apiClient.get('/produtos').catch(err=> console.log(err));

    if(response){
      const produto: Produto[] = response.data;
      setProduto(produto)
    }
  }
  useEffect(() => {
    fetchProduto();
      
  }, []);

  

 async function handleChangeProduto(event: any) {
  
    setProdutoSelecionado(event.target.value);
    console.log(produto[event.target.value])
  }

  async function handleRegister(event: FormEvent) {
    event.preventDefault();
  
    try {
      if (!descricaoServico.trim() || !maoDeObra.trim()) {
        toast.error('Preencha todos os campos');
        return;
      }
  
      const data = {
        descricaoServico: descricaoServico,
        maoDeObra: parseFloat(maoDeObra),
        produtoId: produto[produtoSelecionado].id
      };
  
      await apiClient.post('/servicos', data);
      toast.success('Cadastrado com sucesso!');
      
      setDescricaoServico('');
      setMaoDeObra('');
      setProdutoSelecionado("");

  
    } catch (err) {
      console.log(err);
      toast.error('Ops, erro ao cadastrar!');
    }
  }

  return (
    <>
      <Head>
        <title>Serviços - Auto Center</title>
      </Head>
      <div>
        <Header />
        <main className={styles.container}>
          <div className={styles.cadastro}>
            <h1>Cadastrar Serviço</h1>

            <form onSubmit={handleRegister}>
              <select value={produtoSelecionado} onChange={handleChangeProduto}>
              <option value="" disabled defaultValue="">
                  Selecione um produto
                </option>
                {produto.map((item, index) => {
                  return (
                    <option key={item.id} value={index}>
                      {item.descricaoProduto}
                    </option>
                  );
                })}
              </select>

              <Input
                placeholder="Valor do serviço"
                type="text"
                value={maoDeObra}
                onChange={(e) => setMaoDeObra(e.target.value)}
              />

              <textarea
                placeholder="Descreva o serviço a ser feito..."
                className={styles.input}
                value={descricaoServico}
                onChange={(e) => setDescricaoServico(e.target.value)}
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
    props: {}
  }
})