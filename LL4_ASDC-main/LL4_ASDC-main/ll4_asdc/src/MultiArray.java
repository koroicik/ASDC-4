public class MultiArray {
    private double[][][] data;
    private int[] shape;

    public MultiArray(int[] shape) {
        this.shape = shape;
        data = new double[shape[0]][shape[1]][shape[2]];
    }

    public double get(int i, int j, int k) {
        return data[i][j][k];
    }

    public void set(int i, int j, int k, double value) {
        data[i][j][k] = value;
    }

    public double getFromAiliff(int[] indices) {
        int i = indices[0];
        int j = indices[1];
        int k = indices[2];
        return data[i][j][k];
    }

    public void setFromAiliff(int[] indices, double value) {
        int i = indices[0];
        int j = indices[1];
        int k = indices[2];
        data[i][j][k] = value;
    }

    public double[][][] toColumnRowVectors() {
        double[][][] result = new double[shape[0]][shape[2]][shape[1]];
        for (int i = 0; i < shape[0]; i++) {
            for (int j = 0; j < shape[1]; j++) {
                for (int k = 0; k < shape[2]; k++) {
                    result[i][k][j] = data[i][j][k];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] shape = {10, 20, 30};
        MultiArray arr = new MultiArray(shape);

        // Прямой доступ к элементам
        long start = System.nanoTime();
        double value = arr.get(5, 10, 15);
        long end = System.nanoTime();
        System.out.println("Время доступа к элементу: " + (end - start) + " наносекунд");

        start = System.nanoTime();
        arr.set(5, 10, 15, value + 1.0);
        end = System.nanoTime();
        System.out.println("Время записи элемента: " + (end - start) + " наносекунд");

        // Доступ посредством векторов Айлиффа
        int[] indices = {5, 10, 15};
        start = System.nanoTime();
        value = arr.getFromAiliff(indices);
        end = System.nanoTime();
        System.out.println("Время доступа к элементу по вектору Айлиффа: " + (end - start) + " наносекунд");

        start = System.nanoTime();
        arr.setFromAiliff(indices, value + 1.0);
        end = System.nanoTime();
        System.out.println("Время записи элемента по вектору Айлиффа: " + (end - start) + " наносекунд");

        // Представление в оперативной памяти производится по столбцам и строкам
        start = System.nanoTime();
        double[][][] columnRowVectors = arr.toColumnRowVectors();
        end = System.nanoTime();
        System.out.println("Время представления векторов столбцов и строк: " + (end - start) + " наносекунд");
        // Пример использования
        columnRowVectors[5][15][10] += 1.0;
        double value2 = columnRowVectors[5][15][10];
        System.out.println("Значение элемента после изменения через векторы столбцов и строк: " + value2);
    }
}
