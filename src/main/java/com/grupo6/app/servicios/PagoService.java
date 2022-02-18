package com.grupo6.app.servicios;

import java.util.List;
import com.grupo6.app.entidades.Pago;

public interface PagoService {
    public List<Pago> listarPagos();
    public void eliminarPago(Integer id);
    public void crearPago(Pago Pago);
    public Pago findByIdPago(Integer id);
}
