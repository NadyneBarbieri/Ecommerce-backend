package dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDTO {

    private long id;

    @NotNull
    @Size(min = 5, max = 100, message = "O nome deve ter entre 5 e 100 caracteres.")
    private String nome;

    @NotNull
    @Size(min = 5, max = 100, message = "O usuário deve ter entre 5 e 100 caracteres.")
    private String usuario;

    @NotNull
    private boolean vendedor;

    // Getters e Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public boolean isVendedor() {
        return vendedor;
    }

    public void setVendedor(boolean vendedor) {
        this.vendedor = vendedor;
    }
}
