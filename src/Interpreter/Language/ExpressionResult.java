package Interpreter.Language;

public class ExpressionResult {
    int intValue;
    boolean boolValue;
    float floatValue;
    ExpressionFlags type;

    public ExpressionResult() {
        type = ExpressionFlags.Null;
    }

    public ExpressionResult(int value) {
        intValue = value;
        type = ExpressionFlags.Integer;
    }

    public ExpressionResult(boolean value) {
        boolValue = value;
        type = ExpressionFlags.Boolean;
    }

    public ExpressionResult(float value) {
        floatValue = value;
        type = ExpressionFlags.Float;
    }

    public int getIntValue() {
        return intValue;
    }

    public boolean getBoolValue() {
        return boolValue;
    }

    public float getFloatValue() {
        return floatValue;
    }

    public ExpressionFlags getType() {
        return type;
    }

    public static ExpressionResult add(ExpressionResult a, ExpressionResult b) {
        if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult(a.getIntValue() + b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult(a.getFloatValue() + b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult(a.getIntValue() + b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult(a.getFloatValue() + b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getBoolValue() || b.getBoolValue());
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult((a.getBoolValue() ? 1 : 0) + b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getIntValue() + (b.getBoolValue() ? 1 : 0));
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult((a.getBoolValue() ? 1 : 0) + b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getFloatValue() + (b.getBoolValue() ? 1 : 0));
        } else {
            return new ExpressionResult();
        }
    }

    public static ExpressionResult subtract(ExpressionResult a, ExpressionResult b) {
        if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult(a.getIntValue() - b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult(a.getFloatValue() - b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult(a.getIntValue() - b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult(a.getFloatValue() - b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getBoolValue() && !b.getBoolValue());
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult((a.getBoolValue() ? 1 : 0) - b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getIntValue() - (b.getBoolValue() ? 1 : 0));
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult((a.getBoolValue() ? 1 : 0) - b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getFloatValue() - (b.getBoolValue() ? 1 : 0));
        } else {
            return new ExpressionResult();
        }
    }

    public static ExpressionResult multiply(ExpressionResult a, ExpressionResult b) {
        if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult(a.getIntValue() * b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult(a.getFloatValue() * b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult(a.getIntValue() * b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult(a.getFloatValue() * b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getBoolValue() && b.getBoolValue());
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult((a.getBoolValue() ? 1 : 0) * b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getIntValue() * (b.getBoolValue() ? 1 : 0));
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult((a.getBoolValue() ? 1 : 0) * b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getFloatValue() * (b.getBoolValue() ? 1 : 0));
        } else {
            return new ExpressionResult();
        }
    }

    public static ExpressionResult divide(ExpressionResult a, ExpressionResult b) {
        if (b.getType() == ExpressionFlags.Boolean ||
                (b.getType() == ExpressionFlags.Float && Math.abs(b.getFloatValue()) < 0.0001) ||
                (b.getType() == ExpressionFlags.Integer && b.getIntValue() == 0)) {
            return a;
        }
        if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult(a.getIntValue() / b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult(a.getFloatValue() / b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult(a.getIntValue() / b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult(a.getFloatValue() / b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getBoolValue() && b.getBoolValue());
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult((a.getBoolValue() ? 1 : 0) / b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getIntValue() / (b.getBoolValue() ? 1 : 0));
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult((a.getBoolValue() ? 1 : 0) / b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getFloatValue() / (b.getBoolValue() ? 1 : 0));
        } else {
            return new ExpressionResult();
        }
    }

    public static ExpressionResult modulo(ExpressionResult a, ExpressionResult b) {
        if(b.getType() == ExpressionFlags.Boolean ||
                (b.getType() == ExpressionFlags.Integer && b.getIntValue() == 0) ||
                (b.getType() == ExpressionFlags.Float && Math.abs(b.getFloatValue()) < 0.51)){
            return a;
        }
        if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult(a.getIntValue() % b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult(Math.round(a.getFloatValue()) % Math.round(b.getFloatValue()));
        } else if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult(a.getIntValue() % Math.round(b.getFloatValue()));
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult(Math.round(a.getFloatValue()) % b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getBoolValue() && b.getBoolValue());
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult((a.getBoolValue() ? 1 : 0) % b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult((a.getBoolValue() ? 1 : 0) % Math.round(b.getFloatValue()));
        } else {
            return new ExpressionResult();
        }
    }

    public static ExpressionResult equals(ExpressionResult a, ExpressionResult b) {
        if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult(a.getIntValue() == b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult(a.getFloatValue() == b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult(a.getIntValue() == b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult(a.getFloatValue() == b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getBoolValue() == b.getBoolValue());
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult((a.getBoolValue() ? 1 : 0) == b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getIntValue() == (b.getBoolValue() ? 1 : 0));
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult((a.getBoolValue() ? 1 : 0) == b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getFloatValue() == (b.getBoolValue() ? 1 : 0));
        } else {
            return new ExpressionResult();
        }
    }

    public static ExpressionResult notEquals(ExpressionResult a, ExpressionResult b) {
        if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult(a.getIntValue() != b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult(a.getFloatValue() != b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult(a.getIntValue() != b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult(a.getFloatValue() != b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getBoolValue() != b.getBoolValue());
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult((a.getBoolValue() ? 1 : 0) != b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getIntValue() != (b.getBoolValue() ? 1 : 0));
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult((a.getBoolValue() ? 1 : 0) != b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getFloatValue() != (b.getBoolValue() ? 1 : 0));
        } else {
            return new ExpressionResult();
        }
    }

    public static ExpressionResult greaterThan(ExpressionResult a, ExpressionResult b) {
        if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult(a.getIntValue() > b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult(a.getFloatValue() > b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult(a.getIntValue() > b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult(a.getFloatValue() > b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getBoolValue() && !b.getBoolValue());
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult((a.getBoolValue() ? 1 : 0) > b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getIntValue() > (b.getBoolValue() ? 1 : 0));
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult((a.getBoolValue() ? 1 : 0) > b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getFloatValue() > (b.getBoolValue() ? 1 : 0));
        } else {
            return new ExpressionResult();
        }
    }

    public static ExpressionResult greaterThanOrEqual(ExpressionResult a, ExpressionResult b) {
        if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult(a.getIntValue() >= b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult(a.getFloatValue() >= b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult(a.getIntValue() >= b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult(a.getFloatValue() >= b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getBoolValue() && !b.getBoolValue());
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult((a.getBoolValue() ? 1 : 0) >= b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getIntValue() >= (b.getBoolValue() ? 1 : 0));
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult((a.getBoolValue() ? 1 : 0) >= b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getFloatValue() >= (b.getBoolValue() ? 1 : 0));
        } else {
            return new ExpressionResult();
        }
    }

    public static ExpressionResult lessThan(ExpressionResult a, ExpressionResult b) {
        if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult(a.getIntValue() < b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult(a.getFloatValue() < b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult(a.getIntValue() < b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult(a.getFloatValue() < b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getBoolValue() && !b.getBoolValue());
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult((a.getBoolValue() ? 1 : 0) < b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getIntValue() < (b.getBoolValue() ? 1 : 0));
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult((a.getBoolValue() ? 1 : 0) < b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getFloatValue() < (b.getBoolValue() ? 1 : 0));
        } else {
            return new ExpressionResult();
        }
    }

    public static ExpressionResult lessThanOrEqual(ExpressionResult a, ExpressionResult b) {
        if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult(a.getIntValue() <= b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult(a.getFloatValue() <= b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult(a.getIntValue() <= b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult(a.getFloatValue() <= b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getBoolValue() && !b.getBoolValue());
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Integer) {
            return new ExpressionResult((a.getBoolValue() ? 1 : 0) <= b.getIntValue());
        } else if (a.getType() == ExpressionFlags.Integer && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getIntValue() <= (b.getBoolValue() ? 1 : 0));
        } else if (a.getType() == ExpressionFlags.Boolean && b.getType() == ExpressionFlags.Float) {
            return new ExpressionResult((a.getBoolValue() ? 1 : 0) <= b.getFloatValue());
        } else if (a.getType() == ExpressionFlags.Float && b.getType() == ExpressionFlags.Boolean) {
            return new ExpressionResult(a.getFloatValue() <= (b.getBoolValue() ? 1 : 0));
        } else {
            return new ExpressionResult();
        }
    }

    @Override
    public String toString() {
        if (type == ExpressionFlags.Integer) {
            return Integer.toString(intValue);
        } else if (type == ExpressionFlags.Float) {
            return Float.toString(floatValue);
        } else if (type == ExpressionFlags.Boolean) {
            return Boolean.toString(boolValue);
        } else {
            return "null";
        }
    }
}
