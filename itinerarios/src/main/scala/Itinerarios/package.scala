import Datos._

package object Itinerarios {

    /**
      * @param vuelos una lista de todos los vuelos disponibles
      * @param aeropuertos una lista de todos los aeropuertos
      * @return una funcion que recibe dos strings (codigos de aeropuertos) y devuelve una lista de itinerarios entre esos dos aeropuertos
      */
    def itinerarios(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[Itinerario] = {
        def buscarItinerarios(origen: String, destino: String, vuelosDisponibles: List[Vuelo], visitados: Set[String]): List[Itinerario] = {
            if (origen == destino) List(List())
            else {
                val vuelosDesdeOrigen = vuelosDisponibles.filter(_.org == origen)
                vuelosDesdeOrigen.flatMap { vuelo =>
                    if (!visitados.contains(vuelo.dst)) {
                        val itinerariosRestantes = buscarItinerarios(vuelo.dst, destino, vuelosDisponibles, visitados + origen)
                        itinerariosRestantes.map(itinerario => vuelo :: itinerario)
                    } else {
                        List()
                    }
                }
            }
        }

        (cod1: String, cod2: String) => {
            buscarItinerarios(cod1, cod2, vuelos, Set())
        }
    }

    /**
      * @param vuelos una lista de todos los vuelos disponibles
      * @param aeropuertos una lista de todos los aeropuertos
      * @return una funcion que recibe dos strings (codigos de aeropuertos) 
      *         y devuelve los tres (si los hay) itinerarios que minimizan el tiempo de viaje
      *         entre esos dos aeropuertos
      */
    def itinerariosTiempo(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[Itinerario] = {
        def calcularTiempoVuelo(vuelo: Vuelo): Int = {
            val horaSalidaEnMinutos = vuelo.hs * 60 + vuelo.ms
            val horaLlegadaEnMinutos = vuelo.hl * 60 + vuelo.ms
            if (horaLlegadaEnMinutos >= horaSalidaEnMinutos)
                horaLlegadaEnMinutos - horaSalidaEnMinutos
            else
                (24 * 60 - horaSalidaEnMinutos) + horaLlegadaEnMinutos
        }

        def calcularTiempoTotal(itinerario: Itinerario): Int = {
            if (itinerario.isEmpty) 0
            else itinerario.zip(itinerario.tail).foldLeft(0) {
                case (total, (vueloAnterior, vueloSiguiente)) =>
                    val tiempoVuelo = calcularTiempoVuelo(vueloAnterior)
                    val tiempoEspera = {
                        val llegadaAnterior = vueloAnterior.hl * 60 + vueloAnterior.ml
                        val salidaSiguiente = vueloSiguiente.hs * 60 + vueloSiguiente.ms
                        if (salidaSiguiente >= llegadaAnterior)
                            salidaSiguiente - llegadaAnterior
                        else
                            (24 * 60 - llegadaAnterior) + salidaSiguiente
                    }
                    total + tiempoVuelo + tiempoEspera
            } + calcularTiempoVuelo(itinerario.last)
        }

        def buscarItinerarios(origen: String, destino: String, vuelosDisponibles: List[Vuelo], visitados: Set[String]): List[Itinerario] = {
            if (origen == destino) List(List())
            else {
                val vuelosDesdeOrigen = vuelosDisponibles.filter(_.org == origen)
                vuelosDesdeOrigen.flatMap { vuelo =>
                    if (!visitados.contains(vuelo.dst)) {
                        val itinerariosDesdeDestino = buscarItinerarios(vuelo.dst, destino, vuelosDisponibles, visitados + origen)
                        itinerariosDesdeDestino.map(itinerario => vuelo :: itinerario)
                    } else List()
                }
            }
        }

        (origen: String, destino: String) => {
            val todosLosItinerarios = buscarItinerarios(origen, destino, vuelos, Set())
            val itinerariosConTiempoTotal = todosLosItinerarios.map(itinerario => (itinerario, calcularTiempoTotal(itinerario)))
            val itinerariosOrdenados = itinerariosConTiempoTotal.sortBy(_._2)
            val mejoresItinerarios = itinerariosOrdenados.take(3)
            mejoresItinerarios.map(_._1)
        }

    }
/*
    /**
      * @param vuelos una lista de todos los vuelos disponibles
      * @param aeropuertos una lista de todos los aeropuertos
      * @return una funcion que recibe dos strings (codigos de aeropuertos)
      *         y devuelve los tres (si los hay) itinerarios que minimizan el numero de cambios
      *        de avion entre esos dos aeropuertos
      */

    def itinerariosEscalas(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[Itinerario] = {

    }

    /**
      * @param vuelos una lista de todos los vuelos disponibles
      * @param aeropuertos una lista de todos los aeropuertos
      * @return una funcion que recibe dos strings (codigos de aeropuertos)
      *         y devuelve los tres (si los hay) itinerarios que minimizan el tiempo en el aire
      *         entre esos dos aeropuertos
      */
    def itinerariosAire(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[Itinerario] = {

    }

    /**
      * @param vuelos una lista de todos los vuelos disponibles
      * @param aeropuertos una lista de todos los aeropuertos
      * @return una funcion que recibe dos strings (codigos de aeropuertos), y h:m una hora de
      *         la cita en c2 (destino), y devuelve el itinerario que optimiza la hora de salida para
      *         llegar a tiempo a la cita
      */
    def itinerarioSalida(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String, Int, Int) => Itinerario = {

    }

 */
}