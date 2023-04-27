package object Huffman
{
  /** ****************************************************************************
   * Funciones Entregadas por el profesor
   * **************************************************************************** */
  abstract class ArbolH
  case class Nodo(izq: ArbolH, der: ArbolH, cars: List[Char], peso: Int) extends ArbolH
  case class Hoja(car: Char, peso: Int) extends ArbolH

  type Bit = Int

  type TablaCodigos = List [(Char, List[Bit])]

  def hacerNodoArbolH(izq: ArbolH, der: ArbolH): Nodo =
  {
    Nodo(izq, der, cars(izq) ::: cars(der), peso(izq) + peso(der))
  }

  /** ****************************************************************************
   * FUNCIÓN:                grande
   * DESCRIPCIÓN:            Función que retorna el peso de un objeto Hoja o Nodo
   * PARÁMETROS DE ENTRADA
   * $arbol :                Objeto ArbolH
   * RETORNO
   * peso :  			           Frecuencia o peso.
   * **************************************************************************** */
  def peso(arbol: ArbolH): Int =
  {
    arbol match
    {
      case Nodo(izq, der, cars, peso) => peso
      case Hoja(car, peso) => peso
    }
  }

  /** ****************************************************************************
   * FUNCIÓN:                cars
   * DESCRIPCIÓN:            Función que retorna una lista de caracteres de un objeto Hoja o Nodo
   * PARÁMETROS DE ENTRADA
   * $arbol :                Objeto ArbolH
   * RETORNO
   * List[Char] :  			     Lista de caracteres
   * **************************************************************************** */
  def cars(arbol: ArbolH): List[Char] =
  {
    arbol match
    {
      case Nodo(izq, der, cars, peso) => cars
      case Hoja(car, peso) => List(car)
    }
  }

  /** ****************************************************************************
   * FUNCIÓN:                ocurrencias
   * DESCRIPCIÓN:            Función que empaqueta una lista de caracteres en tuplas con su caracter y la frecuencia de este
   * PARÁMETROS DE ENTRADA
   * cars :                  Lista de caracteres.
   * RETORNO
   * List[(Char, Int)] :  	 Lista de tuplas de caracteres y enteros
   * **************************************************************************** */
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

  /** ****************************************************************************
   * FUNCIÓN:                listaDeHojasOrdenadas
   * DESCRIPCIÓN:            Función que crea objetos Hoja por cada tupla con su respectivo caracter y peso, y los ordena ascendentemente.
   * PARÁMETROS DE ENTRADA
   * cars :                  Lista de caracteres.
   * RETORNO
   * List[(Char, Int)] :  	 Lista de tuplas de caracteres y enteros
   * **************************************************************************** */
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

  /** ****************************************************************************
   * FUNCIÓN:                listaUnitaria
   * DESCRIPCIÓN:            Función que comprueba si una lista de objetos ArbolH tiene solo un elemento
   * PARÁMETROS DE ENTRADA
   * arboles :               Lista de objetos ArbolH
   * RETORNO
   * Boolean :  	           True en caso de una lista unitaria, false en caso contrario
   * **************************************************************************** */
  def listaUnitaria(arboles: List[ArbolH]): Boolean =
  {
    arboles match
    {
      case Nil => false
      case head :: Nil => true
      case _ => false
    }
  }

  /** ****************************************************************************
   * FUNCIÓN:                combinar
   * DESCRIPCIÓN:            Función que combina los 2 primeros objetos de la lista de objetos ArbolH y ordena nuevamente a la lista
   * PARÁMETROS DE ENTRADA
   * arboles :               Lista de objetos ArbolH
   * RETORNO
   * List[ArbolH] :  	       Lista de objetos ArbolH con un elemento menos y ordenada
   * **************************************************************************** */
  def combinar(arboles: List[ArbolH]): List[ArbolH] =
  {
    def ordenar(nodo: Nodo, cola: List[ArbolH]): List[ArbolH] =
    {
      cola match
      {
        case Nil =>  nodo :: cola
        case head :: tail => if(peso(nodo) > peso(head)) head :: ordenar(nodo, tail) else nodo :: cola
      }
    }

    arboles match
    {
      case Nil => Nil
      case a :: b :: tail => ordenar(hacerNodoArbolH(a, b), tail)
      case a :: Nil => arboles
    }
  }

  /** ****************************************************************************
   * FUNCIÓN:                hastaQue
   * DESCRIPCIÓN:            Función currificacda que aplica una funcion mezclar a una lista de objetos ArbolH hasta que se cumpla la funcion cond
   * PARÁMETROS DE ENTRADA
   * cond :                  Funcion condicion del tipo List[ArbolH] => Boolean
   * mezlar :                Funcion operacion del tipo List[ArbolH] => List[ArbolH]
   * listaOrdenadaArboles    Lista de objetos ArbollH
   * RETORNO
   * List[ArbolH] :  	       Lista de objetos ArbolH con un solo elemento
   * **************************************************************************** */
  def hastaQue (cond: List[ArbolH] => Boolean, mezclar: List[ArbolH] => List[ArbolH])(listaOrdenadaArboles: List[ArbolH]): List[ArbolH] =
  {
    if(cond(listaOrdenadaArboles))
    {
      listaOrdenadaArboles
    }
    else
    {
      hastaQue(cond, mezclar)(mezclar(listaOrdenadaArboles))
    }
  }

  /** ****************************************************************************
   * FUNCIÓN:                crearArbolDeHuffman
   * DESCRIPCIÓN:            Función que crea un Arbol de Huffman
   * PARÁMETROS DE ENTRADA
   * cars :                  Lista de caracteres
   * ArbolH :  	             Arbol de huffman
   * **************************************************************************** */
  def crearArbolDeHuffman(cars: List[Char]): ArbolH =
  {
    hastaQue(listaUnitaria, combinar)(listaDeHojasOrdenadas(ocurrencias(cars))).head
  }

  /** ****************************************************************************
   * FUNCIÓN:                decodificar
   * DESCRIPCIÓN:            retorna la lista de caracteres que correspondiente a la lista de Bits resuelta por el ArbolH
   * PARÁMETROS DE ENTRADA:
   * $arbol:                 Un arbol de Huffman : ArbolH.
   * $bits:                  Una lista de Bits: List[Bit]
   * RETORNO
   * List[Char]:  			     La lista de caracteres decodificada mediante la lista de Bits y el arbol de Huffman.
   * **************************************************************************** */
  def decodificar(arbol: ArbolH, bits: List[Bit]): List[Char] =
  {
    def recorrerArbol(auxArbol: ArbolH, auxBits: List[Bit]): List[Char] =
    {
      auxBits match
      {
        case head :: tail => auxArbol match
        {
          case Nodo(izq, der, cars, peso) => if (head == 1) recorrerArbol(izq, tail) else recorrerArbol(der, tail)
          case Hoja(car, peso) => car :: decodificar(arbol, auxBits)
        }
        case Nil => auxArbol match
        {
          case Nodo(izq, der, cars, peso) => Nil
          case Hoja(car, peso) => car :: Nil
        }
      }
    }

    recorrerArbol(arbol, bits)
  }

  /** ****************************************************************************
   * FUNCIÓN:                contiene
   * DESCRIPCIÓN:            Verifica si una Lista contiene un caracter en especifico
   * PARÁMETROS DE ENTRADA:
   * $lista:                  lista de caracteres : List[Char].
   * $c:                      Un caracter: Char
   * RETORNO
   * Boolean:  			          false o true
   * **************************************************************************** */
  def contiene(lista: List[Char], c: Char): Boolean =
  {
    lista match
    {
      case head :: tail => if (head.equals(c)) true else contiene(tail, c)
      case Nil => false
    }
  }

  /** ****************************************************************************
   * FUNCIÓN:                codificar
   * DESCRIPCIÓN:            retorna la lista de Bits que correspondiente a lo que representa cada caracter en el ArbolH.
   * PARÁMETROS DE ENTRADA:
   * $arbol:                 Un arbol de Huffman : ArbolH.
   * $texto:                 Una lista de Caracteres: List[Char]
   * RETORNO
   * List[Char]:  			     La lista de Bits codificada mediante la lista de Caracteres y el arbol de Huffman.
   * **************************************************************************** */
  def codificar(arbol: ArbolH)(texto: List[Char]): List[Bit] =
  {
    def encontrarCaracter(arbolito: ArbolH, auxTexto: List[Char]): List[Bit] =
    {
      auxTexto match
      {
        case head :: tail => arbolito match
        {
          case Nodo(izq, der, carss, peso) => if (contiene(cars(izq), auxTexto.head)) 1 :: encontrarCaracter(izq, auxTexto) else if (contiene(cars(der), auxTexto.head)) 0 :: encontrarCaracter(der, auxTexto) else encontrarCaracter(arbol, auxTexto.tail)
          case Hoja(car, peso) => encontrarCaracter(arbol, auxTexto.tail)
        }
        case Nil => Nil
      }
    }

    encontrarCaracter(arbol, texto)
  }

  /** ****************************************************************************
   * FUNCIÓN:                codigoEnBits
   * DESCRIPCIÓN:            funcion currificada que retorna la lista de Bits que correspondiente el caracter mediante la TablaCodigos.
   * PARÁMETROS DE ENTRADA:
   * $tabla:                 Una TablaCodigos: TablaCodigos.
   * $car:                   Un caracter: Char
   * RETORNO
   * List[Bit]:  			       La lista de Bits decodificada al caracter en especifico.
   * **************************************************************************** */
  def codigoEnBits(tabla: TablaCodigos)(car: Char): List[Bit] =
  {
    tabla match
    {
      case head :: tail => val datosHead = head;
        val (d1, d2) = datosHead; if (car.equals(d1)) d2 else codigoEnBits(tail)(car)
      case Nil => Nil
    }
  }

  /** ****************************************************************************
   * FUNCIÓN:                mezclarTablasDeCodigos
   * DESCRIPCIÓN:            Una función que recibe dos subarboles (a,b) que podria correponderse a lado izq y der de un arbol de Huffman, esta funcion devuelve la TablaCodigos correspondiente al nuevo arbol
   * PARÁMETROS DE ENTRADA:
   * $a:                     TablaCodigos
   * $b:                     TablaCodigos
   * RETORNO
   * TablaCodigos:  			   La TablaCodigos del Arbol "Padre"
   * **************************************************************************** */
  def mezclarTablasDeCodigos(a: TablaCodigos, b: TablaCodigos): TablaCodigos =
  {
    def concatenarPrefijo(prefijo: Int, auxTabla: TablaCodigos): TablaCodigos =
    {
      auxTabla match
      {
        case head :: tail => val datosHead = head;
          val (d1, d2) = datosHead; List((d1, prefijo :: d2)) ::: concatenarPrefijo(prefijo, tail)
        case Nil => Nil
      }
    }

    a match
    {
      case head => b match
      {
        case head => concatenarPrefijo(0, a) ::: concatenarPrefijo(1, b)
        case Nil => concatenarPrefijo(0, a)
      }
      case Nil => b match
      {
        case head => concatenarPrefijo(1, b)
        case Nil => Nil
      }
    }
  }

  /** ****************************************************************************
   * FUNCIÓN:                convertir
   * DESCRIPCIÓN:            Una función que recibe un arbol y devuelve la TablaCodigos de este.
   * PARÁMETROS DE ENTRADA:
   * $arbol:                 ArbolH
   * RETORNO
   * TablaCodigos:  			   La TablaCodigos del Arbol.
   * **************************************************************************** */
  def convertir(arbol: ArbolH): TablaCodigos =
  {
    arbol match
    {
      case Nodo(izq, der, cars, peso) => mezclarTablasDeCodigos(convertir(der), convertir(izq))
      case Hoja(car, peso) => List((car, List()))
    }
  }

  /** ****************************************************************************
   * FUNCIÓN:                codificarRapido
   * DESCRIPCIÓN:            Una función currificada que aprovecha la función convertir para codificar una lista de caracteres mediante un ArbolH
   * PARÁMETROS DE ENTRADA:
   * $arbol:                 ArbolH
   * $texto:                 List[char]
   * RETORNO
   * List[Bit]:  			       La lista de bits codificada.
   * **************************************************************************** */
  def codificarRapido(arbol: ArbolH)(texto: List[Char]): List[Bit] =
  {
    val listArbol = convertir(arbol)

    def generarListaBits(auxTexto: List[Char], lista: TablaCodigos): List[Bit] =
    {
      auxTexto match
      {
        case head :: tail => lista match
        {
          case head2 :: tail2 => val datosHead = head2;
            val (d1, d2) = datosHead; if (head.equals(d1)) d2 ::: generarListaBits(tail, listArbol) else generarListaBits(auxTexto, tail2)
          case Nil => generarListaBits(tail, listArbol)
        }
        case Nil => Nil
      }
    }
    generarListaBits(texto, listArbol)
  }
}
