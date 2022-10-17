public class Container {
    @Save
    public String text;
    @Save
    public int a;
    private double b = 38.8;

    public Container() {
    }

    public Container(String text, int a, double b) {
        this.text = text;
        this.a = a;
        this.b = b;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "Container{" +
                "text='" + text + '\'' +
                ", a=" + a +
                ", b=" + b +
                '}';
    }
}
