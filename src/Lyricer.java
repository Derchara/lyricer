package de.larsjunker.Lyricer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Lyricer {

	public static void main(String[] args) {
		String file = args[0];
                StringBuilder sb = new StringBuilder();
                String title = "title";
                String label = "label";
                String out = null;
                boolean readSuccess = false;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			sb.append("<HTML><HEAD>");
			sb.append("\n");
			sb.append("<LINK rel=\"STYLESHEET\" type=\"text/css\" HREF=\"cuesheet2.css\"> </HEAD>");
			sb.append("\n");
			sb.append("<BODY>");
			sb.append("\n");
			while ((line = br.readLine()) != null) {
				if (line.startsWith("T: ")) {
					title = line.replace("T: ", "");
					sb.append("<FONT CLASS=\"title\">"+title+"</FONT>");
					sb.append("&nbsp;&nbsp;<NOBR>");
					sb.append("\n");
				}
				else if (line.startsWith("LA: ")) {
					label = line.replace("LA: ", "");
					sb.append("<FONT CLASS=\"label\">("+label+")</FONT></NOBR>&nbsp;&nbsp;<NOBR>");
					sb.append("\n");
				}
				else if (line.startsWith("A: ")) {
					sb.append("<FONT CLASS=\"artist\">"+line.replace("ARTIST: ", "")+"</FONT></NOBR><br>");
					sb.append("\n");
				}
				else if (line.startsWith("H: ")) {
					sb.append("<P CLASS=\"hdr\">"+line.replace("H: ", "")+"</P><br><br>");
					sb.append("\n");
				}
				else if (line.startsWith("L: ")) {
					sb.append("<FONT CLASS=\"lyrics\"><L>"+line.replace("L: ", "")+"</L></FONT><br>");
					sb.append("\n");
				}
				else {
					sb.append(line+"<br>\n");
				}
			}
			sb.append("</BODY></HTML>");
                        readSuccess = true;
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: Could not find file: " + file);
		} catch (IOException e) {
			System.err.println("ERROR: Could not read file: " + file);
		}
                if (readSuccess) {
                    try {
                       File in = new File(file);
                       out = in.getParent()+"/"+label+" - "+title+".html";
                       BufferedWriter bw = new BufferedWriter(new FileWriter(out));
                       bw.write(sb.toString());
                       bw.close();
                       System.out.println("SUCCESS: " + out);
                    } catch (IOException e) {
                        System.err.println("ERROR: could not write to file: " + out);
                    }
                }
	}

}
