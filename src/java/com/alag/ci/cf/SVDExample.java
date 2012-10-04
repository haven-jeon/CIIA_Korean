package com.alag.ci.cf;

import weka.core.matrix.Matrix;
import weka.core.matrix.SingularValueDecomposition;

public class SVDExample {

    public static final void main(String [] args) {
        SVDExample eg = new SVDExample();
        Matrix userItem = eg.createUserItemMatrix();
        SingularValueDecomposition svd = eg.computeSVD(userItem);
        eg.reduceDimension(userItem, svd, 1);
        eg.reduceDimension(userItem, svd, 2);
        eg.reduceDimension(userItem, svd, 3);
    }
    
    public Matrix createUserItemMatrix() {
        double [][] values = { {3,4,2}, {2,2,4}, {1,3,5} };
        Matrix userItem =  new Matrix(values);
        System.out.println("UserItem: Rank=" + userItem.rank());
        System.out.println(userItem);
        return userItem;
    }
    
    public SingularValueDecomposition computeSVD(Matrix matrix) {
        SingularValueDecomposition svd = matrix.svd();
        System.out.println("U:\n" + svd.getU());
        System.out.println("UtU is orthogonal:\n" + svd.getU().transpose().times(svd.getU()));
        System.out.println("S:\n" + svd.getS());
        System.out.println("Vt:\n" + svd.getV().transpose());
        System.out.println("VtV is orhogonal:\n" + svd.getV().transpose().times(svd.getV()));
        return svd;
    }
    
    public Matrix reduceDimension(Matrix userItem, SingularValueDecomposition svd, int k) {
        int m = userItem.getRowDimension();
        int n = userItem.getColumnDimension();
        Matrix Uk = matrixSubset(svd.getU(),m,k);
        Matrix Sk = matrixSubset(svd.getS(),k,k);
        Matrix Vtk = matrixSubset(svd.getV().transpose(),k,n);
        Matrix approx = Uk.times(Sk).times(Vtk);
        System.out.println(k + " dimensional approx matrix:\n " + approx);
        return approx;
    }
    
    private Matrix matrixSubset(Matrix orig, int m, int k ) {
        Matrix newMatrix = new Matrix(m,k);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < k; j ++) {
                newMatrix.set(i, j, orig.get(i,j));
            }
        }
        return newMatrix;
    }
}
