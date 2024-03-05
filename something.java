import java.util.ArrayList;

public class something {

    public static void main(String[] args) {
        ((A) (new B(4))).f();
        // System.out.println(((B)(new A(4))).f());
        A a = new A(4);
        B b = new B(4);
        System.out.println(a instanceof B);
        System.out.println(b instanceof A);

    }
    
}
