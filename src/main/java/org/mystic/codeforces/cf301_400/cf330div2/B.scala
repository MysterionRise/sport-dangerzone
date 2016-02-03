package org.mystic.codeforces.cf301_400.cf330div2

import java.io._
import java.util._

import scala.collection.mutable

object B {

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

  /**
   * Segment tree for any commutative function
   * @param values Array of Int
   * @param commutative function like min, max, sum
   * @param zero zero value - e.g. 0 for sum, Inf for min, max
   */
  class SegmentTree(values: Array[Int])(commutative: (Int, Int) => Int)(zero: Int) {
    private val SIZE = 1e5.toInt
    private val n = values.length
    val t = new Array[Int](2 * n)
    Array.copy(values, 0, t, n, n)

    // build segment tree
    def build = {
      for (i <- n - 1 until 0 by -1) {
        t(i) = commutative(t(2 * i), t(2 * i + 1))
      }
    }

    // change value at position p to x
    // TODO beatify
    def modify(p: Int, x: Int) = {
      var pos = p + n
      t(pos) = x
      while (pos > 1) {
        t(pos / 2) = commutative(t(pos), t(pos ^ 1))
        pos /= 2
      }
    }

    // TODO implement me!
    def modify(p: Int, left: Int, right: Int) = ???

    def query(p: Int) = ???

    // sum [l, r)
    // min l = 0
    // max r = n
    // TODO beatify
    def query(left: Int, right: Int): Int = {
      var res = zero
      var r = right + n
      var l = left + n
      while (l < r) {
        if (l % 2 == 1) {
          res = commutative(res, t(l))
          l += 1
        }
        if (r % 2 == 1) {
          r -= 1
          res = commutative(res, t(r))
        }
        l /= 2
        r /= 2
      }
      res
    }
  }

  def nextInt: Int = return Integer.parseInt(next)

  def nextLong: Long = return java.lang.Long.parseLong(next)

  def nextDouble: Double = return java.lang.Double.parseDouble(next)

  val MOD = (1e9 + 7).toLong

  def getKNines(n: Int): String = {
    val sb = new StringBuilder()
    for (i <- 0 until n)
      sb.append('9')
    sb.toString()
  }

  def getKZeroes(n: Int) = {
    val sb = new StringBuilder()
    for (i <- 0 until n)
      sb.append('0')
    sb.toString()
  }

  def solve: Int = {
    val n = nextInt
    val k = nextInt
    val len = n / k
    val a = new Array[Long](len)
    val b = new Array[Long](len)
    for (i <- 0 until len) {
      a(i) = nextLong
    }
    for (i <- 0 until len) {
      b(i) = nextLong
    }
    val num = new Array[Long](len)
    for (i <- 0 until len) {
      if (Math.pow(10, k - 1).toLong / a(i) == 0) {
        var res = 0
         // 0000 always good
        for (j <- 0 to 100) {
          if (a(i) * j.toLong <= Math.pow(10, k).toLong - 1L && (a(i) * j).toString.charAt(0) - '0' != b(i)) {
            res += 1
          }
        }
        num(i) = res
      } else {
        num(i) += Math.ceil(1.0f * java.lang.Long.parseLong(b(i) + getKZeroes(k - 1)) / a(i)).toLong
        num(i) += (java.lang.Long.parseLong(getKNines(k)) - java.lang.Long.parseLong(b(i) + getKNines(k - 1))) / a(i)
      }
    }
    var ans = 1.toLong
    for (i <- 0 until len) {
      ans = (1L * ans * num(i)) % MOD
    }
    out.println(ans)
    return 0
  }
}