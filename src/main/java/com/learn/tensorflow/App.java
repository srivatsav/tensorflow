package com.learn.tensorflow;

import java.io.UnsupportedEncodingException;

import org.tensorflow.DataType;
import org.tensorflow.Graph;
import org.tensorflow.Output;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.TensorFlow;

/**
 * Hello world!
 *
 */
public class App 
{
	
    public static void main( String[] args ) throws UnsupportedEncodingException
    {
    	try(Graph graph = new Graph();
    		Session session = new Session(graph)){
    		    		
			Output x = graph.opBuilder("Placeholder", "x").setAttr("dtype", DataType.FLOAT).build().output(0);    		
			Output y = graph.opBuilder("Placeholder", "y").setAttr("dtype", DataType.FLOAT).build().output(0);    		
			Output z = graph.opBuilder("Div", "z").addInput(x).addInput(y).build().output(0);
			
    		
    		float[] X = new float[]{1};
    		float[] Y = new float[]{2};
    		
    		for(int i=0;i<X.length;i++)
    		{
    			try(Tensor tx = Tensor.create(X[i]);
    				Tensor ty = Tensor.create(Y[i]);
    				Tensor tz = session.runner().feed("x", tx).feed("y", ty).fetch("z").run().get(0)){
    				
    				System.out.println(tz.floatValue());
    			}catch(Exception e)
    			{
    				System.out.println(e);
    			}
    		}
    	}
    	catch(Exception e)
    	{
    		
    	}
    }
}
