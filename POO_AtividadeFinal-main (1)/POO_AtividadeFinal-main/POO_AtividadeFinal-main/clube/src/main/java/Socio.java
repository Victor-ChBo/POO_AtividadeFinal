public class Socio extends Pessoa {
    private int numeroSocio;


    public Socio(String nome, String cpf, String email, int numeroSocio) {
        super(nome, cpf, email);
        this.numeroSocio = numeroSocio;
    }

    public int getNumeroSocio() {
        return numeroSocio;
    }

    public void setNumeroSocio(int numeroSocio) {
        this.numeroSocio = numeroSocio;
    }


    public void exibirInfo() {
        System.out.println("Nome: " + getNome());
        System.out.println("CPF: " + getCpf());
        System.out.println("Número do Sócio: " + numeroSocio);
    }

    @Override
    public String toString() {
        return "Socio{" +
                "nome='" + getNome() + '\'' +
                ", cpf='" + getCpf() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", numeroSocio=" + numeroSocio 
                + '}';
    }
}