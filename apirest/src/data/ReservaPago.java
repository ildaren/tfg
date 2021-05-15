package data;

public class ReservaPago {
    
    private Reserva reserva;
    private Pago pago;

    public ReservaPago () {}

    public ReservaPago (Reserva reserva, Pago pago) {

        this.reserva = reserva;
        this.pago = pago;
    } 
    
    public Reserva getReserva() {
        return reserva;
    }
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Pago getPago() {
        return pago;
    }
    public void setPago(Pago pago) {
        this.pago = pago;
    }
}
