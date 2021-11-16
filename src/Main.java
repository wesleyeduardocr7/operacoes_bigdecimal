import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

public class Main {

    public static void main(String[] args) {
        //TODO. BigDecimal é muito utilizado quando se ter necessidade de trabalhar com valores quebrados e de alta precisão, com arredondamentos e etc.
        // Além disso essa classe oferece muitas funções que servem de apoio para uma programação mais simples e segura.
        List<Colaborador> colaboradores = geraColaboradores();
        operacoesBasicas(colaboradores);
        comparacaoDeBigDecimals(colaboradores);
        maiorEMenorSalario(colaboradores);
        comparacaoComStrings(colaboradores);
        expoenteERaiz(colaboradores);
        valorAbsoluto(colaboradores);
        valorComRetornoNegativo(colaboradores);
        somaSalariosComFiltrosEListaNulaOuVazia(colaboradores);
    }

    private static List<Colaborador> geraColaboradores() {

        List<Colaborador> colaboradores = new ArrayList<>();

        colaboradores.add(new Colaborador(1L,"Murilo",BigDecimal.ZERO));
        colaboradores.add(new Colaborador(2L,"Carlos",BigDecimal.ZERO));

        colaboradores.add(new Colaborador(3L,"Anderson",BigDecimal.ONE));
        colaboradores.add(new Colaborador(4L,"Giorgy",BigDecimal.ONE));

        colaboradores.add(new Colaborador(5L,"Decio",BigDecimal.TEN));
        colaboradores.add(new Colaborador(6L,"Eduardo",BigDecimal.TEN));

        colaboradores.add(new Colaborador(7L,"Ronison",null));
        colaboradores.add(new Colaborador(8L,"Antuniis",null));

        colaboradores.add(new Colaborador(9L,"Daniel",BigDecimal.valueOf(50.45345)));
        colaboradores.add(new Colaborador(10L,"Wesley",BigDecimal.valueOf(550.55564564)));
        colaboradores.add(new Colaborador(11L,"Andre",BigDecimal.valueOf(20)));
        colaboradores.add(new Colaborador(12L,"andre",BigDecimal.valueOf(40)));

        colaboradores.add(new Colaborador(15L,"Gabriel",BigDecimal.valueOf(10.5454)));
        colaboradores.add(new Colaborador(15L,"Luis",BigDecimal.valueOf(10.7459)));
        colaboradores.add(new Colaborador(15L,"Maria",BigDecimal.valueOf(11.845)));
        colaboradores.add(new Colaborador(15L,"Leticia",BigDecimal.valueOf(12.345)));

        colaboradores.add(new Colaborador(20L,"Neymar",BigDecimal.valueOf(30)));
        colaboradores.add(new Colaborador(20L,"Cristiano",BigDecimal.valueOf(34)));

        colaboradores.add(new Colaborador(25L,"Messi",BigDecimal.valueOf(10)));
        colaboradores.add(new Colaborador(25L,"Vini jr",BigDecimal.valueOf(-15)));

        return colaboradores;
    }

    private static void operacoesBasicas(List<Colaborador> colaboradores) {

        BigDecimal soma = colaboradores.stream()
                .map(Colaborador::getSalario)
                .filter(salarioColaborador -> Objects.nonNull(salarioColaborador))
                .reduce(BigDecimal.ZERO,BigDecimal::add)
                .setScale(4,BigDecimal.ROUND_DOWN);

        BigDecimal subtracao = colaboradores.stream()
                .map(Colaborador::getSalario)
                .filter(salarioColaborador -> Objects.nonNull(salarioColaborador))
                .reduce(BigDecimal.ZERO,BigDecimal::subtract)
                .setScale(2,BigDecimal.ROUND_UP);

        BigDecimal multiplicacao = colaboradores.stream()
                .map(Colaborador::getSalario)
                .filter(salarioColaborador -> Objects.nonNull(salarioColaborador))
                .reduce(BigDecimal.ZERO,BigDecimal::add)
                .setScale(4,BigDecimal.ROUND_DOWN)
                .multiply(new BigDecimal(2));

        BigDecimal divisao = colaboradores.stream()
                .map(Colaborador::getSalario)
                .filter(salarioColaborador -> Objects.nonNull(salarioColaborador))
                .reduce(BigDecimal.ZERO,BigDecimal::subtract)
                .setScale(2,BigDecimal.ROUND_UP)
                .divide(BigDecimal.valueOf(10));

        BigDecimal todasAsOperacoes = colaboradores.stream()
                .map(Colaborador::getSalario)
                .filter(salarioColaborador -> Objects.nonNull(salarioColaborador))
                .reduce(BigDecimal.ZERO,BigDecimal::add)
                .subtract(BigDecimal.valueOf(10))
                .multiply(BigDecimal.valueOf(5))
                .divide(new BigDecimal(100));
    }

    private static void comparacaoDeBigDecimals(List<Colaborador> colaboradores) {

        // TODO Comparações em BigDecimal devem ser usadas com o CompareTo, onde a comparação de dois valores a e b
        //  retorna -1 se a < b, 0 se a = b e 1 se a > b

        List<Colaborador> colaboradoresComsalarioMenorQue10 = colaboradores.stream()
                .filter(colaborador-> Objects.nonNull(colaborador.getSalario()))
                .filter(colaborador-> colaborador.getSalario().compareTo(BigDecimal.valueOf(10)) == -1)
                .collect(Collectors.toList());

        List<Colaborador> colaboradoresComsalarioIgual10 = colaboradores.stream()
                .filter(colaborador-> Objects.nonNull(colaborador.getSalario()))
                .filter(colaborador-> colaborador.getSalario().compareTo(BigDecimal.valueOf(10)) == 0)
                .collect(Collectors.toList());

        List<Colaborador> colaboradoresComsalarioMaiorQue10 = colaboradores.stream()
                .filter(colaborador-> Objects.nonNull(colaborador.getSalario()))
                .filter(colaborador-> colaborador.getSalario().compareTo(BigDecimal.valueOf(10)) == 1)
                .collect(Collectors.toList());
    }

    private static void comparacaoComStrings(List<Colaborador> colaboradores) {
        BigDecimal somaDosSalariosDosColaboradoresComNomeAndre = colaboradores.stream()
                .filter(colaborador->colaborador.getNome().equalsIgnoreCase("Andre"))
                .map(Colaborador::getSalario)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
    }

    private static void maiorEMenorSalario(List<Colaborador> colaboradores) {

        //TODO Retorna a primeira posição da lista dos colaboradores com Menor ou Maior Salário, caso tenha valores iguais

        Colaborador colaboradorComMenorSalario = colaboradores.stream()
                .filter(colaborador -> Objects.nonNull(colaborador.getSalario()))
                .min(Comparator.comparing(Colaborador::getSalario)).get();

        Colaborador colaboradorComMaiorSalario = colaboradores.stream()
                .filter(colaborador -> Objects.nonNull(colaborador.getSalario()))
                .max(Comparator.comparing(Colaborador::getSalario)).get();
    }

    private static void expoenteERaiz(List<Colaborador> colaboradores) {
        BigDecimal expoente = colaboradores.stream()
                .filter(colaborador->colaborador.getUid().equals(20L))
                .map(Colaborador::getSalario)
                .reduce(BigDecimal.ZERO,BigDecimal::add)
                .pow(2);

        // TODO para BigDecimal, até o momento não tem uma função prória para calcular a raiz quadrada..mas...dependendo da precisão...
        BigDecimal raiz = BigDecimal.valueOf(Math.pow(expoente.doubleValue(), 0.5));
    }

    private static void valorAbsoluto(List<Colaborador> colaboradores) {
        //TODO caso seja retornado valor negativo, o abs devolve o valor aboluto ou modulo
        BigDecimal valorAbsoluto = colaboradores.stream()
                .filter(colaborador->colaborador.getUid().equals(25L))
                .map(Colaborador::getSalario)
                .reduce(BigDecimal.ZERO,BigDecimal::add)
                .abs();
    }

    private static void valorComRetornoNegativo(List<Colaborador> colaboradores) {
        // TODO caso o retorno seja negativo, o .negate devolve um positivo, e vice versa
        BigDecimal valorAbsoluto = colaboradores.stream()
                .filter(colaborador->colaborador.getUid().equals(20L))
                .map(Colaborador::getSalario)
                .reduce(BigDecimal.ZERO,BigDecimal::add)
                .negate();
    }

    private static void somaSalariosComFiltrosEListaNulaOuVazia(List<Colaborador> colaboradores) {

        BigDecimal somaComFiltros = Optional.ofNullable(colaboradores)
                .orElseGet(Collections::emptyList).stream()
                    .filter(colaborador->Objects.nonNull(colaborador.getSalario()))
                        .filter(colaborador->colaborador.getUid().equals(15L))
                             .filter(colaborador-> colaborador.getSalario().compareTo(BigDecimal.valueOf(11)) == -1)
                                 .map(Colaborador::getSalario)
                                     .reduce(BigDecimal.ZERO,BigDecimal::add)
                                        .setScale(2, ROUND_HALF_DOWN);
    }
}


