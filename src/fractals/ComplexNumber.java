/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fractals;
/*
 * Copyright (c) 2004 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 3nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose,
 * including teaching and use in open-source projects.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book, 
 * please visit http://www.davidflanagan.com/javaexamples3.
 */

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * This class represents complex numbers, and defines methods for performing
 * arithmetic on complex numbers.
 */
public class ComplexNumber {
  // These are the instance variables. Each ComplexNumber object holds
  // two double values, known as x and y. They are private, so they are
  // not accessible from outside this class. Instead, they are available
  // through the real() and imaginary() methods below.
  private BigDecimal x, y;
  public MathContext mc;

  /** This is the constructor. It initializes the x and y variables */
  public ComplexNumber(BigDecimal real, BigDecimal imaginary, int precision) {
    this.x = real;
    this.y = imaginary;
    this.mc = new MathContext(precision);
  }

  /**
   * An accessor method. Returns the real part of the complex number. Note that
   * there is no setReal() method to set the real part. This means that the
   * ComplexNumber class is "immutable".
   */
  public BigDecimal real() {
    return x;
  }

  /** An accessor method. Returns the imaginary part of the complex number */
  public BigDecimal imaginary() {
    return y;
  }

  /** Compute the magnitude of a complex number */
  public BigDecimal magnitude() {

    return x.multiply(x).add(y.multiply(y)).sqrt(mc);
  }

  /**
   * This method converts a ComplexNumber to a string. This is a method of
   * Object that we override so that complex numbers can be meaningfully
   * converted to strings, and so they can conveniently be printed out with
   * System.out.println() and related methods
   */
  public String toString() {
    return "{" + x + "," + y + "}";
  }

  public MathContext getMathContext(){
    return mc;
  }

  /**
   * This is a static class method. It takes two complex numbers, adds them, and
   * returns the result as a third number. Because it is static, there is no
   * "current instance" or "this" object. Use it like this: ComplexNumber c =
   * ComplexNumber.add(a, b);
   */
  public static ComplexNumber add(ComplexNumber a, ComplexNumber b) {
    return new ComplexNumber(b.x.add(a.x), b.y.add(a.y), a.mc.getPrecision());
  }

  /**
   * This is a non-static instance method by the same name. It adds the
   * specified complex number to the current complex number. Use it like this:
   * ComplexNumber c = a.add(b);
   */
  public ComplexNumber add(ComplexNumber a) {
    return new ComplexNumber(this.x.add(a.x), this.y.add(a.y), mc.getPrecision());
  }

  /** A static class method to multiply complex numbers */
  public static ComplexNumber multiply(ComplexNumber a, ComplexNumber b) {
    return new ComplexNumber(a.x.multiply(b.x).subtract(a.y.multiply(b.y)), a.x.multiply(b.y).add(a.y.multiply(b.x)), a.mc.getPrecision());
  }

  /** An instance method to multiply complex numbers */
  public ComplexNumber multiply(ComplexNumber a) {
    return new ComplexNumber(x.multiply(a.x).subtract(y.multiply(a.y)), x.multiply(a.y).add(y.multiply(a.x)), mc.getPrecision());
  }
}
