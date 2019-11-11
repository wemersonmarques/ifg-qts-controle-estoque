package br.edu.ifg.qtscontroleestoque.entity;

import br.edu.ifg.qtscontroleestoque.dto.UsuarioDTO;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String senha;
    @CreationTimestamp
    private Date dataCadastro;

    public Usuario(){

    }

    public Usuario(UsuarioDTO dto) {
        this.id = dto.getId();
        this.email = dto.getEmail();
        this.nome = dto.getNome();
        this.dataCadastro = dto.getDataCadastro();
        this.senha = dto.getSenha();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
