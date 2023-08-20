package com.tiagohenriqueramos.autocenter.config;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tiagohenriqueramos.autocenter.entities.Agendamento;
import com.tiagohenriqueramos.autocenter.entities.Cliente;
import com.tiagohenriqueramos.autocenter.entities.Produto;
import com.tiagohenriqueramos.autocenter.entities.Servico;
import com.tiagohenriqueramos.autocenter.entities.Usuario;
import com.tiagohenriqueramos.autocenter.entities.Veiculo;
import com.tiagohenriqueramos.autocenter.enums.StatusAgendamentos;
import com.tiagohenriqueramos.autocenter.repositories.AgendamentoRepository;
import com.tiagohenriqueramos.autocenter.repositories.ClienteRepository;
import com.tiagohenriqueramos.autocenter.repositories.ProdutoRepository;
import com.tiagohenriqueramos.autocenter.repositories.ServicoRepository;
import com.tiagohenriqueramos.autocenter.repositories.UsuarioRepository;
import com.tiagohenriqueramos.autocenter.repositories.VeiculoRepository;

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

	@Override
	public void run(String... args) throws Exception {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

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

		clienteRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15));

		Veiculo v1 = new Veiculo("Ford", "Ka", "2018", "QNF-1234");
		Veiculo v2 = new Veiculo("Toyota", "Corolla", "2020","FLA-1010");
		Veiculo v3 = new Veiculo("Honda", "Civic", "2019", "MEN-3054");
		Veiculo v4 = new Veiculo("Volkswagen", "Gol", "2017","TIA-2811");
		Veiculo v5 = new Veiculo("Chevrolet", "Onix", "2021","PDF-3698");
		Veiculo v6 = new Veiculo("Fiat", "Palio", "2015","PZC-6987");
		Veiculo v7 = new Veiculo("Renault", "Sandero", "2018","THR-1515");
		Veiculo v8 = new Veiculo("Hyundai", "HB20", "2020","POR-3564");
		Veiculo v9 = new Veiculo("Nissan", "Versa", "2019","QWE-4445");
		Veiculo v10 = new Veiculo("Jeep", "Compass", "2021","ASD-5454");
		Veiculo v11 = new Veiculo("Mitsubishi", "Lancer", "2017","MLO-9878");
		Veiculo v12 = new Veiculo("Ford", "Ranger", "2016","POR-6565");
		Veiculo v13 = new Veiculo("Toyota", "Hilux", "2020","ZXZ-5454");
		Veiculo v14 = new Veiculo("Volkswagen", "T-Cross", "2019","DFG-9877");
		Veiculo v15 = new Veiculo("Chevrolet", "S10", "2018","DFE-3232");

		v1.setCliente(c1);
		v2.setCliente(c2);
		v3.setCliente(c3);
		v4.setCliente(c4);
		v5.setCliente(c5);
		v6.setCliente(c6);
		v7.setCliente(c7);
		v8.setCliente(c8);
		v9.setCliente(c9);
		v10.setCliente(c10);
		v11.setCliente(c11);
		v12.setCliente(c12);
		v13.setCliente(c13);
		v14.setCliente(c14);
		v15.setCliente(c15);
		

		veiculoRepository.saveAll(Arrays.asList(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15));

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

		Produto p1 = new Produto("Pneu Aro 14 Pirelli", "Pneu para automóveis aro 14 da marca Pirelli", 389.55);
		Produto p2 = new Produto("Óleo Motor 5W30", "Óleo para motor de veículos, especificação 5W30", 100.55);
		Produto p3 = new Produto("Bateria Automotiva", "Bateria para veículos de diferentes modelos", 299.99);
		Produto p4 = new Produto("Pastilha de Freio", "Pastilha de freio para carros de diversas marcas", 89.90);
		Produto p5 = new Produto("Filtro de Ar Condicionado", "Filtro de ar condicionado para veículos", 29.99);
		Produto p6 = new Produto("Lâmpada LED H4", "Lâmpada de LED para farol do tipo H4", 49.90);
		Produto p7 = new Produto("Limpador de Para-brisa", "Limpador de para-brisa com borrachas de silicone", 39.90);
		Produto p8 = new Produto("Tapete Automotivo Universal", "Jogo de tapetes para carros, modelo universal", 59.99);
		Produto p9 = new Produto("Aditivo Radiador", "Aditivo para radiador, proteção anticongelante", 19.99);
		Produto p10 = new Produto("Cera para Carro", "Cera de polimento para proteção da pintura do carro", 79.90);
		Produto p11 = new Produto("Shampoo Automotivo", "Shampoo para lavagem de carros, não agride a pintura", 34.90);
		Produto p12 = new Produto("Desengripante", "Óleo desengripante para peças e mecanismos", 14.99);
		Produto p13 = new Produto("Graxa para Rolamentos", "Graxa lubrificante para rolamentos de veículos", 24.90);
		Produto p14 = new Produto("Limpa Vidros Automotivo", "Produto para limpeza de vidros e para-brisas", 19.90);
		Produto p15 = new Produto("Espelho Retrovisor Universal", "Espelho retrovisor para reposição, modelo universal",
				49.90);

		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15));

		Servico s1 = new Servico("Troca de Pneu", 150.00, p1);
		Servico s2 = new Servico("Troca de Óleo", 100.00, p2);
		Servico s3 = new Servico("Alinhamento e Balanceamento", 200.00, p3);
		Servico s4 = new Servico("Revisão Preventiva", 300.00, p4);
		Servico s5 = new Servico("Troca de Pastilhas de Freio", 120.00, p5);
		Servico s6 = new Servico("Limpeza de Ar-condicionado", 80.00, p6);
		Servico s7 = new Servico("Troca de Lâmpadas", 50.00, p7);
		Servico s8 = new Servico("Higienização Interna", 90.00, p8);
		Servico s9 = new Servico("Troca de Correias", 180.00, p9);
		Servico s10 = new Servico("Polimento e Cristalização", 250.00, p10);
		Servico s11 = new Servico("Lavagem Especial", 70.00, p11);
		Servico s12 = new Servico("Troca de Filtros", 100.00, p12);
		Servico s13 = new Servico("Reparo de Pneu Furado", 60.00, p13);
		Servico s14 = new Servico("Recarga de Ar-condicionado", 120.00, p14);
		Servico s15 = new Servico("Instalação de Acessórios", 180.00, p15);

		servicoRepository.saveAll(Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14, s15));

		Agendamento a1 = new Agendamento(c1, v1, s1, data, horario1);
		Agendamento a2 = new Agendamento(c2, v2, s2, data1, horario2);
		Agendamento a3 = new Agendamento(c3, v3, s3, data2, horario3);
		Agendamento a4 = new Agendamento(c4, v4, s4, data3, horario4);
		Agendamento a5 = new Agendamento(c5, v5, s5, data4, horario5);
		Agendamento a6 = new Agendamento(c6, v6 , s6, data5, horario6);
		Agendamento a7 = new Agendamento(c7, v7, s7, data6, horario7);
		Agendamento a8 = new Agendamento(c8, v8, s8, data7, horario8);
		Agendamento a9 = new Agendamento(c9, v9, s9, data, horario9);
		Agendamento a10 = new Agendamento(c10, v10, s10, data1, horario10);
		Agendamento a11 = new Agendamento(c11, v11, s11, data2, horario11);
		Agendamento a12 = new Agendamento(c12, v12, s12, data3, horario1);
		Agendamento a13 = new Agendamento(c13, v13, s13, data4, horario2);
		Agendamento a14 = new Agendamento(c14, v14, s14, data5, horario3);
		Agendamento a15 = new Agendamento(c15, v15, s15, data6, horario4);

		a1.getServicos().add(s1);
		a2.getServicos().add(s2);
		a3.getServicos().add(s3);
		a4.getServicos().add(s4);
		a5.getServicos().add(s5);
		a6.getServicos().add(s6);
		a7.getServicos().add(s7);
		a8.getServicos().add(s8);
		a9.getServicos().add(s9);
		a10.getServicos().add(s10);
		a11.getServicos().add(s11);
		a12.getServicos().add(s12);
		a13.getServicos().add(s13);
		a14.getServicos().add(s14);
		a15.getServicos().add(s15);

		a1.setStatus(StatusAgendamentos.CONCLUIDO);
		a3.setStatus(StatusAgendamentos.CONCLUIDO);
		a5.setStatus(StatusAgendamentos.CONCLUIDO);
		

		agendamentoRepository.saveAll(Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15));

		String senha = "123456";
		String senhaCodificada = passwordEncoder.encode(senha);
		Usuario usuario = new Usuario(null, "tiago", "tiago@tiago.com", senhaCodificada);

		usuarioRepository.saveAll(Arrays.asList(usuario));
	}

}
