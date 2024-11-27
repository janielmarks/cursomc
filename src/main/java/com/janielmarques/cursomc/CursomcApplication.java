package com.janielmarques.cursomc;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.janielmarques.cursomc.domain.Categoria;
import com.janielmarques.cursomc.domain.Cidade;
import com.janielmarques.cursomc.domain.Cliente;
import com.janielmarques.cursomc.domain.Endereco;
import com.janielmarques.cursomc.domain.Estado;
import com.janielmarques.cursomc.domain.ItemPedido;
import com.janielmarques.cursomc.domain.Pagamento;
import com.janielmarques.cursomc.domain.PagamentoBoleto;
import com.janielmarques.cursomc.domain.PagamentoCartao;
import com.janielmarques.cursomc.domain.Pedido;
import com.janielmarques.cursomc.domain.Produto;
import com.janielmarques.cursomc.domain.enums.EstadoPagamento;
import com.janielmarques.cursomc.domain.enums.TipoCliente;
import com.janielmarques.cursomc.repositories.CategoriaRepository;
import com.janielmarques.cursomc.repositories.CidadeRepository;
import com.janielmarques.cursomc.repositories.ClienteRepository;
import com.janielmarques.cursomc.repositories.EnderecoRepository;
import com.janielmarques.cursomc.repositories.EstadoRepository;
import com.janielmarques.cursomc.repositories.ItemPedidoRepository;
import com.janielmarques.cursomc.repositories.PagamentoRepository;
import com.janielmarques.cursomc.repositories.PedidoRepository;
import com.janielmarques.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
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
		SpringApplication.run(CursomcApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "notebook", 2000.00);
		Produto p2 = new Produto(null, "impressora", 300.00);
		Produto p3 = new Produto(null, "teclado", 150.00); 
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p2.getCategorias().addAll(Arrays.asList(cat1));
		
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null, "Uberlandia", est1);
		Cidade cid2 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2));
		
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2));
		
		Cliente cli1 = new Cliente(null, "Maria", "mariamaria@gmail.com", "o393934567", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("3213232","99444515"));
		
		Endereco e1 = new Endereco(null, "bem viver 1", "4555", "ao lado do bv2", "hugo prado", "64022245", cli1, cid1);
		Endereco e2 = new Endereco(null, "Bem viver 2", "4055", "ao lado do bv1", "hugo prado", "64022245", cli1, cid2);
		
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		 
		Pedido ped1 = new Pedido (null, sdf.parse("30/12/2024 09:00"), cli1, e1);
		Pedido ped2 = new Pedido (null, sdf.parse("10/12/2024 12:00"), cli1, e1);
		
		Pagamento pagto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("31/12/2024 09:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p2, 0.00, 2, 80.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip2));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2));
		
		
	}

}
