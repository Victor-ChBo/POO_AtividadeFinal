package atividade-final-poo;

public class Convidado extends Pessoa {
    private String DataVisita;

    public Convidado(String nome, String cpf, String email, String DataVisita) {
        super(nome, cpf, email);
        this.DataVisita = DataVisita;
    }

    public String getDataVisita() {
        return DataVisita;
    }

    public void setDataVisita(String DataVisita) {
        this.DataVisita = DataVisita;
    }

    public void exibirInfo() {
        System.out.println("Nome: " + getNome());
        System.out.println("CPF: " + getCpf());
        System.out.println("Data da Visita: " + DataVisita);
    }