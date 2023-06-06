package com.example.consultorio.model;

public class Dentista {
    private int matriculaCadastro; //ID
    private String nome;
    private String sobrenome;

    public Dentista(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public Dentista(int matriculaCadastro, String nome, String sobrenome) {
        this.matriculaCadastro = matriculaCadastro;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public int getMatriculaCadastro() {
        return matriculaCadastro;
    }

    public void setMatriculaCadastro(int matriculaCadastro) {
        this.matriculaCadastro = matriculaCadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    @Override
    public String toString() {
        return "Dentista{" +
                "matriculaCadastro=" + matriculaCadastro +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                '}';
    }
}
