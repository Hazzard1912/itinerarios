/**
 * Proyecto: El problema de la Planificacion de vuelos
 * Pruebas.sc
 * Juan Esteban Guerrero Camacho (202040798)
 * Oscar David Cuaical Lopez (202270657)
 * Juan Sebastian Tobar Moriones (202040194)
 * Jhonnier Albeiro Hernández Brito (202140113)
 */
import Datos._
import Itinerarios._
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
val itsB1 = itinerarios(vuelosB1, aeropuertosList)
val itsTiempoB1 = itinerariosTiempo(vuelosB1, aeropuertosList)
val itsEscalasB1 = itinerariosEscalas(vuelosB1, aeropuertosList)
val itsAireB1 = itinerariosAire(vuelosB1, aeropuertosList)
val itSalidaB1 = itinerarioSalida(vuelosB1, aeropuertosList)

val itsC1 = itinerarios(vuelosC1, aeropuertosList)
val itsTiempoC1 = itinerariosTiempo(vuelosC1, aeropuertosList)
val itsEscalasC1 = itinerariosEscalas(vuelosC1, aeropuertosList)
val itsAireC1 = itinerariosAire(vuelosC1, aeropuertosList)
val itsSalidaC1 = itinerarioSalida(vuelosC1, aeropuertosList)

val its200C = itinerarios(vuelosC1 ++ vuelosC2, aeropuertosList)
val itsTiempo200C = itinerariosTiempo(vuelosC1 ++ vuelosC2, aeropuertosList)
val itsEscalas200C = itinerariosEscalas(vuelosC1 ++ vuelosC2, aeropuertosList)
val itsAire200C = itinerariosAire(vuelosC1 ++ vuelosC2, aeropuertosList)
val itsSalida200C = itinerarioSalida(vuelosC1 ++ vuelosC2, aeropuertosList)

// Pruebas para diferentes pares de ciudades y criterios de itinerarios
/*
// Ejemplo curso pequeño
val (resultado1, tiempo1) = tiempoYResultadoDe(itsB1("PHX", "DFW"))
println(s"Resultado itsCurso PHX-DFW: $resultado1, Tiempo: $tiempo1")

val (resultado2, tiempo2) = tiempoYResultadoDe(itsB1("CLO", "SVO"))
println(s"Resultado itsCurso CLO-SVO: $resultado2, Tiempo: $tiempo2")

val (resultado3, tiempo3) = tiempoYResultadoDe(itsB1("CLO", "MEX"))
println(s"Resultado itsCurso CLO-MEX: $resultado3, Tiempo: $tiempo3")

val (resultado4, tiempo4) = tiempoYResultadoDe(itsB1("CTG", "PTY"))
println(s"Resultado itsCurso CTG-PTY: $resultado4, Tiempo: $tiempo4")

// Prueba itinerariosTiempo
val (resultado5, tiempo5) = tiempoYResultadoDe(itsTiempoB1("MID", "SVCS"))
println(s"Resultado itsTiempoCurso MID-SVCS: $resultado5, Tiempo: $tiempo5")

val (resultado6, tiempo6) = tiempoYResultadoDe(itsTiempoB1("CLO", "SVCS"))
println(s"Resultado itsTiempoCurso CLO-SVCS: $resultado6, Tiempo: $tiempo6")

val (resultado7, tiempo7) = tiempoYResultadoDe(itsTiempoB1("CLO", "SVO"))
println(s"Resultado itsTiempoCurso CLO-SVO: $resultado7, Tiempo: $tiempo7")

val (resultado8, tiempo8) = tiempoYResultadoDe(itsTiempoB1("CLO", "MEX"))
println(s"Resultado itsTiempoCurso CLO-MEX: $resultado8, Tiempo: $tiempo8")

val (resultado9, tiempo9) = tiempoYResultadoDe(itsTiempoB1("CTG", "PTY"))
println(s"Resultado itsTiempoCurso CTG-PTY: $resultado9, Tiempo: $tiempo9")

// Prueba itinerariosEscalas
val (resultado10, tiempo10) = tiempoYResultadoDe(itsEscalasB1("MID", "SVCS"))
println(s"Resultado itsEscalasCurso MID-SVCS: $resultado10, Tiempo: $tiempo10")

val (resultado11, tiempo11) = tiempoYResultadoDe(itsEscalasB1("CLO", "SVCS"))
println(s"Resultado itsEscalasCurso CLO-SVCS: $resultado11, Tiempo: $tiempo11")

val (resultado12, tiempo12) = tiempoYResultadoDe(itsEscalasB1("CLO", "SVO"))
println(s"Resultado itsEscalasCurso CLO-SVO: $resultado12, Tiempo: $tiempo12")

val (resultado13, tiempo13) = tiempoYResultadoDe(itsEscalasB1("CLO", "MEX"))
println(s"Resultado itsEscalasCurso CLO-MEX: $resultado13, Tiempo: $tiempo13")

val (resultado14, tiempo14) = tiempoYResultadoDe(itsEscalasB1("CTG", "PTY"))
println(s"Resultado itsEscalasCurso CTG-PTY: $resultado14, Tiempo: $tiempo14")

// Prueba itinerariosAire
val (resultado15, tiempo15) = tiempoYResultadoDe(itsAireB1("MID", "SVCS"))
println(s"Resultado itsAireCurso MID-SVCS: $resultado15, Tiempo: $tiempo15")

val (resultado16, tiempo16) = tiempoYResultadoDe(itsAireB1("CLO", "SVCS"))
println(s"Resultado itsAireCurso CLO-SVCS: $resultado16, Tiempo: $tiempo16")

val (resultado17, tiempo17) = tiempoYResultadoDe(itsAireB1("CLO", "SVO"))
println(s"Resultado itsAireCurso CLO-SVO: $resultado17, Tiempo: $tiempo17")

val (resultado18, tiempo18) = tiempoYResultadoDe(itsAireB1("CLO", "MEX"))
println(s"Resultado itsAireCurso CLO-MEX: $resultado18, Tiempo: $tiempo18")

val (resultado19, tiempo19) = tiempoYResultadoDe(itsAireB1("CTG", "PTY"))
println(s"Resultado itsAireCurso CTG-PTY: $resultado19, Tiempo: $tiempo19")

// Prueba itinerarioSalida
val (resultado20, tiempo20) = tiempoYResultadoDe(itSalidaB1("CTG", "PTY", 11, 40))
println(s"Resultado itSalidaCurso CTG-PTY 11:40: $resultado20, Tiempo: $tiempo20")

val (resultado21, tiempo21) = tiempoYResultadoDe(itSalidaB1("CTG", "PTY", 11, 55))
println(s"Resultado itSalidaCurso CTG-PTY 11:55: $resultado21, Tiempo: $tiempo21")

val (resultado22, tiempo22) = tiempoYResultadoDe(itSalidaB1("CTG", "PTY", 10, 30))
println(s"Resultado itSalidaCurso CTG-PTY 10:30: $resultado22, Tiempo: $tiempo22")

// Pruebas con datos adicionales
val (resultado23, tiempo23) = tiempoYResultadoDe(itsC1("ORD", "TPA"))
println(s"Resultado its100C1 ORD-TPA: $resultado23, Tiempo: $tiempo23")

val (resultado24, tiempo24) = tiempoYResultadoDe(itsTiempoC1("ORD", "TPA"))
println(s"Resultado itsTiempo100C1 ORD-TPA: $resultado24, Tiempo: $tiempo24")

val (resultado25, tiempo25) = tiempoYResultadoDe(itsEscalasC1("ORD", "TPA"))
println(s"Resultado itsEsc100C1 ORD-TPA: $resultado25, Tiempo: $tiempo25")

val (resultado26, tiempo26) = tiempoYResultadoDe(itsAireC1("ORD", "TPA"))
println(s"Resultado itsAire100C1 ORD-TPA: $resultado26, Tiempo: $tiempo26")

val (resultado27, tiempo27) = tiempoYResultadoDe(itsSalidaC1("ORD", "TPA", 18, 30))
println(s"Resultado itsSalida100C1 ORD-TPA: $resultado27, Tiempo: $tiempo27")
*/
val (resultado28, tiempo28) = tiempoYResultadoDe(its200C("ORD", "TPA"))
println(s"Resultado its200C ORD-TPA: $resultado28, Tiempo: $tiempo28")

val (resultado29, tiempo29) = tiempoYResultadoDe(itsTiempo200C("ORD", "TPA"))
println(s"Resultado itsTiempo200C ORD-TPA: $resultado29, Tiempo: $tiempo29")

val (resultado30, tiempo30) = tiempoYResultadoDe(itsEscalas200C("ORD", "TPA"))
println(s"Resultado itsEscalas200C ORD-TPA: $resultado30, Tiempo: $tiempo30")

val (resultado31, tiempo31) = tiempoYResultadoDe(itsAire200C("ORD", "TPA"))
println(s"Resultado itsAire200C ORD-TPA: $resultado31, Tiempo: $tiempo31")

val (resultado32, tiempo32) = tiempoYResultadoDe(itsSalida200C("ORD", "TPA", 18, 30))
println(s"Resultado itsSalida200C ORD-TPA: $resultado32, Tiempo: $tiempo32")

/*
val its300C = itinerarios(vuelosC1 ++ vuelosC2 ++ vuelosC3, aeropuertosList)
println(s"Tiempo its300C ORD-TPA: ${tiempoYResultadoDe(its300C("ORD", "TPA"))}")

val its400C = itinerarios(vuelosC1 ++ vuelosC2 ++ vuelosC3 ++ vuelosC4, aeropuertosList)
println(s"Tiempo its400C ORD-TPA: ${tiempoYResultadoDe(its400C("ORD", "TPA"))}")

val its500C = itinerarios(vuelosC1 ++ vuelosC2 ++ vuelosC3 ++ vuelosC4 ++ vuelosC5, aeropuertosList)
println(s"Tiempo its500C ORD-TPA: ${tiempoYResultadoDe(its500C("ORD", "TPA"))}")
 */