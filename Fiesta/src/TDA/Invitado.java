package TDA;

public class Invitado {

    private String nombre;
    private byte edad;
    private String sexo;
    private String edoCivil;

    public Invitado(String nombre, byte edad, String sexo, String edoCivil) {
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.edoCivil = edoCivil;
    }

    public Invitado(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte getEdad() {
        return edad;
    }

    public void setEdad(byte edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEdoCivil() {
        return edoCivil;
    }

    public void setEdoCivil(String edoCivil) {
        this.edoCivil = edoCivil;
    }

    @Override
    public String toString() {
        return "Invitado{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", sexo='" + sexo + '\'' +
                ", edoCivil='" + edoCivil + '\'' +
                '}';
    }
}