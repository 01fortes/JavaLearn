package com.jsamkt.learn.advancedmath.complexnumbers;

/**
 * A class representing a complex number with real and imaginary parts.
 * Complex numbers are numbers of the form a + bi, where a and b are real numbers,
 * and i is the imaginary unit with the property i² = -1.
 */
public class ComplexNumber {
    private final double real;
    private final double imaginary;
    
    /**
     * Creates a complex number with the specified real and imaginary parts.
     *
     * @param real The real part of the complex number
     * @param imaginary The imaginary part of the complex number
     */
    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }
    
    /**
     * Creates a complex number with the specified real part and zero imaginary part.
     *
     * @param real The real part of the complex number
     * @return A new complex number with the specified real part and zero imaginary part
     */
    public static ComplexNumber fromReal(double real) {
        return new ComplexNumber(real, 0);
    }
    
    /**
     * Creates a complex number with the specified imaginary part and zero real part.
     *
     * @param imaginary The imaginary part of the complex number
     * @return A new complex number with the specified imaginary part and zero real part
     */
    public static ComplexNumber fromImaginary(double imaginary) {
        return new ComplexNumber(0, imaginary);
    }
    
    /**
     * Returns the real part of this complex number.
     *
     * @return The real part
     */
    public double getReal() {
        return real;
    }
    
    /**
     * Returns the imaginary part of this complex number.
     *
     * @return The imaginary part
     */
    public double getImaginary() {
        return imaginary;
    }
    
    /**
     * Returns the modulus (absolute value) of this complex number.
     * The modulus is the distance from the origin to the point in the complex plane.
     *
     * @return The modulus of this complex number
     */
    public double modulus() {
        return Math.sqrt(real * real + imaginary * imaginary);
    }
    
    /**
     * Returns the argument (phase) of this complex number.
     * The argument is the angle in radians between the positive real axis and the 
     * line from the origin to the point in the complex plane.
     *
     * @return The argument of this complex number in radians
     */
    public double argument() {
        return Math.atan2(imaginary, real);
    }
    
    /**
     * Adds another complex number to this one and returns the result.
     *
     * @param other The complex number to add
     * @return The sum of this complex number and the other complex number
     */
    public ComplexNumber add(ComplexNumber other) {
        return new ComplexNumber(
                this.real + other.real,
                this.imaginary + other.imaginary
        );
    }
    
    /**
     * Subtracts another complex number from this one and returns the result.
     *
     * @param other The complex number to subtract
     * @return The difference of this complex number and the other complex number
     */
    public ComplexNumber subtract(ComplexNumber other) {
        return new ComplexNumber(
                this.real - other.real,
                this.imaginary - other.imaginary
        );
    }
    
    /**
     * Multiplies this complex number by another complex number and returns the result.
     *
     * @param other The complex number to multiply by
     * @return The product of this complex number and the other complex number
     */
    public ComplexNumber multiply(ComplexNumber other) {
        // (a + bi) * (c + di) = (ac - bd) + (ad + bc)i
        double newReal = this.real * other.real - this.imaginary * other.imaginary;
        double newImaginary = this.real * other.imaginary + this.imaginary * other.real;
        return new ComplexNumber(newReal, newImaginary);
    }
    
    /**
     * Divides this complex number by another complex number and returns the result.
     *
     * @param other The complex number to divide by
     * @return The quotient of this complex number and the other complex number
     * @throws ArithmeticException if the other complex number is zero
     */
    public ComplexNumber divide(ComplexNumber other) {
        // Check if divisor is zero
        if (other.real == 0 && other.imaginary == 0) {
            throw new ArithmeticException("Division by zero complex number");
        }
        
        // (a + bi) / (c + di) = ((ac + bd) + (bc - ad)i) / (c² + d²)
        double denominator = other.real * other.real + other.imaginary * other.imaginary;
        double newReal = (this.real * other.real + this.imaginary * other.imaginary) / denominator;
        double newImaginary = (this.imaginary * other.real - this.real * other.imaginary) / denominator;
        return new ComplexNumber(newReal, newImaginary);
    }
    
    /**
     * Returns the complex conjugate of this complex number.
     * The complex conjugate of a + bi is a - bi.
     *
     * @return The complex conjugate of this complex number
     */
    public ComplexNumber conjugate() {
        return new ComplexNumber(this.real, -this.imaginary);
    }
    
    /**
     * Returns the reciprocal of this complex number.
     * The reciprocal of a complex number is 1 / (a + bi).
     *
     * @return The reciprocal of this complex number
     * @throws ArithmeticException if this complex number is zero
     */
    public ComplexNumber reciprocal() {
        if (this.real == 0 && this.imaginary == 0) {
            throw new ArithmeticException("Reciprocal of zero");
        }
        
        double denominator = this.real * this.real + this.imaginary * this.imaginary;
        return new ComplexNumber(this.real / denominator, -this.imaginary / denominator);
    }
    
    /**
     * Returns a string representation of this complex number.
     *
     * @return A string representation of this complex number
     */
    @Override
    public String toString() {
        if (imaginary == 0) {
            return String.format("%.2f", real);
        } else if (real == 0) {
            return String.format("%.2fi", imaginary);
        } else if (imaginary < 0) {
            return String.format("%.2f - %.2fi", real, -imaginary);
        } else {
            return String.format("%.2f + %.2fi", real, imaginary);
        }
    }
    
    /**
     * Returns true if the specified object is equal to this complex number.
     *
     * @param obj The object to compare with
     * @return true if the specified object is equal to this complex number
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ComplexNumber)) {
            return false;
        }
        ComplexNumber other = (ComplexNumber) obj;
        // Using epsilon for floating-point comparison
        double epsilon = 1e-10;
        return Math.abs(this.real - other.real) < epsilon &&
               Math.abs(this.imaginary - other.imaginary) < epsilon;
    }
    
    /**
     * Returns the hash code for this complex number.
     *
     * @return The hash code for this complex number
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + Double.hashCode(real);
        result = 31 * result + Double.hashCode(imaginary);
        return result;
    }
}