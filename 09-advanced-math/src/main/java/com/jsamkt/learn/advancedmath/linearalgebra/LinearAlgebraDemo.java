package com.jsamkt.learn.advancedmath.linearalgebra;

import org.apache.commons.math3.linear.*;
import org.la4j.Matrix;
import org.la4j.Vector;
import org.la4j.matrix.dense.Basic2DMatrix;
import org.la4j.decomposition.EigenDecompositor;
import org.la4j.matrix.functor.MatrixFunction;

import java.util.Arrays;

/**
 * Demonstrates linear algebra concepts and operations using Java libraries.
 * Linear algebra is a branch of mathematics that deals with vectors, matrices,
 * and linear transformations.
 */
public class LinearAlgebraDemo {
    
    public static void demo() {
        System.out.println("\n----- LINEAR ALGEBRA -----");
        
        // Matrix operations using Apache Commons Math
        apacheMatrixDemo();
        
        // Matrix operations using LA4J
        la4jMatrixDemo();
        
        // Matrix decompositions
        matrixDecompositionDemo();
        
        // Solving systems of linear equations
        systemOfEquationsDemo();
        
        System.out.println("----- END OF LINEAR ALGEBRA -----\n");
    }
    
    private static void apacheMatrixDemo() {
        System.out.println("\n# Matrix Operations with Apache Commons Math");
        
        // Create matrices
        double[][] matrixData1 = {
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0},
                {7.0, 8.0, 9.0}
        };
        
        double[][] matrixData2 = {
                {9.0, 8.0, 7.0},
                {6.0, 5.0, 4.0},
                {3.0, 2.0, 1.0}
        };
        
        RealMatrix matrix1 = new Array2DRowRealMatrix(matrixData1);
        RealMatrix matrix2 = new Array2DRowRealMatrix(matrixData2);
        
        System.out.println("Matrix A:");
        printMatrix(matrix1);
        
        System.out.println("\nMatrix B:");
        printMatrix(matrix2);
        
        // Basic matrix operations
        System.out.println("\nBasic Matrix Operations:");
        
        // Addition
        RealMatrix sum = matrix1.add(matrix2);
        System.out.println("A + B =");
        printMatrix(sum);
        
        // Subtraction
        RealMatrix difference = matrix1.subtract(matrix2);
        System.out.println("\nA - B =");
        printMatrix(difference);
        
        // Multiplication
        RealMatrix product = matrix1.multiply(matrix2);
        System.out.println("\nA * B =");
        printMatrix(product);
        
        // Scalar multiplication
        RealMatrix scaled = matrix1.scalarMultiply(2.0);
        System.out.println("\n2 * A =");
        printMatrix(scaled);
        
        // Transpose
        RealMatrix transpose = matrix1.transpose();
        System.out.println("\nTranspose of A:");
        printMatrix(transpose);
        
        // Get a submatrix
        RealMatrix submatrix = matrix1.getSubMatrix(0, 1, 1, 2);
        System.out.println("\nSubmatrix of A (rows 0-1, columns 1-2):");
        printMatrix(submatrix);
        
        // Matrix determinant
        double determinant = new LUDecomposition(matrix1).getDeterminant();
        System.out.println("\nDeterminant of A: " + determinant);
        
        // Try calculating inverse (may not exist if determinant is 0)
        try {
            RealMatrix inverse = new LUDecomposition(matrix1).getSolver().getInverse();
            System.out.println("\nInverse of A:");
            printMatrix(inverse);
            
            // Verify that A * A^-1 = I (Identity matrix)
            RealMatrix identity = matrix1.multiply(inverse);
            System.out.println("\nA * A^-1 (should be close to identity matrix):");
            printMatrix(identity);
        } catch (SingularMatrixException e) {
            System.out.println("\nMatrix A is singular (determinant = 0), so it doesn't have an inverse.");
        }
    }
    
    private static void la4jMatrixDemo() {
        System.out.println("\n# Matrix Operations with LA4J Library");
        
        // Create matrices
        double[][] matrixData1 = {
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0},
                {7.0, 8.0, 9.0}
        };
        
        Matrix matrixA = new Basic2DMatrix(matrixData1);
        
        System.out.println("Matrix A (using LA4J):");
        System.out.println(matrixA);
        
        // Basic matrix operations
        System.out.println("\nMatrix Trace (sum of diagonal elements): " + matrixA.trace());
        
        // Matrix norm
        System.out.println("Matrix Frobenius Norm: " + matrixA.norm());
        
        // Create a vector
        Vector vector = Vector.fromArray(new double[] {1, 2, 3});
        System.out.println("\nVector v: " + vector);
        
        // Matrix-vector multiplication
        Vector result = matrixA.multiply(vector);
        System.out.println("A * v = " + result);
        
        // Element-wise operations (using simple scalar operations)
        System.out.println("\nSquaring each element of matrix A:");
        Matrix squared = matrixA.multiply(matrixA.hadamardProduct(Matrix.unit(3, 3)));
        System.out.println(squared);
        
        // Create identity matrix
        Matrix identity = Matrix.identity(3);
        System.out.println("\n3x3 Identity matrix:");
        System.out.println(identity);
        
        // Creating diagonal matrix
        Matrix diagonal = Matrix.zero(3, 3);
        diagonal.set(0, 0, 1);
        diagonal.set(1, 1, 2);
        diagonal.set(2, 2, 3);
        System.out.println("\nDiagonal matrix with elements [1, 2, 3]:");
        System.out.println(diagonal);
        
        // Matrix power (using LA4J)
        System.out.println("\nMatrix A^2 (square of matrix A):");
        Matrix powerMatrix = matrixA.multiply(matrixA);
        System.out.println(powerMatrix);
    }
    
    private static void matrixDecompositionDemo() {
        System.out.println("\n# Matrix Decomposition Examples");
        
        // Create a matrix for decomposition
        double[][] matrixData = {
                {3.0, 1.0, -4.0},
                {2.0, -3.0, 5.0},
                {1.0, 2.0, -1.0}
        };
        
        RealMatrix matrix = new Array2DRowRealMatrix(matrixData);
        System.out.println("Original Matrix:");
        printMatrix(matrix);
        
        // LU Decomposition (for solving linear systems)
        System.out.println("\nLU Decomposition:");
        LUDecomposition luDecomposition = new LUDecomposition(matrix);
        RealMatrix l = luDecomposition.getL();
        RealMatrix u = luDecomposition.getU();
        RealMatrix p = luDecomposition.getP();
        
        System.out.println("L (Lower triangular matrix):");
        printMatrix(l);
        
        System.out.println("\nU (Upper triangular matrix):");
        printMatrix(u);
        
        System.out.println("\nP (Permutation matrix):");
        printMatrix(p);
        
        // Verify that P*A = L*U
        RealMatrix pa = p.multiply(matrix);
        RealMatrix lu = l.multiply(u);
        System.out.println("\nVerification - P*A should equal L*U");
        System.out.println("P*A =");
        printMatrix(pa);
        System.out.println("\nL*U =");
        printMatrix(lu);
        
        // QR Decomposition (useful for linear least squares problems)
        System.out.println("\nQR Decomposition:");
        QRDecomposition qrDecomposition = new QRDecomposition(matrix);
        RealMatrix q = qrDecomposition.getQ();
        RealMatrix r = qrDecomposition.getR();
        
        System.out.println("Q (Orthogonal matrix):");
        printMatrix(q);
        
        System.out.println("\nR (Upper triangular matrix):");
        printMatrix(r);
        
        // Verify that A = Q*R
        RealMatrix qr = q.multiply(r);
        System.out.println("\nVerification - A should equal Q*R");
        System.out.println("Q*R =");
        printMatrix(qr);
        
        // Use LA4J for Eigenvalue decomposition
        System.out.println("\nEigenvalue Decomposition (using LA4J):");
        
        // Create matrix in LA4J format
        Matrix la4jMatrix = new Basic2DMatrix(matrixData);
        
        // Perform eigenvalue decomposition
        EigenDecompositor decompositor = new EigenDecompositor(la4jMatrix);
        Matrix[] decomposition = decompositor.decompose();
        
        if (decomposition != null && decomposition.length == 2) {
            Matrix eigenvalues = decomposition[0];
            Matrix eigenvectors = decomposition[1];
            
            System.out.println("Eigenvalues (diagonal matrix):");
            System.out.println(eigenvalues);
            
            System.out.println("\nEigenvectors (as columns):");
            System.out.println(eigenvectors);
        } else {
            System.out.println("Eigenvalue decomposition failed or not supported for this matrix.");
        }
    }
    
    private static void systemOfEquationsDemo() {
        System.out.println("\n# Solving Systems of Linear Equations");
        
        // Consider the system:
        // 3x + 2y - z = 1
        // 2x - 3y + z = -2
        // x + 4y - y = 4
        
        // Coefficient matrix
        RealMatrix coefficients = new Array2DRowRealMatrix(new double[][] {
                {3.0, 2.0, -1.0},
                {2.0, -3.0, 1.0},
                {1.0, 4.0, -1.0}
        });
        
        // Constants vector
        RealVector constants = new ArrayRealVector(new double[] {1.0, -2.0, 4.0});
        
        System.out.println("System of equations:");
        System.out.println("3x + 2y - z = 1");
        System.out.println("2x - 3y + z = -2");
        System.out.println("x + 4y - y = 4");
        
        // Solve the system using LU decomposition
        DecompositionSolver solver = new LUDecomposition(coefficients).getSolver();
        
        if (solver.isNonSingular()) {
            RealVector solution = solver.solve(constants);
            
            System.out.println("\nSolution:");
            System.out.println("x = " + solution.getEntry(0));
            System.out.println("y = " + solution.getEntry(1));
            System.out.println("z = " + solution.getEntry(2));
            
            // Verify the solution
            RealVector result = coefficients.operate(solution);
            System.out.println("\nVerification (A * solution):");
            System.out.println(Arrays.toString(result.toArray()));
            System.out.println("Should be equal to the constants vector: " + Arrays.toString(constants.toArray()));
        } else {
            System.out.println("\nThe coefficient matrix is singular. The system may have no solution or infinite solutions.");
        }
        
        // Solve an overdetermined system (more equations than unknowns) using least squares
        System.out.println("\nSolving an overdetermined system using least squares:");
        System.out.println("System of equations:");
        System.out.println("x + y = 2");
        System.out.println("2x + y = 3");
        System.out.println("x + 2y = 3");
        
        // Coefficient matrix
        RealMatrix overdeterminedCoeffs = new Array2DRowRealMatrix(new double[][] {
                {1.0, 1.0},
                {2.0, 1.0},
                {1.0, 2.0}
        });
        
        // Constants vector
        RealVector overdeterminedConstants = new ArrayRealVector(new double[] {2.0, 3.0, 3.0});
        
        // Solve using QR decomposition (optimal for least squares)
        DecompositionSolver qrSolver = new QRDecomposition(overdeterminedCoeffs).getSolver();
        RealVector leastSquaresSolution = qrSolver.solve(overdeterminedConstants);
        
        System.out.println("\nLeast squares solution:");
        System.out.println("x = " + leastSquaresSolution.getEntry(0));
        System.out.println("y = " + leastSquaresSolution.getEntry(1));
        
        // Calculate residuals (how far the solution is from satisfying each equation)
        RealVector residuals = overdeterminedConstants.subtract(overdeterminedCoeffs.operate(leastSquaresSolution));
        System.out.println("\nResiduals (constants - A * solution):");
        System.out.println(Arrays.toString(residuals.toArray()));
        
        // Calculate the sum of squared residuals (this is what least squares minimizes)
        double sumOfSquaredResiduals = 0;
        for (double residual : residuals.toArray()) {
            sumOfSquaredResiduals += residual * residual;
        }
        System.out.println("Sum of squared residuals: " + sumOfSquaredResiduals);
    }
    
    // Helper method to print matrices
    private static void printMatrix(RealMatrix matrix) {
        int rows = matrix.getRowDimension();
        int cols = matrix.getColumnDimension();
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("%8.3f ", matrix.getEntry(i, j));
            }
            System.out.println();
        }
    }
}