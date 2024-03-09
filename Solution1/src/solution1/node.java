/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package solution1;

/**
 *
 * @author Admin
 */
public class node {
    public int[] state;
    public node parent;
    public Action action;
    public node(int[] state,node parent,Action action){
        this.state = state;
        this.parent = parent;
        this.action = action;
    }
}
