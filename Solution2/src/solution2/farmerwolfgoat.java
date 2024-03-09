/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package solution2;
import java.util.*;
/**
 *
 * @author Admin
 */
public class farmerwolfgoat {
    public ArrayList<node> explore = new ArrayList<>();
    public Queue<node> fontier = new LinkedList<>();
    public state init_state = new state("w","w","w","w");
    public node solve(){
        state cur = new state("w","w","w","w");
        node cur_node = new node(null,cur,null);
        this.fontier.add(cur_node);
        while(true){
            if(this.fontier.isEmpty()){
                return null;
            }
            node current_node = this.fontier.remove();
            if(!this.inexplore(current_node)){
                this.explore.add(current_node);
            }
            Action[] actions = this.getFeasibleAction(current_node);
//            System.out.println(actions.toString());
            for(Action action:actions){
                node child = this.getchild(current_node, action);
                if(!infontier(child)&&!inexplore(child)){
                    if(this.isGoal(child)){
                        return child;
                    }
                    this.fontier.add(child);
                }
            }            
        }
    }
    public boolean isGoal(node cur){
        if(cur.state.farmer.equals("e")&&cur.state.wolf.equals("e")&&cur.state.goat.equals("e")&&cur.state.cabbage.equals("e")){
            return true;
        }
        return false;
    }
    public boolean infontier(node cur){
        if(this.fontier.contains(cur)){
            return true;
        }
        return false;
    }
    public boolean inexplore(node cur){
        for(int i =0;i<this.explore.size();i++){
            state curr = this.explore.get(i).state;
            if(cur.state == curr)return true;
        }
        return false;
    }
    public Action[] getFeasibleAction(node current){
        String farmer = current.state.farmer;
        String wolf = current.state.wolf;
        String goat = current.state.goat;
        String cabbage = current.state.cabbage;
        ArrayList<Action> actionList = new ArrayList<>();

        if(farmer.equals("w")) {
            if(wolf.equals("w") && goat.equals("w") && cabbage.equals("w")) {
                String[] contain = {"farmer", "goat"};
                actionList.add(new Action("w", "e", contain));
            }
            if(wolf.equals("w") && goat.equals("e") && cabbage.equals("w")) {
                String[] contain = {"farmer", "cabbage"};
                actionList.add(new Action("w", "e", contain));
            }
            if(wolf.equals("w") && goat.equals("w") && cabbage.equals("e")) {
                String[] contain = {"farmer", "goat"};
                actionList.add(new Action("w", "e", contain));
            }
            if(wolf.equals("w") && goat.equals("w") && cabbage.equals("e")) {
                String[] contain = {"farmer", "wolf"};
                actionList.add(new Action("w", "e", contain));
            }
            if(wolf.equals("e") && goat.equals("w") && cabbage.equals("w")) {
                String[] contain = {"farmer", "cabbage"};
                actionList.add(new Action("w", "e", contain));
            }
            if(wolf.equals("e") && goat.equals("w") && cabbage.equals("e")) {
                String[] contain = {"farmer", "goat"};
                actionList.add(new Action("w", "e", contain));
            }
            // Add other feasible actions for west side
        } else {
            if(wolf.equals("w") && goat.equals("e") && cabbage.equals("w")) {
                String[] contain = {"farmer", ""};
                actionList.add(new Action("e", "w", contain));
            }
            if(wolf.equals("w") && goat.equals("e") && cabbage.equals("e")) {
                String[] contain = {"farmer", "goat"};
                actionList.add(new Action("e", "w", contain));
            }// farmer is at east
            if(wolf.equals("e") && goat.equals("e") && cabbage.equals("w")) {
                String[] contain = {"farmer", "goat"};
                actionList.add(new Action("e", "w", contain));
            }
            if(wolf.equals("e") && goat.equals("w") && cabbage.equals("e")) {
                String[] contain = {"farmer", ""};
                actionList.add(new Action("e", "w", contain));
            }// Add other feasible actions for east side
        }
        Action[] arrayAction = new Action[actionList.size()];
        return actionList.toArray(arrayAction);
    }
    public node getchild(node current, Action action){
    String farmer = current.state.farmer;
    String wolf = current.state.wolf;
    String goat = current.state.goat;
    String cabbage = current.state.cabbage;
    
    // Create a copy of the current state to update
    state newState = new state(farmer, wolf, goat, cabbage);

    // Ensure action is not null and valid
    if (action != null) {
        // Check if moving from west to east
        if (action.to.equals("e") && action.from.equals("w")) {
            if (action.contain[1].equals("goat")) {
                newState.farmer = "e";
                newState.goat = "e";
            }else if (action.contain[1].equals("cabbage") && wolf.equals("w") && goat.equals("e") && cabbage.equals("w")) {
                newState.farmer = "e";
                newState.cabbage = "e";
            } else if (action.contain[1].equals("wolf") && goat.equals("e") && cabbage.equals("w")) {
                newState.farmer = "e";
                newState.wolf = "e";
            } else if (action.contain[1].equals("wolf") && goat.equals("w") && cabbage.equals("e")) {
                newState.farmer = "e";
                newState.wolf = "e";
            }
        }
        // Check if moving from east to west
        else if (action.to.equals("w") && action.from.equals("e")) {
            if (action.contain[1].equals("")) {
                newState.farmer = "w";
            } else if (action.contain[1].equals("goat")) {
                newState.farmer = "w";
                newState.goat = "w";
            } else if (action.contain[1].equals("wolf")) {
                newState.farmer = "w";
                newState.wolf = "w";
            }
            } else if (action.contain[1].equals("cabbage")) {
                newState.farmer = "w";
                newState.cabbage = "w";
            }
        }
    // Create a new node with the updated state and action
    node new_node = new node(current, newState, action);
    return new_node;
}

    public void printsolution(node solution){
        ArrayList<String> steps = new ArrayList<String>();

        // Truy ngược từ nút cuối cùng về nút gốc
        while(solution.parent != null){
            // Xây dựng thông điệp cho mỗi bước di chuyển
            String step = "Move " + solution.action.to + " with boat " + solution.action.from ;
            // Thêm thông điệp và trạng thái mới vào danh sách các bước
            steps.add(step + ". New state: "  + solution.state.farmer +" "+solution.state.wolf+" "+solution.state.goat+" "+solution.state.cabbage );

            // Di chuyển đến nút cha
            solution = solution.parent;
        }

        // Thêm trạng thái cuối cùng của nút gốc
        steps.add("State of the farmer, wolf, goat, and cabbage: \n" + 
            "Farmer: " + (solution.state.farmer.equals("w") ? "west" : "east") + ", " +
            "Wolf: " + (solution.state.wolf.equals("w") ? "west" : "east") + ", " +
            "Goat: " + (solution.state.goat.equals("w") ? "west" : "east") + ", " +
            "Cabbage: " + (solution.state.cabbage.equals("w") ? "west" : "east"));


        // In các bước di chuyển theo thứ tự ngược lại
        System.out.println("Solution steps:");
        for(int i = steps.size() - 1; i >= 0; i--){
            System.out.println(steps.get(i));
        }
    }
}