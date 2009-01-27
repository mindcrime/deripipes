package org.deri.execeng.rdf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Vector;

import org.deri.execeng.core.BoxParser;
import org.deri.execeng.core.PipeParser;
import org.deri.execeng.model.Operator;
import org.deri.execeng.model.Stream;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.w3c.dom.Element;
import org.deri.execeng.utils.XMLUtil;
/**
 * @author Danh Le Phuoc, danh.lephuoc@deri.org
 *
 */
public class SameAsBox extends AbstractMerge{ 
	final Logger logger = LoggerFactory.getLogger(SameAsBox.class);
	
	 public SameAsBox(PipeParser parser,Element element){
		 this.parser=parser;
		 initialize(element);
		 
     }
     
     public void execute(){
    	 buffer= new SesameMemoryBuffer(parser);
    	 mergeInputs();
    	 
    	 RepositoryConnection conn = buffer.getConnection();
    	 Repository rep = conn.getRepository();
    	 
    	 Smoosher smusher = new Smoosher();
    	 try {
			smusher.smoosh(rep);
		} catch (RepositoryException e) {
			parser.log(e);
		}
    	 
    	 isExecuted=true;
     }     
}