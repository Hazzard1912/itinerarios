import Datos._
import common._
import Itinerarios._

import scala.collection.parallel.CollectionConverters._
import scala.collection.parallel.ParSeq

package object ItinerariosPar {

    type ItinerarioPar = List[Vuelo]

    def buscarItinerarios(vuelos: List[Vuelo], origen: String, destino: String, visitados: Set[String]): List[Itinerario] = {
      if (origen == destino) List(List())
      else {
        val vuelosFiltrados = vuelos.filter(vuelo => vuelo.org == origen && !visitados.contains(vuelo.dst))
        val itinerariosParalelos: ParSeq[List[Vuelo]] = vuelosFiltrados.par.flatMap { vuelo =>
          buscarItinerarios(vuelos, vuelo.dst, destino, visitados + origen).map(vuelo :: _)
        }
        itinerariosParalelos.toList
      }
    }

    /**
      * @param vuelos una lista de todos los vuelos disponibles
      * @param aeropuertos una lista de todos los aeropuertos
      * @return una funcion que recibe dos strings (codigos de aeropuertos) 
      *         y devuelve todos los itinerarios entre esos dos aeropuertos
      */
    def itinerariosPar(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[Itinerario] = {
      (origen: String, destino: String) => buscarItinerarios(vuelos, origen, destino, Set())
    }

    /**
      * @param vuelos una lista de todos los vuelos disponibles
      * @param aeropuertos una lista de todos los aeropuertos
      * @return una funcion que recibe dos strings (codigos de aeropuertos)
      *         y devuelve los tres (si los hay) itinerarios que minimizan el tiempo de viaje
      *         entre esos dos aeropuertos
      */
    def itinerariosTiempoPar(vuelos : List [Vuelo] , aeropuertos : List [Aeropuerto ]):( String , String) => List[Itinerario] = {

      def tiempoTotal(itinerario: List[Vuelo]): Int = {
        // Paraleliza el cálculo del tiempo en aire
        val tiempoEnAirePar = itinerario.par.map { vuelo =>
          val hlModificado = if (vuelo.hl < vuelo.hs) vuelo.hl + 24 else vuelo.hl
          (hlModificado - vuelo.hs) * 60 + vuelo.ml - vuelo.ms
        }.sum
        // Paraleliza el cálculo de los tiempos de espera
        val tiemposEsperaPar = (itinerario.par.zip(itinerario.tail.par)).map { case (vuelo1, vuelo2) =>
          val hsModificado = if (vuelo2.hs < vuelo1.hl) vuelo2.hs + 24 else vuelo2.hs
          (hsModificado - vuelo1.hl) * 60 + vuelo2.ms - vuelo1.ml
        }.sum

        tiempoEnAirePar + tiemposEsperaPar
      }
      // Paraleliza la búsqueda de itinerarios y el cálculo del tiempo total
      (origen: String, destino: String) =>
        buscarItinerarios(vuelos, origen, destino, Set()).par.map(itinerario => (itinerario, tiempoTotal(itinerario))).toList.sortBy(_._2).map(_._1).take(3)
    }

    /**
      * @param vuelos una lista de todos los vuelos disponibles
      * @param aeropuertos una lista de todos los aeropuertos
      * @return una funcion que recibe dos strings (codigos de aeropuertos)
      *         y devuelve los tres (si los hay) itinerarios que minimizan el numero de cambios
      *         de avion entre esos dos aeropuertos
      */
    def itinerariosEscalasPar(vuelos : List [Vuelo] , aeropuertos : List [Aeropuerto ]):( String , String) => List[Itinerario] = {
      def numeroEscalas(itinerario: List[Vuelo]): Int = itinerario.length
      (origen: String, destino: String) =>
        // Paraleliza map para calcular el número de escalas de cada itinerario en paralelo
        buscarItinerarios(vuelos, origen, destino, Set()).par.map(itinerario => (itinerario, numeroEscalas(itinerario))).toList.sortBy(_._2).map(_._1).take(3)
    }

    /**
      * @param vuelos una lista de todos los vuelos disponibles
      * @param aeropuertos una lista de todos los aeropuertos
      * @return una funcion que recibe dos strings (codigos de aeropuertos)
      *         y devuelve los tres (si los hay) itinerarios que minimizan el tiempo en el aire
      *         entre esos dos aeropuertos
      */
    def itinerariosAirePar(vuelos : List [Vuelo] , aeropuertos : List [Aeropuerto ]):( String , String) => List[Itinerario] = {
      def tiempoTotal(itinerario: List[Vuelo]): Int = {
        itinerario.map { vuelo =>
          val hlModificado = if (vuelo.hl < vuelo.hs) vuelo.hl + 24 else vuelo.hl
          (hlModificado - vuelo.hs) * 60 + vuelo.ml - vuelo.ms
        }.sum
      }

      (origen: String, destino: String) =>
        buscarItinerarios(vuelos, origen, destino, Set()).par.map(itinerario => (itinerario, tiempoTotal(itinerario))).toList.sortBy(_._2).map(_._1).take(3)
    }

    /**
      * @param vuelos una lista de todos los vuelos disponibles
      * @param aeropuertos una lista de todos los aeropuertos
      * @return una funcion que recibe dos strings (codigos de aeropuertos), y h:m una hora de
      *         la cita en c2 (destino), y devuelve el itinerario que optimiza la hora de salida para
      *         llegar a tiempo a la cita
      */
    def itinerarioSalidaPar(vuelos : List [Vuelo] , aeropuertos : List [Aeropuerto ]):( String , String , Int , Int) => Itinerario = {
      def tiempoEnMinutos(hora: Int, minuto: Int): Int = hora * 60 + minuto

      def optimizarSalida(itinerarios: List[Itinerario], hora: Int, minuto: Int): Itinerario = {
        val itinerariosValidos = itinerarios.par.filter(itinerario => // Paraleliza el filtrado de itinerarios
          tiempoEnMinutos(itinerario.last.hl, itinerario.last.ml) <= tiempoEnMinutos(hora, minuto)
        ).seq

        if (itinerariosValidos.isEmpty) {
          List()
        } else {
          itinerariosValidos.maxBy(itinerario => (itinerario.head.hs, itinerario.head.ms))
        }
      }

      (origen: String, destino: String, hora: Int, min: Int) =>
        optimizarSalida(buscarItinerarios(vuelos, origen, destino, Set()), hora, min)
    }

}