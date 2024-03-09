/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package solution2;

/**
 *
 * @author Admin
 */
public class node {
    public node parent;
    public state state;
    public Action action;
    public node(node parent, state state,Action action){
        this.parent = parent;
        this.state =  state;
        this.action = action;
    }
}
