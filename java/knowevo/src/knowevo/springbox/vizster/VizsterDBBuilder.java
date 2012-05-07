/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package knowevo.springbox.vizster;

import edu.berkeley.guir.prefuse.ItemRegistry;
import edu.berkeley.guir.prefuse.activity.ActionList;
import edu.berkeley.guir.prefuse.graph.Graph;
import edu.berkeley.guir.prefuse.graph.io.XMLGraphReader;
import knowevo.springbox.DBBuilder;
import knowevo.springbox.Node;
import knowevo.springbox.Edge;
import knowevo.springbox.ScoreMachine;

import java.io.*;
import java.sql.SQLException;
import knowevo.springbox.*;

/**
 *
 * @author gabrovski
 */
public class VizsterDBBuilder extends DBBuilder {
    
    private String out;
    private BufferedWriter bw;
    
    public VizsterDBBuilder(ScoreMachine sm, String o) {
        super(sm);
        out = o;
    }
    
    @Override
    public void convertGraph() {
        try {
            bw = new BufferedWriter(new FileWriter(out));
            bw.write("<graph directed=\"1\">\n");
            super.convertGraph();
            bw.write("</graph>");
            
            bw.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void convertNode(Node n) {
        try {
            bw.write("\t<node id=\""+n.getId()+"\">\n");
            bw.write("\t\t<att name=\"name\" value=\""+n.getName()+"\"> </att>\n");
            bw.write("\t\t<att name=\"uid\" value=\""+n.getName()+"\"> </att>\n");
            bw.write("\t</node>\n");
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override 
    public void convertEdge(Edge e) {
        try {
            int id1 = e.getFirst().getId();
            int id2 = e.getSecond().getId();
            float score = e.getScore();
            
            bw.write("\t<edge source=\""+id1+"\" target=\""+id2+"\""
                    + " weight=\""+score+"\"> </edge>\n");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
