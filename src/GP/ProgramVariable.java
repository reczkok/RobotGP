package GP;

public class ProgramVariable {
    public int intValue = 0;
    public boolean boolValue = false;
    public float floatValue = 0.0f;
    private LastAccessed lastAccessed = LastAccessed.FLOAT;

    public ProgramVariable() {
        this.intValue = (int) (Math.random() * (Parameters.maxInitialInt - Parameters.minInitialInt) + Parameters.minInitialInt);
        this.floatValue = (float) (Math.random() * (Parameters.maxInitialFloat - Parameters.minInitialFloat) + Parameters.minInitialFloat);
        this.boolValue = Math.random() < 0.5;
    }

    public LastAccessed getLastAccessed() {
        return this.lastAccessed;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
        this.lastAccessed = LastAccessed.INT;
    }

    public void setBoolValue(boolean boolValue) {
        this.boolValue = boolValue;
        this.lastAccessed = LastAccessed.BOOL;
    }

    public void setFloatValue(float floatValue) {
        this.floatValue = floatValue;
        this.lastAccessed = LastAccessed.FLOAT;
    }

    public int getIntValue() {
        return this.intValue;
    }

    public boolean getBoolValue() {
        return this.boolValue;
    }

    public float getFloatValue() {
        return this.floatValue;
    }
}

