package kruskMST_pack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class KruskalAlgorithm
{
	
	public static ArrayList<EDGE> Kruskal(char[] vertices, EDGE[] edges){  
	        //Initialize A = empty set
	        ArrayList<EDGE> mst = new ArrayList<>();
	        
	        //for each vertex v belongs to G.V MAKE-SET(v)
	        initialize(vertices);
	         
	        //sort the edges of G.E into non decreasing order by weight w
	        Arrays.sort(edges);
	        
	        //For each edge (u,v) belongs to G.E taken in non decreasing order by weight
	        for(EDGE edge:edges){
	            //If (find-set(u)!=find-set(v)
	            if(FindSet(edge.from)!=FindSet(edge.to)){
	                //A = A union (u, v)
	                mst.add(edge);
	                //UNION(u, v)
	                Union(edge.from, edge.to);
	            }
	        }             
	        //Display contents
	        System.out.println("MST contains the edges: "+mst);
	        return mst;
	}
	
	static class EDGE implements Comparable<EDGE>{
        char from, to;
        int weight;
        EDGE(char f, char t, int w){
            from = f;
            to = t;
            weight = w; 
        }  

        @Override
        public int compareTo(EDGE o) {
            return weight<o.weight?-1:(weight>o.weight?1:0);
        } 
        
        @Override
        public String toString(){
            return "["+from+", "+to+"]";
        }
    }
    
    private static Map<Character, Character> PARENT;
    private static Map<Character, Integer> RANKS; //to store the depths
    
    public static void initialize(char[] universe){ 
        PARENT = new HashMap<>();
        RANKS = new HashMap<>();
        for(char x:universe){
            PARENT.put(x, x);
            RANKS.put(x, 1);
        } 
    }
    
    public static char FindSet(char item){
        char parent = PARENT.get(item); 
        if(parent==item)return item;
        else return FindSet(parent);
    }
    
    public static void Union(char setA, char setB){
        char pA, pB;
        while((pA = PARENT.get(setA))!=setA){setA = pA;}
        while((pB = PARENT.get(setB))!=setB){setB = pB;}
        
        int rankFirst = RANKS.get(setA), rankSecond = RANKS.get(setB);
        if(rankFirst>rankSecond){
            PARENT.put(setB, setA);  
            updateRanksUpward(setB);
        }else if(rankSecond>rankFirst){
            PARENT.put(setA, setB);  
            updateRanksUpward(setA);
        }else{
            PARENT.put(setB, setA); 
            updateRanksUpward(setB);
        }
    }
    
    public static void updateRanksUpward(char current){
        int currentDepth = RANKS.get(current);
        char currentsParent = PARENT.get(current);
        int parentsDepth = RANKS.get(currentsParent);
        if(!(currentDepth<parentsDepth || currentsParent == current)){ 
            RANKS.put(currentsParent, currentDepth+1);
            updateRanksUpward(currentsParent);
        }
    } 
    
    public static void main(String[] args){
        //Test data
        //CLRS Example p632
        char[] vertices = new char[]{
        		'a',
        		'b',
        		'c',
        		'd',
        		'e',
        		'f',
        		'g',
        		'h',
        		'i',
        		'j',
        		'k',
        		'l',
        		'm',
        		'n',
        		'o',
        		'p',
        		'q',
        		'r',
        		's',
        		't',
        		'u'}; 
        EDGE[] edges = new EDGE[84];
        
        edges[0] = new EDGE('a','g',184);
        edges[1] = new EDGE('a','p',90);
        edges[2] = new EDGE('a','t',184);
        edges[3] = new EDGE('a','u',142);
        
        edges[4] = new EDGE('b','h',440);
        edges[5] = new EDGE('b','m',123);
        edges[6] = new EDGE('b','u',225);
     
        edges[7] = new EDGE('c','e',103);
        edges[8] = new EDGE('c','j',68);
        edges[9] = new EDGE('c','q',80);
        
        edges[10] = new EDGE('d','f',161);
        edges[11] = new EDGE('d','n',60);  
        
        edges[12] = new EDGE('e','c',103);
        edges[13] = new EDGE('e','i',100);
        edges[14] = new EDGE('e','s',146);
        edges[15] = new EDGE('e','t',86);   
        
        edges[16] = new EDGE('f','d',161);
        edges[17] = new EDGE('f','i',217);
        edges[18] = new EDGE('f','k',143);
        edges[19] = new EDGE('f','n',154);
        edges[20] = new EDGE('f','q',143); 
        
        edges[21] = new EDGE('g','a',184);
        edges[22] = new EDGE('g','l',126);
        edges[23] = new EDGE('g','r',177);
        edges[24] = new EDGE('g','s',95);   
        edges[25] = new EDGE('g','u',142);  
        
        edges[26] = new EDGE('h','b',440);
        edges[27] = new EDGE('h','k',606);
        edges[28] = new EDGE('h','o',305);
        
        edges[29] = new EDGE('i','e',100);
        edges[30] = new EDGE('i','f',217);
        edges[31] = new EDGE('i','q',197);
        edges[32] = new EDGE('i','s',199);   
        
        edges[33] = new EDGE('j','c',68);
        edges[34] = new EDGE('j','p',184);
        edges[35] = new EDGE('j','t',63);  
        
        edges[36] = new EDGE('k','f',143);  
        edges[37] = new EDGE('k','h',606); 
        edges[38] = new EDGE('k','n',144); 
        edges[39] = new EDGE('k','o',418); 
        edges[40] = new EDGE('k','q',156); 
        
        edges[41] = new EDGE('l','g',126);  
        edges[42] = new EDGE('l','r',90); 
        edges[43] = new EDGE('l','s',38); 
        
        edges[44] = new EDGE('m','b',123);  
        edges[45] = new EDGE('m','o',118); 
        edges[46] = new EDGE('m','u',210); 
        
        edges[47] = new EDGE('n','d',60);  
        edges[48] = new EDGE('n','f',154); 
        edges[49] = new EDGE('n','k',144); 
        
        edges[50] = new EDGE('o','h',305);  
        edges[51] = new EDGE('o','k',418); 
        edges[52] = new EDGE('o','m',118); 
        edges[53] = new EDGE('o','p',112); 
        
        edges[54] = new EDGE('p','a',90);  
        edges[55] = new EDGE('p','j',184);
        edges[56] = new EDGE('p','o',112);
        edges[57] = new EDGE('p','q',212);
        edges[58] = new EDGE('p','t',215);
        edges[59] = new EDGE('p','u',239);
        
        edges[60] = new EDGE('q','c',80);  
        edges[61] = new EDGE('q','f',143);
        edges[62] = new EDGE('q','i',197);
        edges[63] = new EDGE('q','k',156);
        edges[64] = new EDGE('q','p',212);
        
        edges[65] = new EDGE('r','g',177);  
        edges[66] = new EDGE('r','l',90); 
        edges[67] = new EDGE('r','u',272); 
        
        edges[68] = new EDGE('s','e',146);          
        edges[69] = new EDGE('s','g',95);
        edges[70] = new EDGE('s','i',199);
        edges[71] = new EDGE('s','l',38);
        edges[72] = new EDGE('s','t',132);
        
        edges[73] = new EDGE('t','a',184);  
        edges[74] = new EDGE('t','e',86);
        edges[75] = new EDGE('t','j',63);
        edges[76] = new EDGE('t','p',215);
        edges[77] = new EDGE('t','s',132);
        
        edges[78] = new EDGE('u','a',142);  
        edges[79] = new EDGE('u','b',225);
        edges[80] = new EDGE('u','g',142);
        edges[81] = new EDGE('u','m',210);
        edges[82] = new EDGE('u','p',239);
        edges[83] = new EDGE('u','r',272);

        
        
        //Call Kruskal Algorithm
        Kruskal(vertices, edges);
    }
    

	
}
	