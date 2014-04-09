package org.mystic.tc.srm615;

import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class LongLongTripDiv2 {
    public String isAble(long D, int T, int B) {
        long X_2 = ((long) T * (long) B - D) / ((long) B - 1L);
        long X = ((long) T * (long) B - D) % ((long) B - 1L);
        if (X == 0L && X_2 <= T && X_2 >= 0) {
            return "Possible";
        } else {
            return "Impossible";
        }
    }

    // BEGIN KAWIGIEDIT TESTING
    // Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
    private static boolean KawigiEdit_RunTest(int testNum, long p0, int p1, int p2, boolean hasAnswer, String p3) {
        System.out.print("Test " + testNum + ": [" + p0 + "," + p1 + "," + p2);
        System.out.println("]");
        LongLongTripDiv2 obj;
        String answer;
        obj = new LongLongTripDiv2();
        long startTime = System.currentTimeMillis();
        answer = obj.isAble(p0, p1, p2);
        long endTime = System.currentTimeMillis();
        boolean res;
        res = true;
        System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
        if (hasAnswer) {
            System.out.println("Desired answer:");
            System.out.println("\t" + "\"" + p3 + "\"");
        }
        System.out.println("Your answer:");
        System.out.println("\t" + "\"" + answer + "\"");
        if (hasAnswer) {
            res = answer.equals(p3);
        }
        if (!res) {
            System.out.println("DOESN'T MATCH!!!!");
        } else if ((endTime - startTime) / 1000.0 >= 2) {
            System.out.println("FAIL the timeout");
            res = false;
        } else if (hasAnswer) {
            System.out.println("Match :-)");
        } else {
            System.out.println("OK, but is it right?");
        }
        System.out.println("");
        return res;
    }

    public static void main(String[] args) {
        boolean all_right;
        all_right = true;

        long p0;
        int p1;
        int p2;
        String p3;

        // ----- test 0 -----
        p0 = 10L;
        p1 = 6;
        p2 = 3;
        p3 = "Possible";
        all_right = KawigiEdit_RunTest(0, p0, p1, p2, true, p3) && all_right;
        // ------------------

        // ----- test 1 -----
        p0 = 10L;
        p1 = 5;
        p2 = 3;
        p3 = "Impossible";
        all_right = KawigiEdit_RunTest(1, p0, p1, p2, true, p3) && all_right;
        // ------------------

        // ----- test 2 -----
        p0 = 50L;
        p1 = 100;
        p2 = 2;
        p3 = "Impossible";
        all_right = KawigiEdit_RunTest(2, p0, p1, p2, true, p3) && all_right;
        // ------------------

        // ----- test 3 -----
        p0 = 120L;
        p1 = 10;
        p2 = 11;
        p3 = "Impossible";
        all_right = KawigiEdit_RunTest(3, p0, p1, p2, true, p3) && all_right;
        // ------------------

        // ----- test 4 -----
        p0 = 10L;
        p1 = 10;
        p2 = 9999;
        p3 = "Possible";
        all_right = KawigiEdit_RunTest(4, p0, p1, p2, true, p3) && all_right;
        // ------------------

        // ----- test 5 -----
        p0 = 1000L;
        p1 = 100;
        p2 = 10;
        p3 = "Possible";
        all_right = KawigiEdit_RunTest(5, p0, p1, p2, true, p3) && all_right;
        // ------------------

        // ----- test 6 -----
        p0 = 1000010000100001L;
        p1 = 1100011;
        p2 = 1000000000;
        p3 = "Possible";
        all_right = KawigiEdit_RunTest(6, p0, p1, p2, true, p3) && all_right;
        // ------------------

        if (all_right) {
            System.out.println("You're a stud (at least on the example cases)!");
        } else {
            System.out.println("Some of the test cases had errors.");
        }
    }
    // END KAWIGIEDIT TESTING
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!