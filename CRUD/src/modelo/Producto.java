package modelo;

public class Producto {

    private String id;
    private String nom;
    private String cod;
    private String pre;

    public Producto() {

    }

    public Producto(String id, String nom, String cod, String pre) {
       this.id = id;
        this.nom = nom;
        this.cod = cod;
        this.pre = pre;
    }
 
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getPre() {
        return pre;
    }

    public void setPre(String pre) {
        this.pre = pre;
    }
    
    

    
}