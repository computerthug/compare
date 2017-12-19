package com.malfunction.filecmprapp.model.Trigger;

public class Trigger {

    private String code;
    private String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trigger trigger = (Trigger) o;

        if (code != null ? !code.equals(trigger.code) : trigger.code != null)
            return false;
        return type != null ? type.equals(trigger.type) : trigger.type == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    public Trigger(String c, String t){
        this.code = c;
        this.type = t;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
