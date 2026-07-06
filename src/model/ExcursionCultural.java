package model;

/**
 * Servicio turístico orientado al patrimonio y la cultura local.
 */
public class ExcursionCultural extends ServicioTuristico {

    private String lugarHistorico;

    public ExcursionCultural() {
        super();
        this.lugarHistorico = "Sin información";
    }

    public ExcursionCultural(String nombre, double duracionHoras, UbicacionServicio ubicacion, String lugarHistorico) {
        super(nombre, duracionHoras);
        setUbicacion(ubicacion);
        setLugarHistorico(lugarHistorico);
    }

    public String getLugarHistorico() {
        return lugarHistorico;
    }

    public void setLugarHistorico(String lugarHistorico) {
        if (lugarHistorico != null && !lugarHistorico.trim().isEmpty()) {
            this.lugarHistorico = lugarHistorico;
        } else {
            System.out.println("El lugar histórico no puede estar vacío. Se asigna valor por defecto.");
            this.lugarHistorico = "Sin información";
        }
    }

    @Override
    public String mostrarInformacion() {
        return toString();
    }

    @Override
    public String toString() {
        return "Excursión Cultural: " + super.toString() +
                " | Lugar histórico: " + lugarHistorico;
    }
}
