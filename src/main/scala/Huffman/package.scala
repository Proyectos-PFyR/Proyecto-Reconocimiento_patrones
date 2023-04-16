package object Huffman
{
  abstract class ArbolH
  case class Nodo(izq: ArbolH, der: ArbolH, cars: List[Char], peso: Int) extends ArbolH
  case class Hoja(car: Char, peso: Int) extends ArbolH

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

  def hacerNodoArbolH(izq: ArbolH, der: ArbolH): Nodo =
  {
    Nodo(izq, der, cars(izq) ::: cars(der), peso(izq) + peso(der))
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

  def listaUnitaria(arboles: List[ArbolH]): Boolean =
  {
    arboles match
    {
      case Nil => false
      case List(a) => true
      case _ => false
    }
  }

  def combinar(arboles: List[ArbolH]): List[ArbolH] =
  {
    arboles match
    {
      case Nil => Nil
      case a :: b :: tail => combinar(hacerNodoArbolH(a, b) :: combinar(tail))
      case _ => arboles
    }
  }

  def crearArbolDeHuffman(cars: List[Char]): ArbolH =
  {
    combinar(listaDeHojasOrdenadas(ocurrencias(cars))).head
  }
}
