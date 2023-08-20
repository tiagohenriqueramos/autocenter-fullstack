package com.tiagohenriqueramos.autocenter.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.tiagohenriqueramos.autocenter.entities.Agendamento;
import com.tiagohenriqueramos.autocenter.service.AgendamentoService;
import com.tiagohenriqueramos.autocenter.service.PdfService;

@Controller
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    private AgendamentoService agendamentoService;
    
    @Autowired
    private PdfService pdfService;


    @GetMapping("/{id}")
    public void generatePdf(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response)
            throws DocumentException, IOException {
        Agendamento agendamento = agendamentoService.buscarPorId(id);

        if (agendamento != null) {
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + agendamento.getCliente().getNome() + ".pdf\"");

            document.open();
            pdfService.gerarPdfPorId(agendamento, agendamento.getVeiculo(), document);
            document.close();
        }
    }
   
}
