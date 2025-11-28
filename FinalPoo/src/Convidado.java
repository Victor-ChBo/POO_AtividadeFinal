public class Convidado extends Pessoa {
    private String dataVisita;

    // Construtor
    public Convidado(String nome, String cpf, String email, String dataVisita) {
        super(nome, cpf, email);
        this.dataVisita = dataVisita;
    }

    public String getDataVisita() {
        return dataVisita;
    }

    public void setDataVisita(String dataVisita) {
        this.dataVisita = dataVisita;
    }

    public void exibirInfo() {
        System.out.println("Nome: " + getNome());
        System.out.println("CPF: " + getCpf());
        System.out.println("Data da Visita: " + dataVisita);
    }

    @Override
    public String toString() {
        return "Convidado{" +
                "nome='" + getNome() + '\'' +
                ", cpf='" + getCpf() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", dataVisita='" + dataVisita + '\'' +
                '}';
    }
}