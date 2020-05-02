package com.fegc.modelo;

import com.fegc.modeloDAO.JerarquiaDAO;
import com.fegc.modeloDAO.TarifasComisionDAO;
import com.fegc.modeloDAO.VentasDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fausto
 */
public class Metodos {

    int contadorNiveles = 0;
    List<Integer> EmpleadosGrupo = new ArrayList<>();
    List<DatosEmpleado> EmpleadosGrupo1 = new ArrayList<>();

    public void asignarComisionVentaPropia(int idEmpleado, int idVenta) {
        JerarquiaDAO jerarquia = new JerarquiaDAO();
        int idPadre = jerarquia.listarID(idEmpleado);

        VentasDAO ventas = new VentasDAO();
        double monto = ventas.listarById(idVenta);
        double bonoMetaPropia = 0;
        double bonoQuincena = 0;
        
        EmpleadosGrupo.add(idEmpleado);
        EmpleadosGrupo1.add(new DatosEmpleado(idEmpleado, contadorNiveles, 0));
        if (contadorNiveles == 0) {
            bonoMetaPropia = asignarBonoMetaPropia(monto);
            System.out.println("Bono meta propia -> " + bonoMetaPropia);
            bonoQuincena = asignarBonoQuincena("05", "2020", idEmpleado);
            System.out.println("Bono de Quincena -> " + bonoQuincena);
        }

        TarifasComisionDAO tarifas = new TarifasComisionDAO();
        double porcentaje = tarifas.listarID(contadorNiveles + 1);

        double comision = monto * porcentaje;
        System.out.println("Comision por venta -> " + comision);
        if (idPadre != 0) {
            contadorNiveles++;
            if (contadorNiveles < 5) {
                asignarComisionVentaPropia(idPadre, idVenta);
            }
        }

    }

    private boolean tieneHijosLideres(int idEmpleado, int nivel) {
        JerarquiaDAO jerarquia = new JerarquiaDAO();
        int listaHijosNivel2, listaHijosNivel3, listaHijosNivel4 = 0;
        boolean respuesta = false;

        switch (nivel) {
            case 3:
                listaHijosNivel2 = jerarquia.contarHijosLideresNivel3(idEmpleado).size();
                if (listaHijosNivel2 >= 1) {
                    respuesta = true;
                }
                break;
            case 4:
                listaHijosNivel3 = jerarquia.contarHijosLideresNivel3(idEmpleado).size();
                listaHijosNivel2 = jerarquia.contarHijosLideresNivel4(idEmpleado).size();
                if (listaHijosNivel3 >= 1 && listaHijosNivel2 >= 2) {
                    respuesta = true;
                }
                break;
            case 5:
                listaHijosNivel4 = jerarquia.contarHijosLideresNivel3(idEmpleado).size();
                listaHijosNivel3 = jerarquia.contarHijosLideresNivel4(idEmpleado).size();
                listaHijosNivel2 = jerarquia.contarHijosLideresNivel5(idEmpleado).size();
                if (listaHijosNivel2 >= 3 && listaHijosNivel3 >= 2 && listaHijosNivel4 >= 1) {
                    respuesta = true;
                }
                break;
            default:
                break;
        }
        return respuesta;
    }

    public void asignarBonoLiderazgoMensual() {
        VentasDAO ventas = new VentasDAO();
        double monto = ventas.calcularMontoLiderazgo("05", "2020", EmpleadosGrupo);
        double bonoLiderazgo = 0.00;
        if (monto >= 20000 && monto < 40000) {
            bonoLiderazgo = 150;
        } else if (monto >= 40000 && monto < 60000) {
            bonoLiderazgo = 250;
        } else if (monto >= 60000 && monto < 80000) {
            bonoLiderazgo = 350;
        } else if (monto >= 80000) {
            bonoLiderazgo = 500;
        }
        for (DatosEmpleado datosEmpleado : EmpleadosGrupo1) {
            datosEmpleado.setMonto(totalVentasMensualEmpleado(datosEmpleado.getId()));
            boolean tieneHijos = tieneHijosLideres(datosEmpleado.getId(), datosEmpleado.getNivel());
            if (datosEmpleado.getNivel() == 2
                    && datosEmpleado.getMonto() > 5000
                    && monto > 35000) {
                System.out.println("El equipo vendió en este mes $" + monto);
                System.out.println("Bono de liderazgo mensual del Lider N" + (datosEmpleado.getNivel()) + " --> " + bonoLiderazgo);
            } else if (datosEmpleado.getNivel() == 3
                    && datosEmpleado.getMonto() > 8000
                    && monto > 60000 && tieneHijos) {
                System.out.println("El equipo vendió en este mes $" + monto);
                System.out.println("Bono de liderazgo mensual del Lider N" + (datosEmpleado.getNivel()) + " --> " + bonoLiderazgo);
            } else if (datosEmpleado.getNivel() == 4
                    && datosEmpleado.getMonto() > 8000
                    && monto > 120000 && tieneHijos) {
                System.out.println("El equipo vendió en este mes $" + monto);
                System.out.println("Bono de liderazgo mensual del Lider N" + (datosEmpleado.getNivel()) + " --> " + bonoLiderazgo);
            } else if (datosEmpleado.getNivel() == 5
                    && datosEmpleado.getMonto() > 6000
                    && monto > 240000 && tieneHijos) {
                System.out.println("El equipo vendió en este mes $" + monto);
                System.out.println("Bono de liderazgo mensual del Lider N" + (datosEmpleado.getNivel()) + " --> " + bonoLiderazgo);
            }
        }
    }

    private double totalVentasMensualEmpleado(int idEmpleado) {
        VentasDAO ventas = new VentasDAO();
        double monto = ventas.calcularTotalVentasMensualEmpleado("05", "2020", idEmpleado);
        return monto;
    }

    public void asignarBonoLiderazgoTrimestral() {
        VentasDAO ventas = new VentasDAO();
        double monto = ventas.calcularMontoLiderazgoTrimestral("2020-03-01", "2020-05-31", EmpleadosGrupo);
        double bonoLiderazgo = 0.00;
        if (monto >= 45000 && monto < 65000) {
            bonoLiderazgo = 350;
        } else if (monto >= 65000 && monto < 85000) {
            bonoLiderazgo = 550;
        } else if (monto >= 85000 && monto < 100000) {
            bonoLiderazgo = 650;
        } else if (monto >= 100000) {
            bonoLiderazgo = 1000;
        }
        System.out.println("El equipo vendió en este trimestre $" + monto);
        System.out.println("Monto de liderazgo trimestral del equipo --> " + bonoLiderazgo);
    }

    private double asignarBonoMetaPropia(double monto) {
        double comisionRecibir = 0.0;
        if (monto >= 5000 && monto < 10000) {
            comisionRecibir = 150;
        } else if (monto >= 10000 && monto < 15000) {
            comisionRecibir = 200;
        } else if (monto >= 15000 && monto < 25000) {
            comisionRecibir = 300;
        } else if (monto >= 25000) {
            comisionRecibir = 400;
        }
        return comisionRecibir;
    }

    private double asignarBonoQuincena(String month, String year, int idEmpleado) {
        double totalVentasQuincena = 0.0;
        VentasDAO ventasDAO = new VentasDAO();
        totalVentasQuincena = ventasDAO.calcularMontoQuincena(month, year, idEmpleado);
        if (totalVentasQuincena >= 5000) {
            totalVentasQuincena = 100;
        } else {
            totalVentasQuincena = 0;
        }
        return totalVentasQuincena;
    }
}
