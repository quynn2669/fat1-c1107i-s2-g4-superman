/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package patientinfo;

/**
 *
 * @author tug
 */
public class Patient {
int ID;
String FullName;
String Address;
String Age;
String Gender;
String Department;
String Description;
String Sick;
String Doctor;
String DateIn;
String DateOut;
String  InHospital;
int DrStt;

    public Patient() {
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setAge(String Age) {
        this.Age = Age;
    }

    public void setDateIn(String DateIn) {
        this.DateIn = DateIn;
    }

    public void setDateOut(String DateOut) {
        this.DateOut = DateOut;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setDoctor(String Doctor) {
        this.Doctor = Doctor;
    }

    public void setDrStt(int DrStt) {
        this.DrStt = DrStt;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setSick(String Sick) {
        this.Sick = Sick;
    }

    public void setInHospital(String Stt) {
        this.InHospital = Stt;
    }

    public String getAddress() {
        return Address;
    }

    public String getAge() {
        return Age;
    }

    public String getDateIn() {
        return DateIn;
    }

    public String getDateOut() {
        return DateOut;
    }

    public String getDepartment() {
        return Department;
    }

    public String getDescription() {
        return Description;
    }

    public String getDoctor() {
        return Doctor;
    }

    public int getDrStt() {
        return DrStt;
    }

    public String getFullName() {
        return FullName;
    }

    public String getGender() {
        return Gender;
    }

    public int getID() {
        return ID;
    }

    public String getSick() {
        return Sick;
    }

    public String getInHospital() {
        return InHospital;
    }
    public String toString(){
        return "ID: "+ID+"\nDescription: "+Description+"\nName: "+FullName+"\nAddress: "+Address+"\nAge: "+Age+
                "\nSick: "+Sick+"\nGender: "+Gender+"\nDepartment: "+Department+
                "\nIn Hospital: "+InHospital+"\nDate In: "+DateIn+"\nDate Out: "+DateOut;
    }

}
