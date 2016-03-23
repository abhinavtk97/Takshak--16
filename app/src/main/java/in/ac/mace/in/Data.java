package in.ac.mace.in;

/**
 * Created by Abhinav on 3/24/2016.
 */
public class Data {
    private String name,mail,phone,college;

    public Data(String name,String mail,String phone,String college){
        this.setName(name);
        this.setMail(mail);
        this.setPhone(phone);
        this.setCollege(college);

    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
