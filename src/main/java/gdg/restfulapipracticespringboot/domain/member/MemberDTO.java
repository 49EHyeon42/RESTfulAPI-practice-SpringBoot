package gdg.restfulapipracticespringboot.domain.member;

public class MemberDTO {

    private final String email;
    private final String name;
    private final int age;

    public MemberDTO(String email, String name, int age) {
        this.email = email;
        this.name = name;
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
