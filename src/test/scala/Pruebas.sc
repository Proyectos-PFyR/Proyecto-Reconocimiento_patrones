import Huffman._
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

val tuplas1 = List(('A', 10), ('B', 6), ('C', 2), ('D', 1))
val tuplas2 = List(('E', 2), ('F', 2))
val tuplas3 = List(('G', 8), ('H', 3), ('I', 12))
val tuplas4 = List(('J', 1), ('K', 2), ('L', 5), ('M', 9), ('N', 3))
val tuplas5 = List(('A', 50))

val cars1 = List('Q', 'U', 'E', 'E', 'S', 'E', 'S', 'O')
val cars2 = List('A', 'A', 'A', 'B', 'B', 'C')
val cars3 = List('D', 'D', 'D')
val cars4 = List('Z', 'Z', 'L', 'Z', 'Z', 'L', 'L', 'Z', 'Z', 'L')
val cars5 = List('M', 'I' , 'M', 'A', 'M', 'A', 'M', 'E', 'M', 'I', 'M', 'A')
val cars6 = List('A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'C', 'C', 'C', 'C', 'C', 'D', 'D', 'D', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'F', 'F', 'F', 'F', 'G', 'H', 'H')
val cars7 = List('B','A','D','A','E','A','D','F','A','C')

val bits1 = List(0, 0, 1, 1)
val bits2 = List(0, 1, 0 , 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0)
val bits3 = List(0, 0, 1, 0, 1, 1, 0, 1, 0, 0)
val bits4 = List(0, 1, 1, 0, 0, 0, 1, 1, 1)

val tablaCodigos1 = List(('A',List(0,0)), ('B',List(0,1,0)), ('C',List(1,1,0)))
val tablaCodigos2 = List(('D',List(0,1,1,0)), ('E',List(1,0)), ('F',List(1,1,1)))
val tablaCodigos3 = List(('M',List(0)), ('A',List(1, 0)), ('I',List(1, 1, 0)), ('E',List(1, 1, 1, 1)))
val tablaCodigos4 = List(('Z',List(0)), ('Y',List(1,0)), ('X',List(1,1)))
val tablaCodigos5 = List (('A',List(0,0)), ('B',List(0,1,0)), ('C',List(1,1,0)), ('D',List(0,1,1,0)), ('E',List(1,0)), ('F',List(1,1,1)), ('G',List(0, 1, 1, 1)), ('H',List(0, 1, 1, 1, 0)))

val tablaHijo1 = List(('A',List(0)), ('B',List(1,0)), ('D',List(1,1,0)))
val tablaHijo2 = List(('E',List(0)), ('F',List(1,1)))
val tablaHijo3 = List(('M',List(0)), ('A',List(1)))
val tablaHijo4 = List(('I',List(1, 1, 0)), ('E',List(1, 1, 1, 1)))

val arbolH6 = crearArbolDeHuffman(cars6)

/******************************************************************************
 * FUNCIÓN:                peso
 ******************************************************************************/
peso(hoja3)
peso(hoja4)
peso(nodo3)
peso(nodo4)
peso(nodo5)

/******************************************************************************
 * FUNCIÓN:                cars
 ******************************************************************************/
cars(hoja1)
cars(hoja2)
cars(nodo1)
cars(nodo2)
cars(hoja5)

/******************************************************************************
 * FUNCIÓN:                ocurrencias
 ******************************************************************************/
ocurrencias(cars1)
ocurrencias(cars2)
ocurrencias(cars3)
ocurrencias(cars4)
ocurrencias(cars5)

/******************************************************************************
 * FUNCIÓN:                listaDeHojasOrdenadas
 ******************************************************************************/
listaDeHojasOrdenadas(tuplas1)
listaDeHojasOrdenadas(tuplas2)
listaDeHojasOrdenadas(tuplas3)
listaDeHojasOrdenadas(tuplas4)
listaDeHojasOrdenadas(tuplas5)

/******************************************************************************
 * FUNCIÓN:                listaUnitaria
 ******************************************************************************/
listaUnitaria(List(hoja5, nodo4, hoja1))
listaUnitaria(List(hoja5))
listaUnitaria(List(nodo2, nodo3))
listaUnitaria(List(hoja2, hoja3))
listaUnitaria(List(nodo2, nodo1))

/******************************************************************************
 * FUNCIÓN:                combinar
 ******************************************************************************/
combinar(List(hoja5, hoja1, nodo4))
combinar(List(hoja5))
combinar(List(nodo2, nodo1, nodo4, nodo3))
combinar(List(hoja5, hoja3, hoja4, hoja2, hoja1))
combinar(List(hoja2, nodo1, nodo4, nodo3))

/******************************************************************************
 * FUNCIÓN:                hastaQue
 ******************************************************************************/
hastaQue(listaUnitaria, combinar)(List(hoja5, hoja1, nodo4))
hastaQue(listaUnitaria, combinar)(List(hoja5))
hastaQue(listaUnitaria, combinar)(List(nodo2, nodo1))
hastaQue(listaUnitaria, combinar)(List(hoja5, hoja3, hoja4, hoja2, hoja1))
hastaQue(listaUnitaria, combinar)(List(hoja2, nodo1))

/******************************************************************************
 * FUNCIÓN:                crearArbolDeHuffman
 ******************************************************************************/
val arbolH1 = crearArbolDeHuffman(cars1)
val arbolH2 = crearArbolDeHuffman(cars2)
val arbolH3 = crearArbolDeHuffman(cars3)
val arbolH4 = crearArbolDeHuffman(cars4)
val arbolH5 = crearArbolDeHuffman(cars5)

/******************************************************************************
 * FUNCIÓN:                decodificar
 ******************************************************************************/
decodificar(arbolH2, bits1)
decodificar(arbolH4, bits3)
decodificar(arbolH5, bits3)
decodificar(arbolH6, bits2)
decodificar(arbolH6, bits4)

/******************************************************************************
 * FUNCIÓN:                codificar
 ******************************************************************************/
codificar(arbolH2)(cars2)
codificar(arbolH5)(cars5)
codificar(arbolH1)(cars1)
val codificarCurry = codificar(arbolH6)(_)
codificarCurry(cars7)
codificarCurry(cars2)

/******************************************************************************
 * FUNCIÓN:                codigoEnBits
 ******************************************************************************/
codigoEnBits(tablaCodigos1)('A')
codigoEnBits(tablaCodigos2)('D')
codigoEnBits(tablaCodigos3)('I')
codigoEnBits(tablaCodigos4)('Z')
codigoEnBits(tablaCodigos5)('G')

/******************************************************************************
 * FUNCIÓN:                mezclarTablasDeCodigos
 ******************************************************************************/
mezclarTablasDeCodigos(tablaHijo1, tablaHijo2)
mezclarTablasDeCodigos(tablaHijo3, tablaHijo4)
mezclarTablasDeCodigos(tablaHijo2, tablaHijo1)
mezclarTablasDeCodigos(tablaCodigos3, tablaCodigos4)
mezclarTablasDeCodigos(tablaHijo4, tablaHijo1)

/******************************************************************************
 * FUNCIÓN:                convertir
 ******************************************************************************/
convertir(arbolH1)
convertir(arbolH2)
convertir(arbolH4)
convertir(arbolH5)
convertir(arbolH6)
/******************************************************************************
 * FUNCIÓN:                codificarRapido
 ******************************************************************************/
codificarRapido(arbolH1)(cars1)
codificarRapido(arbolH5)(cars5)
codificarRapido(arbolH4)(cars4)
val codificarRapidoCurry = codificarRapido(arbolH6)(_)
codificarRapidoCurry(cars7)
codificarRapidoCurry(cars2)

