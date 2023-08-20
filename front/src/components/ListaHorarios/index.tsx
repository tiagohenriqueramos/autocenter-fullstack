import { useState, FormEvent, useEffect } from "react";
import { setupAPIClient } from "@/services/api";
import Datepicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { format } from "date-fns";
import styles from "./styles.module.scss";
import React, { FC } from "react";

type ListaHorariosProps = {
    dataSeleciona: Date | "";
    horaSeleciona: string;
    onDataChange: (newDate: Date | "") => void;
    onHoraChange: (newHour: string) => void;
  };
export type Agendamento = {
    id: string;
    data: string;
    hora: string;
  };
  
  export const ListaHorarios: FC<ListaHorariosProps> = ({
    dataSeleciona,
    horaSeleciona,
    onDataChange,
    onHoraChange,
  }) =>{

    const [dataSelecionada, setDataSelecionada] = useState<Date | "">("");
  const [horaSelecionada, setHoraSelecionada] = useState("");

  const [agendamento, setAgendamento] = useState<Agendamento[]>([]);
  const [dataExistente, setDataExistente] = useState<string[]>([]);
  const [horaExistente, setHoraExistente] = useState<string[]>([]);


  const [dataClick, setDataClick] = useState(false);
    const apiClient = setupAPIClient();

    const fetchAgendamento = async () => {
        const response = await apiClient
          .get("/agendamentos")
          .catch((err) => console.log(err));
        if (response) {
          const agendamento: Agendamento[] = response.data;
          setAgendamento(agendamento);
    
          const horasExistente = agendamento.map((item) => item.hora);
          setHoraExistente(horasExistente);
    
          const datasExistente = agendamento.map((item) => item.data);
          setDataExistente(datasExistente);
        }
      };

      useEffect(() => {
        fetchAgendamento();
      }, []);

      
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
        .map((agend) => agend.hora);

      // Filtra a lista de horários disponíveis para excluir os horários marcados
      hourListFiltered = hourList.filter(
        (hour) => !horariosMarcados.includes(hour)
      );
    }
  }

  
  


    return (
        <div className={styles.cadastro}>
        <Datepicker
          className={`${styles.date} ${
            dataClick ? styles.dateClicked : ""
          }`}
          placeholderText="Selecione uma data"
          selected={dataSelecionada}
          onChange={(date) => {
            setDataSelecionada(date); // Atualize o estado da data selecionada
            setHoraSelecionada(""); // Reinicie a hora selecionada ao escolher uma nova data
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
      </div>
    );
    
  }