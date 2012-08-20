/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DateTime;

import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author tug
 */
public class DateTimeTDV {

   public DateTimeTDV() {
   } 
    private int date;

    private int month;

    private int year;


    public int getDate() {
        return date;
    }


    public void setDate(int date) {
        this.date = date;
    }

    public int getMonth() {
        return month;
    }

    
    public void setMonth(int month) {
        this.month = month;
    }

   
    public int getYear() {
        return year;
    }

    
    public void setYear(int year) {
        this.year = year;
    }


    public DefaultComboBoxModel getDayByMonth(int month, int year){
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        
        switch(month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                for (int i = 1; i <= 31; i++) {
                    combo.addElement(i);

                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                for (int i = 1; i <= 30; i++) {
                    combo.addElement(i);

                }
                break;
            case 2:
                if ((year - 1900) % 4 == 0) {
                    for (int i = 1; i <= 29; i++) {
                        combo.addElement(i);

                    }
                } else {
                    for (int i = 1; i <= 28; i++) {
                        combo.addElement(i);

                    }
                }

                break;
            default:
                break;
        }
        
        return combo;
    }

    
    public DefaultComboBoxModel getListYear(){

        DefaultComboBoxModel year = new DefaultComboBoxModel();

        for (int i = 0; i < 100; i++) {
            year.addElement((2012-i));
        }

        return year;
    }
}
