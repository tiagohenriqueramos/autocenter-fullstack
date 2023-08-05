import {
  useState,
  FormEvent,
  ChangeEvent,
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
import { FiUpload } from "react-icons/fi";

type Servico = {
  id: string;
  descricaoServico: string;
  maoDeObra: string;
};

export default function ServicosImagem() {
  const [nomeImagem, setNomeImagem] = useState("");
  const [descricaoImagem, setDescricaoImagem] = useState("");

  const [avatarUrl, setAvatarUrl] = useState("");
  const [file, setFile] = useState(null);

  const [servico, setServico] = useState<Servico[]>([]);
  const [servicoSelecionado, setServicoSelecionado] = useState("");

  const apiClient = setupAPIClient();

  const fetchServico = async () => {
    const response = await apiClient
      .get("/servicos")
      .catch((err) => console.log(err));

    if (response) {
      const servico: Servico[] = response.data;
      setServico(servico);
    }
  };

  useEffect(() => {
    fetchServico();
  }, []);

  async function handleChangeServico(event) {
    setServicoSelecionado(event.target.value);
    console.log(servico[event.target.value]);
  }

  function handleFile(e: ChangeEvent<HTMLInputElement>) {
    if (!e.target.files) {
      return;
    }

    const image = e.target.files[0];

    if (!image) {
      return;
    }

    if (image.type === "image/jpeg" || image.type === "image/png") {
      setFile(image);
      setAvatarUrl(URL.createObjectURL(e.target.files[0]));
    }
    console.log(e.target.files[0]);
  }

  const handleRegister = async (event: FormEvent) => {
    event.preventDefault();

    try {
      if (!nomeImagem.trim() || !descricaoImagem.trim() || !file) {
        toast.error("Preencha todos os campos");
        return;
      }

    
      const formData = new FormData();
      formData.append("file", file);
      formData.append("descricaoImagem", descricaoImagem);
      formData.append("nomeImagem", nomeImagem);
      formData.append("servicoId",  servico[servicoSelecionado].id);

      await apiClient.post("/servicos/imagem", formData, {
        headers: {
          "Content-Type": "multipart/form-data"
        },
      });

      toast.success("Cadastrado com sucesso!");

    } catch (error) {
      console.log(error);
      toast.error("Ops, erro ao cadastrar!");
    }

    // Limpar os campos
    setNomeImagem("");
    setDescricaoImagem("");
    setServicoSelecionado("");
    setFile(null);
    setAvatarUrl("");
  };
  return (
    <>
      <Head>
        <title>Serviços - Auto Center</title>
      </Head>
      <div>
        <Header />
        <main className={styles.container}>
          <div className={styles.cadastro}>
            <h1>Cadastrar Imagem do Serviço</h1>

            <form onSubmit={handleRegister}>
              <select value={servicoSelecionado} onChange={handleChangeServico}>
              <option value="" disabled defaultValue="">
                  Selecione um serviço
                </option>
                {servico.map((item, index) => {
                  return (
                    <option key={item.id} value={index}>
                      {item.descricaoServico}
                    </option>
                  );
                })}
              </select>

              <Input
                placeholder="Nome da Foto"
                type="text"
                value={nomeImagem}
                onChange={(e) => setNomeImagem(e.target.value)}
              />

              <textarea
                placeholder="Descreva o serviço a ser feito..."
                className={styles.input}
                value={descricaoImagem}
                onChange={(e) => setDescricaoImagem(e.target.value)}
              />
              <label className={styles.labelAvatar}>
                <span>
                  <FiUpload size={35} color="#FFF" />
                </span>
                <input
                  type="file"
                  accept="image/png, image/jpeg"
                  onChange={handleFile}
                />

                {avatarUrl && (
                  <img
                    className={styles.preview}
                    src={avatarUrl}
                    alt="Foto da veiculo"
                    width={150}
                    height={150}
                  />
                )}
              </label>
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
