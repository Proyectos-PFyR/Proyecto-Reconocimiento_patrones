import Huffman._

/*hacerNodoArbolH(Hoja('A', 5), Hoja('B', 6))

val a = hacerNodoArbolH(Nodo(Hoja('A', 5), Hoja('B', 6), List('A', 'B'), 11), Nodo(Hoja('C', 3), Hoja('D', 2), List('C', 'D'), 5))

peso(a)
cars(a)

val z = List('M', 'I', 'M', 'A', 'M', 'A', 'M', 'E', 'M', 'I', 'M', 'A')

val b = ocurrencias(List('A', 'B', 'B', 'C', 'A', 'A', 'C', 'D', 'D'))

listaDeHojasOrdenadas(b)

val c = List(Hoja('A', 1), Hoja('B', 2), Hoja('C', 3))
val d = List(Hoja('A', 5))
val e = List(Nodo(Hoja('A', 5), Hoja('B', 6), List('A', 'B'), 11))
val f = List(Nodo(Hoja('A', 5), Hoja('B', 6), List('A', 'B'), 11), Nodo(Hoja('C', 3), Hoja('D', 2), List('C', 'D'), 5))

listaUnitaria(c)
listaUnitaria(d)
listaUnitaria(e)
listaUnitaria(f)*/

/*val z = List('M', 'I', 'M', 'A', 'M', 'A', 'M', 'E', 'M', 'I', 'M', 'A')

val g = crearArbolDeHuffman(z)
val c = crearArbolDeHuffman(List('A', 'B'))

decodificar(g, List(0,1,1,0))

ocurrencias(List())*/

/******************************************************************************
 * VALORES
 ******************************************************************************/

val hoja1 = Hoja('T', 10)
val hoja2 = Hoja('C', 7)
val hoja3 = Hoja('A', 2)
val hoja4 = Hoja('P', 5)
val hoja5 = Hoja('Z', 1)

val nodo1 = Nodo(hoja3, hoja1, List('A', 'T'), 12)
val nodo2 = Nodo(hoja5, hoja4, List('Z', 'P'), 6)
val nodo3 = Nodo(nodo2, nodo1, List('Z', 'P', 'A', 'T'), 18)
val nodo4 = Nodo(hoja2, hoja1, List('C', 'T'), 17)
val nodo5 = Nodo(hoja5, hoja3, List('Z', 'P'), 3)

/******************************************************************************
 * FUNCIÓN:                cars
 ******************************************************************************/
cars(hoja1)
cars(hoja2)
cars(nodo1)
cars(nodo2)
cars(hoja5)

/******************************************************************************
 * FUNCIÓN:                peso
 ******************************************************************************/
peso(hoja3)
peso(hoja4)
peso(nodo3)
peso(nodo4)
peso(nodo5)

/******************************************************************************
 * FUNCIÓN:                listaUnitaria
 ******************************************************************************/
listaUnitaria(List(hoja1, hoja4, nodo5))
listaUnitaria(List(hoja5))
listaUnitaria(List(nodo3))
listaUnitaria(List(hoja2, hoja3))
listaUnitaria(List(nodo2, nodo1))