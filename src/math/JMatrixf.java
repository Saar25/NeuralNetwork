package math;

public class JMatrixf {

    private final float[][] matrix;
    private final int rows;
    private final int cols;

    public JMatrixf(int rows, int cols) {
        this.matrix = new float[rows][cols];
        this.rows = rows;
        this.cols = cols;
    }

    public static JMatrixf fromArray(float[] array) {
        JMatrixf m = new JMatrixf(array.length, 1);
        m.map0((v, row, col) -> array[row]);
        return m;
    }

    public static JMatrixf randomize(int rows, int cols, int min, int max) {
        JMatrixf m = new JMatrixf(rows, cols);
        m.map0((v, row, col) -> Maths.randomf(min, max));
        return m;
    }

    public static JMatrixf randomize(int rows, int cols) {
        return randomize(rows, cols, -1, 1);
    }

    public float[] toArray() {
        float[] array = new float[rows * cols];
        /*
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i * cols + j] = matrix[i][j];
            }
        }
        return array;
        */
        for (int i = 0; i < rows; i++) {
            System.arraycopy(matrix[i], 0, array, i * cols, cols);
        }
        return array;
    }

    private void map0(Function3 function) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = function.invoke(matrix[i][j], i, j);
            }
        }
    }

    public JMatrixf map(Function3 function) {
        JMatrixf m = new JMatrixf(rows, cols);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                m.matrix[i][j] = function.invoke(matrix[i][j], i, j);
            }
        }
        return m;
    }

    private void requiredSameDimensions(JMatrixf t, String operation) {
        if (!isSameDimensions(t)) {
            throw new IllegalArgumentException("Cannot perform " + operation +
                    " to matrix (size = " + rows + ", " + cols + ") with (size = " + t.rows + ", " + t.cols + ")");
        }
    }

    private boolean isSameDimensions(JMatrixf m) {
        return cols == m.cols && rows == m.rows;
    }

    public boolean canMultiply(JMatrixf m) {
        return cols == m.rows;
    }

    public JMatrixf add(float t) {
        return map((v, row, col) -> v + t);
    }

    public JMatrixf sub(float t) {
        return map((v, row, col) -> v - t);
    }

    public JMatrixf mul(float t) {
        return map((v, row, col) -> v * t);
    }

    public JMatrixf div(float t) {
        return map((v, row, col) -> v / t);
    }

    public JMatrixf add(JMatrixf t) {
        requiredSameDimensions(t, "addition");
        return map((v, row, col) -> v + t.matrix[row][col]);
    }

    public JMatrixf sub(JMatrixf t) {
        requiredSameDimensions(t, "subtraction");
        return map((v, row, col) -> v - t.matrix[row][col]);
    }

    public JMatrixf mul(JMatrixf t) {
        requiredSameDimensions(t, "multiplication");
        return map((v, row, col) -> v * t.matrix[row][col]);
    }

    public JMatrixf transpose() {
        final JMatrixf m = new JMatrixf(cols, rows);
        m.map0((v, row, col) -> matrix[col][row]);
        return m;
    }

    public JMatrixf dot(JMatrixf t) {
        if (!canMultiply(t)) {
            throw new IllegalArgumentException("Cannot multiply matrix (cols = " +
                    cols + ") with (rows = " + t.rows + ")");
        }
        final JMatrixf m = new JMatrixf(rows, t.cols);
        m.map0((v, row, col) -> {
            float sum = 0f;
            for (int k = 0; k < cols; k++) {
                sum += matrix[row][k] * t.matrix[k][col];
            }
            return sum;
        });
        return m;
    }

    public interface Function3 {
        float invoke(float v, int row, int col);
    }
}
