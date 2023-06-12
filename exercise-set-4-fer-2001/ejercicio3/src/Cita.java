
public class Cita implements Comparable<Cita>{
    private String descripcion;
    private int fecha;
    private int horaInicio;
    private int horaFin;
    private int prioridad;

    public Cita(String descripcion, int fecha, int horaInicio, int horaFin, int prioridad) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.prioridad=prioridad;
    }
    public Cita(int fecha, int horaInicio, int horaFin,int prioridad) {
        this.fecha = fecha;
        this.prioridad=prioridad;
        this.horaInicio = horaInicio;
    }

    public static boolean seSolapan(Cita c1, Cita c2){
        return (c1.getFecha() == c2.getFecha()) && (c1.getHoraInicio() == c2.getHoraInicio()
                && c1.horaFin > c2.horaInicio);
    }

    // Getter y setter de cada atributo

    private void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    private void setFecha(int fecha) {
        this.fecha = fecha;
    }

    private void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    private void setHoraFin(int horaFin) {
        this.horaFin = horaFin;
    }

    private int getHoraFin() {
        return horaFin;
    }

    private String getDescripcion() {
        return descripcion;
    }

    private int getFecha() {
        return fecha;
    }

    private int getHoraInicio() {
        return horaInicio;
    }

    private void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    private int getPrioridad() {
        return prioridad;
    }

    @Override
    public String toString() {
        return "Prioridad :" + this.getPrioridad() + "\n";
    }

    @Override
    public int compareTo(Cita o)
    {
        if (this.getPrioridad() > o.getPrioridad()) {
            return 1;
        }else if (this.getPrioridad() < o.getPrioridad())
        {
            return  -1 ;
        } else { return 0 ; }
    }

}
