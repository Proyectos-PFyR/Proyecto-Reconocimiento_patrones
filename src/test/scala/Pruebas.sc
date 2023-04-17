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

val z = List('M', 'I', 'M', 'A', 'M', 'A', 'M', 'E', 'M', 'I', 'M', 'A')

val g = crearArbolDeHuffman(z)

decodificar(g, List(0,1,1,0))

