package Lesson4HW;

public class Abc implements Int1, Int2 {
    @Override
    public void a(String a) {
        System.out.println("aaa");
    }

}
interface Int2 {
    public void a(String a);
}

interface Int1 {
    public void a(String a);
}
