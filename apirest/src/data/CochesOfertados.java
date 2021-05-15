package data;

import java.util.ArrayList;

public class CochesOfertados {
    
    private String href;
    private String prev;
    private String next;
    private ArrayList<CocheOfertado> cochesOfertados;

    public CochesOfertados() {
        this.cochesOfertados = new ArrayList<CocheOfertado>();
    }

    public CochesOfertados(String href, String prev, String next, ArrayList<CocheOfertado> cochesOfertados) {
        this.href = href;
        this.prev = prev;
        this.next = next;
        this.cochesOfertados = cochesOfertados;
    }

    
    public String getHref() {
        return this.href;
    }

    public void setHref(String href) {
        this.href = href;
    }


    public String getPrev() {
        return this.prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }


    public String getNext() {
        return this.next;
    }

    public void setNext(String next) {
        this.next = next;
    }


    public ArrayList<CocheOfertado> getCochesOfertados() {
        return this.cochesOfertados;
    }

    public void setCochesOfertados(ArrayList<CocheOfertado> cochesOfertados) {
        this.cochesOfertados = cochesOfertados;
    }

    public void addCoche(CocheOfertado coche) {
        this.cochesOfertados.add(coche);
    }
}
