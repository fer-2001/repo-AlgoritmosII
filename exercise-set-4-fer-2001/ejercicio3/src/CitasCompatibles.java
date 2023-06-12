import java.util.ArrayList;

public class CitasCompatibles {

    private ArrayList<Cita> citasCompatibles;
    private ArrayList<Cita> citasPaciente;


    public CitasCompatibles(ArrayList<Cita> citasPaciente){
        this.citasPaciente = citasPaciente;
        citasCompatibles = new ArrayList<>();
    }

    // Tiempo de ejecución en el peor caso O(n^3)
    public ArrayList<Cita> maxCitas(){
        boolean solapados=false;
        int n = citasPaciente.size();
        if(citasPaciente.size() == 0){
            throw new ArrayIndexOutOfBoundsException("Vacio");
        }
        Cita aux;
        for(int i = n-1; i>0; i--){
            aux = citasPaciente.get(i);
                for(int j=0; j<citasCompatibles.size();j++){
                    solapados=false;
                    if(Cita.seSolapan(aux,citasCompatibles.get(j))){
                        j=citasCompatibles.size();
                        solapados=true;
                    }
                }
                if(!solapados){
                    citasCompatibles.add(aux);
                }

        }

        return citasCompatibles;
    }


    public static void main(String[] args){
        Cita c1 = new Cita(18,10,19,6);
        Cita c2 = new Cita(18,8,9,7);
        Cita c3 = new Cita(18,16,20,8);
        //Cita c4 = new Cita(1,1,3);
        //Cita c5 = new Cita(2,2,10);
        //Cita c6 = new Cita(3,3,1);
        ArrayList<Cita> array = new ArrayList<>();
        array.add(c1);
        array.add(c2);
        array.add(c3);
        //array.add(c4);
        //array.add(c5);
        //array.add(c6);
        array.sort(Cita::compareTo);

        CitasCompatibles test1 = new CitasCompatibles(array);
        ArrayList<Cita> salida = test1.maxCitas();
        System.out.println((salida));


    }
    @Override
    public String toString(){
        String out="";
        for(int i = 0; i<citasCompatibles.size(); i++){
            Cita aux = citasCompatibles.get(i);
            out = out + aux.toString();
        }
        return out;
    }

}
