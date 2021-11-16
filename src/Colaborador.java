import java.math.BigDecimal;
import java.util.Objects;

public class Colaborador implements Comparable<Colaborador>{

    private Long uid;
    private String nome;
    private BigDecimal salario;

    public Colaborador() {}

    public Colaborador(Long uid, String nome, BigDecimal salario) {
        this.uid = uid;
        this.nome = nome;
        this.salario = salario;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Colaborador that = (Colaborador) o;
        return uid.equals(that.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid);
    }

    @Override
    public int compareTo(Colaborador colaborador) {
        return this.getUid().compareTo(colaborador.getUid());
    }
}
