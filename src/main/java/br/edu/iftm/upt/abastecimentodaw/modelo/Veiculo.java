package br.edu.iftm.upt.abastecimentodaw.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "veiculo")
public class Veiculo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idveiculo;
	
	@NotBlank(message = "A placa é obrigatória")
	@Size(min = 1, max = 45, message = "A placa deve ter entre 1 e 45 caracteres")
	private String placa;
	
	@NotBlank(message = "O modelo é obrigatório")
	@Size(min = 1, max = 45, message = "O modelo deve ter entre 1 e 45 caracteres")
	private String modelo;
	
	@NotBlank(message = "O ano é obrigatório")
	@Size(min = 4, max = 4, message = "O ano deve ter 4 caracteres")
	private String ano;
	
	public Veiculo() {
	}

	public Veiculo(String placa, String modelo, String ano) {
		this.placa = placa;
		this.modelo = modelo;
		this.ano = ano;
	}
	
	public Veiculo(long idveiculo, String placa, String modelo, String ano) {
		this.idveiculo = idveiculo;
		this.placa = placa;
		this.modelo = modelo;
		this.ano = ano;
	}

	public long getIdveiculo() {
		return idveiculo;
	}

	public void setIdveiculo(long idveiculo) {
		this.idveiculo = idveiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	@Override
	public String toString() {
		return "Veiculo [idveiculo=" + idveiculo + ", placa=" + placa + ", modelo=" + modelo + ", ano=" + ano + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idveiculo ^ (idveiculo >>> 32));
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
		Veiculo other = (Veiculo) obj;
		if (idveiculo != other.idveiculo)
			return false;
		return true;
	}
	
	
}
