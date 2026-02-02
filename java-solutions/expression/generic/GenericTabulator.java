package expression.generic;

public class GenericTabulator implements Tabulator {
    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {
        if ("i".equals(mode)) {
            return buildTable(expression, new IntegerOperations(true), x1, x2, y1, y2, z1, z2);
        } else if ("d".equals(mode)) {
            return buildTable(expression, new DoubleOperations(), x1, x2, y1, y2, z1, z2);
        } else if ("bi".equals(mode)) {
            return buildTable(expression, new BigIntegerOperations(), x1, x2, y1, y2, z1, z2);
        } else if ("s".equals(mode)) {
            return buildTable(expression, new ShortOperations(), x1, x2, y1, y2, z1, z2);
        } else if ("f".equals(mode)) {
            return buildTable(expression, new FloatOperations(), x1, x2, y1, y2, z1, z2);
        } else if ("u".equals(mode)) {
            return buildTable(expression, new IntegerOperations(false), x1, x2, y1, y2, z1, z2);
        }
        else {
            throw new IllegalArgumentException("Unknown mode: " + mode);
        }
    }

    private <T> Object[][][] buildTable(String expression, Operation<T> operation,
                                        int x1, int x2, int y1, int y2, int z1, int z2) {
        InterfaceExpression<T> parsed = ExpressionParser.parse(expression, operation);
        int sizeX = x2 - x1 + 1;
        int sizeY = y2 - y1 + 1;
        int sizeZ = z2 - z1 + 1;
        Object[][][] result = new Object[sizeX][sizeY][sizeZ];
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                for (int k = 0; k < sizeZ; k++) {
                    T xv = operation.fromInt(x1 + i);
                    T yv = operation.fromInt(y1 + j);
                    T zv = operation.fromInt(z1 + k);
                    try {
                        result[i][j][k] = parsed.evaluate(xv, yv, zv);
                    } catch (Exception e) {
                        result[i][j][k] = null;
                    }
                }
            }
        }
        return result;
    }
}

