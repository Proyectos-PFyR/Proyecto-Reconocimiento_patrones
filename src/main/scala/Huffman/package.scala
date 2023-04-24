package object Huffman
{
  abstract class ArbolH
  case class Nodo(izq: ArbolH, der: ArbolH, cars: List[Char], peso: Int) extends ArbolH
  case class Hoja(car: Char, peso: Int) extends ArbolH

  def hacerNodoArbolH(izq: ArbolH, der: ArbolH): Nodo =
  {
    Nodo(izq, der, cars(izq) ::: cars(der), peso(izq) + peso(der))
  }

  def peso(arbol: ArbolH): Int =
  {
    arbol match
    {
      case Nodo(izq, der, cars, peso) => peso
      case Hoja(car, peso) => peso
    }
  }

  def cars(arbol: ArbolH): List[Char] =
  {
    arbol match
    {
      case Nodo(izq, der, cars, peso) => cars
      case Hoja(car, peso) => List(car)
    }
  }

  def ocurrencias(cars: List[Char]): List[(Char, Int)] =
  {
    def empaquetar(carsEmp: List[Char]): List[List[Char]] =
    {
      carsEmp match
      {
        case Nil => Nil
        case head :: tail => carsEmp.filter(x => (x == head)) :: empaquetar(carsEmp.filterNot(x => (x == head)))
      }
    }

    def codificar(carsCod: List[List[Char]]): List[(Char, Int)] =
    {
      carsCod match
      {
        case Nil => Nil
        case head :: tail => (head.head, head.length) :: codificar(tail)
      }

    }

    codificar(empaquetar(cars))
  }

  def listaDeHojasOrdenadas(frecs: List[(Char, Int)]): List[Hoja] =
  {
    def convertir(frecsCon: List[(Char, Int)]): List[Hoja] =
    {
      frecsCon match
      {
        case Nil => Nil
        case head :: tail => Hoja(head._1, head._2) :: convertir(tail)
      }
    }

    def ordenar(frecsOrd: List[Hoja]): List[Hoja] =
    {
      def orden(frecsOrd1: List[Hoja], frecsOrd2: List[Hoja]): List[Hoja] =
      {
        frecsOrd1 match
        {
          case Nil => frecsOrd2
          case head1 :: tail1 => frecsOrd2 match
          {
            case Nil => frecsOrd1
            case head2 :: tail2 => if (peso(head1) < peso(head2)) head1 :: orden(tail1, frecsOrd2) else head2 :: orden(frecsOrd1, tail2)
          }
        }
      }

      val n = frecsOrd.length / 2
      if (n == 0)
      {
        frecsOrd
      }
      else
      {
        val (frecs1, frecs2) = frecsOrd splitAt n
        orden(ordenar(frecs1), ordenar(frecs2))
      }
    }

    ordenar(convertir(frecs))
  }

  def ordenar2(frecsOrd: List[ArbolH]): List[ArbolH] = {
    def orden(frecsOrd1: List[ArbolH], frecsOrd2: List[ArbolH]): List[ArbolH] = {
      frecsOrd1 match {
        case Nil => frecsOrd2
        case head1 :: tail1 => frecsOrd2 match {
          case Nil => frecsOrd1
          case head2 :: tail2 => if (peso(head1) < peso(head2)) head1 :: orden(tail1, frecsOrd2) else if (peso(head1) == peso(head2)) head1 :: orden(tail1, frecsOrd2) else head2 :: orden(frecsOrd1, tail2)
        }
      }
    }

    val n = frecsOrd.length / 2
    if (n == 0) {
      frecsOrd
    }
    else {
      val (frecs1, frecs2) = frecsOrd splitAt n
      orden(ordenar2(frecs1), ordenar2(frecs2))
    }
  }

  def listaUnitaria(arboles: List[ArbolH]): Boolean =
  {
    arboles match
    {
      case Nil => false
      case List(a) => true
      case _ => false
    }
  }

  def combinar(arboles: List[ArbolH]): List[ArbolH] = {
    arboles match
    {
      case Nil => Nil
      case a :: b :: tail => hacerNodoArbolH(a, b) :: tail
      case _ => arboles
    }
  }

  def hastaQue (cond: List[ArbolH] => Boolean, mezclar: List[ArbolH] => List[ArbolH])(listaOrdenadaArboles: List[ArbolH]): List[ArbolH] =
  {
    println(listaOrdenadaArboles)
    if(cond(listaOrdenadaArboles))
    {
      listaOrdenadaArboles
    }
    else
    {
      hastaQue(cond, mezclar)(ordenar2(mezclar(listaOrdenadaArboles)))
    }

  }
  def crearArbolDeHuffman(cars: List[Char]): ArbolH =
  {
    hastaQue(listaUnitaria, combinar)(listaDeHojasOrdenadas(ocurrencias(cars))).head
  }

  type Bit = Int

  def decodificar(arbol: ArbolH, bits: List[Bit]): List[Char] = {
    def recorrerArbol(auxArbol: ArbolH, auxBits: List[Bit]): List[Char] = {
      println("iteracion")
      println(auxArbol)
      println(auxBits)
      auxBits match {
        case head :: tail => auxArbol match {
          case Nodo(izq, der, cars, peso) => if (head == 1) recorrerArbol(izq, tail) else recorrerArbol(der, tail)
          case Hoja(car, peso) => car :: decodificar(arbol, auxBits)
        }
        case Nil => auxArbol match {
          case Nodo(izq, der, cars, peso) => Nil
          case Hoja(car, peso) => car :: Nil
        }
      }
    }
    recorrerArbol(arbol,bits)
  }

 /*
  def codificar(arbol: ArbolH)(texto: List[Char]): List[Bit] =
  {
    def encontrarCaracter(arbolito: ArbolH, auxTexto: List[Char]): List[Bit] =
    {
      arbolito match
      {
        case Nodo(izq, der, carss, peso) => if (cars(izq).contains(auxTexto.head)) 1 :: encontrarCaracter(izq, auxTexto) else if (cars(der).contains(auxTexto.head)) 0 :: encontrarCaracter(der, auxTexto) else concatenarLista(arbol, auxTexto.tail)
        case Hoja(car, peso) => concatenarLista(arbol, auxTexto.tail)
      }
    }

    def concatenarLista(auxArbol: ArbolH, auxTexto: List[Char]): List[Bit] =
    {
      auxTexto match
      {
        case head :: tail => encontrarCaracter(auxArbol, auxTexto)
        case Nil => Nil
      }
    }

  concatenarLista(arbol,texto)
  }

  */

  def codificar(arbol: ArbolH)(texto: List[Char]): List[Bit] = {
    def encontrarCaracter(arbolito: ArbolH, auxTexto: List[Char]): List[Bit] = {
      auxTexto match {
        case head :: tail => arbolito match {
          case Nodo(izq, der, carss, peso) => if (cars(izq).contains(auxTexto.head)) 1 :: encontrarCaracter(izq, auxTexto) else if (cars(der).contains(auxTexto.head)) 0 :: encontrarCaracter(der, auxTexto) else encontrarCaracter(arbol, auxTexto.tail)
          case Hoja(car, peso) => encontrarCaracter(arbol, auxTexto.tail)
      }
        case Nil => Nil
      }
    }
    encontrarCaracter(arbol, texto)
  }

  type TablaCodigos = List [(Char, List[Bit])]

  def codigoEnBits(tabla: TablaCodigos)(car: Char): List[Bit] = {
    tabla match {
      case head::tail => val datosHead = head; val (d1, d2) = datosHead; if(car.equals(d1)) d2 else codigoEnBits(tail)(car)
      case Nil => Nil
    }
  }

  def mezclarTablasDeCodigos(a: TablaCodigos, b: TablaCodigos): TablaCodigos = {

    def concatenarPrefijo(prefijo: Int, auxTabla: TablaCodigos) : TablaCodigos = {
      auxTabla match {
        case head::tail => val datosHead = head; val (d1,d2) = datosHead; List((d1, prefijo::d2)) ++ concatenarPrefijo(prefijo, tail)
        case Nil => Nil
      }
    }
    a match {
      case head => b match {
        case head => concatenarPrefijo(0,a) ++ concatenarPrefijo(1,b)
        case Nil => concatenarPrefijo(0,a)
      }
      case Nil => b match {
        case head => concatenarPrefijo(1,b)
        case Nil => Nil
      }
    }
  }

  def convertir(arbol: ArbolH): TablaCodigos =
  {
    println(arbol)
    arbol match
    {
      case Nodo(izq, der, cars, peso) => mezclarTablasDeCodigos(convertir(der),convertir(izq))
      case Hoja(car, peso) => List((car, List()))
    }
  }

  def codificarRapido(arbol: ArbolH)(texto: List[Char]): List[Bit] = {
    val listArbol = convertir(arbol)
    def generarListaBits(auxTexto: List[Char], lista:TablaCodigos): List[Bit] = {
        auxTexto match {
          case head :: tail => lista match {
            case head2 :: tail2 => val datosHead = head2; val (d1,d2) = datosHead; if(head.equals(d1)) d2 ::: generarListaBits(tail,listArbol) else generarListaBits(auxTexto, tail2)
            case Nil => generarListaBits(tail, listArbol)
          }
          case Nil => Nil
        }
    }
    generarListaBits(texto,listArbol)
  }
}
