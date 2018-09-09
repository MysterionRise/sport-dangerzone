package org.mystic.custom.booking

import java.io._
import java.util
import java.util.{Scanner, StringTokenizer, TreeMap}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object B {

  var out: PrintWriter = _
  var br: BufferedReader = _
  var st: StringTokenizer = _

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
    st.nextToken
  }

  def nextInt: Int = Integer.parseInt(next)

  def nextLong: Long = java.lang.Long.parseLong(next)

  def nextDouble: Double = java.lang.Double.parseDouble(next)

  class MultiHashSet[T <% Comparable[T]] {
    val map = new mutable.HashMap[T, Int]()

    def count(x: T): Int = {
      map.getOrElse(x, 0)
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
      true
    }
  }

  class MultiTreeSet[T <% Comparable[T]] {
    val map = new TreeMap[T, Int]()

    def count(x: T): Int = {
      map.getOrDefault(x, 0)
    }

    def add(x: T): Unit = map.put(x, count(x) + 1)

    def first(): T = map.firstKey()

    def last(): T = map.lastKey()

    def remove(x: T): Boolean = {
      val prev = count(x)
      if (prev == 0)
        return false
      if (prev == 1) {
        map.remove(x)
      } else {
        map.put(x, prev - 1)
      }
      true
    }
  }

  /**
    * Segment tree for any commutative function
    *
    * @param values      Array of Int
    * @param commutative function like min, max, sum
    * @param zero        zero value - e.g. 0 for sum, Inf for min, max
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

  def solve = {
    val in = new Scanner(System.in)
    val words = new mutable.HashSet[String]()
    in.nextLine().toLowerCase().split(" ").foreach(x => words.add(x))
    val m = in.nextInt()
    val res = new mutable.HashMap[Int, Int]()
    val hotels = new mutable.HashMap[Int, util.ArrayList[(String, Int)]]()
    for (i <- 0 until m) {
      val hotelId = in.nextInt()
      val prevHotel = hotels.getOrElse(hotelId, new util.ArrayList[(String, Int)]())
      val review = new util.ArrayList[(String, Int)]
      in.nextLine().toLowerCase.split("[\\s,.]+").toList.map(x => (x, 1)).foreach(x => review.add(x))
        //
      prevHotel.addAll(review)
      hotels.put(hotelId, prevHotel)
    }
    hotels.keySet.foreach(hotelId => {
      val review = hotels.getOrElse(hotelId, new util.ArrayList[(String, Int)]())
      val set = review.toArray(new Array[(String, Int)](0)).toList.groupBy(_._1).map(x => (x._1, x._2.length)).toSet
      val before = res.getOrElse(hotelId, 0)
      val length = set.filter(x => words.contains(x._1)).toList.map(x => x._2).sum
      res.put(hotelId, before + length)
    })
    res.toList.sortBy(_._1).sortBy(-_._2).foreach(x => print(s"$x "))
  }
}
