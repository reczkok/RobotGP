package GP;

public class ProgramVariable {
    public int intValue = 0;
    public boolean boolValue = false;
    public float floatValue = 0.0f;
    private LastAccessed lastAccessed = LastAccessed.Float;

    public ProgramVariable() {
        this.intValue = 0;
        this.floatValue = 0f;
        this.boolValue = false;
    }

    public LastAccessed getLastAccessed() {
        return this.lastAccessed;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
        this.lastAccessed = LastAccessed.Integer;
    }

    public void setBoolValue(boolean boolValue) {
        this.boolValue = boolValue;
        this.lastAccessed = LastAccessed.Boolean;
    }

    public void setFloatValue(float floatValue) {
        this.floatValue = floatValue;
        this.lastAccessed = LastAccessed.Float;
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

    public void reset() {
        this.intValue = 0;
        this.floatValue = 0f;
        this.boolValue = false;
        this.lastAccessed = LastAccessed.Float;
    }
}

