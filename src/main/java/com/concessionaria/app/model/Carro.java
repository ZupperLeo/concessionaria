package com.concessionaria.app.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Carro {

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String placa;

	@NotBlank
	@Column(nullable = false, length = 11)
	private String renavam;

	@NotBlank
	@Column(nullable = false)
	private String marca;

	@NotBlank
	@Column(nullable = false)
	private String modelo;

	@NotBlank
	@Column(nullable = false, unique = true)
	private String chassi;

	@NotNull
	@Column(nullable = false)
	@Positive
	private Integer ano;

	@NotNull
	@Column(nullable = false)
	private BigDecimal valor;

	@ManyToOne(optional = false)
	private Cliente cliente;

	@Deprecated
	public Carro() {
	}

	public Carro(@NotBlank String placa, @NotBlank String renavam, @NotBlank String marca, @NotBlank String modelo,
			@NotBlank String chassi, @NotNull @Positive Integer ano, @NotNull BigDecimal valor, Cliente cliente) {
		this.placa = placa;
		this.renavam = renavam;
		this.marca = marca;
		this.modelo = modelo;
		this.chassi = chassi;
		this.ano = ano;
		this.valor = valor;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

}
