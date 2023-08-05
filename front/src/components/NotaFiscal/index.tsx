import GeradorPDF from "../GeradorPDF/index";
import { parseString } from "xml2js";
import { setupAPIClient } from "@/services/api";
import { useEffect, useState } from "react";
import { StatusAgendamentos } from "@/enum/StatusAgendamentos";
import xmlbuilder from "xmlbuilder";


export type Agendamento = {
  id: string;
  data: string;
  horario: string;
  status: StatusAgendamentos; 
  cliente: Cliente;
  servico: Servico;
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
  cliente_Id: string;
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

export type PropsNotaFiscal = {
  notaFiscalId: string;
  
};

export type NotaFiscal = {
  id: string;
  nomeEmitente: string;
  cnpjEmitente: string;
  inscricaoEstadualEmitente: string;
  enderecoEmitente: string;
  nomeDestinatario: string;
  cpfCnpjDestinatario: string;
  enderecoDestinatario: string;
  descricaoProduto: string;
  maoDeObra: string;
  valorProduto: string;
  valorTotal: string;
  cfop: string;
  cst: string;
  aliquota: string;
  dataEmissao: string;
  numeroNotaFiscal: string;
  formaPagamento: string;
  informacoesAdicionais: string;
};

export default function NotaFiscal({ notaFiscalId }: PropsNotaFiscal) {
  const [agendamento, setAgendamento] = useState<Agendamento | null>(null);
  const [notaFiscal, setNotaFiscal] = useState<NotaFiscal | null>(null);
  const apiClient = setupAPIClient();

  useEffect(() => {
    async function buscarAgendamento() {
      try {
        const response = await apiClient.get(`/agendamentos/${notaFiscalId}/concluido`);
        const dadosAgendamento = response.data;
        setAgendamento(dadosAgendamento);
      } catch (error) {
        console.error("Erro ao buscar o agendamento:", error);
      }
    }

    buscarAgendamento();
  }, [notaFiscalId]);

  

  useEffect(() => {
    // Verifica se os dados do agendamento foram buscados e, em seguida, cria a NotaFiscal
    if (agendamento) {
      // Supondo que os dados da NotaFiscal possam ser gerados a partir dos dados do agendamento
      const dadosNotaFiscal: NotaFiscal = {
        id: null,
        nomeEmitente: "Auto Center",
        cnpjEmitente: "CNPJ do Emitente: 04.341.954/0001-19",
        inscricaoEstadualEmitente: "Inscrição Estadual do Emitente: 813.705.838/4467",
        enderecoEmitente: "Endereço do Emitente: Praça Miguel Luiz Nº 650",
        nomeDestinatario: agendamento.cliente.nome  ,
        cpfCnpjDestinatario: agendamento.cliente.cpf,
        enderecoDestinatario: agendamento.cliente.email,
        descricaoProduto: agendamento.servico.produto.descricaoProduto,
        maoDeObra: agendamento.servico.maoDeObra,
        valorProduto:  agendamento.servico.produto.preco,
        valorTotal: agendamento.servico.produto.preco + agendamento.servico.maoDeObra ,
        cfop: "6108",
        cst: "010",
        aliquota: "18",
        dataEmissao: agendamento.data,
        numeroNotaFiscal: agendamento.id,
        formaPagamento: "Boleto",
        informacoesAdicionais: "",
        // ... (dados da NotaFiscal)
      };
      setNotaFiscal(dadosNotaFiscal);

    }
  }, [agendamento]);


  return (
    <div>
      <GeradorPDF notaFiscalData={notaFiscal} />

    </div>
  );
}


