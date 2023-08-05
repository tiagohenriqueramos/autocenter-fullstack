import pdfMake from 'pdfmake/build/pdfmake';
import pdfFonts from 'pdfmake/build/vfs_fonts';
import style from "./style.module.scss";


// Carrega as fontes usadas pelo pdfmake
pdfMake.vfs = pdfFonts.pdfMake.vfs;

export default function GeradorPDF({ notaFiscalData }) {
  const styles = {
    container: {
      border: [1, 1, 1, 1], // Borda em todas as direções (topo, direita, baixo, esquerda)
      padding: [10, 20], // Espaçamento interno (topo, direita, baixo, esquerda)
    },
    header: {
      fontSize: 20,
      bold: true,
      alignment: 'center',
      marginBottom: 20,
    },
    section: {
      fontSize: 12,
      marginBottom: 10,
    },
    sectionTitle: {
      fontSize: 14,
      bold: true,
      marginBottom: 5,
    },
    footer: {
      fontSize: 10,
      marginTop: 30,
      alignment: 'center',
    },
  };

  const generatePDF = () => {
    const documentDefinition = {
      content: [
        { text: 'NOTA FISCAL ELETRÔNICA', style: 'header' },
        {
          canvas: [
            {
              type: 'line',
              x1: 0,
              y1: 20,
              x2: 595,
              y2: 20,
              lineWidth: 1,
              lineColor: '#000000', // Cor preta para a linha horizontal do cabeçalho
            },
          ],
        },
        {
          layout: 'noBorders', // Remove as bordas internas da coluna
          table: {
            widths: ['50%', '50%'],
            body: [
              [
                {
                  stack: [
                    { text: 'DADOS DA EMPRESA', style: 'sectionTitle' },
                    { text: `Nome Emitente: ${notaFiscalData.nomeEmitente}`, style: 'section' },
                    { text: `CNPJ Emitente: ${notaFiscalData.cnpjEmitente}`, style: 'section' },
                    { text: `Inscrição Estadual: ${notaFiscalData.inscricaoEstadualEmitente}`, style: 'section' },
                    { text: `Endereço: ${notaFiscalData.enderecoEmitente}`, style: 'section' },

                    // Adicione mais campos da empresa aqui
                  ],
                },
                {
                  stack: [
                    { text: 'DADOS DO CLIENTE', style: 'sectionTitle' },
                    { text: `Nome Destinatário: ${notaFiscalData.nomeDestinatario}`, style: 'section' },
                    { text: `CPF/CNPJ Destinatário: ${notaFiscalData.cpfCnpjDestinatario}`, style: 'section' },
                    { text: `Endereço eletronico: ${notaFiscalData.enderecoDestinatario}`, style: 'section' },
                    // Adicione mais campos do cliente aqui
                  ],
                },
              ],
            ],
          },
        },
        {
          canvas: [
            {
              type: 'line',
              x1: 0,
              y1: 220,
              x2: 595,
              y2: 220,
              lineWidth: 1,
              lineColor: '#000000', // Cor preta para a linha horizontal que separa as seções
            },
          ],
        },
        {
          text: 'DADOS DO PRODUTO/SERVIÇO',
          style: 'sectionTitle',
        },
        {
          stack: [
            { text: `Descrição do Produto: ${notaFiscalData.descricaoProduto}`, style: 'section' },
            { text: `Valor do Produto: ${notaFiscalData.valorProduto}`, style: 'section' },
            { text: `Data da Emissão: ${notaFiscalData.dataEmissao}`, style: 'section' },
            { text: `Serviço Prestado: ${notaFiscalData.maoDeObra}`, style: 'section' },
            { text: `Valor Total: ${notaFiscalData.valorTotal}`, style: 'section' },

          ],
        },
        // Adicione mais seções e linhas horizontais conforme necessário
        {
          stack: [
            { text: `Código Fiscal de Operações e Prestações: ${notaFiscalData.cfop}`, style: 'section' },
            { text: ` Código de Situação Tributária: ${notaFiscalData.cst}`, style: 'section' },
            { text: `Alíquota: ${notaFiscalData.aliquota}`, style: 'section' },
            { text: `Serviço Prestado: ${notaFiscalData.numeroNotaFiscal}`, style: 'section' },

          ],
        },
      ],
      styles: styles,
      footer: function (currentPage, pageCount) {
        return { text: `Página ${currentPage.toString()} de ${pageCount}`, style: 'footer' };
      },
    };

    pdfMake.createPdf(documentDefinition).download('NotaFiscal.pdf');
  };

  return (
    <button className={style.buttonOrder}  onClick={generatePDF}>Gerar PDF</button>
  );
}
