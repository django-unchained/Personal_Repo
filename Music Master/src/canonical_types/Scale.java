package canonical_types;

import general_utils.Utils;
import canonical_types.Interval;
import java.util.ArrayList;
import java.util.HashSet;

public class Scale {
	
	
	///////////////// TO DO!!///////////////
	//Same as the to dos in chord.java.
	
	
	private String root;
	private String type;
	private ArrayList<Interval> notes;
	private HashSet<Integer> note_ids_set;
	
	public Scale(String root, String type)
	{
		this.root = root;
		this.type = type;
		this.notes = Utils.scale_type_to_formula.get(type);
		for(Interval i : notes)
		{
			i.setInterval_note(i.get_interval_note_given_root(root));
			note_ids_set.add(Utils.note_to_index.get(i.getInterval_note()));
		}
	}
	
	public String getRoot() {
		return root;
	}

	public String getType() {
		return type;
	}

	public ArrayList<Interval> getNotes() {
		return notes;
	}

	public HashSet<Integer> getNote_ids_set() {
		return note_ids_set;
	}

	public boolean is_note_in_scale(String note)
	{
		if(note_ids_set.contains(Utils.note_to_index.get(note)))
		{
			return(true);
		}
		return(false);
	}
	
	public String get_notes_in_scale_as_string()
	{
		String ret_str="";
		for(Interval i : notes)
		{
			ret_str += i.getInterval_note() + ",";
		}
		return(ret_str);
	}
	
}
