package mat.unical.it.bookly.persistance;

public class SingleToken {
    private static SingleToken instance = null;
    private String stringData;

    private SingleToken() {}

    public static SingleToken getInstance() {
        if (instance == null) {
            instance = new SingleToken();
        }
        return instance;
    }

    public String getStringData() {
        return stringData;
    }

    public void setStringData(String stringData) {
        this.stringData = stringData;
    }
}
