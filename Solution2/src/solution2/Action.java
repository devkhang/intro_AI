/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package solution2;

/**
 *
 * @author Admin
 */
public class Action {
    public String from;
    public String to;
    public String[] contain = new String[2];
    public Action(String from,String to,String[] contain){
        this.from = from;
        this.to = to;
        this.contain = contain;
    }
}
