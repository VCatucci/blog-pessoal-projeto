package org.generation.blogPessoal.model.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRegisterDTO {






	private @NotBlank @Size(min = 1, max = 50) String usuario;
	private @NotBlank @Email String email;
	private @NotBlank @Size(min = 5, max = 25) String senha;
	

		public UsuarioRegisterDTO(){ }
		
		public UsuarioRegisterDTO(String usuario,String email, String senha){
		 
		this.usuario = usuario;
		
		this.email= email;
		
		this.senha = senha; 
		
		}

		public String getUsuario() {
			return usuario;
		}

		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}
	
	
	
	
	}
