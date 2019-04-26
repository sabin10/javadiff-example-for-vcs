import difflib.Patch;

import java.io.Serializable;

public class CustomPatch implements Serializable {

    private Patch patch;

    public CustomPatch(Patch patch) {
        this.patch = patch;
    }

    public Patch getPatch() {
        return patch;
    }

    public void setPatch(Patch patch) {
        this.patch = patch;
    }
}
