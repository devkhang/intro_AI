/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package solution1;
import java.util.*;
/**
 *
 * @author Admin
 */
public class waterjug {
    
    Stack<node> frontier = new Stack<>();
    ArrayList<int[]> visited = new ArrayList<>();
    int[]  init_state = {0,0};
    
    public node solve(int[] init_state){
        node current_node = new node(init_state, null, null);
        frontier.add(current_node);
        
        System.out.print("Đang xét: ");
        System.out.print("                                ");
        System.out.print("Open Set: ");
        System.out.print("                                ");
        System.out.println("Close Set:");
        
        while(!frontier.isEmpty()){
            node current = frontier.pop();
            System.out.print("(" + current.state[0] + "," + current.state[1] + ")");
            System.out.print("                                ");

            if(isGoal(current)){
                return current;
            }
            
            Action[] actionList = this.getFeasibleAction(current.state);
            visited.add(current.state);
            
            for(Action action:actionList){
                node child = this.getchild(current, action);
                if(!this.frontier.contains(child) && !this.invisit(child.state)){
                    this.frontier.add(child);
                }
            }
            
            System.out.print(frontierToString());
            System.out.print("                                ");
            System.out.println(visitToString());
        }
        return null;
    }

    public String frontierToString() {
        if(frontier.isEmpty()) return "Empty";
        
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (node n : frontier) {
            sb.append("(").append(n.state[0]).append(",").append(n.state[1]).append("), ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        
        return sb.toString();
    }

    public String visitToString() {
        if(visited.isEmpty()) return "Empty";
        
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int[] state : visited) {
            sb.append("(").append(state[0]).append(",").append(state[1]).append("), ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        
        return sb.toString();
    }
    
    public boolean invisit(int[] state){
        for(int i=0;i<this.visited.size();i++){
            int[]cur = this.visited.get(i);
            if(cur[0]==state[0]&& cur[1]==state[1]){
                return true;
            }
        }
        return false;
    }
    public Action[] getFeasibleAction(int[] state){
        int x = state[0];
        int y =  state[1];
        
        ArrayList<Action> actionList = new ArrayList<Action>();
        if(y==0){
            actionList.add(new Action("tap","y",3));
        }
        
        if(x==0){
            actionList.add(new Action("tap","x",4));
        }
        
        if(x==4){
            actionList.add(new Action("x","ground",4));
        }
        if(y==3){
            actionList.add(new Action("y","ground",3));
        }
        if((x>0)&&(3-y)>0){
            actionList.add(new Action("x","y",Math.min(x,(3-y))));
        }
        if((y>0)&&(4-x)>0){
            actionList.add(new Action("y","x",Math.min(y, (4-x))));
        }
        Action[] arrayAction = new Action[actionList.size()];
        return actionList.toArray(arrayAction);
    }
    public node getchild(node cur,Action action){
        int[] new_state = new int[2];
        new_state[0]=cur.state[0];
        new_state[1]=cur.state[1];
        
        if(action!=null){
            if(action.from == "x"){
                new_state[0]=cur.state[0] - action.quantity;
            }
            if(action.from == "y"){
                new_state[1]=cur.state[1]- action.quantity;
            }
            if(action.to=="x"){
                new_state[0] = cur.state[0] + action.quantity;
            }
            if(action.to=="y"){
                new_state[1] = cur.state[1] + action.quantity;
            }
        }
        node new_node = new node(new_state,cur,action);
        return new_node;
    }
    public boolean isGoal(node node){
        return (node.state[0]==2 || node.state[1]==2);
    }
    public void printsolution(node solution){
        ArrayList<String> step = new ArrayList<String>();
        while(solution.parent!=null){
            step.add("fill "+solution.action.to+" with water from "+solution.action.from+"(ie:"+solution.action.quantity+")"+" New state: " + solution.state [0] + "," + solution.state[1]);
            solution = solution.parent;
        }
        System.out.println("step:");
        step.add("4 liters jug has "+solution.state[0] + " and 3 liters jug has :"+solution.state[1]);
        while(step.size()>0){
            String result = step.remove(step.size()-1);
            System.out.println(result);
        }
    }
}
