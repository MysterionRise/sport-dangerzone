package org.mystic.codeforces.cf0_100.cf16div2

import java.util._
import java.lang._
import java.io._

object B {

  var out: PrintWriter = _
  var br: BufferedReader = _
  var st: StringTokenizer = _

  def next: String = {
    while (st == null || !st.hasMoreTokens) {
      st = new StringTokenizer(br.readLine)
    }
    st.nextToken
  }

  def nextInt: Int = {
    Integer.parseInt(next)
  }

  def nextLong: Long = {
    Long.parseLong(next)
  }

  def comp(x: (Long, Long), y: (Long, Long)): Boolean = {
    if (x._2 == y._2) x._1 > y._1
    else x._2 > y._2
  }

  def solve = {
    val n = nextInt
    val m = nextInt
    val containers = new Array[(Long, Long)](m)
    for (i <- 0 until m) {
      containers(i) = (nextLong, nextLong)
    }
    //    containers.sortWith(comp).foreach(print)
    val sortedContainers = containers.sortWith(comp)
    var ind = 0
    var leftSpace: Long = n
    var ans: Long = 0
    while (leftSpace > 0 && ind < m) {
      if (sortedContainers(ind)._1 > 0) {
        val min: Long = Math.min(leftSpace, sortedContainers(ind)._1)
        ans += min * sortedContainers(ind)._2
        sortedContainers(ind) = (sortedContainers(ind)._1 - min, sortedContainers(ind)._2)
        leftSpace -= min
      } else {
        ind += 1
      }
    }
    out.println(ans)
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
