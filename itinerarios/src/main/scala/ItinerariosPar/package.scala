import Datos._
import common._
import Itinerarios._

import scala.collection.parallel.CollectionConverters._
import scala.collection.parallel.ParSeq

package object ItinerariosPar {
    
    /**
      * @param vuelos una lista de todos los vuelos disponibles
      * @param aeropuertos una lista de todos los aeropuertos
      * @return una funcion que recibe dos strings (codigos de aeropuertos) 
      *         y devuelve todos los itinerarios entre esos dos aeropuertos
      */
    def itinerariosPar(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[Itinerario] = {

    }

    /**
      * @param vuelos una lista de todos los vuelos disponibles
      * @param aeropuertos una lista de todos los aeropuertos
      * @return una funcion que recibe dos strings (codigos de aeropuertos)
      *         y devuelve los tres (si los hay) itinerarios que minimizan el tiempo de viaje
      *         entre esos dos aeropuertos
      */
    def itinerariosTiempoPar(vuelos : List [Vuelo] , aeropuertos : List [Aeropuerto ]):( String , String) => List[Itinerario] = {

    }

    /**
      * @param vuelos una lista de todos los vuelos disponibles
      * @param aeropuertos una lista de todos los aeropuertos
      * @return una funcion que recibe dos strings (codigos de aeropuertos)
      *         y devuelve los tres (si los hay) itinerarios que minimizan el numero de cambios
      *         de avion entre esos dos aeropuertos
      */
    def itinerariosEscalasPar(vuelos : List [Vuelo] , aeropuertos : List [Aeropuerto ]):( String , String) => List[Itinerario] = {

    }

    /**
      * @param vuelos una lista de todos los vuelos disponibles
      * @param aeropuertos una lista de todos los aeropuertos
      * @return una funcion que recibe dos strings (codigos de aeropuertos)
      *         y devuelve los tres (si los hay) itinerarios que minimizan el tiempo en el aire
      *         entre esos dos aeropuertos
      */
    def itinerariosAirePar(vuelos : List [Vuelo] , aeropuertos : List [Aeropuerto ]):( String , String) => List[Itinerario] = {

    }

    /**
      * @param vuelos una lista de todos los vuelos disponibles
      * @param aeropuertos una lista de todos los aeropuertos
      * @return una funcion que recibe dos strings (codigos de aeropuertos), y h:m una hora de
      *         la cita en c2 (destino), y devuelve el itinerario que optimiza la hora de salida para
      *         llegar a tiempo a la cita
      */
    def itinerarioSalidaPar(vuelos : List [Vuelo] , aeropuertos : List [Aeropuerto ]):( String , String , Int , Int) => Itinerario = {

    }
}