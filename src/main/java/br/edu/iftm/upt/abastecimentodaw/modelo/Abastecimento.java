package br.edu.iftm.upt.abastecimentodaw.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "abastecimento")
public class Abastecimento implements Serializable{

	private static final long serialVersionUID = -3490942217232363561L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idabastecimento;
	
	@ManyToOne(targetEntity = Veiculo.class)
	@JoinColumn(name = "idveiculo")
	private Veiculo veiculo;
	
	@ManyToOne(targetEntity = Usuario.class)
	@JoinColumn(name = "idusuario")
	private Usuario usuario;
	
	private String dia;
	private String hora;
	private double kmatual;
	private double valor;
	private double litros;
	private String tipocombustivel;
	
	
	
	public Abastecimento() {
	}
	
	


	public Abastecimento(long idabastecimento, Veiculo veiculo, Usuario usuario, String dia, String hora,
			Double kmatual, Double valor, Double litros, String tipocombustivel) {
		this.idabastecimento = idabastecimento;
		this.veiculo = veiculo;
		this.usuario = usuario;
		this.dia = dia;
		this.hora = hora;
		this.kmatual = kmatual;
		this.valor = valor;
		this.litros = litros;
		this.tipocombustivel = tipocombustivel;
	}
	
	
	
}
