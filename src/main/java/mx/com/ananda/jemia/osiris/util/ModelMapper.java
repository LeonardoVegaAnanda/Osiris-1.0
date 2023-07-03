package mx.com.ananda.jemia.osiris.util;

import mx.com.ananda.jemia.osiris.model.dto.PurchaseOrders;
import mx.com.ananda.jemia.osiris.model.dto.VendorPayments;
import mx.com.ananda.jemia.osiris.model.entity.OrdenCompraModel;
import mx.com.ananda.jemia.osiris.model.entity.ProveedorModel;

public class ModelMapper {

    public static OrdenCompraModel mapearEntidadOrden(PurchaseOrders po) {
        OrdenCompraModel ordenMapeada = new OrdenCompraModel();
        ordenMapeada.setDocNum((long) po.getDocNum());
        ordenMapeada.setCardCode(po.getCardCode());
        ordenMapeada.setDocTotal(po.getDocTotal());
        ordenMapeada.setDocDate(po.getDocDate());
        ordenMapeada.setCardName(po.getCardName());
        return ordenMapeada;
    }

    public static ProveedorModel mapearEntidadProveedor(VendorPayments vp) {
        ProveedorModel provedorMapeado = new ProveedorModel();
        provedorMapeado.setCodigoProveedor(vp.getCardCode());
        provedorMapeado.setNombreProveedor(vp.getCardName());
        provedorMapeado.setCodigoGrupo(vp.getGroupCode());
        provedorMapeado.setTelefonoProveedor(vp.getPhone1());
        provedorMapeado.setRazonSocial(vp.getU_AV_RazonSocial());
        return provedorMapeado;
    }
}
