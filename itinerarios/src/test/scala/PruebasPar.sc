/**
 * Proyecto: El problema de la Planificacion de vuelos
 * PruebasPar.sc
 * Juan Esteban Guerrero Camacho (202040798)
 * Oscar David Cuaical Lopez (202270657)
 * Juan Sebastian Tobar Moriones (202040194)
 * Jhonnier Albeiro Hernández Brito (202140113)
 */
import Datos._
import ItinerariosPar._
import org.scalameter._

def tiempoYResultadoDe[T](body: => T): (T, Quantity[Double]) = {
  val configMedida = config(
    KeyValue(Key.exec.minWarmupRuns -> 20),
    KeyValue(Key.exec.maxWarmupRuns -> 60),
    KeyValue(Key.verbose -> false)
  )
  val tiempo = configMedida withWarmer(new Warmer.Default) measure (body)
  (body, tiempo)
}

val aeropuertosList = aeropuertos.toList

// Funciones de itinerarios
val itsB1Par = itinerariosPar(vuelosB1, aeropuertosList)
val itsTiempoB1Par = itinerariosTiempoPar(vuelosB1, aeropuertosList)
val itsEscalasB1Par = itinerariosEscalasPar(vuelosB1, aeropuertosList)
val itsAireB1Par = itinerariosAirePar(vuelosB1, aeropuertosList)
val itSalidaB1Par = itinerarioSalidaPar(vuelosB1, aeropuertosList)

val itsC1Par = itinerariosPar(vuelosC1, aeropuertosList)
val itsTiempoC1Par = itinerariosTiempoPar(vuelosC1, aeropuertosList)
val itsEscalasC1Par = itinerariosEscalasPar(vuelosC1, aeropuertosList)
val itsAireC1Par = itinerariosAirePar(vuelosC1, aeropuertosList)
val itsSalidaC1Par = itinerarioSalidaPar(vuelosC1, aeropuertosList)

val its200CPar = itinerariosPar(vuelosC1 ++ vuelosC2, aeropuertosList)
val itsTiempo200CPar = itinerariosTiempoPar(vuelosC1 ++ vuelosC2, aeropuertosList)
val itsEscalas200CPar = itinerariosEscalasPar(vuelosC1 ++ vuelosC2, aeropuertosList)
val itsAire200CPar = itinerariosAirePar(vuelosC1 ++ vuelosC2, aeropuertosList)
val itsSalida200CPar = itinerarioSalidaPar(vuelosC1 ++ vuelosC2, aeropuertosList)

// Pruebas para diferentes pares de ciudades y criterios de itinerarios
/*
// Ejemplo curso pequeño
val (resultado1, tiempo1) = tiempoYResultadoDe(itsB1Par("MID", "SVCS"))
println(s"Resultado itsCursoPar MID-SVCS: $resultado1, Tiempo: $tiempo1")

val (resultado2, tiempo2) = tiempoYResultadoDe(itsB1Par("CLO", "SVCS"))
println(s"Resultado itsCursoPar CLO-SVCS: $resultado2, Tiempo: $tiempo2")

val (resultado3, tiempo3) = tiempoYResultadoDe(itsB1Par("CLO", "SVO"))
println(s"Resultado itsCursoPar CLO-SVO: $resultado3, Tiempo: $tiempo3")

val (resultado4, tiempo4) = tiempoYResultadoDe(itsB1Par("CLO", "MEX"))
println(s"Resultado itsCursoPar CLO-MEX: $resultado4, Tiempo: $tiempo4")

val (resultado5, tiempo5) = tiempoYResultadoDe(itsB1Par("CTG", "PTY"))
println(s"Resultado itsCursoPar CTG-PTY: $resultado5, Tiempo: $tiempo5")

// Prueba itinerariosTiempo
val (resultado6, tiempo6) = tiempoYResultadoDe(itsTiempoB1Par("MID", "SVCS"))
println(s"Resultado itsTiempoCursoPar MID-SVCS: $resultado6, Tiempo: $tiempo6")

val (resultado7, tiempo7) = tiempoYResultadoDe(itsTiempoB1Par("CLO", "SVCS"))
println(s"Resultado itsTiempoCursoPar CLO-SVCS: $resultado7, Tiempo: $tiempo7")

val (resultado8, tiempo8) = tiempoYResultadoDe(itsTiempoB1Par("CLO", "SVO"))
println(s"Resultado itsTiempoCursoPar CLO-SVO: $resultado8, Tiempo: $tiempo8")

val (resultado9, tiempo9) = tiempoYResultadoDe(itsTiempoB1Par("CLO", "MEX"))
println(s"Resultado itsTiempoCursoPar CLO-MEX: $resultado9, Tiempo: $tiempo9")

val (resultado10, tiempo10) = tiempoYResultadoDe(itsTiempoB1Par("CTG", "PTY"))
println(s"Resultado itsTiempoCursoPar CTG-PTY: $resultado10, Tiempo: $tiempo10")

// Prueba itinerariosEscalas
val (resultado11, tiempo11) = tiempoYResultadoDe(itsEscalasB1Par("MID", "SVCS"))
println(s"Resultado itsEscalasCursoPar MID-SVCS: $resultado11, Tiempo: $tiempo11")

val (resultado12, tiempo12) = tiempoYResultadoDe(itsEscalasB1Par("CLO", "SVCS"))
println(s"Resultado itsEscalasCursoPar CLO-SVCS: $resultado12, Tiempo: $tiempo12")

val (resultado13, tiempo13) = tiempoYResultadoDe(itsEscalasB1Par("CLO", "SVO"))
println(s"Resultado itsEscalasCursoPar CLO-SVO: $resultado13, Tiempo: $tiempo13")

val (resultado14, tiempo14) = tiempoYResultadoDe(itsEscalasB1Par("CLO", "MEX"))
println(s"Resultado itsEscalasCursoPar CLO-MEX: $resultado14, Tiempo: $tiempo14")

val (resultado15, tiempo15) = tiempoYResultadoDe(itsEscalasB1Par("CTG", "PTY"))
println(s"Resultado itsEscalasCursoPar CTG-PTY: $resultado15, Tiempo: $tiempo15")

// Prueba itinerariosAire
val (resultado16, tiempo16) = tiempoYResultadoDe(itsAireB1Par("MID", "SVCS"))
println(s"Resultado itsAireCursoPar MID-SVCS: $resultado16, Tiempo: $tiempo16")

val (resultado17, tiempo17) = tiempoYResultadoDe(itsAireB1Par("CLO", "SVCS"))
println(s"Resultado itsAireCursoPar CLO-SVCS: $resultado17, Tiempo: $tiempo17")

val (resultado18, tiempo18) = tiempoYResultadoDe(itsAireB1Par("CLO", "SVO"))
println(s"Resultado itsAireCursoPar CLO-SVO: $resultado18, Tiempo: $tiempo18")

val (resultado19, tiempo19) = tiempoYResultadoDe(itsAireB1Par("CLO", "MEX"))
println(s"Resultado itsAireCursoPar CLO-MEX: $resultado19, Tiempo: $tiempo19")

val (resultado20, tiempo20) = tiempoYResultadoDe(itsAireB1Par("CTG", "PTY"))
println(s"Resultado itsAireCursoPar CTG-PTY: $resultado20, Tiempo: $tiempo20")

// Prueba itinerarioSalida
val (resultado21, tiempo21) = tiempoYResultadoDe(itSalidaB1Par("CTG", "PTY", 11, 40))
println(s"Resultado itSalidaCursoPar CTG-PTY 11:40: $resultado21, Tiempo: $tiempo21")

val (resultado22, tiempo22) = tiempoYResultadoDe(itSalidaB1Par("CTG", "PTY", 11, 55))
println(s"Resultado itSalidaCursoPar CTG-PTY 11:55: $resultado22, Tiempo: $tiempo22")

val (resultado23, tiempo23) = tiempoYResultadoDe(itSalidaB1Par("CTG", "PTY", 10, 30))
println(s"Resultado itSalidaCursoPar CTG-PTY 10:30: $resultado23, Tiempo: $tiempo23")

// Pruebas con datos adicionales
val (resultado24, tiempo24) = tiempoYResultadoDe(itsC1Par("ORD", "TPA"))
println(s"Resultado its100C1Par ORD-TPA: $resultado24, Tiempo: $tiempo24")

val (resultado25, tiempo25) = tiempoYResultadoDe(itsTiempoC1Par("ORD", "TPA"))
println(s"Resultado itsTiempo100C1Par ORD-TPA: $resultado25, Tiempo: $tiempo25")

val (resultado26, tiempo26) = tiempoYResultadoDe(itsEscalasC1Par("ORD", "TPA"))
println(s"Resultado itsEsc100C1Par ORD-TPA: $resultado26, Tiempo: $tiempo26")

val (resultado27, tiempo27) = tiempoYResultadoDe(itsAireC1Par("ORD", "TPA"))
println(s"Resultado itsAire100C1Par ORD-TPA: $resultado27, Tiempo: $tiempo27")
*/
val (resultado28, tiempo28) = tiempoYResultadoDe(itsSalidaC1Par("ORD", "TPA", 18, 30))
println(s"Resultado itsSalida100C1Par ORD-TPA: $resultado28, Tiempo: $tiempo28")

val (resultado29, tiempo29) = tiempoYResultadoDe(its200CPar("ORD", "TPA"))
println(s"Resultado its200CPar ORD-TPA: $resultado29, Tiempo: $tiempo29")

val (resultado30, tiempo30) = tiempoYResultadoDe(itsTiempo200CPar("ORD", "TPA"))
println(s"Resultado itsTiempo200CPar ORD-TPA: $resultado30, Tiempo: $tiempo30")

val (resultado31, tiempo31) = tiempoYResultadoDe(itsEscalas200CPar("ORD", "TPA"))
println(s"Resultado itsEscalas200CPar ORD-TPA: $resultado31, Tiempo: $tiempo31")

val (resultado32, tiempo32) = tiempoYResultadoDe(itsAire200CPar("ORD", "TPA"))
println(s"Resultado itsAire200CPar ORD-TPA: $resultado32, Tiempo: $tiempo32")

val (resultado33, tiempo33) = tiempoYResultadoDe(itsSalida200CPar("ORD", "TPA", 18, 30))
println(s"Resultado itsSalida200CPar ORD-TPA: $resultado33, Tiempo: $tiempo33")

/*
// Casos adicionales comentados
val its300CPar = itinerariosPar((vuelosC1 ++ vuelosC2 ++ vuelosC3), aeropuertosList)
println(s"Resultado its300CPar ORD-TPA: ${tiempoYResultadoDe(its300CPar("ORD", "TPA"))}")

val its400CPar = itinerariosPar((vuelosC1 ++ vuelosC2 ++ vuelosC3 ++ vuelosC4), aeropuertosList)
println(s"Resultado its400CPar ORD-TPA: ${tiempoYResultadoDe(its400CPar("ORD", "TPA"))}")

val its500CPar = itinerariosPar((vuelosC1 ++ vuelosC2 ++ vuelosC3 ++ vuelosC4 ++ vuelosC5), aeropuertosList)
println(s"Resultado its500CPar ORD-TPA: ${tiempoYResultadoDe(its500CPar("ORD", "TPA"))}")
*/
