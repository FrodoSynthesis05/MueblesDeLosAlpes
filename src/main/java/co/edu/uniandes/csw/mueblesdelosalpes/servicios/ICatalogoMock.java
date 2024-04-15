/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mueblesdelosalpes.servicios;
import co.edu.uniandes.csw.mueblesdelosalpes.dto.Mueble;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioCatalogoMockLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/catalogoMock")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ICatalogoMock {

    @EJB
    private IServicioCatalogoMockLocal catalogoService;

    @GET
    @Path("getMueble/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Mueble> getMuebles() {
        return catalogoService.darMuebles();
    }

    @POST
    @Path("agregarMueble/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void agregarMueble(Mueble mueble) {
        catalogoService.agregarMueble(mueble);
    }

    @PUT
    @Path("actualizarMueble/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void actualizarMueble(@PathParam("id") long id, Mueble mueble) {
        Mueble found = findMuebleById(id);
        found.setCantidad(mueble.getCantidad());
        catalogoService.removerEjemplarMueble(id);
        catalogoService.agregarMueble(found);
    }

    @DELETE
    @Path("eliminarMueble/{id}")
    public void eliminarMueble(@PathParam("id") long id) {
        catalogoService.eliminarMueble(id);
    }

    private Mueble findMuebleById(long id) {
        List<Mueble> muebles = catalogoService.darMuebles();
        for (Mueble mueble : muebles) {
            if (mueble.getReferencia() == id) {
                return mueble;
            }
        }
        return null;
    }
}