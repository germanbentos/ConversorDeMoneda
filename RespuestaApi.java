
package programas.java.conversondemoneda;

public class RespuestaApi {
    private String resultado;
    private ConversionDinero conversion_dinero;

    // Getters y setters
    public String getResultado() {
        return resultado;
    }

    public void setResult(String resultado) {
        this.resultado = resultado;
    }

    public ConversionDinero getConversionDinero() {
        return conversion_dinero;
    }

    public void setConversionDinero(ConversionDinero conversion_dinero) {
        this.conversion_dinero = conversion_dinero;
    }
}
