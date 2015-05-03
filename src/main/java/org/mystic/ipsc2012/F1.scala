package org.mystic.ipsc2012

import java.io._
import java.util.{StringTokenizer, TreeMap}

import scala.StringBuilder
import scala.collection.mutable

object F1 {

  var out: PrintWriter = null
  var br: BufferedReader = null
  var st: StringTokenizer = null

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new FileWriter("f2.out"))
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

  def pow(a: Int, b: Int): Long = {
    var x: Long = 1L
    for (i <- 1 to b) {
      x *= a.toLong
    }
    x
  }

  def solve: Int = {
    val t = nextInt
    for (i <- 0 until t) {
      var c1 = 0
      var flag = false
      val n = nextInt
      val prob = new Array[Double](n)
      for (j <- 0 until n) {
        prob(j) = nextDouble
        if (Math.abs(prob(j) - 0.5) < 1e-8) {
          c1 += 1
          flag = true
        }
      }
      if (flag) {
        out.println((pow(2, c1) - 1L) * pow(2, n - c1))
      } else {
        out.println(0)
      }
    }
    return 0
  }
}
