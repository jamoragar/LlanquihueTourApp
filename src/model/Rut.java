package model;

import exceptions.RutInvalidoException;

/**
 * Objeto de valor que representa y valida un RUT chileno.
 */
public class Rut {

    private int numero;
    private char digitoVerificador;

    public Rut(String rutCompleto) throws RutInvalidoException {
        asignar(rutCompleto);
    }

    public Rut(Rut otro) {
        if (otro == null) {
            throw new IllegalArgumentException("El RUT de origen es obligatorio.");
        }
        numero = otro.numero;
        digitoVerificador = otro.digitoVerificador;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) throws RutInvalidoException {
        validar(numero, digitoVerificador);
        this.numero = numero;
    }

    public char getDigitoVerificador() {
        return digitoVerificador;
    }

    public void setDigitoVerificador(char digitoVerificador) throws RutInvalidoException {
        validar(numero, digitoVerificador);
        this.digitoVerificador = Character.toUpperCase(digitoVerificador);
    }

    public void setRutCompleto(String rutCompleto) throws RutInvalidoException {
        asignar(rutCompleto);
    }

    private void asignar(String rutCompleto) throws RutInvalidoException {
        if (rutCompleto == null) {
            throw new RutInvalidoException("El RUT es obligatorio.");
        }

        String normalizado = rutCompleto.trim().replace(".", "").toUpperCase();
        if (!normalizado.matches("[0-9]{7,8}-[0-9K]")) {
            throw new RutInvalidoException("El RUT debe usar el formato 12345678-5.");
        }

        String[] partes = normalizado.split("-");
        int nuevoNumero;
        try {
            nuevoNumero = Integer.parseInt(partes[0]);
        } catch (NumberFormatException ex) {
            throw new RutInvalidoException("El número del RUT no es válido.");
        }

        char nuevoDigito = partes[1].charAt(0);
        validar(nuevoNumero, nuevoDigito);
        numero = nuevoNumero;
        digitoVerificador = nuevoDigito;
    }

    private static void validar(int numero, char digitoVerificador) throws RutInvalidoException {
        if (numero < 1_000_000 || numero > 99_999_999) {
            throw new RutInvalidoException("El número del RUT debe contener 7 u 8 dígitos.");
        }

        char digitoNormalizado = Character.toUpperCase(digitoVerificador);
        if (digitoNormalizado != calcularDigitoVerificador(numero)) {
            throw new RutInvalidoException("El dígito verificador del RUT no es válido.");
        }
    }

    private static char calcularDigitoVerificador(int numero) {
        int suma = 0;
        int multiplicador = 2;
        int restante = numero;

        while (restante > 0) {
            suma += (restante % 10) * multiplicador;
            restante /= 10;
            multiplicador = multiplicador == 7 ? 2 : multiplicador + 1;
        }

        int resultado = 11 - (suma % 11);
        if (resultado == 11) {
            return '0';
        }
        if (resultado == 10) {
            return 'K';
        }
        return Character.forDigit(resultado, 10);
    }

    @Override
    public String toString() {
        return numero + "-" + digitoVerificador;
    }
}
