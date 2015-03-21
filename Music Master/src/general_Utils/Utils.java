package general_Utils;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import canonical_types.Interval;


public class Utils {

	//Public static variables are needed for intervals,
	//Chords (make class for chords), scales (another class)
	
	//Make an init methods to populate all variables
	
	private static String input_file_path = "C:\\Users\\Akash Bharadwaj\\Desktop\\MLT CMU\\Prep\\Personal_Repo\\Music Master\\src\\inputs"; 
	private static String chords = "\\Chord Formulae";
	private static String scales = "\\Scale Formulae";
	private static String intervals = "\\Intervals";
	
	public static HashMap<Integer,Integer> interval_to_offset = new HashMap<Integer, Integer>();
	public static HashMap<String, ArrayList<Interval>> chord_type_to_formula = new HashMap<String, ArrayList<Interval>>();
	public static HashMap<String, ArrayList<Interval>> scale_type_to_formula = new HashMap<String, ArrayList<Interval>>();
	
	public static void init_Variables()
	{
		read_Intervals();				
		read_Chords();	
		read_Scales();
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
