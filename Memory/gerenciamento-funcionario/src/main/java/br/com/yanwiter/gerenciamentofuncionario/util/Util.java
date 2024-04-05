package br.com.yanwiter.gerenciamentofuncionario.util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Util {

    public static boolean isValid(String cpf) {
        if (cpf == null || cpf.length() != 11)
            return false;

        if (temDigitosIguais(cpf))
            return false;

        int primeiroDigitoVerificador = calcularDigitoVerificador(cpf, 9);
        if (!verificarDigitoVerificador(cpf, primeiroDigitoVerificador, 9))
            return false;

        int segundoDigitoVerificador = calcularDigitoVerificador(cpf, 10);
        return verificarDigitoVerificador(cpf, segundoDigitoVerificador, 10);
    }

    public static String formatarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11)
            return cpf;

        return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9);
    }

    private static boolean temDigitosIguais(String cpf) {
        return cpf.matches("(\\d)\\1{10}");
    }

    private static int calcularDigitoVerificador(String cpf, int posicao) {
        int soma = 0;
        for (int i = 0; i < posicao; i++) {
            soma += (cpf.charAt(i) - '0') * (posicao + 1 - i);
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }

    private static boolean verificarDigitoVerificador(String cpf, int digitoVerificador, int posicao) {
        return (cpf.charAt(posicao) - '0') == digitoVerificador;
    }

    public static String formatarRemuneracao(String remuneracao) {
        if (remuneracao == null || remuneracao.isEmpty())
            return "0,00";

        try {
            NumberFormat formatoLocal = NumberFormat.getNumberInstance(Locale.getDefault());
            double valor = formatoLocal.parse(remuneracao).doubleValue();
            NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            return formatoMoeda.format(valor);
        } catch (ParseException e) {
            return remuneracao;
        }
    }

    public static String ObterDataFormatada(String dataString){
        try {
            DateFormat formatoEntrada = new SimpleDateFormat("dd-MM-yyyy");
            DateFormat formatoSaida = new SimpleDateFormat("dd/MM/yyyy");
            Date data = formatoEntrada.parse(dataString);
            return formatoSaida.format(data);
        } catch (ParseException e) {
            return dataString;
        }
    }

}
