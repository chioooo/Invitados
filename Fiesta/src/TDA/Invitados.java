package TDA;

import java.util.ArrayList;

public class Invitados {
    ArrayList<Invitado> invitados;
    public Invitados(){
        invitados = new <Invitado> ArrayList();
    }

    public void añadir(Invitado a){
        invitados.add(a);
    }

    public int totalnvitados(){
        return invitados.size();
    }

    public int totalGenero(String genero) {
        return invitados.stream().mapToInt(
                invitado -> invitado.getSexo().equals(genero) ? 1 : 0
        ).sum();
    }

    public int totalEstadoCivil(String edoCivil) {
        return invitados.stream().mapToInt(
                invitado -> invitado.getEdoCivil().equals(edoCivil) ? 1 : 0
        ).sum();
    }
    public int totalMayor(int edad) {
        return invitados.stream().mapToInt(
                invitado -> invitado.getEdad() >= edad ? 1 : 0
        ).sum();
    }

    public int totalMenor(int edad) {
        return invitados.stream().mapToInt(
                invitado -> invitado.getEdad() < edad ? 1 : 0
        ).sum();
    }
}
