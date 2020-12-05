package ru.srqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimesTest {

  @Test
  public void testPrimes() {
    Assert.assertTrue(Primes.isPrimesFast(Integer.MAX_VALUE));
  }

  @Test
  public void testNonPrimes() {
    Assert.assertFalse(Primes.isPrimes(Integer.MAX_VALUE-2));
  }

  @Test(enabled = false)
  public void testPrimesLong() {
    long n = Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrimes(n));
  }


}
