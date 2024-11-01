package org.uv.dsweb.practica04;

import java.util.List;

public interface IDAOGeneral<T, ID> {
    public T buscarPorID(ID id);
    List<T> buscarTodos();
    public boolean guardar(T p);
    public boolean modificar(ID id,T p);
    public boolean eliminar(ID id);
}
