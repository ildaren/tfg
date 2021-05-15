package data;

import java.util.ArrayList;

public class Reservas {

    private String href;
    private String prev;
    private String next;
    private ArrayList <Reserva> reservas;

    public Reservas() {
        this.reservas = new ArrayList <Reserva>(); 
    }

    public Reservas(String href, String prev, String next, ArrayList<Reserva> reservas) {
        this.href = href;
        this.prev = prev;
        this.next = next;
        this.reservas = reservas;
    }


    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    public void addReserva(Reserva reserva) {
        this.reservas.add(reserva);
    }
}
