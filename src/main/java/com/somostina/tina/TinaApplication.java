package com.somostina.tina;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.somostina.tina.domain.Cidade;
import com.somostina.tina.domain.Cliente;
import com.somostina.tina.domain.Endereco;
import com.somostina.tina.domain.Estado;
import com.somostina.tina.domain.ItemPedido;
import com.somostina.tina.domain.Pagamento;
import com.somostina.tina.domain.PagamentoComCartao;
import com.somostina.tina.domain.PagamentoEmEspecie;
import com.somostina.tina.domain.Pedido;
import com.somostina.tina.domain.Procedimento;
import com.somostina.tina.domain.Servico;
import com.somostina.tina.domain.enums.EstadoPagamento;
import com.somostina.tina.domain.enums.SexoCliente;
import com.somostina.tina.repositories.CidadeRepository;
import com.somostina.tina.repositories.ClienteRepository;
import com.somostina.tina.repositories.EnderecoRepository;
import com.somostina.tina.repositories.EstadoRepository;
import com.somostina.tina.repositories.ItemPedidoRepository;
import com.somostina.tina.repositories.PagamentoRepository;
import com.somostina.tina.repositories.PedidoRepository;
import com.somostina.tina.repositories.ProcedimentoRepository;
import com.somostina.tina.repositories.ServicoRepository;

@SpringBootApplication
public class TinaApplication implements CommandLineRunner {

	@Autowired
	private ServicoRepository servicoRepository;
	@Autowired
	private ProcedimentoRepository procedimentoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(TinaApplication.class, args);
	}

	public void run(String... args) throws Exception {

		Servico serv1 = new Servico(null, "Cabelo");
		Servico serv2 = new Servico(null, "Maquiagem");

		Procedimento p1 = new Procedimento(null, "Corte Feminino", 50.00, serv1);
		Procedimento p2 = new Procedimento(null, "Corte Masculino", 30.00, serv1);
		Procedimento p3 = new Procedimento(null, "Hidratação", 150.00, serv1);
		Procedimento p4 = new Procedimento(null, "Maquiagem Artistica", 200.00, serv2);
		Procedimento p5 = new Procedimento(null, "Maquiagem + Cílios", 150.00, serv2);

		servicoRepository.saveAll(Arrays.asList(serv1, serv2));
		procedimentoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		Estado est1 = new Estado(null, "Piauí");
		Estado est2 = new Estado(null, "Maranhão");

		Cidade c1 = new Cidade(null, "Teresina", est1);
		Cidade c2 = new Cidade(null, "Floriano", est1);
		Cidade c3 = new Cidade(null, "São Luís", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Eduardo Henrique", "eduhenriquesrs@gmail.com", "(89) 99921-7065",
				"36378912377", SexoCliente.MASCULINO);
		Cliente cli2 = new Cliente(null, "Maria Eduarda", "maria@yahool.com", "(89) 12354-0765",
				"54189651354", SexoCliente.FEMININO);
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1,cli2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoEmEspecie(null, EstadoPagamento.QUITADO, ped2, sdf.parse("20/03/2021 15:30"));
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 10.00, 1, 50.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 150.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 10.00, 1, 30.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
