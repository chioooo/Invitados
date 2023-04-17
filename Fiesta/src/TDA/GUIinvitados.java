package TDA;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

public class GUIinvitados extends JFrame implements ActionListener, ItemListener, ChangeListener {

    private Invitados lista;
    private Container contenedor;
    private JLabel nombre;
    private JLabel edad;
    private JLabel sexo;
    private JLabel edoCivil;
    private JTextField cNombre;
    private JTextField cEdad;
    private JButton añadir;
    private JButton resultados;
    private JList listaInvitados;
    private DefaultListModel modelo;
    private JScrollPane scrollLista;
    private JCheckBox f;
    private JCheckBox m;
    private JCheckBox sex;
    private JRadioButton soltero;
    private JRadioButton casado;
    private JRadioButton divorciado;
    private JRadioButton viudo;
    private JRadioButton estado;
    private ButtonGroup grupoBotones;

    public GUIinvitados(){
        lista=new Invitados();
        inicio();
        setTitle("Estadisticas de invitados");
        setSize(600,650);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void inicio(){
        contenedor=getContentPane();
        contenedor.setLayout(null);

        grupoBotones = new ButtonGroup();

        nombre = new JLabel();
        nombre.setText("Nombre: ");
        nombre.setBounds(10, 10, 100, 30);
        cNombre = new JTextField();
        cNombre.setBounds(90, 10, 250, 30);

        edad = new JLabel();
        edad.setText("Edad: ");
        edad.setBounds(10, 45, 100, 30);
        cEdad = new JTextField();
        cEdad.setBounds(90, 45, 150, 30);

        sexo = new JLabel();
        sexo.setText("Sexo: ");
        sexo.setBounds(10, 80, 100, 30);

        f = new JCheckBox("femenino");
        f.setBounds(90, 80, 80, 30);
        f.addItemListener(this);
        m = new JCheckBox("masculino");
        m.setBounds(170, 80, 150, 30);
        m.addItemListener(this);

        edoCivil = new JLabel();
        edoCivil.setText("Edo civil: ");
        edoCivil.setBounds(10, 115, 100, 30);

        soltero=new JRadioButton("soltero/a");
        soltero.setBounds(90, 115, 80, 30);
        soltero.addChangeListener(this);
        grupoBotones.add(soltero);

        casado=new JRadioButton("casado/a");
        casado.setBounds(180, 115, 80, 30);
        casado.addChangeListener(this);
        grupoBotones.add(casado);

        divorciado=new JRadioButton("divorciado/a");
        divorciado.setBounds(270, 115, 100, 30);
        divorciado.addChangeListener(this);
        grupoBotones.add(divorciado);

        viudo=new JRadioButton("viudo/a");
        viudo.setBounds(370, 115, 150, 30);
        viudo.addChangeListener(this);
        grupoBotones.add(viudo);

        añadir = new JButton ("Añadir");
        añadir.setBounds(10, 520, 80, 23);
        añadir.addActionListener(this);

        resultados = new JButton ("Resultados");
        resultados.setBounds(100, 520, 120, 23);
        resultados.addActionListener(this);
        setVisible(true);

        listaInvitados=new JList();
        listaInvitados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        modelo=new DefaultListModel();

        scrollLista=new JScrollPane();
        scrollLista.setBounds(85, 200, 420, 300);
        scrollLista.setViewportView(listaInvitados);

        contenedor.add(nombre);
        contenedor.add(cNombre);
        contenedor.add(edad);
        contenedor.add(cEdad);
        contenedor.add(sexo);
        contenedor.add(m);
        contenedor.add(f);
        contenedor.add(edoCivil);
        contenedor.add(soltero);
        contenedor.add(casado);
        contenedor.add(divorciado);
        contenedor.add(viudo);
        contenedor.add(añadir);
        contenedor.add(resultados);
        contenedor.add(scrollLista);
    }

    public JCheckBox sexo(){
        if(f.isSelected()){
            sex=f;
        }
        if(m.isSelected()){
            sex=m;
        }
        return sex;
    }

    public JRadioButton estado(){
        if(soltero.isSelected()){
            estado=soltero;
        }
        if(casado.isSelected()){
            estado=casado;
        }
        if(divorciado.isSelected()){
            estado=divorciado;
        }
        if(viudo.isSelected()){
            estado=viudo;
        }
        return estado;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==añadir){
            añadirAlumno();
        }
        if(e.getSource()==resultados){
            resultadosInvitados();
            graficaBarra(creaDatosCategory());
        }
    }

    private void añadirAlumno() {
        Invitado p = new Invitado(cNombre.getText(),
                (Byte.parseByte(cEdad.getText())),
                sexo().getText(), estado().getText());
        lista.añadir(p);
        cNombre.setText("");
        cEdad.setText("");
        this.f.setSelected(false);
        this.m.setSelected(false);
        grupoBotones.clearSelection();
    }
    private void resultadosInvitados(){
        modelo.addElement("El total de invitados es: "+lista.totalnvitados());
        modelo.addElement("El total de mayores de edad es: " + lista.totalMayor(18));
        modelo.addElement("El total de menores de edad es: " + lista.totalMenor(18));
        modelo.addElement("El total de personas con genero femenino es: " + lista.totalGenero("femenino"));
        modelo.addElement("El total de personas con genero masculino es: " + lista.totalGenero("masculino"));
        modelo.addElement("El total de solteros es: " + lista.totalEstadoCivil("soltero/a"));
        modelo.addElement("El total de casados es: " + lista.totalEstadoCivil("casado/a"));
        modelo.addElement("El total de divorciados es: " + lista.totalEstadoCivil("divorciado/a"));
        modelo.addElement("El total de viudos es: " + lista.totalEstadoCivil("viudo/a"));
        modelo.addElement("El porcentaje de genero femenino es: " +
                ((float)lista.totalGenero("femenino")/(float)lista.totalnvitados())*100 + "%");
        modelo.addElement("El porcentaje de genero masculino es: " +
                ((float)lista.totalGenero("masculino")/(float)lista.totalnvitados())*100 + "%");
        listaInvitados.setModel(modelo);
    }

    public  CategoryDataset creaDatosCategory()
    {
        DefaultCategoryDataset datos=new DefaultCategoryDataset();

        datos.setValue(lista.totalnvitados(),"invitado","Total invitados");
        datos.setValue(lista.totalMayor(18),"invitado","Mayores de edad");
        datos.setValue(lista.totalMenor(18),"invitado","Menores de edad");
        datos.setValue(lista.totalGenero("femenino"),"invitado","Genero femenino");
        datos.setValue(lista.totalGenero("masculino"),"invitado","Genero masculino");
        datos.setValue(lista.totalEstadoCivil("soltero/a"),"invitado","Total solteros");
        datos.setValue(lista.totalEstadoCivil("casado/a"),"invitado","total casados");
        datos.setValue(lista.totalEstadoCivil("divorciado/a"),"invitado","Total divorciados");
        datos.setValue(lista.totalEstadoCivil("viudo/a"),"invitado","Total viudos");
        datos.setValue(((float)lista.totalGenero("femenino")/(float)lista.totalnvitados())*100,
                "invitado","Porcentaje femenino");
        datos.setValue(((float)lista.totalGenero("masculino")/(float)lista.totalnvitados())*100,
                "invitado","Porcentaje masculino");
        return datos;
    }

    public  void panelJframe(JFreeChart grafica){
        ChartPanel panel= new ChartPanel(grafica);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(600,400));
        JFrame ventana = new JFrame("Grafica"); // titulo del frame
        ventana.setVisible(true);
        ventana.setSize(1300, 600); // tamaño del Jframe
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.add(panel);// se agrega al Jframe el panel(Grafico)
    }

    public  void grabaFileJPG(JFreeChart grafica){
        try {
            ChartUtilities.saveChartAsJPEG(new File
                    ("C://Users//rocio//OneDrive//Escritorio//grafica_Barras.jpg"),
                    grafica, 800,500);
        } catch (IOException e) {
            System.err.println("Error creando grafico.");
        }
    }

    public  void graficaBarra(CategoryDataset datos)
    {
        JFreeChart grafico;
        grafico = ChartFactory. createBarChart ("Estadisticas" , null ,
                "Estadistica" ,datos , PlotOrientation.HORIZONTAL , false ,
                true , false );
        panelJframe(grafico);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
    }
    @Override
    public void stateChanged(ChangeEvent e) {
    }
}
