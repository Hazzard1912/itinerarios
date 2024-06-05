import Datos._
import Itinerarios._
import org.scalameter._

def tiempoDe[T](body: => T) = {
  val timeA1 = config(
    KeyValue(Key.exec.minWarmupRuns -> 20),
    KeyValue(Key.exec.maxWarmupRuns -> 60),
    KeyValue(Key.verbose -> false)
  ) withWarmer(new Warmer.Default) measure (body)
  timeA1
}

// Ejemplo curso pequeño
val itsCurso = itinerarios(vuelosC1 ++ vuelosC2, aeropuertosCurso.toList)

// Aeropuertos incomunicados
println(s"Tiempo itsCurso PHX-DFW: ${tiempoDe(itsCurso("PHX", "DFW"))}")

// Descomentar los siguientes bloques para más pruebas:

// 4 itinerarios CLO-SVO
println(s"Tiempo itsCurso CLO-SVO: ${tiempoDe(itsCurso("CLO", "SVO"))}")

// 2 itinerarios CLO-MEX
println(s"Tiempo itsCurso CLO-MEX: ${tiempoDe(itsCurso("CLO", "MEX"))}")

// 2 itinerarios CTG-PTY
println(s"Tiempo itsCurso CTG-PTY: ${tiempoDe(itsCurso("CTG", "PTY"))}")

val itsTiempoCurso = itinerariosTiempo(vuelosCurso, aeropuertosCurso)

// Prueba itinerariosTiempo
println(s"Tiempo itsTiempoCurso MID-SVCS: ${tiempoDe(itsTiempoCurso("MID", "SVCS"))}")
println(s"Tiempo itsTiempoCurso CLO-SVCS: ${tiempoDe(itsTiempoCurso("CLO", "SVCS"))}")
println(s"Tiempo itsTiempoCurso CLO-SVO: ${tiempoDe(itsTiempoCurso("CLO", "SVO"))}")
println(s"Tiempo itsTiempoCurso CLO-MEX: ${tiempoDe(itsTiempoCurso("CLO", "MEX"))}")
println(s"Tiempo itsTiempoCurso CTG-PTY: ${tiempoDe(itsTiempoCurso("CTG", "PTY"))}")

val itsEscalasCurso = itinerariosEscalas(vuelosCurso, aeropuertosCurso)

// Prueba itinerariosEscalas
println(s"Tiempo itsEscalasCurso MID-SVCS: ${tiempoDe(itsEscalasCurso("MID", "SVCS"))}")
println(s"Tiempo itsEscalasCurso CLO-SVCS: ${tiempoDe(itsEscalasCurso("CLO", "SVCS"))}")
println(s"Tiempo itsEscalasCurso CLO-SVO: ${tiempoDe(itsEscalasCurso("CLO", "SVO"))}")
println(s"Tiempo itsEscalasCurso CLO-MEX: ${tiempoDe(itsEscalasCurso("CLO", "MEX"))}")
println(s"Tiempo itsEscalasCurso CTG-PTY: ${tiempoDe(itsEscalasCurso("CTG", "PTY"))}")

val itsAireCurso = itinerariosAire(vuelosCurso, aeropuertosCurso)

// Prueba itinerariosAire
println(s"Tiempo itsAireCurso MID-SVCS: ${tiempoDe(itsAireCurso("MID", "SVCS"))}")
println(s"Tiempo itsAireCurso CLO-SVCS: ${tiempoDe(itsAireCurso("CLO", "SVCS"))}")
println(s"Tiempo itsAireCurso CLO-SVO: ${tiempoDe(itsAireCurso("CLO", "SVO"))}")
println(s"Tiempo itsAireCurso CLO-MEX: ${tiempoDe(itsAireCurso("CLO", "MEX"))}")
println(s"Tiempo itsAireCurso CTG-PTY: ${tiempoDe(itsAireCurso("CTG", "PTY"))}")

val itSalidaCurso = itinerarioSalida(vuelosCurso, aeropuertosCurso)

// Prueba itinerarioSalida
println(s"Tiempo itSalidaCurso CTG-PTY 11:40: ${tiempoDe(itSalidaCurso("CTG", "PTY", 11, 40))}")
println(s"Tiempo itSalidaCurso CTG-PTY 11:55: ${tiempoDe(itSalidaCurso("CTG", "PTY", 11, 55))}")
println(s"Tiempo itSalidaCurso CTG-PTY 10:30: ${tiempoDe(itSalidaCurso("CTG", "PTY", 10, 30))}")

// Pruebas con datos adicionales

val its100C1 = itinerarios(vuelosC1, aeropuertos.toList)
val itsTpo100C1 = itinerariosTiempo(vuelosC1, aeropuertos.toList)
val itsEsc100C1 = itinerariosEscalas(vuelosC1, aeropuertos.toList)
val itsAir100C1 = itinerariosAire(vuelosC1, aeropuertos.toList)
val itsSal100C1 = itinerarioSalida(vuelosC1, aeropuertos.toList)

println(s"Tiempo its100C1 ORD-TPA: ${tiempoDe(its100C1("ORD", "TPA"))}")
println(s"Tiempo itsTpo100C1 ORD-TPA: ${tiempoDe(itsTpo100C1("ORD", "TPA"))}")
println(s"Tiempo itsEsc100C1 ORD-TPA: ${tiempoDe(itsEsc100C1("ORD", "TPA"))}")
println(s"Tiempo itsAir100C1 ORD-TPA: ${tiempoDe(itsAir100C1("ORD", "TPA"))}")
println(s"Tiempo itsSal100C1 ORD-TPA: ${tiempoDe(itsSal100C1("ORD", "TPA", 18, 30))}")

val its200C = itinerarios(vuelosC1 ++ vuelosC2, aeropuertos.toList)
val itsTpo200C = itinerariosTiempo(vuelosC1 ++ vuelosC2, aeropuertos.toList)
val itsEsc200C = itinerariosEscalas(vuelosC1 ++ vuelosC2, aeropuertos.toList)
val itsAir200C = itinerariosAire(vuelosC1 ++ vuelosC2, aeropuertos.toList)
val itsSal200C = itinerarioSalida(vuelosC1 ++ vuelosC2, aeropuertos.toList)

println(s"Tiempo its200C ORD-TPA: ${tiempoDe(its200C("ORD", "TPA"))}")
println(s"Tiempo itsTpo200C ORD-TPA: ${tiempoDe(itsTpo200C("ORD", "TPA"))}")
println(s"Tiempo itsEsc200C ORD-TPA: ${tiempoDe(itsEsc200C("ORD", "TPA"))}")
println(s"Tiempo itsAir200C ORD-TPA: ${tiempoDe(itsAir200C("ORD", "TPA"))}")
println(s"Tiempo itsSal200C ORD-TPA: ${tiempoDe(itsSal200C("ORD", "TPA", 18, 30))}")

/*
val its300C = itinerarios(vuelosC1 ++ vuelosC2 ++ vuelosC3, aeropuertos)
println(s"Tiempo its300C ORD-TPA: ${tiempoDe(its300C("ORD", "TPA"))}")

val its400C = itinerarios(vuelosC1 ++ vuelosC2 ++ vuelosC3 ++ vuelosC4, aeropuertos)
println(s"Tiempo its400C ORD-TPA: ${tiempoDe(its400C("ORD", "TPA"))}")

val its500C = itinerarios(vuelosC1 ++ vuelosC2 ++ vuelosC3 ++ vuelosC4 ++ vuelosC5, aeropuertos)
println(s"Tiempo its500C ORD-TPA: ${tiempoDe(its500C("ORD", "TPA"))}")
*/
