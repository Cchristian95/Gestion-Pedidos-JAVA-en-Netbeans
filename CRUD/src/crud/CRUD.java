
package crud;

import Controlador.Controlador;
import Vista.vista;

public class CRUD {

    public static void main(String[] args) {
        vista v = new vista();
        Controlador c = new Controlador(v);
        v.setVisible(true);
        c.listar(v.Tabla);
    }
    
}
