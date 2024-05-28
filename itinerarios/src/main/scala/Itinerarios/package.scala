import Datos._

package object Itinerarios {

    def buscarItinerarios(vuelos: List[Vuelo], origen: String, destino: String, visitados: Set[String]): List[List[Vuelo]] = {
      if (origen == destino) List(List())
      else {
        for {
          vuelo <- vuelos.filter(vuelo => vuelo.org == origen && !visitados.contains(vuelo.dst))
          itinerario <- buscarItinerarios(vuelos, vuelo.dst, destino, visitados + origen)
        } yield vuelo :: itinerario
      }
    }

    /**
      * @param vuelos una lista de todos los vuelos disponibles
      * @param aeropuertos una lista de todos los aeropuertos
      * @return una funcion que recibe dos strings (codigos de aeropuertos) y devuelve una lista de itinerarios entre esos dos aeropuertos
      */
    def itinerarios(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[Itinerario] = {
        (origen: String, destino: String) => buscarItinerarios(vuelos, origen, destino, Set())
    }

    /**
      * @param vuelos una lista de todos los vuelos disponibles
      * @param aeropuertos una lista de todos los aeropuertos
      * @return una funcion que recibe dos strings (codigos de aeropuertos) 
      *         y devuelve los tres (si los hay) itinerarios que minimizan el tiempo de viaje
      *         entre esos dos aeropuertos
      */
    def itinerariosTiempo(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[Itinerario] = {

        def tiempoTotal(itinerario: List[Vuelo]): Int = {
            val tiempoEnAire = itinerario.map { vuelo =>
              val hlModificado = if (vuelo.hl < vuelo.hs) vuelo.hl + 24 else vuelo.hl
              (hlModificado - vuelo.hs) * 60 + vuelo.ml - vuelo.ms
            }.sum

            val tiemposEspera = for ((vuelo1, vuelo2) <- itinerario.zip(itinerario.tail)) yield {
              val hsModificado = if (vuelo2.hs < vuelo1.hl) vuelo2.hs + 24 else vuelo2.hs
              (hsModificado - vuelo1.hl) * 60 + vuelo2.ms - vuelo1.ml
            }
            
            tiempoEnAire + tiemposEspera.sum
        }

        (origen: String, destino: String) => buscarItinerarios(vuelos, origen, destino, Set()).map(itinerario => (itinerario, tiempoTotal(itinerario))).sortBy(_._2).map(_._1).take(3)
    }

    /**
      * @param vuelos una lista de todos los vuelos disponibles
      * @param aeropuertos una lista de todos los aeropuertos
      * @return una funcion que recibe dos strings (codigos de aeropuertos)
      *         y devuelve los tres (si los hay) itinerarios que minimizan el numero de cambios
      *        de avion entre esos dos aeropuertos
      */
    def itinerariosEscalas(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[Itinerario] = {
        def numeroEscalas(itinerario: List[Vuelo]): Int = itinerario.length
        (origen: String, destino: String) => buscarItinerarios(vuelos, origen, destino, Set()).map(itinerario => (itinerario, numeroEscalas(itinerario))).sortBy(_._2).map(_._1).take(3)
    }

    /**
      * @param vuelos una lista de todos los vuelos disponibles
      * @param aeropuertos una lista de todos los aeropuertos
      * @return una funcion que recibe dos strings (codigos de aeropuertos)
      *         y devuelve los tres (si los hay) itinerarios que minimizan el tiempo en el aire
      *         entre esos dos aeropuertos
      */
    def itinerariosAire(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[Itinerario] = {
        def tiempoTotal(itinerario: List[Vuelo]): Int = {
            itinerario.map { vuelo =>
              val hlModificado = if (vuelo.hl < vuelo.hs) vuelo.hl + 24 else vuelo.hl
              (hlModificado - vuelo.hs) * 60 + vuelo.ml - vuelo.ms
            }.sum
        }
        (origen: String, destino: String) => buscarItinerarios(vuelos, origen, destino, Set()).map(itinerario => (itinerario, tiempoTotal(itinerario))).sortBy(_._2).map(_._1).take(3)
    }

    /**
      * @param vuelos una lista de todos los vuelos disponibles
      * @param aeropuertos una lista de todos los aeropuertos
      * @return una funcion que recibe dos strings (codigos de aeropuertos), y h:m una hora de
      *         la cita en c2 (destino), y devuelve el itinerario que optimiza la hora de salida para
      *         llegar a tiempo a la cita
      */
    def itinerarioSalida(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String, Int, Int) => Itinerario = {
      
      def tiempoEnMinutos(hora: Int, minuto: Int): Int = hora * 60 + minuto
      
      def optimizarSalida(itinerarios: List[Itinerario], hora: Int, minuto: Int): Itinerario = {
          val itinerariosValidos = itinerarios.filter(itinerario => 
              tiempoEnMinutos(itinerario.last.hl, itinerario.last.ml) <= tiempoEnMinutos(hora, minuto)
          )
          if (itinerariosValidos.isEmpty) {
              List()
          } else {
              itinerariosValidos.maxBy(itinerario => (itinerario.head.hs, itinerario.head.ms))
          }
      }

      (origen: String, destino: String, hora: Int, min: Int) => optimizarSalida(buscarItinerarios(vuelos, origen, destino, Set()), hora, min)
    }
}