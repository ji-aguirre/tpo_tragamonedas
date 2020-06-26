package paquete;

import java.util.Random;
public class Casino {

    public static void main(String[] args){
        final String[] frutas = {"banana","frutilla","guinda","manzana","sandia","uva"};
        Controlador control = new Controlador();
        int nroMaquinas = 10;
        int cantPremios = 10; //premios por maquina
        Random rn = new Random(); //Generador de randoms

        System.out.println("---PRUEBAS DE GENERADOR DE TRAGAMONEDAS---");
        for(int i = 0; i<nroMaquinas;i++){ //Generador de Tragamonedas automatico y aleatorio

            control.crearTragamonedas(rn.nextFloat()*1000, rn.nextInt(3)+3,rn.nextFloat()*100);

        }
        System.out.println("---PRUEBAS DE AGREGAR PREMIO A TRAGAMONEDAS---");
        for(int i = 1; i<nroMaquinas;i++){ //Generador de premios aleatorios
            Tragamonedas maquina = control.obtenerMaquina(i);

            for(int j = 0; j<cantPremios;j++) {
                //System.out.println(maquina.cantidadCasillas);
                String[] lista = new String[maquina.cantidadCasillas];

                for(int k = 0;k<maquina.cantidadCasillas;k++) {
                    lista[k] = frutas[rn.nextInt(frutas.length)];
                    //System.out.println(lista[k]);
                }
                control.agregarPremio(i,lista,rn.nextFloat()*200);
            }

        }

        System.out.println("---PRUEBAS DE ELIMINAR MAQUINA---");
        int idMaquina = rn.nextInt(nroMaquinas);
        if (control.existeMaquina(idMaquina)) {

            Tragamonedas maquinaBorrada = control.eliminarTragamonedas(idMaquina);
            System.out.println("Borre la maquina numero: " + maquinaBorrada.soyLaMaquina());
        }else{
            System.out.println("No existe la maquina "+idMaquina);
        }
        System.out.println("---PRUEBAS DE JUGADAS, AGREGAR SALDO Y ACEPTAR PREMIO---");
        idMaquina = rn.nextInt(nroMaquinas);
        if (control.existeMaquina(idMaquina)) {
            System.out.println("Me conecte en la maquina " + idMaquina);
            control.iniciarJuego(rn.nextFloat() * 1000, idMaquina);

            while (control.puedeJugar(idMaquina)) { //Jugar hasta quedarse sin saldo
                System.out.println("Credito actual: " + control.mostrarCreditoMaquina(idMaquina));
                System.out.println("Caja Actual: "+control.mostrarCajaMaquina(idMaquina));
                control.realizarJugada(idMaquina);
                if (rn.nextInt(10) == 3){ //Aleatoriamente agrego credito al jugador
                    control.agregarCreditoAlJugador(idMaquina,100);
                    System.out.println("Agregue credito!");

                }
            }
        }else{
            System.out.println("La maquina "+idMaquina+" solicitada no existe.");
        }

        System.out.println("---PRUEBAS DE ELIMINAR PREMIO DE MAQUINA---");
        idMaquina = rn.nextInt(nroMaquinas);
        int prem = rn.nextInt(cantPremios + 5);
        if (control.eliminarPremio(idMaquina,prem)) {
            System.out.println("Se elimino el premio "+ prem +" de la maquina "+idMaquina);
        }else{
            System.out.println("No se encontro premio");
        }


        System.out.println("---PRUEBAS DE SALDO DE JUGADOR---");
        idMaquina = rn.nextInt(nroMaquinas);
        control.iniciarJuego(100,idMaquina);
        while (control.puedeJugar(idMaquina)) { //Jugar hasta quedarse sin saldo
            //System.out.println("Credito actual: " + control.mostrarCreditoMaquina(idMaquina));
            //System.out.println("Caja Actual: " + control.mostrarCajaMaquina(idMaquina));
            control.realizarJugada(idMaquina);
        }
        System.out.println("---COBRO EL SALDO---");
        System.out.println("Caja actual ANTES: " + control.mostrarCajaMaquina(idMaquina));
        System.out.println("Credito actual ANTES: " + control.mostrarCreditoMaquina(idMaquina));
        System.out.println("Cobro saldo disponible!");
        control.cobrarCreditoDisponible(idMaquina);
        System.out.println("Caja actual DESPUES: " + control.mostrarCajaMaquina(idMaquina));
        System.out.println("Credito actual DESPUES: " + control.mostrarCreditoMaquina(idMaquina));



    }
}
