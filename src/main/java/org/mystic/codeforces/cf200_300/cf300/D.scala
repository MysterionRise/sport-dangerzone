package org.mystic.codeforces.cf200_300.cf300

import java.io._
import java.util
import java.util._

import scala.collection.mutable

object D {

  var out: PrintWriter = null
  var br: BufferedReader = null
  var st: StringTokenizer = null

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }

  def next: String = {
    while (st == null || !st.hasMoreTokens) {
      st = new StringTokenizer(br.readLine)
    }
    return st.nextToken
  }

  def nextInt: Int = return Integer.parseInt(next)

  def nextLong: Long = return java.lang.Long.parseLong(next)

  def nextDouble: Double = return java.lang.Double.parseDouble(next)

  class MultiHashSet[T <% Comparable[T]] {
    val map = new mutable.HashMap[T, Int]()

    def count(x: T): Int = {
      return map.getOrElse(x, 0)
    }

    def add(x: T): Unit = map.put(x, count(x) + 1)

    def remove(x: T): Boolean = {
      val prev = count(x)
      if (prev == 0)
        return false
      if (prev == 1) {
        map.remove(x)
      } else {
        map.put(x, prev - 1)
      }
      return true
    }
  }

  class MultiTreeSet[T <% Comparable[T]] {
    val map = new TreeMap[T, Int]()

    def count(x: T): Int = {
      val res = map.get(x)
      if (res == null)
        return 0
      return res
    }

    def add(x: T): Unit = map.put(x, count(x) + 1)

    def first(): T = return map.firstKey()

    def last(): T = return map.lastKey()

    def remove(x: T): Boolean = {
      val prev = count(x)
      if (prev == 0)
        return false
      if (prev == 1) {
        map.remove(x)
      } else {
        map.put(x, prev - 1)
      }
      return true
    }
  }

  def solve: Int = {
    val n = nextInt
    val field = new Array[Array[Char]](n)
    for (i <- 0 until n) {
      field(i) = next.toCharArray
    }
    val possibleMovements = new util.ArrayList[(Int, Int)]()
    for {
      i <- 0 until n
      j <- 0 until n
    } field(i)(j) match {
      case 'o' => {
        for {
          k <- 0 until n
          m <- 0 until n
        } field(k)(m) match {
          case 'x' => {
            possibleMovements.add((k - i, m - j))
          }
          case _ =>
        }
      }
      case _ =>
    }
    for {
      i <- 0 until n
      j <- 0 until n
    } field(i)(j) match {
      case 'x' => {
        var flag = false
        for (k <- 0 until possibleMovements.size) {
          val dx = possibleMovements.get(k)._1
          val dy = possibleMovements.get(k)._2
          if (i + dx >= 0 && i + dx < n && j + dy >= 0 && j + dy < n && field(i + dx)(j + dy) == 'o') {
            flag = true
          }
        }
        if (!flag) {
          out.println("NO")
          return 1
        }
      }
      case _ =>
    }
    out.println("YES")
    for (i <- 0 until 2 * n - 1) {
      for (j <- 0 until 2 * n - 1) {
        if (i == j && i == n - 1) {
          out.print('o')
        } else {
          var flag = false
          for (k <- 0 until possibleMovements.size()) {
            val dx = possibleMovements.get(k)._1
            val dy = possibleMovements.get(k)._2
            if (!flag && i == n - 1 + dx && j == n - 1 + dy) {
              out.print('x')
              flag = true
            }
          }
          if (!flag) {
            out.print('.')
          }
        }
      }
      out.println
    }
    return 0
  }
}
