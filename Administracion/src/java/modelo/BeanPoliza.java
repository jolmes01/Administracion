/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author JL
 */
public class BeanPoliza {

    private HashMap<Integer, Poliza> polizas = new HashMap<Integer, Poliza>();

    public HashMap<Integer, Poliza> getPolizas() {
        return polizas;
    }

    public void setPolizas(HashMap<Integer, Poliza> polizas) {
        this.polizas = polizas;
    }

}
