package com.core.timmy.data.model;

import java.io.Serializable;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*Esto a través de entity se convierte en una clase especial que va a funcionar como espejo, va tener que tener 
una contra parte en una base de datos.*/
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "PROVIDER") //Preferible usar librerias más cercanas a java como jakarta


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor // Nos crea el constructor vacio
@ToString
@Slf4j
public class Provider implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@PositiveOrZero
	private Long id;
	
	//@OneToMany(mappedBy ="provider")
   // private List<BudgetItem> budgetItemList;
	//las etiquetas solo afecta a el elemento justo debajo de la etiqueta
	@NotNull
	@Column(nullable=false, columnDefinition="VARCHAR(150) CHECK(LENGTH(NAME) >= 1)")//queremos que la columna no sea anulable . Si usamos name="nombre" se le asigna este nombre a esta columna name. en la tabla
	@Size(min=1,max=520, message="{model.data.validation.Provider.name}") //Name must be from 1 to 520 characters
	private String name;
	
	@Column(columnDefinition="VARCHAR(30) CHECK(LENGTH(CIF) >= 9)")
	@Size(min=9,max=30, message="{model.data.validation.Provider.cif}")//Cif must be from 9 to 30 characters
	private String cif;
	
		
	@Column(columnDefinition="CHAR(18) CHECK(LENGTH(PHONE) >= 9)")
	@Size(min=9 ,max=20, message="{model.data.validation.Provider.phone}") //Phone must from 9 to 20 characters
	@NotNull
	private String phone; 
	
	@Email
	@Size(min=0, max=255,message="{model.data.validation.Provider.email}")//email must have from 0 to 255 characters
	private String email;
	
      
	@Column(columnDefinition="VARCHAR(9) CHECK(LENGTH(NIF) >= 2) CHECK(NIF REGEXP '^[0-9]{1,8}[A-Za-z]{1}')") //Esto solo funciona en la base de datos es una constraint para la base de datos
	@Size(min=2,max=9, message="{model.data.validation.Provider.nif}")//Cif must be from 2 to 9 characters
	private String nif;
	
	
	
	
	
}
