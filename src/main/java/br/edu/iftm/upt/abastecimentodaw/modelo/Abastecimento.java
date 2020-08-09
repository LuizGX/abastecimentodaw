package br.edu.iftm.upt.abastecimentodaw.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

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
	
	@NotNull(message = "A data é obrigatória")
	@Past(message = "O dia deve estar no passado")
	private LocalDate dia;
	
	@NotNull(message = "A hora é obrigatória")
	@Past(message = "A hora deve estar no passado")
	private LocalTime hora;
	
	@Positive(message = "O Km atual deve ser positivo")
	private double kmatual;
	
	@Positive(message = "O valor deve ser positivo")
	private double valor;
	
	@Positive(message = "A litragem deve ser positiva")
	private double litros;
	
	@NotBlank(message = "O tipo de combustível é obrigatório")
	@Size(min = 1, max = 45, message = "O tipo de combustível deve ter entre 1 e 45 caracteres")
	private String tipocombustivel;
	
	public Abastecimento() {
	}
	
	public Abastecimento(Veiculo veiculo, Usuario usuario, LocalDate dia, LocalTime hora,
			double kmatual, double valor, double litros, String tipocombustivel) {
		this.veiculo = veiculo;
		this.usuario = usuario;
		this.dia = dia;
		this.hora = hora;
		this.kmatual = kmatual;
		this.valor = valor;
		this.litros = litros;
		this.tipocombustivel = tipocombustivel;
	}

	public Abastecimento(long idabastecimento, Veiculo veiculo, Usuario usuario, LocalDate dia, LocalTime hora,
			double kmatual, double valor, double litros, String tipocombustivel) {
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

	public long getIdabastecimento() {
		return idabastecimento;
	}

	public void setIdabastecimento(long idabastecimento) {
		this.idabastecimento = idabastecimento;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDate getDia() {
		return dia;
	}

	public void setDia(LocalDate dia) {
		this.dia = dia;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public double getKmatual() {
		return kmatual;
	}

	public void setKmatual(double kmatual) {
		this.kmatual = kmatual;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getLitros() {
		return litros;
	}

	public void setLitros(double litros) {
		this.litros = litros;
	}

	public String getTipocombustivel() {
		return tipocombustivel;
	}

	public void setTipocombustivel(String tipocombustivel) {
		this.tipocombustivel = tipocombustivel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Abastecimento [idabastecimento=" + idabastecimento + ", veiculo=" + veiculo + ", usuario=" + usuario
				+ ", dia=" + dia + ", hora=" + hora + ", kmatual=" + kmatual + ", valor=" + valor + ", litros=" + litros
				+ ", tipocombustivel=" + tipocombustivel + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idabastecimento ^ (idabastecimento >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Abastecimento other = (Abastecimento) obj;
		if (idabastecimento != other.idabastecimento)
			return false;
		return true;
	}
	
	

	
}
