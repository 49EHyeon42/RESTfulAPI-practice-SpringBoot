package gdg.restfulapipracticespringboot.domain.member;

public class Member {

    private Long id;
    private String email;
    private String name;
    private int age;

    public Member() {
    }

    public void createMember(Long id, MemberDTO memberDTO) {
        this.id = id;
        this.email = memberDTO.getEmail();
        this.name = memberDTO.getName();
        this.age = memberDTO.getAge();
    }

    public void updateMember(MemberDTO memberDTO) {
        this.email = memberDTO.getEmail();
        this.name = memberDTO.getName();
        this.age = memberDTO.getAge();
    }

    public Long getId() {
        return id;
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
