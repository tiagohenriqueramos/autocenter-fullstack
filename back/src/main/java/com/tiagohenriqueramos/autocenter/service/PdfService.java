package com.tiagohenriqueramos.autocenter.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.tiagohenriqueramos.autocenter.entities.Agendamento;
import com.tiagohenriqueramos.autocenter.entities.Veiculo;

@Service
public class PdfService {

		public void gerarPdfPorId(Agendamento agendamento, Veiculo veiculo, Document document) throws DocumentException {
		try {
			Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
			Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
			Font fontBody = FontFactory.getFont(FontFactory.HELVETICA, 10);

			PdfPTable tableEmpresa = new PdfPTable(2);
			tableEmpresa.setWidthPercentage(100);
			tableEmpresa.setSpacingBefore(20);

			Paragraph headerEmpresa = new Paragraph("Informações da Empresa", fontTitle);
			headerEmpresa.setAlignment(Element.ALIGN_CENTER);
			PdfPCell headerCellEmpresa = new PdfPCell(headerEmpresa);
			headerCellEmpresa.setColspan(2);
			headerCellEmpresa.setBackgroundColor(BaseColor.LIGHT_GRAY);
			tableEmpresa.addCell(headerCellEmpresa);

			addZebraCell(tableEmpresa, "Razão Social: ", "AUTO CENTER LTDA", fontHeader, fontBody);
			addZebraCell(tableEmpresa, "CPNJ: ", "12.345.987/0000-12", fontHeader, fontBody);
			addZebraCell(tableEmpresa, "Endereço: ", "Rua do Flamengo Nº 10", fontHeader, fontBody);


			PdfPTable tableCliente = new PdfPTable(2);
			tableCliente.setWidthPercentage(100);
			tableCliente.setSpacingBefore(20);

			Paragraph headerCliente = new Paragraph("Informações do Cliente", fontTitle);
			headerCliente.setAlignment(Element.ALIGN_CENTER);
			PdfPCell headerCellCliente = new PdfPCell(headerCliente);
			headerCellCliente.setColspan(2);
			headerCellCliente.setBackgroundColor(BaseColor.LIGHT_GRAY);
			tableCliente.addCell(headerCellCliente);

			addZebraCell(tableCliente, "Nome:", agendamento.getCliente().getNome(), fontHeader, fontBody);
			addZebraCell(tableCliente, "CPF:", agendamento.getCliente().getCpf(), fontHeader, fontBody);
			addZebraCell(tableCliente, "Email", agendamento.getCliente().getEmail(), fontHeader, fontBody);
			addZebraCell(tableCliente, "Telefone:", agendamento.getCliente().getTelefone(), fontHeader, fontBody);

			PdfPTable tableVeiculo = new PdfPTable(2);
			tableVeiculo.setWidthPercentage(100);
			tableVeiculo.setSpacingBefore(20);

			Paragraph headerVeiculo = new Paragraph("Informações do Veículo", fontTitle);
			headerVeiculo.setAlignment(Element.ALIGN_CENTER);
			PdfPCell headerCellVeiculo = new PdfPCell(headerVeiculo);
			headerCellVeiculo.setColspan(2);
			headerCellVeiculo.setBackgroundColor(BaseColor.LIGHT_GRAY);
			tableVeiculo.addCell(headerCellVeiculo);

			addZebraCell(tableVeiculo, "Marca:", agendamento.getVeiculo().getMarca(), fontHeader, fontBody);
			addZebraCell(tableVeiculo, "Modelo:", agendamento.getVeiculo().getModelo(), fontHeader, fontBody);
			addZebraCell(tableVeiculo, "Ano:", agendamento.getVeiculo().getAno(), fontHeader, fontBody);
			addZebraCell(tableVeiculo, "Placa:", agendamento.getVeiculo().getPlaca(), fontHeader, fontBody);


			PdfPTable tableServico = new PdfPTable(2);
			tableServico.setWidthPercentage(100);
			tableServico.setSpacingBefore(20);

			Paragraph headerServico = new Paragraph("Informações do Serviço", fontTitle);
			headerServico.setAlignment(Element.ALIGN_CENTER);
			PdfPCell headerCellServico = new PdfPCell(headerServico);
			headerCellServico.setColspan(2);
			headerCellServico.setBackgroundColor(BaseColor.LIGHT_GRAY);
			tableServico.addCell(headerCellServico);

			addZebraCell(tableServico, "Descrição do serviço:", agendamento.getServico().getDescricaoServico(),
					fontHeader, fontBody);
			addZebraCell(tableServico, "Mão de Obra:", agendamento.getServico().getMaoDeObra().toString(), fontHeader,
					fontBody);
			addZebraCell(tableServico, "Produto:", agendamento.getServico().getProduto().getNome(), fontHeader,
					fontBody);
			addZebraCell(tableServico, "Valor do produto:", agendamento.getServico().getProduto().getPreco().toString(), fontHeader,
					fontBody);

			PdfPTable tableTributo = new PdfPTable(2);
			tableTributo.setWidthPercentage(100);
			tableTributo.setSpacingBefore(20);

			Paragraph headerTribulo = new Paragraph("Informações do Tributos", fontTitle);
			headerServico.setAlignment(Element.ALIGN_CENTER);
			PdfPCell headerCellTributo = new PdfPCell(headerTribulo);
			headerCellTributo.setColspan(2);
			headerCellTributo.setBackgroundColor(BaseColor.LIGHT_GRAY);
			tableTributo.addCell(headerCellTributo);

			addZebraCell(tableTributo, "Serviço :", agendamento.getServico().getDescricaoServico(), fontHeader,
					fontBody);
			addZebraCell(tableTributo, "Valor do serviço:", agendamento.getServico().getMaoDeObra().toString(), fontHeader,
					fontBody);
			addZebraCell(tableTributo, "Produto:", agendamento.getServico().getProduto().getNome(), fontHeader,
					fontBody);
			addZebraCell(tableTributo, "Valor do produto :",
					agendamento.getServico().getProduto().getPreco().toString(), fontHeader, fontBody);

			double maoDeObra = agendamento.getServico().getMaoDeObra();
			double impostoMaoDeObra = maoDeObra * 0.05;
			addZebraCell(tableTributo, "Imposto sobre serviço:", String.format("%.2f", impostoMaoDeObra),
					fontHeader, fontBody);

			double precoProduto = agendamento.getServico().getProduto().getPreco();
			double impostoProduto = precoProduto * 0.10;
			double totalImposto = impostoMaoDeObra + impostoProduto;
			addZebraCell(tableTributo, "Imposto sobre Produto:", String.format("%.2f", impostoProduto), fontHeader,
					fontBody);
			addZebraCell(tableTributo, "Total de imposto:", String.format("%.2f", totalImposto),
					fontHeader, fontBody);
			double valorTotal = totalImposto + maoDeObra + precoProduto;
			addZebraCell(tableTributo, "Valor total:", String.format("%.2f", valorTotal),
					fontHeader, fontBody);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String currentDate = dateFormat.format(new Date());
			Paragraph dateParagraph = new Paragraph("Data de geração: " + currentDate, fontBody);
			dateParagraph.setAlignment(Element.ALIGN_RIGHT);
			document.add(dateParagraph);

			document.open();
			document.add(tableEmpresa);
			document.add(tableCliente);
			document.add(tableVeiculo);
			document.add(tableServico);
			document.add(tableTributo);

			document.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	private void addZebraCell(PdfPTable table, String header, String value, Font headerFont, Font bodyFont) {
		PdfPCell headerCell = new PdfPCell(new Phrase(header, headerFont));
		PdfPCell valueCell = new PdfPCell(new Phrase(value, bodyFont));

		headerCell.setBorder(Rectangle.NO_BORDER);
		valueCell.setBorder(Rectangle.NO_BORDER);

		if (table.getRows().size() % 2 == 0) {
			headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			valueCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		}

		table.addCell(headerCell);
		table.addCell(valueCell);
	}

}
