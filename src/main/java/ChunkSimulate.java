public class ChunkSimulate {
    private String deltaType;

    private int positionOriginal;
    private String linesOriginal;

    private int positionRevised;
    private String linesRevised;

    public ChunkSimulate() {
    }

    public ChunkSimulate(String deltaType, int positionOriginal, String linesOriginal, int positionRevised, String linesRevised) {
        this.deltaType = deltaType;
        this.positionOriginal = positionOriginal;
        this.linesOriginal = linesOriginal;
        this.positionRevised = positionRevised;
        this.linesRevised = linesRevised;
    }

    public String getDeltaType() {
        return deltaType;
    }

    public void setDeltaType(String deltaType) {
        this.deltaType = deltaType;
    }

    public int getPositionOriginal() {
        return positionOriginal;
    }

    public void setPositionOriginal(int positionOriginal) {
        this.positionOriginal = positionOriginal;
    }

    public String getLinesOriginal() {
        return linesOriginal;
    }

    public void setLinesOriginal(String linesOriginal) {
        this.linesOriginal = linesOriginal;
    }

    public int getPositionRevised() {
        return positionRevised;
    }

    public void setPositionRevised(int positionRevised) {
        this.positionRevised = positionRevised;
    }

    public String getLinesRevised() {
        return linesRevised;
    }

    public void setLinesRevised(String linesRevised) {
        this.linesRevised = linesRevised;
    }
}
