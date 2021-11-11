package co.com.nequi.model.customerdefaultdata;

public class CustomerDefaultData {

    private Integer datoDefectoId;
    private String nombre;
    private String valorDefecto;
    private String usrCreacion;
    private String fechaCreacion;
    private String usrModificacion;
    private String fechaModificacion;
    private String servicioId;

    public Integer getDatoDefectoId() {
        return datoDefectoId;
    }

    public void setDatoDefectoId(Integer datoDefectoId) {
        this.datoDefectoId = datoDefectoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValorDefecto() {
        return valorDefecto;
    }

    public void setValorDefecto(String valorDefecto) {
        this.valorDefecto = valorDefecto;
    }

    public String getUsrCreacion() {
        return usrCreacion;
    }

    public void setUsrCreacion(String usrCreacion) {
        this.usrCreacion = usrCreacion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsrModificacion() {
        return usrModificacion;
    }

    public void setUsrModificacion(String usrModificacion) {
        this.usrModificacion = usrModificacion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getServicioId() {
        return servicioId;
    }

    public void setServicioId(String servicioId) {
        this.servicioId = servicioId;
    }

    @Override
    public String toString() {
        return "CustomerDefaultData{" +
                "datoDefectoId=" + datoDefectoId +
                ", nombre='" + nombre + '\'' +
                ", valorDefecto='" + valorDefecto + '\'' +
                ", usrCreacion='" + usrCreacion + '\'' +
                ", fechaCreacion='" + fechaCreacion + '\'' +
                ", usrModificacion='" + usrModificacion + '\'' +
                ", fechaModificacion='" + fechaModificacion + '\'' +
                ", servicioId='" + servicioId + '\'' +
                '}';
    }
}
