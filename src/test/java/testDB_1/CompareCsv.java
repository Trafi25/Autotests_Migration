package testDB_1;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/* file1 - file2 = file3*/
public class CompareCsv {
	public CompareCsv() {
	}

	public void Comapre(String file1, String file2, String file3) throws FileNotFoundException, IOException {
		ArrayList<String> al1 = new ArrayList();
		ArrayList<String> al2 = new ArrayList();
		new ArrayList();
		BufferedReader CSVFile1 = new BufferedReader(new FileReader(file1));
		String dataRow1 = CSVFile1.readLine();

		String Titles;
		for(Titles = dataRow1; dataRow1 != null; dataRow1 = CSVFile1.readLine()) {
			al1.add(dataRow1);
		}

		CSVFile1.close();
		BufferedReader CSVFile2 = new BufferedReader(new FileReader(file2));

		for(String dataRow2 = CSVFile2.readLine(); dataRow2 != null; dataRow2 = CSVFile2.readLine()) {
			al2.add(dataRow2);
		}

		CSVFile2.close();
		Iterator var12 = al2.iterator();

		while(var12.hasNext()) {
			String bs = (String)var12.next();
			al1.remove(bs);
		}

		int size = al1.size();
		System.out.println(size);

		try {
			FileWriter writer = new FileWriter(file3);
			writer.append(Titles);
			writer.append("\n");

			while(size != 0) {
				--size;
				writer.append((CharSequence)al1.get(size));
				writer.append('\n');
			}

			writer.flush();
			writer.close();
		} catch (IOException var14) {
			var14.printStackTrace();
		}

	}
}