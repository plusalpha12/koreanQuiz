package Client;

import java.io.Serializable;
import java.util.ArrayList;

public class DataList implements Serializable{
	ArrayList list;
	
	public ArrayList getList() {
		return list;
	}
	
	public void setList(ArrayList list) {
		this.list = list;
	}
}