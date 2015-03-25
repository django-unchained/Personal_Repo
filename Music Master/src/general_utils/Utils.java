package general_utils;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import canonical_types.Interval;


public class Utils {

	//Public static variables are needed for intervals,
	//Chords (make class for chords), scales (another class)
	
	//Make an init methods to populate all variables
	
	//////////////////// T O   D O !!!////////////////////
	//Normalize names of scales and chords
	
	private static String input_file_path = "C:\\Users\\Akash Bharadwaj\\Desktop\\MLT CMU\\Prep\\Personal_Repo\\Music Master\\src\\inputs"; 
	private static String chords = "\\Chord Formulae";
	private static String scales = "\\Scale Formulae";
	private static String intervals = "\\Intervals";
	
	public static HashMap<Integer,Integer> interval_to_offset = new HashMap<Integer, Integer>();
	public static HashMap<String, ArrayList<Interval>> chord_type_to_formula = new HashMap<String, ArrayList<Interval>>();
	public static HashMap<String, ArrayList<Interval>> scale_type_to_formula = new HashMap<String, ArrayList<Interval>>();
	//Maps an index to possible notes that can map to that index
	public static HashMap<Integer, HashSet<String>> index_to_note = new HashMap<Integer, HashSet<String>>();
	//Maps a note to its index
	public static HashMap<String, Integer> note_to_index = new HashMap<String, Integer>();
	public static HashMap<String, String> preferred_synonyms = new HashMap<String, String>();
	
	///////////////////TO DO!!///////////////////
	//Add another hash set for converting E+ to F
	//as per convention
	
	public static void init_Variables()
	{
		read_Intervals();				
		read_Chords();	
		read_Scales();
		init_index_maps();
	}
	
	
	
	private static HashSet<String> get_hash_set(String...strings)
	{
		HashSet<String> set = new HashSet<String>();
		for(String str : strings)
		{
			set.add(str);
		}
		return(set);
	}
	
	private static void init_index_maps()
	{
		//Initializing index to note map
		index_to_note.put(0, get_hash_set("C","B+"));//C
		index_to_note.put(1, get_hash_set("C+","D-"));//C#
		index_to_note.put(2, get_hash_set("D"));//D
		index_to_note.put(3, get_hash_set("D+","E-"));//Eb
		index_to_note.put(4, get_hash_set("E","F-"));//E
		index_to_note.put(5, get_hash_set("E+","F"));//F
		index_to_note.put(6, get_hash_set("F+","G-"));//F#
		index_to_note.put(7, get_hash_set("G"));//G
		index_to_note.put(8, get_hash_set("G+","A-"));//G#
		index_to_note.put(9, get_hash_set("A"));//A
		index_to_note.put(10, get_hash_set("A+","B-"));//A#
		index_to_note.put(11, get_hash_set("B","C-"));//B	
		index_to_note.put(12, get_hash_set("C","B+"));
		
		//Initializing note to index map
		note_to_index.put("C", 0);
		note_to_index.put("B+", 0);
		note_to_index.put("C+", 1);
		note_to_index.put("D-", 1);
		note_to_index.put("D", 2);
		note_to_index.put("D+", 3);
		note_to_index.put("E-", 3);
		note_to_index.put("E", 4);
		note_to_index.put("F-", 4);
		note_to_index.put("F", 5);
		note_to_index.put("E+", 5);
		note_to_index.put("F+", 6);
		note_to_index.put("G-", 6);
		note_to_index.put("G", 7);
		note_to_index.put("G+", 8);
		note_to_index.put("A-", 8);
		note_to_index.put("A", 9);
		note_to_index.put("A+", 10);
		note_to_index.put("B-", 10);
		note_to_index.put("B", 11);
		note_to_index.put("C-", 11);
		
		//Populate preferred synonyms
		preferred_synonyms.put("C-", "B");
		preferred_synonyms.put("C", "C");
		preferred_synonyms.put("C+", "C+");
		preferred_synonyms.put("D-", "C+");
		preferred_synonyms.put("D", "D");
		preferred_synonyms.put("D+", "E-");
		preferred_synonyms.put("E-", "E-");
		preferred_synonyms.put("E", "E");
		preferred_synonyms.put("E+", "F");
		preferred_synonyms.put("F-", "E");
		preferred_synonyms.put("F", "F");
		preferred_synonyms.put("F+", "F+");
		preferred_synonyms.put("G-", "F+");
		preferred_synonyms.put("G", "G");
		preferred_synonyms.put("G+", "G+");
		preferred_synonyms.put("A-", "G+");
		preferred_synonyms.put("A", "A");
		preferred_synonyms.put("A+", "B-");
		preferred_synonyms.put("B-", "B-");
		preferred_synonyms.put("B", "B");
		preferred_synonyms.put("B+", "C");
		
		
	}
	
	private static void read_Scales()
	{		
		String scale_Type_Name = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(input_file_path + scales));
			String line;
			while((line = br.readLine()) != null)
			{
				//skip comments
				if(line.contains("#"))//any line with # anywhere in it is ignored as a comment
				{
					continue;
				}
				else if("".equals(line.trim()))//case of empty line
				{
					continue;
				}
				else if(!line.contains(","))//Non-empty line before interval specification
				{
					scale_Type_Name = line.trim();
					continue;
				}
				String[] intervals_in_scale = line.split(","); 
				ArrayList<Interval> intvl_arr = new ArrayList<Interval>();
				for (String interval : intervals_in_scale)
				{
					intvl_arr.add(new Interval(interval.trim()));
				}
				scale_type_to_formula.put(scale_Type_Name, intvl_arr);
			}			
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}	
	}
	
	private static void read_Intervals()
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader(input_file_path + intervals));
			String line;
			while((line = br.readLine()) != null)
			{
				//skip comments
				if(line.contains("#"))//any line with # anywhere in it is ignored as a comment
				{
					continue;
				}
				else if(!line.contains("->"))//case of no mapping defined on that line
				{
					continue;
				}
				String[] key_val_tuple = line.split("->");
				interval_to_offset.put(Integer.parseInt(key_val_tuple[0].trim()), Integer.parseInt(key_val_tuple[1].trim()));						
			}			
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}	
	}
	
	private static void read_Chords()
	{				
		String chord_Type_Name = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(input_file_path + chords));
			String line;
			while((line = br.readLine()) != null)
			{
				//skip comments
				if(line.contains("#"))//any line with # anywhere in it is ignored as a comment
				{
					continue;
				}
				else if("".equals(line.trim()))//case of empty line
				{
					continue;
				}
				else if(!line.contains(","))//Non-empty line before interval specification
				{
					chord_Type_Name = line.trim();
					continue;
				}
				String[] intervals_in_chord = line.split(","); 
				ArrayList<Interval> intvl_arr = new ArrayList<Interval>();
				for (String interval : intervals_in_chord)
				{
					intvl_arr.add(new Interval(interval.trim()));
				}
				chord_type_to_formula.put(chord_Type_Name, intvl_arr);
			}			
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}	
	}
	
}
