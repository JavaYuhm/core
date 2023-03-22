package hello.core.singleton;

public class SingletonService {

    // 자기자신을 객체 인스턴스 Static 영역에 올림.

    private  static final SingletonService instance = new SingletonService();

    // 오직 이 getInstatnce() 메서드 통해서만 가능함.
    public static SingletonService getInstance(){
        return instance;
    }

    // private 생성자
    private SingletonService(){

    }

    public void login(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
