package neumont;

public class LargeEmployee extends Employee {
    private byte[] someData;

    public LargeEmployee(int id, String firstName, String lastName, int hireYear, byte[] someData){
        super(id, firstName, lastName, hireYear);
        this.someData = someData;
    }

    public byte[] getSomeData() {
        return someData;
    }

    public void setSomeData(byte[] someData) {
        this.someData = someData;
    }

    @Override
    public String toString(){

        StringBuilder formatted = new StringBuilder();

        formatted.append("\n").append("Employee specific data");

        for(int i = 0; i < someData.length; i ++) {
            formatted.append("\n").append(someData[i]);
        }

        return formatted.toString();
    }


}
