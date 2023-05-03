package util.sequences;

import java.util.ArrayList;
import java.util.List;

public class Subsecuencias {

    public static void main(String[] args) {
        String secuencia = "abc";
        List<String> subsecuencias = calcularSubsecuencias(secuencia);
        System.out.println("Subsecuencias de la secuencia " + secuencia + ": ");
        for (String subsecuencia : subsecuencias) {
            System.out.println(subsecuencia);
        }
    }

    public static List<String> calcularSubsecuencias(String secuencia) {
        List<String> subsecuencias = new ArrayList<>();
        subsecuencias.add(""); // subsecuencia vacía
        calcularSubsecuenciasAux(secuencia, "", 0, subsecuencias);
        return subsecuencias;
    }

    private static void calcularSubsecuenciasAux(String secuencia, String subsecuenciaActual, int indice, List<String> subsecuencias) {
        int longitud = secuencia.length();
        if (indice == longitud) {
            return;
        }
        for (int i = indice; i < longitud; i++) {
            subsecuenciaActual += secuencia.charAt(i);
            subsecuencias.add(subsecuenciaActual);
            calcularSubsecuenciasAux(secuencia, subsecuenciaActual, i + 1, subsecuencias);
            subsecuenciaActual = subsecuenciaActual.substring(0, subsecuenciaActual.length() - 1);
        }
    }
}