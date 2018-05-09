package br.com.bean;

public class ContatoBean {
    private int id;
    private String nome;
    private String email;
    private String endereco;
    private Data data_nascimento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Data getData_nascimento() {
        return data_nascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public void setData_nascimento(Data data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    
    
    
    
}
