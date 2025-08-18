package com.example.orchestrator.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class Name {
	
	@NotNull
	@NotBlank
    @Pattern(regexp = "^[A-Za-z]+$", message = "Name must contain only alphabets")
	private String name;
	
	@NotNull
	@NotBlank
    @Pattern(regexp = "^[A-Za-z]+$", message = "Surname must contain only alphabets")
	private String surname;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "Name [name=" + name + ", surname=" + surname + "]";
	}
	
	

}
