import Datos._
import ItinerariosPar._
import org.scalameter._

def tiempoDe[T](body: => T): Quantity[Double] = {
  val timeA1 = config(
    KeyValue(Key.exec.minWarmupRuns -> 20),
    KeyValue(Key.exec.maxWarmupRuns -> 60),
    KeyValue(Key.verbose -> false)
  ) withWarmer(new Warmer.Default) measure (body)
  timeA1
}

val itsCursoPar = itinerariosPar(vuelosCurso, aeropuertosCurso)

// Medición de tiempos para cada caso de prueba

// Ejemplo curso pequeño
println(s"Tiempo itsCursoPar MID-SVCS: ${tiempoDe(itsCursoPar("MID", "SVCS"))}")
println(s"Tiempo itsCursoPar CLO-SVCS: ${tiempoDe(itsCursoPar("CLO", "SVCS"))}")

// 4 itinerarios CLO-SVO
println(s"Tiempo itsCursoPar CLO-SVO: ${tiempoDe(itsCursoPar("CLO", "SVO"))}")

// 2 itinerarios CLO-MEX
println(s"Tiempo itsCursoPar CLO-MEX: ${tiempoDe(itsCursoPar("CLO", "MEX"))}")

// 2 itinerarios CTG-PTY
println(s"Tiempo itsCursoPar CTG-PTY: ${tiempoDe(itsCursoPar("CTG", "PTY"))}")

// Pruebas con datos adicionales

val itsPar100C1 = itinerariosPar(vuelosC1, aeropuertos.toList)
val itsTpoPar100C1 = itinerariosTiempoPar(vuelosC1, aeropuertos.toList)
val itsEscPar100C1 = itinerariosEscalasPar(vuelosC1, aeropuertos.toList)
val itsAirPar100C1 = itinerariosAirePar(vuelosC1, aeropuertos.toList)
val itsSalPar100C1 = itinerarioSalidaPar(vuelosC1, aeropuertos.toList)

println(s"Tiempo itsPar100C1 ORD-TPA: ${tiempoDe(itsPar100C1("ORD", "TPA"))}")
println(s"Tiempo itsTpoPar100C1 ORD-TPA: ${tiempoDe(itsTpoPar100C1("ORD", "TPA"))}")
println(s"Tiempo itsEscPar100C1 ORD-TPA: ${tiempoDe(itsEscPar100C1("ORD", "TPA"))}")
println(s"Tiempo itsAirPar100C1 ORD-TPA: ${tiempoDe(itsAirPar100C1("ORD", "TPA"))}")
println(s"Tiempo itsSalPar100C1 ORD-TPA: ${tiempoDe(itsSalPar100C1("ORD", "TPA", 18, 30))}")

val itsPar200C = itinerariosPar(vuelosC1 ++ vuelosC2, aeropuertos.toList)
val itsTpoPar200C = itinerariosTiempoPar(vuelosC1 ++ vuelosC2, aeropuertos.toList)
val itsEscPar200C = itinerariosEscalasPar(vuelosC1 ++ vuelosC2, aeropuertos.toList)
val itsAirPar200C = itinerariosAirePar(vuelosC1 ++ vuelosC2, aeropuertos.toList)
val itsSalPar200C = itinerarioSalidaPar(vuelosC1 ++ vuelosC2, aeropuertos.toList)

println(s"Tiempo itsPar200C ORD-TPA: ${tiempoDe(itsPar200C("ORD", "TPA"))}")
println(s"Tiempo itsTpoPar200C ORD-TPA: ${tiempoDe(itsTpoPar200C("ORD", "TPA"))}")
println(s"Tiempo itsEscPar200C ORD-TPA: ${tiempoDe(itsEscPar200C("ORD", "TPA"))}")
println(s"Tiempo itsAirPar200C ORD-TPA: ${tiempoDe(itsAirPar200C("ORD", "TPA"))}")
println(s"Tiempo itsSalPar200C ORD-TPA: ${tiempoDe(itsSalPar200C("ORD", "TPA", 18, 30))}")

/*
// Casos adicionales comentados
val its300CPar = itinerariosPar((vuelosC1 ++ vuelosC2 ++ vuelosC3), aeropuertos.toList)
println(s"Tiempo its300CPar ORD-TPA: ${tiempoDe(its300CPar("ORD", "TPA"))}")

val its400CPar = itinerariosPar((vuelosC1 ++ vuelosC2 ++ vuelosC3 ++ vuelosC4), aeropuertos.toList)
println(s"Tiempo its400CPar ORD-TPA: ${tiempoDe(its400CPar("ORD", "TPA"))}")

val its500CPar = itinerariosPar((vuelosC1 ++ vuelosC2 ++ vuelosC3 ++ vuelosC4 ++ vuelosC5), aeropuertos.toList)
println(s"Tiempo its500CPar ORD-TPA: ${tiempoDe(its300CPar("ORD", "TPA"))}")
