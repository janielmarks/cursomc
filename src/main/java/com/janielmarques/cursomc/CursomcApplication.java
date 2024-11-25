package com.janielmarques.cursomc;

import java.lang.reflect.Array;
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
import com.janielmarques.cursomc.domain.Produto;
import com.janielmarques.cursomc.domain.enums.TipoCliente;
import com.janielmarques.cursomc.repositories.CategoriaRepository;
import com.janielmarques.cursomc.repositories.CidadeRepository;
import com.janielmarques.cursomc.repositories.ClienteRepository;
import com.janielmarques.cursomc.repositories.EnderecoRepository;
import com.janielmarques.cursomc.repositories.EstadoRepository;
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
		
		
	}

}
