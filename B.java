public class B extends A {
    private int y;
    public B(int y) {
        super(y-1);
        this.y = y;
    }
    public void f() {
        System.out.println(y+1);
    }
}
