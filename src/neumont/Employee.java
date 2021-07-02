package neumont;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private int hireYear;

    public Employee(int id, String firstName, String lastName, int hireYear){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireYear = hireYear;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getHireYear() {
        return hireYear;
    }

    public void setHireYear(int hireYear) {
        this.hireYear = hireYear;
    }

    @Override
    public String toString(){

        StringBuilder formatted = new StringBuilder();

        formatted.append("Employee ID: ").append(id).append("\n").append("Employee First Name: ").append(firstName).append("\n").append("Employee Last Name: ").append(lastName).append("\n").append("Hire Date: ").append(hireYear);

        return formatted.toString();
    }

}
