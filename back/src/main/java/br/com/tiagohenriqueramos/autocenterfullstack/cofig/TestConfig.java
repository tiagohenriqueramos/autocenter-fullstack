package br.com.tiagohenriqueramos.autocenterfullstack.cofig;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.tiagohenriqueramos.autocenterfullstack.entities.Agendamento;
import br.com.tiagohenriqueramos.autocenterfullstack.entities.Cliente;
import br.com.tiagohenriqueramos.autocenterfullstack.entities.NotaFiscal;
import br.com.tiagohenriqueramos.autocenterfullstack.entities.Produto;
import br.com.tiagohenriqueramos.autocenterfullstack.entities.Servico;
import br.com.tiagohenriqueramos.autocenterfullstack.entities.Usuario;
import br.com.tiagohenriqueramos.autocenterfullstack.entities.Veiculo;
import br.com.tiagohenriqueramos.autocenterfullstack.repositories.AgendamentoRepository;
import br.com.tiagohenriqueramos.autocenterfullstack.repositories.ClienteRepository;
import br.com.tiagohenriqueramos.autocenterfullstack.repositories.NotaFiscalRepository;
import br.com.tiagohenriqueramos.autocenterfullstack.repositories.ProdutoRepository;
import br.com.tiagohenriqueramos.autocenterfullstack.repositories.ServicoRepository;
import br.com.tiagohenriqueramos.autocenterfullstack.repositories.UsuarioRepository;
import br.com.tiagohenriqueramos.autocenterfullstack.repositories.VeiculoRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private VeiculoRepository veiculoRepository;

	@Autowired
	private AgendamentoRepository agendamentoRepository;

	@Autowired
	private ServicoRepository servicoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private NotaFiscalRepository notaFiscalRepository;

	@Override
	public void run(String... args) throws Exception {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

		Veiculo v1 = new Veiculo("Ford", "Ka", "2018");
		Veiculo v2 = new Veiculo("Toyota", "Corolla", "2020");
		Veiculo v3 = new Veiculo("Honda", "Civic", "2019");
		Veiculo v4 = new Veiculo("Volkswagen", "Gol", "2017");
		Veiculo v5 = new Veiculo("Chevrolet", "Onix", "2021");
		Veiculo v6 = new Veiculo("Fiat", "Palio", "2015");
		Veiculo v7 = new Veiculo("Renault", "Sandero", "2018");
		Veiculo v8 = new Veiculo("Hyundai", "HB20", "2020");
		Veiculo v9 = new Veiculo("Nissan", "Versa", "2019");
		Veiculo v10 = new Veiculo("Jeep", "Compass", "2021");
		Veiculo v11 = new Veiculo("Mitsubishi", "Lancer", "2017");
		Veiculo v12 = new Veiculo("Ford", "Ranger", "2016");
		Veiculo v13 = new Veiculo("Toyota", "Hilux", "2020");
		Veiculo v14 = new Veiculo("Volkswagen", "T-Cross", "2019");
		Veiculo v15 = new Veiculo("Chevrolet", "S10", "2018");

		Cliente c1 = new Cliente("João Silva", "456.545.211-00", "joao@example.com", "34992811544");
		Cliente c2 = new Cliente("Maria Santos", "987.545.211-00", "maria@example.com", "34992811544");
		Cliente c3 = new Cliente("Pedro Almeida", "123.545.211-00", "pedro@example.com", "34992811544");
		Cliente c4 = new Cliente("Ana Costa", "789.545.211-00", "ana@example.com", "34992811544");
		Cliente c5 = new Cliente("Rafael Oliveira", "111.222.333-44", "rafael@example.com", "34992811544");
		Cliente c6 = new Cliente("Camila Pereira", "555.666.777-88", "camila@example.com", "34992811544");
		Cliente c7 = new Cliente("Lucas Rodrigues", "999.888.777-66", "lucas@example.com", "34992811544");
		Cliente c8 = new Cliente("Isabela Souza", "555.444.333-22", "isabela@example.com", "34992811544");
		Cliente c9 = new Cliente("Gabriel Martins", "111.222.333-44", "gabriel@example.com", "34992811544");
		Cliente c10 = new Cliente("Larissa Gomes", "222.333.444-55", "larissa@example.com", "34992811544");
		Cliente c11 = new Cliente("Matheus Ferreira", "777.888.999-00", "matheus@example.com", "34992811544");
		Cliente c12 = new Cliente("Laura Santos", "222.333.444-55", "laura@example.com", "34992811544");
		Cliente c13 = new Cliente("Bruno Alves", "777.888.999-00", "bruno@example.com", "34992811544");
		Cliente c14 = new Cliente("Juliana Ribeiro", "333.444.555-66", "juliana@example.com", "34992811544");
		Cliente c15 = new Cliente("Guilherme Lima", "888.777.666-55", "guilherme@example.com", "34992811544");

		v1.setCliente_Id(c1.getId());
		v2.setCliente_Id(c2.getId());
		v3.setCliente_Id(c3.getId());
		v4.setCliente_Id(c4.getId());
		v5.setCliente_Id(c5.getId());
		v6.setCliente_Id(c6.getId());
		v7.setCliente_Id(c7.getId());
		v8.setCliente_Id(c8.getId());
		v9.setCliente_Id(c9.getId());
		v10.setCliente_Id(c10.getId());
		v11.setCliente_Id(c11.getId());
		v12.setCliente_Id(c12.getId());
		v13.setCliente_Id(c13.getId());
		v14.setCliente_Id(c14.getId());
		v15.setCliente_Id(c15.getId());

		veiculoRepository.saveAll(Arrays.asList(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15));

		clienteRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15));

		LocalTime horario1 = LocalTime.parse("08:00", dtf);
		LocalTime horario2 = LocalTime.parse("09:00", dtf);
		LocalTime horario3 = LocalTime.parse("10:00", dtf);
		LocalTime horario4 = LocalTime.parse("11:00", dtf);
		LocalTime horario5 = LocalTime.parse("12:00", dtf);
		LocalTime horario6 = LocalTime.parse("13:00", dtf);
		LocalTime horario7 = LocalTime.parse("14:00", dtf);
		LocalTime horario8 = LocalTime.parse("15:00", dtf);
		LocalTime horario9 = LocalTime.parse("16:00", dtf);
		LocalTime horario10 = LocalTime.parse("17:00", dtf);
		LocalTime horario11 = LocalTime.parse("18:00", dtf);

		LocalDate data = LocalDate.now();
		LocalDate data1 = data.plusDays(1);
		LocalDate data2 = data.plusDays(1);
		LocalDate data3 = data.plusDays(2);
		LocalDate data4 = data.plusDays(3);
		LocalDate data5 = data.plusDays(4);
		LocalDate data6 = data.plusDays(3);
		LocalDate data7 = data.plusDays(2);

		Produto p1 = new Produto((long) 1, "Pneu Aro 14 Pirelli", "Pneu para automóveis aro 14 da marca Pirelli",
				389.55);
		Produto p2 = new Produto((long) 2, "Óleo Motor 5W30", "Óleo para motor de veículos, especificação 5W30",
				100.55);
		Produto p3 = new Produto((long) 3, "Bateria Automotiva", "Bateria para veículos de diferentes modelos", 299.99);
		Produto p4 = new Produto((long) 4, "Pastilha de Freio", "Pastilha de freio para carros de diversas marcas",
				89.90);
		Produto p5 = new Produto((long) 5, "Filtro de Ar Condicionado", "Filtro de ar condicionado para veículos",
				29.99);
		Produto p6 = new Produto((long) 6, "Lâmpada LED H4", "Lâmpada de LED para farol do tipo H4", 49.90);
		Produto p7 = new Produto((long) 7, "Limpador de Para-brisa", "Limpador de para-brisa com borrachas de silicone",
				39.90);
		Produto p8 = new Produto((long) 8, "Tapete Automotivo Universal",
				"Jogo de tapetes para carros, modelo universal", 59.99);
		Produto p9 = new Produto((long) 9, "Aditivo Radiador", "Aditivo para radiador, proteção anticongelante", 19.99);
		Produto p10 = new Produto((long) 10, "Cera para Carro", "Cera de polimento para proteção da pintura do carro",
				79.90);
		Produto p11 = new Produto((long) 11, "Shampoo Automotivo",
				"Shampoo para lavagem de carros, não agride a pintura", 34.90);
		Produto p12 = new Produto((long) 12, "Desengripante", "Óleo desengripante para peças e mecanismos", 14.99);
		Produto p13 = new Produto((long) 13, "Graxa para Rolamentos", "Graxa lubrificante para rolamentos de veículos",
				24.90);
		Produto p14 = new Produto((long) 14, "Limpa Vidros Automotivo", "Produto para limpeza de vidros e para-brisas",
				19.90);
		Produto p15 = new Produto((long) 15, "Espelho Retrovisor Universal",
				"Espelho retrovisor para reposição, modelo universal", 49.90);

		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15));

		Servico s1 = new Servico((long) 1, "Troca de Pneu", 150.00, p1.getId());
		Servico s2 = new Servico((long) 2, "Troca de Óleo", 100.00, p2.getId());
		Servico s3 = new Servico((long) 3, "Alinhamento e Balanceamento", 200.00, p3.getId());
		Servico s4 = new Servico((long) 4, "Revisão Preventiva", 300.00, p4.getId());
		Servico s5 = new Servico((long) 5, "Troca de Pastilhas de Freio", 120.00, p5.getId());
		Servico s6 = new Servico((long) 6, "Limpeza de Ar-condicionado", 80.00, p6.getId());
		Servico s7 = new Servico((long) 7, "Troca de Lâmpadas", 50.00, p7.getId());
		Servico s8 = new Servico((long) 8, "Higienização Interna", 90.00, p8.getId());
		Servico s9 = new Servico((long) 9, "Troca de Correias", 180.00, p9.getId());
		Servico s10 = new Servico((long) 10, "Polimento e Cristalização", 250.00, p10.getId());
		Servico s11 = new Servico((long) 11, "Lavagem Especial", 70.00, p11.getId());
		Servico s12 = new Servico((long) 12, "Troca de Filtros", 100.00, p12.getId());
		Servico s13 = new Servico((long) 13, "Reparo de Pneu Furado", 60.00, p13.getId());
		Servico s14 = new Servico((long) 14, "Recarga de Ar-condicionado", 120.00, p14.getId());
		Servico s15 = new Servico((long) 15, "Instalação de Acessórios", 180.00, p15.getId());

		servicoRepository.saveAll(Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14, s15));

		Agendamento a1 = new Agendamento(data, horario1, c1.getId(), s1.getId());
		Agendamento a2 = new Agendamento(data1, horario2, c2.getId(), s2.getId());
		Agendamento a3 = new Agendamento(data2, horario3, c3.getId(), s3.getId());
		Agendamento a4 = new Agendamento(data3, horario4, c4.getId(), s4.getId());
		Agendamento a5 = new Agendamento(data4, horario5, c5.getId(), s5.getId());
		Agendamento a6 = new Agendamento(data5, horario6, c6.getId(), s6.getId());
		Agendamento a7 = new Agendamento(data6, horario7, c7.getId(), s7.getId());
		Agendamento a8 = new Agendamento(data7, horario8, c8.getId(), s8.getId());
		Agendamento a9 = new Agendamento(data, horario9, c9.getId(), s9.getId());
		Agendamento a10 = new Agendamento(data1, horario10, c10.getId(), s10.getId());
		Agendamento a11 = new Agendamento(data2, horario11, c11.getId(), s11.getId());
		Agendamento a12 = new Agendamento(data3, horario1, c12.getId(), s12.getId());
		Agendamento a13 = new Agendamento(data4, horario2, c13.getId(), s12.getId());
		Agendamento a14 = new Agendamento(data5, horario3, c14.getId(), s14.getId());
		Agendamento a15 = new Agendamento(data6, horario4, c15.getId(), s15.getId());

		agendamentoRepository.saveAll(Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15));
		
	/*	double valor = p1.getPreco() + s1.getMaoDeObra();

		NotaFiscal notaFiscal = new NotaFiscal();

		notaFiscal.setNomeEmitente("Auto Center LTDA");
		notaFiscal.setCnpjEmitente("12345678900001");
		notaFiscal.setInscricaoEstadualEmitente("123456789");
		notaFiscal.setEnderecoEmitente("Rua dos Emitentes, 123");

		notaFiscal.setNomeDestinatario(c1.getNome());
		notaFiscal.setCpfCnpjDestinatario(c1.getCpf());
		notaFiscal.setEnderecoDestinatario(c1.getEmail());

		notaFiscal.setDescricaoProduto(p1.getDescricaoProduto());
		notaFiscal.setValorProduto(p1.getPreco());
		notaFiscal.setMaoDeObra(s1.getMaoDeObra());
		notaFiscal.setValorTotal(valor);
		notaFiscal.setCfop("5102");
		notaFiscal.setCst("040");
		notaFiscal.setAliquota(18.0);
		notaFiscal.setDataEmissao(data);
		notaFiscal.setNumeroNotaFiscal(a1.getId());

		notaFiscal.setFormaPagamento("Boleto");
		notaFiscal.setInformacoesAdicionais("Dowload");

		notaFiscal.setAgendamentoId(a1.getId());
		notaFiscal.setClienteId(c1.getId());
		notaFiscal.setServicoId(s1.getId());

		notaFiscalRepository.save(notaFiscal);
		
		double valor2 = p2.getPreco() + s2.getMaoDeObra();

		NotaFiscal notaFiscal2 = new NotaFiscal();

		notaFiscal2.setNomeEmitente("Auto Center LTDA");
		notaFiscal2.setCnpjEmitente("12345678900001");
		notaFiscal2.setInscricaoEstadualEmitente("123456789");
		notaFiscal2.setEnderecoEmitente("Rua dos Emitentes, 123");

		notaFiscal2.setNomeDestinatario(c2.getNome());
		notaFiscal2.setCpfCnpjDestinatario(c2.getCpf());
		notaFiscal2.setEnderecoDestinatario(c2.getEmail());

		notaFiscal2.setDescricaoProduto(p2.getDescricaoProduto());
		notaFiscal2.setValorProduto(p2.getPreco());
		notaFiscal2.setMaoDeObra(s2.getMaoDeObra());
		notaFiscal2.setValorTotal(valor2);
		notaFiscal2.setCfop("5102");
		notaFiscal2.setCst("040");
		notaFiscal2.setAliquota(18.0);
		notaFiscal2.setDataEmissao(data1);
		notaFiscal2.setNumeroNotaFiscal(a2.getId());

		notaFiscal2.setFormaPagamento("Boleto");
		notaFiscal2.setInformacoesAdicionais("Download");

		notaFiscal2.setAgendamentoId(a2.getId());
		notaFiscal2.setClienteId(c2.getId());
		notaFiscal2.setServicoId(s2.getId());

		notaFiscalRepository.save(notaFiscal2);

		// Nota Fiscal 3
		double valor3 = p3.getPreco() + s3.getMaoDeObra();

		NotaFiscal notaFiscal3 = new NotaFiscal();

		notaFiscal3.setNomeEmitente("Auto Center LTDA");
		notaFiscal3.setCnpjEmitente("12345678900001");
		notaFiscal3.setInscricaoEstadualEmitente("123456789");
		notaFiscal3.setEnderecoEmitente("Rua dos Emitentes, 123");

		notaFiscal3.setNomeDestinatario(c3.getNome());
		notaFiscal3.setCpfCnpjDestinatario(c3.getCpf());
		notaFiscal3.setEnderecoDestinatario(c3.getEmail());

		notaFiscal3.setDescricaoProduto(p3.getDescricaoProduto());
		notaFiscal3.setValorProduto(p3.getPreco());
		notaFiscal3.setMaoDeObra(s3.getMaoDeObra());

		notaFiscal3.setValorTotal(valor3);
		notaFiscal3.setCfop("5102");
		notaFiscal3.setCst("040");
		notaFiscal3.setAliquota(18.0);
		notaFiscal3.setDataEmissao(data2);
		notaFiscal3.setNumeroNotaFiscal(a3.getId());

		notaFiscal3.setFormaPagamento("Boleto");
		notaFiscal3.setInformacoesAdicionais("Download");

		notaFiscal3.setAgendamentoId(a3.getId());
		notaFiscal3.setClienteId(c3.getId());
		notaFiscal3.setServicoId(s3.getId());

		notaFiscalRepository.save(notaFiscal3);

		// Nota Fiscal 4
		double valor4 = p4.getPreco() + s4.getMaoDeObra();

		NotaFiscal notaFiscal4 = new NotaFiscal();

		notaFiscal4.setNomeEmitente("Auto Center LTDA");
		notaFiscal4.setCnpjEmitente("12345678900001");
		notaFiscal4.setInscricaoEstadualEmitente("123456789");
		notaFiscal4.setEnderecoEmitente("Rua dos Emitentes, 123");

		notaFiscal4.setNomeDestinatario(c4.getNome());
		notaFiscal4.setCpfCnpjDestinatario(c4.getCpf());
		notaFiscal4.setEnderecoDestinatario(c4.getEmail());

		notaFiscal4.setDescricaoProduto(p4.getDescricaoProduto());
		notaFiscal4.setValorProduto(p4.getPreco());
		notaFiscal4.setMaoDeObra(s4.getMaoDeObra());

		notaFiscal4.setValorTotal(valor4);
		notaFiscal4.setCfop("5102");
		notaFiscal4.setCst("040");
		notaFiscal4.setAliquota(18.0);
		notaFiscal4.setDataEmissao(data3);
		notaFiscal4.setNumeroNotaFiscal(a4.getId());

		notaFiscal4.setFormaPagamento("Boleto");
		notaFiscal4.setInformacoesAdicionais("Download");

		notaFiscal4.setAgendamentoId(a4.getId());
		notaFiscal4.setClienteId(c4.getId());
		notaFiscal4.setServicoId(s4.getId());

		notaFiscalRepository.save(notaFiscal4);

		// Nota Fiscal 5
		double valor5 = p5.getPreco() + s5.getMaoDeObra();

		NotaFiscal notaFiscal5 = new NotaFiscal();

		notaFiscal5.setNomeEmitente("Auto Center LTDA");
		notaFiscal5.setCnpjEmitente("12345678900001");
		notaFiscal5.setInscricaoEstadualEmitente("123456789");
		notaFiscal5.setEnderecoEmitente("Rua dos Emitentes, 123");

		notaFiscal5.setNomeDestinatario(c5.getNome());
		notaFiscal5.setCpfCnpjDestinatario(c5.getCpf());
		notaFiscal5.setEnderecoDestinatario(c5.getEmail());

		notaFiscal5.setDescricaoProduto(p5.getDescricaoProduto());
		notaFiscal5.setValorProduto(p5.getPreco());
		notaFiscal5.setMaoDeObra(s5.getMaoDeObra());

		notaFiscal5.setValorTotal(valor5);
		notaFiscal5.setCfop("5102");
		notaFiscal5.setCst("040");
		notaFiscal5.setAliquota(18.0);
		notaFiscal5.setDataEmissao(data4);
		notaFiscal5.setNumeroNotaFiscal(a5.getId());

		notaFiscal5.setFormaPagamento("Boleto");
		notaFiscal5.setInformacoesAdicionais("Download");

		notaFiscal5.setAgendamentoId(a5.getId());
		notaFiscal5.setClienteId(c5.getId());
		notaFiscal5.setServicoId(s5.getId());

		notaFiscalRepository.save(notaFiscal5);

		// Nota Fiscal 6
		double valor6 = p6.getPreco() + s6.getMaoDeObra();

		NotaFiscal notaFiscal6 = new NotaFiscal();

		notaFiscal6.setNomeEmitente("Auto Center LTDA");
		notaFiscal6.setCnpjEmitente("12345678900001");
		notaFiscal6.setInscricaoEstadualEmitente("123456789");
		notaFiscal6.setEnderecoEmitente("Rua dos Emitentes, 123");

		notaFiscal6.setNomeDestinatario(c6.getNome());
		notaFiscal6.setCpfCnpjDestinatario(c6.getCpf());
		notaFiscal6.setEnderecoDestinatario(c6.getEmail());

		notaFiscal6.setDescricaoProduto(p6.getDescricaoProduto());
		notaFiscal6.setValorProduto(p6.getPreco());
		notaFiscal6.setMaoDeObra(s6.getMaoDeObra());

		notaFiscal6.setValorTotal(valor6);
		notaFiscal6.setCfop("5102");
		notaFiscal6.setCst("040");
		notaFiscal6.setAliquota(18.0);
		notaFiscal6.setDataEmissao(data5);
		notaFiscal6.setNumeroNotaFiscal(a6.getId());

		notaFiscal6.setFormaPagamento("Boleto");
		notaFiscal6.setInformacoesAdicionais("Download");

		notaFiscal6.setAgendamentoId(a6.getId());
		notaFiscal6.setClienteId(c6.getId());
		notaFiscal6.setServicoId(s6.getId());

		notaFiscalRepository.save(notaFiscal6);

		// Nota Fiscal 7
		double valor7 = p7.getPreco() + s7.getMaoDeObra();

		NotaFiscal notaFiscal7 = new NotaFiscal();

		notaFiscal7.setNomeEmitente("Auto Center LTDA");
		notaFiscal7.setCnpjEmitente("12345678900001");
		notaFiscal7.setInscricaoEstadualEmitente("123456789");
		notaFiscal7.setEnderecoEmitente("Rua dos Emitentes, 123");

		notaFiscal7.setNomeDestinatario(c7.getNome());
		notaFiscal7.setCpfCnpjDestinatario(c7.getCpf());
		notaFiscal7.setEnderecoDestinatario(c7.getEmail());

		notaFiscal7.setDescricaoProduto(p7.getDescricaoProduto());
		notaFiscal7.setValorProduto(p7.getPreco());
		notaFiscal7.setMaoDeObra(s7.getMaoDeObra());

		notaFiscal7.setValorTotal(valor7);
		notaFiscal7.setCfop("5102");
		notaFiscal7.setCst("040");
		notaFiscal7.setAliquota(18.0);
		notaFiscal7.setDataEmissao(data6);
		notaFiscal7.setNumeroNotaFiscal(a7.getId());

		notaFiscal7.setFormaPagamento("Boleto");
		notaFiscal7.setInformacoesAdicionais("Download");

		notaFiscal7.setAgendamentoId(a7.getId());
		notaFiscal7.setClienteId(c7.getId());
		notaFiscal7.setServicoId(s7.getId());

		notaFiscalRepository.save(notaFiscal7);

		// Nota Fiscal 8
		double valor8 = p8.getPreco() + s8.getMaoDeObra();

		NotaFiscal notaFiscal8 = new NotaFiscal();

		notaFiscal8.setNomeEmitente("Auto Center LTDA");
		notaFiscal8.setCnpjEmitente("12345678900001");
		notaFiscal8.setInscricaoEstadualEmitente("123456789");
		notaFiscal8.setEnderecoEmitente("Rua dos Emitentes, 123");

		notaFiscal8.setNomeDestinatario(c8.getNome());
		notaFiscal8.setCpfCnpjDestinatario(c8.getCpf());
		notaFiscal8.setEnderecoDestinatario(c8.getEmail());

		notaFiscal8.setDescricaoProduto(p8.getDescricaoProduto());
		notaFiscal8.setValorProduto(p8.getPreco());
		notaFiscal8.setMaoDeObra(s7.getMaoDeObra());

		notaFiscal8.setValorTotal(valor8);
		notaFiscal8.setCfop("5102");
		notaFiscal8.setCst("040");
		notaFiscal8.setAliquota(18.0);
		notaFiscal8.setDataEmissao(data7);
		notaFiscal8.setNumeroNotaFiscal(a8.getId());

		notaFiscal8.setFormaPagamento("Boleto");
		notaFiscal8.setInformacoesAdicionais("Download");

		notaFiscal8.setAgendamentoId(a8.getId());
		notaFiscal8.setClienteId(c8.getId());
		notaFiscal8.setServicoId(s8.getId());

		notaFiscalRepository.save(notaFiscal8);

		// Nota Fiscal 9
		double valor9 = p9.getPreco() + s9.getMaoDeObra();

		NotaFiscal notaFiscal9 = new NotaFiscal();

		notaFiscal9.setNomeEmitente("Auto Center LTDA");
		notaFiscal9.setCnpjEmitente("12345678900001");
		notaFiscal9.setInscricaoEstadualEmitente("123456789");
		notaFiscal9.setEnderecoEmitente("Rua dos Emitentes, 123");

		notaFiscal9.setNomeDestinatario(c9.getNome());
		notaFiscal9.setCpfCnpjDestinatario(c9.getCpf());
		notaFiscal9.setEnderecoDestinatario(c9.getEmail());

		notaFiscal9.setDescricaoProduto(p9.getDescricaoProduto());
		notaFiscal9.setValorProduto(p9.getPreco());
		notaFiscal9.setMaoDeObra(s9.getMaoDeObra());

		notaFiscal9.setValorTotal(valor9);
		notaFiscal9.setCfop("5102");
		notaFiscal9.setCst("040");
		notaFiscal9.setAliquota(18.0);
		notaFiscal9.setDataEmissao(data);
		notaFiscal9.setNumeroNotaFiscal(a9.getId());

		notaFiscal9.setFormaPagamento("Boleto");
		notaFiscal9.setInformacoesAdicionais("Download");

		notaFiscal9.setAgendamentoId(a9.getId());
		notaFiscal9.setClienteId(c9.getId());
		notaFiscal9.setServicoId(s9.getId());

		notaFiscalRepository.save(notaFiscal9);

		// Nota Fiscal 10
		double valor10 = p10.getPreco() + s10.getMaoDeObra();

		NotaFiscal notaFiscal10 = new NotaFiscal();

		notaFiscal10.setNomeEmitente("Auto Center LTDA");
		notaFiscal10.setCnpjEmitente("12345678900001");
		notaFiscal10.setInscricaoEstadualEmitente("123456789");
		notaFiscal10.setEnderecoEmitente("Rua dos Emitentes, 123");

		notaFiscal10.setNomeDestinatario(c10.getNome());
		notaFiscal10.setCpfCnpjDestinatario(c10.getCpf());
		notaFiscal10.setEnderecoDestinatario(c10.getEmail());

		notaFiscal10.setDescricaoProduto(p10.getDescricaoProduto());
		notaFiscal10.setValorProduto(p10.getPreco());
		notaFiscal10.setMaoDeObra(s10.getMaoDeObra());

		notaFiscal10.setValorTotal(valor10);
		notaFiscal10.setCfop("5102");
		notaFiscal10.setCst("040");
		notaFiscal10.setAliquota(18.0);
		notaFiscal10.setDataEmissao(data1);
		notaFiscal10.setNumeroNotaFiscal(a10.getId());

		notaFiscal10.setFormaPagamento("Boleto");
		notaFiscal10.setInformacoesAdicionais("Download");

		notaFiscal10.setAgendamentoId(a10.getId());
		notaFiscal10.setClienteId(c10.getId());
		notaFiscal10.setServicoId(s10.getId());

		notaFiscalRepository.save(notaFiscal10);

		// Nota Fiscal 11
		double valor11 = p11.getPreco() + s11.getMaoDeObra();

		NotaFiscal notaFiscal11 = new NotaFiscal();

		notaFiscal11.setNomeEmitente("Auto Center LTDA");
		notaFiscal11.setCnpjEmitente("12345678900001");
		notaFiscal11.setInscricaoEstadualEmitente("123456789");
		notaFiscal11.setEnderecoEmitente("Rua dos Emitentes, 123");

		notaFiscal11.setNomeDestinatario(c11.getNome());
		notaFiscal11.setCpfCnpjDestinatario(c11.getCpf());
		notaFiscal11.setEnderecoDestinatario(c11.getEmail());

		notaFiscal11.setDescricaoProduto(p11.getDescricaoProduto());
		notaFiscal11.setValorProduto(p11.getPreco());
		notaFiscal11.setMaoDeObra(s11.getMaoDeObra());

		notaFiscal11.setValorTotal(valor11);
		notaFiscal11.setCfop("5102");
		notaFiscal11.setCst("040");
		notaFiscal11.setAliquota(18.0);
		notaFiscal11.setDataEmissao(data2);
		notaFiscal11.setNumeroNotaFiscal(a11.getId());

		notaFiscal11.setFormaPagamento("Boleto");
		notaFiscal11.setInformacoesAdicionais("Download");

		notaFiscal11.setAgendamentoId(a11.getId());
		notaFiscal11.setClienteId(c11.getId());
		notaFiscal11.setServicoId(s11.getId());

		notaFiscalRepository.save(notaFiscal11);

		// Nota Fiscal 12
		double valor12 = p12.getPreco() + s12.getMaoDeObra();

		NotaFiscal notaFiscal12 = new NotaFiscal();

		notaFiscal12.setNomeEmitente("Auto Center LTDA");
		notaFiscal12.setCnpjEmitente("12345678900001");
		notaFiscal12.setInscricaoEstadualEmitente("123456789");
		notaFiscal12.setEnderecoEmitente("Rua dos Emitentes, 123");

		notaFiscal12.setNomeDestinatario(c12.getNome());
		notaFiscal12.setCpfCnpjDestinatario(c12.getCpf());
		notaFiscal12.setEnderecoDestinatario(c12.getEmail());

		notaFiscal12.setDescricaoProduto(p12.getDescricaoProduto());
		notaFiscal12.setValorProduto(p12.getPreco());
		notaFiscal12.setMaoDeObra(s12.getMaoDeObra());

		notaFiscal12.setValorTotal(valor12);
		notaFiscal12.setCfop("5102");
		notaFiscal12.setCst("040");
		notaFiscal12.setAliquota(18.0);
		notaFiscal12.setDataEmissao(data3);
		notaFiscal12.setNumeroNotaFiscal(a12.getId());

		notaFiscal12.setFormaPagamento("Boleto");
		notaFiscal12.setInformacoesAdicionais("Download");

		notaFiscal12.setAgendamentoId(a12.getId());
		notaFiscal12.setClienteId(c12.getId());
		notaFiscal12.setServicoId(s12.getId());

		notaFiscalRepository.save(notaFiscal12);


		// Nota Fiscal 13
		double valor13 = p13.getPreco() + s13.getMaoDeObra();

		NotaFiscal notaFiscal13 = new NotaFiscal();

		notaFiscal13.setNomeEmitente("Auto Center LTDA");
		notaFiscal13.setCnpjEmitente("12345678900001");
		notaFiscal13.setInscricaoEstadualEmitente("123456789");
		notaFiscal13.setEnderecoEmitente("Rua dos Emitentes, 123");

		notaFiscal13.setNomeDestinatario(c13.getNome());
		notaFiscal13.setCpfCnpjDestinatario(c13.getCpf());
		notaFiscal13.setEnderecoDestinatario(c13.getEmail());

		notaFiscal13.setDescricaoProduto(p13.getDescricaoProduto());
		notaFiscal13.setValorProduto(p13.getPreco());
		notaFiscal13.setMaoDeObra(s13.getMaoDeObra());

		notaFiscal13.setValorTotal(valor13);
		notaFiscal13.setCfop("5102");
		notaFiscal13.setCst("040");
		notaFiscal13.setAliquota(18.0);
		notaFiscal13.setDataEmissao(data4);
		notaFiscal13.setNumeroNotaFiscal(a13.getId());

		notaFiscal13.setFormaPagamento("Boleto");
		notaFiscal13.setInformacoesAdicionais("Download");

		notaFiscal13.setAgendamentoId(a13.getId());
		notaFiscal13.setClienteId(c13.getId());
		notaFiscal13.setServicoId(s13.getId());

		notaFiscalRepository.save(notaFiscal13);


		// Nota Fiscal 14
		double valor14 = p14.getPreco() + s14.getMaoDeObra();

		NotaFiscal notaFiscal14 = new NotaFiscal();

		notaFiscal14.setNomeEmitente("Auto Center LTDA");
		notaFiscal14.setCnpjEmitente("12345678900001");
		notaFiscal14.setInscricaoEstadualEmitente("123456789");
		notaFiscal14.setEnderecoEmitente("Rua dos Emitentes, 123");

		notaFiscal14.setNomeDestinatario(c14.getNome());
		notaFiscal14.setCpfCnpjDestinatario(c14.getCpf());
		notaFiscal14.setEnderecoDestinatario(c14.getEmail());

		notaFiscal14.setDescricaoProduto(p14.getDescricaoProduto());
		notaFiscal14.setValorProduto(p14.getPreco());
		notaFiscal14.setMaoDeObra(s14.getMaoDeObra());

		notaFiscal14.setValorTotal(valor14);
		notaFiscal14.setCfop("5102");
		notaFiscal14.setCst("040");
		notaFiscal14.setAliquota(18.0);
		notaFiscal14.setDataEmissao(data5);
		notaFiscal14.setNumeroNotaFiscal(a14.getId());

		notaFiscal14.setFormaPagamento("Boleto");
		notaFiscal14.setInformacoesAdicionais("Download");

		notaFiscal14.setAgendamentoId(a14.getId());
		notaFiscal14.setClienteId(c14.getId());
		notaFiscal14.setServicoId(s14.getId());

		notaFiscalRepository.save(notaFiscal14);

		// Nota Fiscal 15
		double valor15 = p15.getPreco() + s15.getMaoDeObra();

		NotaFiscal notaFiscal15 = new NotaFiscal();

		notaFiscal15.setNomeEmitente("Auto Center LTDA");
		notaFiscal15.setCnpjEmitente("12345678900001");
		notaFiscal15.setInscricaoEstadualEmitente("123456789");
		notaFiscal15.setEnderecoEmitente("Rua dos Emitentes, 123");

		notaFiscal15.setNomeDestinatario(c15.getNome());
		notaFiscal15.setCpfCnpjDestinatario(c15.getCpf());
		notaFiscal15.setEnderecoDestinatario(c15.getEmail());

		notaFiscal15.setDescricaoProduto(p15.getDescricaoProduto());
		notaFiscal15.setValorProduto(p15.getPreco());
		notaFiscal15.setMaoDeObra(s15.getMaoDeObra());

		notaFiscal15.setValorTotal(valor15);
		notaFiscal15.setCfop("5102");
		notaFiscal15.setCst("040");
		notaFiscal15.setAliquota(18.0);
		notaFiscal15.setDataEmissao(data6);
		notaFiscal15.setNumeroNotaFiscal(a15.getId());

		notaFiscal15.setFormaPagamento("Boleto");
		notaFiscal15.setInformacoesAdicionais("Download");

		notaFiscal15.setAgendamentoId(a15.getId());
		notaFiscal15.setClienteId(c15.getId());
		notaFiscal15.setServicoId(s15.getId());

		notaFiscalRepository.save(notaFiscal15);*/


		String senha = "123456";
		String senhaCodificada = passwordEncoder.encode(senha);
		Usuario usuario = new Usuario(null, "tiago", "tiago@tiago.com", senhaCodificada);

		usuarioRepository.saveAll(Arrays.asList(usuario));

	}
}