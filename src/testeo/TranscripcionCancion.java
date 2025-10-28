package testeo;

public class TranscripcionCancion {
    private String letra;
    private double tiempo;

    public TranscripcionCancion(String letra, double tiempo) {
        this.letra = letra;
        this.tiempo = tiempo;
    }

    public String getLetra() {
        return letra;
    }

    public double getTiempo() {
        return tiempo;
    }
}