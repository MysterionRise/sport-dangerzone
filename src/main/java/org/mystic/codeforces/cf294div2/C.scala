package org.mystic.codeforces.cf294div2

import java.io._
import java.util.StringTokenizer

import scala.collection.mutable

object C {

  var out: PrintWriter = null
  var br: BufferedReader = null
  var st: StringTokenizer = null

  def next: String = {
    while (st == null || !st.hasMoreTokens) {
      st = new StringTokenizer(br.readLine)
    }
    return st.nextToken
  }

  def nextInt: Int = {
    return Integer.parseInt(next)
  }

  def nextLong: Long = {
    return java.lang.Long.parseLong(next)
  }

  def f1(n: Int, m: Int): Long = {
    return Math.min(n / 2, m)
  }

  def f2(n: Int, m: Int): Long = {
    return Math.min(n, m / 2)
  }

  def solve: Int = {
    val n = nextInt
    val m = nextInt
    var max: Long = 0
    for (i <- 1 to n) {
      if (n - 2 * i >= 0 && m - i >= 0) {
        max = Math.max(max, i + f2(n - 2 * i, m - i))
      }
    }
    for (i <- 1 to m) {
      if (n - i >= 0 && m - 2 * i >= 0) {
        max = Math.max(max, i + f1(n - i, m - 2 * i))
      }
    }
    out.println(max)
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}