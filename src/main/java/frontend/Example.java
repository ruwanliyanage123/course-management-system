package frontend;

public class Example {
    public static void main(String[] args) {
        mmm();
    }

    static void mmm() {
        int a = 20;
        try {
            if(a<18){
                throw new MyExcpection();
            }
        }catch (MyExcpection e){

        }
    }
}

class MyExcpection extends  Exception{
    MyExcpection(){
        super("Oya podi wadi. dukai dukai dukai");
    }
}



