package br.edu.iftm.upt.abastecimentodaw.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "veiculo")
public class Veiculo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idveiculo;
	private String placa;
	private String modelo;
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
