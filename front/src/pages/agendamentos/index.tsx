import { useState, FormEvent, useEffect } from "react";
import Head from "next/head";
import Router from "next/router";
import { Header } from "@/components/Header";
import { Input } from "@/components/ui/Input";
import styles from "./styles.module.scss";
import { Button } from "@/components/ui/Button";
import { setupAPIClient } from "@/services/api";
import { toast } from "react-toastify";
import { canSSRAuth } from "@/utils/canSSRAuth";
import Datepicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { format } from "date-fns";
import DateTimePicker from "react-datetime-picker";

type Cliente = {
  id: string;
  nome: string;
  cpf: string;
  email: string;
  telefone: string;
};

type Servico = {
  id: string;
  descricaoServico: string;
  maoDeObra: string;
};

type Agendamento = {
  id: string;
  data: string;
  horario: string;
};

export default function Agendamentos() {
  const [cliente, setCliente] = useState<Cliente[]>([]);
  const [clienteSelecionado, setClienteSelecionado] = useState("");

  const [servico, setServico] = useState<Servico[]>([]);
  const [servicoSelecionado, setServicoSelecionado] = useState("");

  const [dataSelecionada, setDataSelecionada] = useState<Date | "">("");
  const [horaSelecionada, setHoraSelecionada] = useState("");

  const [agendamento, setAgendamento] = useState<Agendamento[]>([]);
  const [dataExistente, setDataExistente] = useState<string[]>([]);
  const [horaExistente, setHoraExistente] = useState<string[]>([]);
  const [horaList, setHoraList] = useState<string[]>([]);

  const [dataClick, setDataClick] = useState(false);

  const apiClient = setupAPIClient();

  const fetchCliente = async () => {
    const response = await apiClient
      .get("/clientes")
      .catch((err) => console.log(err));

    if (response) {
      const produto: Cliente[] = response.data;
      setCliente(produto);
    }
  };

  useEffect(() => {
    fetchCliente();
  }, []);

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

  const fetchAgendamento = async () => {
    const response = await apiClient
      .get("/agendamentos")
      .catch((err) => console.log(err));
    if (response) {
      const agendamento: Agendamento[] = response.data;
      setAgendamento(agendamento);

      const horasExistente = agendamento.map((item) => item.horario);
      setHoraExistente(horasExistente);

      const datasExistente = agendamento.map((item) => item.data);
      setDataExistente(datasExistente);
    }
  };

  useEffect(() => {
    fetchAgendamento();
  }, []);

  async function handleChangeServico(event) {
    setServicoSelecionado(event.target.value);
    console.log(servico[event.target.value]);
  }

  async function handleChangeCliente(event: any) {
    setClienteSelecionado(event.target.value);
    console.log(cliente[event.target.value]);
  }

  const generateHourList = (startTime, endTime, increment) => {
    const hourList = [];
    let currentTime = new Date(startTime);

    while (currentTime <= endTime) {
      const formattedTime = format(currentTime, "HH:mm");
      hourList.push(formattedTime);

      currentTime.setHours(currentTime.getHours() + increment);
    }

    return hourList;
  };

  const startTime = new Date();
  startTime.setHours(8, 0, 0); // Defina o horário de início desejado
  const endTime = new Date();
  endTime.setHours(18, 0, 0); // Defina o horário de término desejado
  const increment = 1; // Defina o incremento em horas desejado

  const hourList = generateHourList(startTime, endTime, increment);
// ...

let hourListFiltered = hourList;

if (dataSelecionada) {
  const formattedDataSelecionada = format(dataSelecionada, "dd/MM/yyyy");
  
  // Verifica se há algum horário marcado na data selecionada
  if (dataExistente.includes(formattedDataSelecionada)) {
    const horariosMarcados = agendamento
      .filter((agend) => agend.data === formattedDataSelecionada)
      .map((agend) => agend.horario);

    // Filtra a lista de horários disponíveis para excluir os horários marcados
    hourListFiltered = hourList.filter((hour) => !horariosMarcados.includes(hour));
  }
}

// ...
  
  

  async function handleRegister(event: FormEvent) {
    event.preventDefault();

    try {
      if (!dataSelecionada || !horaSelecionada) {
        toast.error("Selecione todos os campos");
        return;
      }

      const dataFormatada = format(dataSelecionada, "dd/MM/yyyy");

      const data = {
        data: dataFormatada,
        horario: horaSelecionada,
        servicoId: servico[servicoSelecionado].id,
        cliente_Id: cliente[clienteSelecionado].id,
      };
      console.log("Dados que serão enviados:", data);

      console.log("Data selecionada:", data.data);
      console.log("Hora selecionada:", data.horario);

      await apiClient.post("/agendamentos", data);
      toast.success("Cadastrado com sucesso!");

      const horaListAtualizada = horaList.filter(
        (hour) => hour !== horaSelecionada
      );
      setHoraExistente([...horaExistente, horaSelecionada]);
      setHoraList(horaListAtualizada);

      setServicoSelecionado("");
      setClienteSelecionado("");
      setHoraSelecionada("");
      setDataSelecionada("");
      setDataClick(false)
    } catch (err) {
      console.log(err);
      toast.error("Ops, erro ao cadastrar!");
    }
  }
  return (
    <>
      <Head>
        <title>Agendamentos - Auto Center</title>
      </Head>
      <div>
        <Header />
        <main className={styles.containerCenter}>
          <div className={styles.cadastro}>
            <h1>Cadastrar Agendamento</h1>

            <form onSubmit={handleRegister}>
              <select value={clienteSelecionado} onChange={handleChangeCliente}>
                <option value="" disabled defaultValue="">
                  Selecione um cliente
                </option>
                {cliente.map((item, index) => {
                  return (
                    <option key={item.id} value={index}>
                      {item.nome}
                    </option>
                  );
                })}
              </select>

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
              <Datepicker
                className={`${styles.date} ${
                  dataClick ? styles.dateClicked : ""
                }`}
                placeholderText="Selecione uma data"
                selected={dataSelecionada}
                onChange={(date) => {
                  setDataSelecionada(date);
                  setDataClick(true); 
                }}
                dateFormat="dd/MM/yyyy"
                filterDate={(date) =>
                  date.getDay() !== 6 && date.getDay() !== 0
                }
              />
 {dataSelecionada && (
            <select
              value={horaSelecionada}
              onChange={(event) => setHoraSelecionada(event.target.value)}
            >
              <option value="" disabled defaultValue="">
                Selecione um horário
              </option>
              {hourListFiltered.length === 0 ? (
                <option disabled>Nenhum horário disponível</option>
              ) : (
                hourListFiltered.map((hour) => (
                  <option key={hour} value={hour}>
                    {hour}
                  </option>
                ))
              )}
            </select>
          )}


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