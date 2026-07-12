package model;

/**
 * Representa una empresa externa que presta servicios a la agencia.
 */
public class ColaboradorExterno extends RecursoAgencia implements Registrable {

    private String empresa;
    private String servicioPrestado;

    public ColaboradorExterno() {
        this(1, "Sin información", "Sin información", "Sin información");
    }

    public ColaboradorExterno(int id, String nombre, String empresa, String servicioPrestado) {
        super(id, nombre);
        setEmpresa(empresa);
        setServicioPrestado(servicioPrestado);
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        if (empresa != null && !empresa.trim().isEmpty()) {
            this.empresa = empresa.trim();
        } else {
            this.empresa = "Sin información";
        }
    }

    public String getServicioPrestado() {
        return servicioPrestado;
    }

    public void setServicioPrestado(String servicioPrestado) {
        if (servicioPrestado != null && !servicioPrestado.trim().isEmpty()) {
            this.servicioPrestado = servicioPrestado.trim();
        } else {
            this.servicioPrestado = "Sin información";
        }
    }

    @Override
    public String mostrarResumen() {
        return "Colaborador externo: " + empresa
                + " | Servicio: " + servicioPrestado
                + " | Contacto: " + getNombre();
    }

    @Override
    public String toString() {
        return super.toString() + " | Empresa: " + empresa
                + " | Servicio: " + servicioPrestado;
    }
}
