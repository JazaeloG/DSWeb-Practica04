/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.dsweb.practica04;

import java.sql.Connection;
import java.util.List;

public abstract class SelectionDB<T> {
    public abstract List<T> buscar(Connection con);
}
