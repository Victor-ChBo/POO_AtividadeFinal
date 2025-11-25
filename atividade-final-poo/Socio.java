package atividade-final-poo;

public class Socio extends Pessoa {
    private int numeroSocio;
    private String plano;

    public Socio(String nome, String cpf, String email, int numeroSocio, String plano) {
        super(nome, cpf, email);
        this.numeroSocio = numeroSocio;
        this.plano = plano;
    }

    public int getNumeroSocio() {
        return numeroSocio;
    }

    public void setNumeroSocio(int numeroSocio) {
        this.numeroSocio = numeroSocio;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    @Override
    public String toString() {
        return "Socio{" +
                "nome='" + getNome() + '\'' +
                ", cpf='" + getCpf() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", numeroSocio=" + numeroSocio +
                ", plano='" + plano + '\'' +
                '}';
    }
}